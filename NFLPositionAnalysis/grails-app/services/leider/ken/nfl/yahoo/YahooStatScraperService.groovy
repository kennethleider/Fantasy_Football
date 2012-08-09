package leider.ken.nfl.yahoo

import leider.ken.nfl.stats.Season
import leider.ken.nfl.stats.Week
import leider.ken.nfl.stats.Player
import leider.ken.nfl.stats.PlayerStats
import leider.ken.nfl.stats.PlayerWeekStats
import leider.ken.nfl.armchairanalysis.ArmchairPlayerRef
import leider.ken.nfl.stats.PlayerSeasonStats

class YahooStatScraperService {
    static transactional = false
    def sessionFactory
    private def slurper = new XmlSlurper(new org.ccil.cowan.tagsoup.Parser())
    def grailsApplication
    
    def load() {
       def seasons = determineSeasons()
       for (def season in seasons) {
           def weeks = determineWeeks(season)
           scrapePlayers(season)
           for (def week in weeks) {
               scrapeQBs(week)
               scrapeRBs(week)
               scrapeWRs(week)
               scrapeTEs(week)
           }
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
            "http://sports.yahoo.com/nfl/stats/byposition?pos=${position}&year=season_${season.year}&qualified=0",
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
                    if (code != null) {
                        player = ArmchairPlayerRef.findByCode(code)?.player
                        player.name = name
                    }
                    
                    if (player == null) {
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
                    
                    if (player != null) {
                        player.position = position
                        player.save()
                        
                        ref.player = player
                        ref.save()
                        
                        PlayerSeasonStats stats = PlayerSeasonStats.findOrCreateWhere(player : player, season : season)
                        stats.games = toInt(td[2].text())
                        stats.save()
                    } else {
                        println "Unable to find a match for yahoo player: ${i} - ${name}}"
                    }
                    
                }    
            ) 
        }
    }

     def scrapeQBs(Week week) {
         process("QBs - ${week}",
            "http://sports.yahoo.com/nfl/stats/byposition?pos=QB&year=season_${week.season.year}&timeframe=Week${week.number}&qualified=0",
            { it.@class.text().contains("ysprow")},
            {
                def td = it.td
                def id = (td[0].a.@href.text()=~ /\d+/)[0].toLong()
   
                Player player = YahooPlayerRef.get(id)?.player
                PlayerWeekStats stats = PlayerWeekStats.findOrCreateWhere(player: player, week :  week)
                stats.passing.qbRating = td[4].span.text().toDouble()
                stats.passing.completions = toInt(td[5].text())
                stats.passing.attempts = toInt(td[6].text())
                stats.passing.yards = toInt(td[7].text())
                stats.passing.interceptions = toInt(td[10].text())
                stats.passing.TDs = toInt(td[11].text())
                                              
                stats.rushing.attempts = toInt(td[13].text())
                stats.rushing.yards = toInt(td[14].text())
                stats.rushing.TDs = toInt(td[17].text())
                
                stats.passing.sacks = toInt(td[19].text())
                stats.passing.sackYards = toInt(td[20].text())
                
                stats.rushing.fumbles = toInt(td[22].text())
                stats.rushing.fumblesLost = toInt(td[23].text())
            }
        ) 

    }
        
    def scrapeRBs(Week week) {
        process("RBs - ${week}",
            "http://sports.yahoo.com/nfl/stats/byposition?pos=RB&year=season_${week.season.year}&timeframe=Week${week.number}&qualified=0",
            { it.@class.text().contains("ysprow")},
            {
                def td = it.td
                def id = (td[0].a.@href.text()=~ /\d+/)[0].toLong()
                
                Player player = YahooPlayerRef.get(id)?.player
                PlayerWeekStats stats = PlayerWeekStats.findOrCreateWhere(player: player, week :  week)                       
                stats.rushing.attempts = toInt(td[4].text())
                stats.rushing.yards = toInt(td[5].text())
                stats.rushing.TDs = toInt(td[8].text())
                
                stats.receiving.receptions = toInt(td[10].text())
                stats.receiving.yards = toInt(td[11].text())
                stats.receiving.TDs = toInt(td[14].text())
                
                stats.rushing.fumbles = toInt(td[16].text())
                stats.rushing.fumblesLost = toInt(td[17].text())
            }
        ) 
    }
    
    def scrapeWRs(Week week) {
        process("WRs - ${week}",
            "http://sports.yahoo.com/nfl/stats/byposition?pos=WR&year=season_${week.season.year}&timeframe=Week${week.number}&qualified=0",
            { it.@class.text().contains("ysprow")},
            {
                def td = it.td
                def id = (td[0].a.@href.text()=~ /\d+/)[0].toLong()
                
                Player player = YahooPlayerRef.get(id)?.player
                PlayerWeekStats stats = PlayerWeekStats.findOrCreateWhere(player: player, week :  week)                       
                stats.receiving.receptions = toInt(td[4].text())
                stats.receiving.yards = toInt(td[5].text())
                stats.receiving.TDs = toInt(td[8].text())
                
                stats.kickoff.attempts = toInt(td[10].text())
                stats.kickoff.yards = toInt(td[11].text())
                stats.kickoff.TDs = toInt(td[14].text())
                
                stats.punt.attempts = toInt(td[16].text())
                stats.punt.yards = toInt(td[17].text())
                stats.punt.TDs = toInt(td[20].text())
              
                stats.rushing.fumbles = toInt(td[22].text())
                stats.rushing.fumblesLost = toInt(td[23].text())
            }
        ) 
    }
    
    def scrapeTEs(Week week) {
        process("TEs - ${week}",
            "http://sports.yahoo.com/nfl/stats/byposition?pos=TE&year=season_${week.season.year}&timeframe=Week${week.number}&qualified=0",
            { it.@class.text().contains("ysprow")},
            {
                def td = it.td
                def id = (td[0].a.@href.text()=~ /\d+/)[0].toLong()
                
                Player player = YahooPlayerRef.get(id)?.player
                PlayerWeekStats stats = PlayerWeekStats.findOrCreateWhere(player: player, week :  week)                       
                stats.receiving.receptions = toInt(td[4].text())
                stats.receiving.yards = toInt(td[5].text())
                stats.receiving.TDs = toInt(td[8].text())
                
                stats.rushing.attempts = toInt(td[10].text())
                stats.rushing.yards = toInt(td[11].text())
                stats.rushing.TDs = toInt(td[14].text())
                
                stats.rushing.fumbles = toInt(td[16].text())
                stats.rushing.fumblesLost = toInt(td[17].text())
            }
        ) 
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
    
    
    private int toInt(String field) {
        field = field.replaceAll("[^-0-9]","")
        if (field) {
            return field.toInteger()
        }
        
        return 0
    }
}
