package leider.ken.nfl.nflDotCom

import leider.ken.nfl.Franchise
import leider.ken.nfl.Player
import leider.ken.nfl.PlayerReconciliationService
import leider.ken.nfl.Season
import leider.ken.nfl.Week
import leider.ken.nfl.stats.PlayerSeasonStats
import leider.ken.nfl.stats.PlayerStats
import leider.ken.nfl.stats.PlayerWeekStats
import leider.ken.nfl.yahoo.YahooPlayerRef

import java.util.regex.Matcher

class NFLStatScraperService {
    static transactional = false
    def sessionFactory
    private def slurper = new XmlSlurper(new org.ccil.cowan.tagsoup.Parser())
    def grailsApplication
    PlayerReconciliationService playerReconciliationService

    def load() {
        def seasons = determineSeasons()
        for (Season season in seasons) {
            //scrapePlayers(season)
            //scrapePassing(season)
            //scrapeRushing(season)
            scrapeReceiving(season)
        }
    }

    def determineSeasons() {
        process("Seasons",
                "http://www.nfl.com/stats/categorystats?archive=true&conference=null",
                { it.parent().@name == 'season' && it.parent().name() == 'select' && it.name() == 'option' && it.@value ==~ /\d+/ },
                {
                    int year = it.@value.text().trim().toInteger()
                    return Season.findOrSaveWhere(year : year)
                }
        )
    }

    def scrapePlayers(Season season) {
        for (def position in [ 'QUARTERBACK' : 'QB', 'RUNNING_BACK' : 'RB', 'WIDE_RECEIVER' : 'WR', 'TIGHT_END' : 'TE']) {
            process("Players - ${season} - ${position.value}",
                    "http://www.nfl.com/stats/categorystats?archive=true&conference=null&statisticPositionCategory=${position.key}&season=${season}&seasonType=REG&experience=0&tabSeq=1&qualified=false&Submit=Go",
                    { it.name().equals("tr") && it.td[0] ==~ /\d+/},
                    {
                        def td = it.td
                        String name = td[1].a.text()
                        String id = (td[1].a.@href.text() =~ /id=(.*)$/)[0][1]
                        String team = td[2].a.text()
                        NFLPlayerRef ref = NFLPlayerRef.findOrCreateBySiteId(id)
                        if (ref.player == null) {
                            ref.player = playerReconciliationService.reconcile("nfl", name, id, position.value, team, season)

                            if (ref.player == null) {
                                println "Creating new player for: ${name}"
                                ref.player = new Player()
                                ref.player.name = name
                            }


                            ref.player.position = position.value
                            ref.player.firstYear = season
                            ref.player.save()
                            ref.save()
                        }
                    }
            )
        }
    }

    def scrapePassing(Season season) {
        int page = 1
        def rows = [ page ]
        while (!rows.isEmpty()) {
            rows = process("Passing - ${season} - ${page}",
                    "http://www.nfl.com/stats/categorystats?statisticCategory=PASSING&season=${season}&seasonType=REG&qualified=false&d-447263-p=${page}",
                    { it.name().equals("tr") && it.td[0] ==~ /\d+/},
                    {
                        def td = it.td
                        String id = (td[1].a.@href.text() =~ /id=(.*)$/)[0][1]
                        NFLPlayerRef ref = NFLPlayerRef.findBySiteId(id)
                        if (ref == null) return

                        PlayerSeasonStats stats = PlayerSeasonStats.findOrCreateWhere(player : ref.player, season : season)
                        stats.passing.completions = toInt(td[4].text())
                        stats.passing.attempts = toInt(td[5].text())
                        stats.passing.yards = toInt(td[8].text())
                        stats.passing.TDs =  toInt(td[11].text())
                        stats.passing.interceptions =  toInt(td[12].text())
                        stats.passing.sacks =  toInt(td[18].text())
                        stats.passing.qbRating = td[19].text().trim().toDouble()
                        stats.save()
                        return stats

                    }
            )
            rows = rows.findAll { it != null }
            page++
        }
    }

    def scrapeRushing(Season season) {
        int page = 1
        def rows = [ page ]
        while (!rows.isEmpty()) {
            rows = process("Rushing - ${season} - ${page}",
                    "http://www.nfl.com/stats/categorystats?statisticCategory=RUSHING&season=${season}&seasonType=REG&qualified=false&d-447263-p=${page}",
                    { it.name().equals("tr") && it.td[0] ==~ /\d+/},
                    {
                        def td = it.td
                        String id = (td[1].a.@href.text() =~ /id=(.*)$/)[0][1]
                        NFLPlayerRef ref = NFLPlayerRef.findBySiteId(id)
                        if (ref == null) return

                        PlayerSeasonStats stats = PlayerSeasonStats.findOrCreateWhere(player : ref.player, season : season)
                        stats.rushing.attempts = toInt(td[4].text())
                        stats.rushing.yards = toInt(td[6].text())
                        stats.rushing.TDs =  toInt(td[9].text())
                        stats.rushing.fumbles =  toInt(td[15].text())
                        stats.save()
                        return stats

                    }
            )
            rows = rows.findAll { it != null }
            page++
        }
    }

    def scrapeReceiving(Season season) {
        int page = 1
        def rows = [ page ]
        while (!rows.isEmpty()) {
            rows = process("Receiving - ${season} - ${page}",
                    "http://www.nfl.com/stats/categorystats?statisticCategory=RECEIVING&season=${season}&seasonType=REG&qualified=false&d-447263-p=${page}",
                    { it.name().equals("tr") && it.td[0] ==~ /\d+/},
                    {
                        def td = it.td
                        String id = (td[1].a.@href.text() =~ /id=(.*)$/)[0][1]
                        NFLPlayerRef ref = NFLPlayerRef.findBySiteId(id)
                        if (ref == null) return

                        PlayerSeasonStats stats = PlayerSeasonStats.findOrCreateWhere(player : ref.player, season : season)
                        stats.receiving.receptions = toInt(td[4].text())
                        stats.receiving.yards = toInt(td[5].text())
                        stats.receiving.TDs =  toInt(td[9].text())
                        stats.rushing.fumbles =  toInt(td[14].text())
                        stats.save()
                        return stats

                    }
            )
            rows = rows.findAll { it != null }
            page++
        }
    }

    private def process(String name, String url, Closure filter, Closure scraper) throws Exception {
        InputStream input = openUrl(new URL(url))

        if (!input) return
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
                println "=== ${name}: ${i} of ${matches.size()}: ${(System.currentTimeMillis() - start)/records} ${PlayerSeasonStats.count()}"
                start = System.currentTimeMillis()
            }

        }

        input.close()
        return retval
    }

    private InputStream openUrl(URL url) {

        File cacheDir = new File(grailsApplication.config.url.cache.app.dir)
        cacheDir.mkdirs()
        File cacheFile = new File(cacheDir, URLEncoder.encode(url.toString()))

        if (!cacheFile.exists()) {
            for (def attempt in 1..3) {
                try {
                    println "Connecting to ${url}"
                    FileOutputStream out = new FileOutputStream(cacheFile)
                    InputStream input = url.openStream()
                    out << input;
                    out.close()
                    input.close();
                    break
                } catch (IOException e) {
                    println e.message
                }
            }
        }
        return new FileInputStream(cacheFile);
    }

    private Integer toInt(String field) {
        field = field.replaceAll("[^-0-9]","")
        if (field) {
            return field.toInteger()
        }

        return PlayerStats.NULL;
    }
}
