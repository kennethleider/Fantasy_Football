package leider.ken.nfl

class MatchupScraperService {

    static transactional = false
    def sessionFactory
    private def slurper = new XmlSlurper(new org.ccil.cowan.tagsoup.Parser())
    def grailsApplication
    def importService
    
    def load() {
        int year = new Date().getYear()
        def seasons = determineSeasons()
        for (def season in Season.list()) {
            if (season.year != 2012) continue;
            def weeks = determineWeeks(season)
            for (def week in weeks) {
                scrapeMatchups(week)
            }
        }
    }
    
    def determineSeasons() {
        InputStream input = new URL("http://www.nfl.com/schedules/2012/REG1").openStream()
        def htmlParser = slurper.parse(input)
        def script = htmlParser.depthFirst().find({it.@id == "season-dp-tpl"})
        def scriptParser = slurper.parse(new StringReader(script.text()))
        def matches = scriptParser.depthFirst().findAll({ it.parent().@class == 'schedules-nav-dropdown-item-content-ul'})
        def retval = importService.process("Seasons", matches, 
            { it, index ->
                int year = toInt(it.text())
                return Season.findOrSaveWhere(year : year)
            }   
        )
        input.close()
        return retval
    }
    
    def determineWeeks(Season season) {
        def retval = []
        for (def number in 1..17) {
            retval.add(Week.findOrSaveWhere(number : number, season : season))
        }
        
        return retval
    }
    
    def scrapeMatchups(Week week) {
        String url = "http://www.nfl.com/schedules/${week.season.year}/REG${week.number}"
        
        InputStream input = new URL(url).openStream()
        def htmlParser = slurper.parse(input)
        def matches = htmlParser.depthFirst().findAll({it.@class == "list-matchup-row-team"})
        importService.process("Matchups ${week}", matches, 
            { row, index ->
                def awayTeam = Franchise.findByName(findByClass(row, "team-name away").text())
                def homeTeam = Franchise.findByName(findByClass(row, "team-name home").text())

                if (awayTeam == null) {
                    println awayTeam.toString() + " : " + findByClass(row, "team-name away").text()
                }
                if (homeTeam == null) {
                    println homeTeam.toString() + " : " + findByClass(row, "team-name home").text()
                }
                
                if (awayTeam != null && homeTeam != null) {
                    def matchup = Matchup.findOrCreateWhere(week : week, away : awayTeam, home : homeTeam)
                    matchup.awayScore = toInt(findByClass(row, "team-score away")?.text())
                    matchup.homeScore =  toInt(findByClass(row, "team-score home")?.text())
                    matchup.save()
                }
       
            }   
        )
    }
    
    
    
    private def process(String name, String url, String scriptName, Closure filter, Closure scraper) throws Exception {
        InputStream input = new URL(url).openStream()
        def htmlParser = slurper.parse(input)
        def script = htmlParser.depthFirst().find({it.@id == scriptName})
        def scriptParser = slurper.parse(new StringReader(script.text()))
        def matches = scriptParser.depthFirst().findAll(filter)
        def retval = importService.process(name, matches, scraper)

        input.close()
        return retval
    }
    
    
    private Integer toInt(String field) {
        if (field == null) return null
        field = field.replaceAll("[^-0-9]","")
        if (field) {
            return field.toInteger()
        }
        
        return 0
    }
    
    private def findByClass(def row, String className) {
        return row.depthFirst().find { it.@class.text().contains(className)}
    }
}
