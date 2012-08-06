package leider.ken.nfl.yahoo

import leider.ken.nfl.stats.Season
import leider.ken.nfl.stats.Week
import leider.ken.nfl.stats.Player
import leider.ken.nfl.stats.PlayerStats
import leider.ken.nfl.stats.PlayerWeekStats
import leider.ken.nfl.armchairanalysis.ArmchairPlayerRef

class YahooStatScraperService {
    static transactional = false
    def sessionFactory
    private def slurper = new XmlSlurper(new org.ccil.cowan.tagsoup.Parser())
    def grailsApplication
    
    def scrape() {
       def seasons = determineSeasons()
       println seasons
       for (def season in seasons) {
           def weeks = determineWeeks(season)
           scrapePlayers(season)
//           for (def week in weeks) {
//               scrapeQBs(week)
//           }
       }
    }
    
    def determineSeasons() {
        process("Seasons",
            "http://sports.yahoo.com/nfl/stats/byposition",
            { it.parent().@name == 'year' && it.parent().name() == 'select' && it.name() == 'option' && it.text().contains(' Season') },
            {
                int year = (it.text() =~  /\d+/)[0].toInteger()
                return Season.findOrSaveWhere(year : year)
            }    
        )
    }
    
    def determineWeeks(Season season) {
        def retval = []
        for (def number in 1..17) {
            retval.add(Week.findOrSaveWhere(number : number, season : season))
        }
        
        return retval
    }
    
    def scrapePlayers(Season season) {
        for (def position in [ 'QB', 'RB', 'WR', 'TE' ]) {
            process("Players - ${season} - ${position}",
            "http://sports.yahoo.com/nfl/stats/byposition?pos=${position}&year=season_${season.year}",
                { it.@class.text().contains("ysprow")},
                {
                    def td = it.td
                    def id = (td[0].a.@href.text()=~ /\d+/)[0].toLong()
                    def name = td[0].a.text()
                    
                    def ref = YahooPlayerRef.get(id)
                    if (!ref) {
                        ref = new YahooPlayerRef()
                        ref.id = id
                        ref.save()
                    } 
                    def player
                    
                    def code = grailsApplication.config.yahooLookup[id]
                    if (code) {
                        player = ArmchairPlayerRef.findByCode(code)?.player
                        player.name = name
                        player.save()
                    }
                    
                    if (!player) {
                        def matches = Player.findAllByName(name)
                        if (matches.size() == 1) {
                            player = matches[0]
                        } else if (matches.size() > 1) {
                            matches = matches.findAll { it.position == position }
                            
                            if (matches.size() == 1) {
                                player = matches[0]
                            }
                        }
                    }
                    
                    if (player) {
                        ref.player = player
                        ref.save()
                    } else {
                        println "Unable to find a match for yahoo player: ${i} - ${name}}"
                    }
                    
                }    
            ) 
        }
    }

    
    private def process(String name, String url, Closure filter, Closure scraper) throws Exception {
        InputStream input = new URL(url).openStream()
        def htmlParser = slurper.parse(input)
        
        def matches = htmlParser.depthFirst().findAll(filter)
        int i = 1
        int records = 100
        long start = System.currentTimeMillis()
        def retval = []
        for (def match in matches) {
            retval.add(scraper(match))
           
            if (i++ % records == 0 || i == matches.size()) {
                sessionFactory.currentSession.flush()
                sessionFactory.currentSession.clear()
                
                org.codehaus.groovy.grails.plugins.DomainClassGrailsPlugin.PROPERTY_INSTANCE_MAP.get().clear()
                println "=== ${name}: ${i} of ${matches.size()}: ${(System.currentTimeMillis() - start)/records}"
                start = System.currentTimeMillis()
            }
        } 
        
        input.close()
        return retval

    }
    
  
}
