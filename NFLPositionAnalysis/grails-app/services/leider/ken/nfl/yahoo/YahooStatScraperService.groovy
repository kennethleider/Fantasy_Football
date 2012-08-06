package leider.ken.nfl.yahoo

import leider.ken.nfl.stats.Season
import leider.ken.nfl.stats.Week
import leider.ken.nfl.stats.Player
import leider.ken.nfl.stats.PlayerStats
import leider.ken.nfl.stats.PlayerWeekStats

class YahooStatScraperService {
    static transactional = false
    def sessionFactory
    private def slurper = new XmlSlurper(new org.ccil.cowan.tagsoup.Parser())
    
    def scrape() {
       def seasons = determineSeasons()
       for (def season in seasons) {
           def weeks = determineWeeks(season)
//           scrapePlayers(season)
//           for (def week in weeks) {
//               scrapeQBs(week)
//           }
       }
    }
    
    def determineSeasons() {
        process("Seasons",
            "http://sports.yahoo.com/nfl/stats/byposition",
            { it.parent().@name = 'year' && it.parent().name() == 'select' && it.name() == 'option' && it.text().contains(' Season') },
            {
                int year = (it.text() =~  /\d+/)[0].toInteger()
                return Season.findOrSaveWhere(year : year)
            }    
        )
    }
    
    def determineWeeks() {
         
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