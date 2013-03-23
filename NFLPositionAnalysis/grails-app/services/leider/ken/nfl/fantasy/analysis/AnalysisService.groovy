package leider.ken.nfl.fantasy.analysis

import leider.ken.nfl.fantasy.*
import leider.ken.nfl.stats.Mean
import leider.ken.nfl.Season
import leider.ken.nfl.stats.Occurance
import leider.ken.nfl.stats.Ranking
import leider.ken.nfl.stats.PlayerSeasonStats
import leider.ken.nfl.stats.PlayerWeekStats

class AnalysisService {
    static transactional = false
    def sessionFactory
    def grailsApplication
    def importService
    
    def analyzePositions(League league) {
        def depths = computeDepths(league.roster, league.teams)
        for (def depth in depths) {
            analyzePosition(league, depth.key, Math.ceil(depth.value).toInteger())
        }
    }
    
    private Map<String, Double> computeDepths(def roster, int teams) {
        Map<String, Double> retval = [:]
        
        for (def rosterPosition in roster) {
            double depth = rosterPosition.slots * teams / rosterPosition.positions.size()
            for (def position in rosterPosition.positions) {
                if (retval.containsKey(position)) {
                    retval[position] += depth
                } else {
                    retval[position] = depth
                }
            }
        } 
        return retval  
    }
    
    private def analyzePosition(League league, String position, int depth) {
        int p0 = 0
        int p25 = depth / 4 - 1
        int p50 = depth / 2 - 1
        int p100 = depth - 1
        
        for (def season in Season.list()) {
            def lists = [ (p0) : [], (p25) : [], (p50) : [], (p100) : [], playables : []]
            
            def playerAnalysisCache = [ : ]
            for (def week in season.weeks) {
                if (week.number > grailsApplication.config.regularSeasonWeeks) {
                    continue
                }
                println "Analyzing ${position} during ${week}"
                def scores = Score.executeQuery("\
                    select score.player, score.points\
                    from Score as score\
                    where score.league = :league\
                    and score.week = :week\
                    and score.player.position = :position\
                    order by score.points desc\
                    ", [league : league, week : week, position : position])
                
                lists[p0].add(scores[0][1])
                lists[p25].add(scores[p25][1])
                lists[p50].add(scores[p50][1])
                lists[p100].add(scores[p100][1])
                lists['playables'].addAll(scores.take(depth).collect { it[1]})
                
                for (int index in 0..scores.size() - 1) {
                    def player = scores[index][0]
                    PlayerSeasonAnalysis analysis = playerAnalysisCache.get(player.id)
                    
                    if (analysis == null) {
                        analysis = PlayerSeasonAnalysis.findOrCreateWhere(league : league, season : season, player : player)
                        analysis.rankings.each { it.times = 0 }
                        playerAnalysisCache.put(player.id, analysis)
                    }
                    
                    Ranking ranking = analysis.rankings.find { it.rank == index + 1}
                    if (ranking) {
                        ranking.times++
                    } else {
                        ranking = new Ranking(times : 1, percentage : 0)
                        ranking.rank = index + 1
                        if (analysis.rankings == null) {
                            analysis.rankings = []
                        }
                        analysis.rankings.add(ranking)
                    }
                }
            }
                
            PositionSeasonAnalysis analysis = PositionSeasonAnalysis.findOrCreateWhere(season : season, league : league, position : position)
            analysis.zerothPercentile = Mean.compute(lists[p0])
            analysis.twentyFifthPercentile = Mean.compute(lists[p25])
            analysis.fiftiethPercentile = Mean.compute(lists[p50])
            analysis.hundrethPercentile = Mean.compute(lists[p100])
            analysis.playables = Mean.compute(lists['playables'])
            analysis.save()
            
            for (def playerAnalysis in playerAnalysisCache.values()) {
                playerAnalysis.rankings = playerAnalysis.rankings.findAll { it != null}
                playerAnalysis.rankings = playerAnalysis.rankings.findAll { it.times > 0}
                def rankings = playerAnalysis.rankings.sort { it.rank }
                
                int sum = 0
                for (Ranking ranking in rankings) {
                    sum += ranking.times
                    ranking.times = sum
                }
                
                for (Ranking ranking in rankings) {
                    ranking.percentage = 100 * ranking.times / sum
                    ranking.save()
                }
                playerAnalysis.rankings = rankings
                playerAnalysis.save()
            }
        }

        sessionFactory.currentSession.flush()
        sessionFactory.currentSession.clear()
       
    }
    
    def analyzePlayers(League league) {
        def positions = computeDepths(league.roster, league.teams).keySet()
        for (def position in positions) {
            for (def season in Season.list()) {
                PositionSeasonAnalysis positionAnalysis = 
                PositionSeasonAnalysis.findBySeasonAndLeagueAndPosition(season, league, position)
                
                if (positionAnalysis != null) {
                    analyzePlayers(positionAnalysis)
                }
            }
        }
    }
        
    def analyzePlayers(PositionSeasonAnalysis positionAnalysis) {
        def league = positionAnalysis.league
        def season = positionAnalysis.season
       
        def scores = Score.executeQuery("\
                    select score.player, score.points\
                    from Score as score\
                    where score.league = :league\
                    and score.week.season = :season\
                    and score.player.position = :position\
                    and score.week.number <= :maxWeek\
                    order by score.player\
                    ", [league : league, 
                        season : season, 
                        position : positionAnalysis.position,
                        maxWeek : grailsApplication.config.regularSeasonWeeks]
                )
        
        
        for (def playerScores in scores.groupBy { it[0] }) {
            def player = playerScores.key
            def points = playerScores.value.collect { it[1] }
            
            PlayerSeasonAnalysis analysis = 
            PlayerSeasonAnalysis.findOrCreateWhere(season : season, league : league, player : player)
            analysis.twentyFifthPercentile = Occurance.compute(points, { it > positionAnalysis.twentyFifthPercentile.average })
            analysis.fiftiethPercentile = Occurance.compute(points, { it > positionAnalysis.fiftiethPercentile.average })
            analysis.hundrethPercentile = Occurance.compute(points, { it > positionAnalysis.hundrethPercentile.average })
            analysis.playables = Occurance.compute(points, { it > positionAnalysis.playables.average })
            analysis.save()
        }
    }
    
    def computePointsAgainst(League league) {
        def means = [:]
        
        importService.process("Points Against for ${league}", 
            {
                def scores = Score.executeQuery("\
                    select score.week, stats.franchise, matchup, score.player.position, sum(score.points) \
                    from Score as score, \
                         PlayerWeekStats as stats,\
                         Matchup as matchup\
                    where score.league = :league\
                    and score.week.number <= :maxWeek\
                    and score.week = stats.week\
                    and score.player = stats.player\
                    and matchup.week = score.week\
                    and (matchup.home = stats.franchise\
                          or matchup.away = stats.franchise)\
                    group by score.week, stats.franchise, matchup, score.player.position\
                    order by score.week, stats.franchise, matchup, score.player.position\
                    ", [league : league, 
                        maxWeek : grailsApplication.config.regularSeasonWeeks]
                )

                means = scores.groupBy { [ it[0].season, it[3]] }
                means.each {
                    it.value = Mean.compute(it.value.collect { it[4]}).average
                }
   
                return scores.groupBy { [ it[0].season, it[1] == it[2].home ? it[2].away : it[2].home, it[3]]}.entrySet()
            },  
            { entry, index ->
                def mean = means.get([entry.key[0], entry.key[2]])
                PointsAgainst fpa = PointsAgainst.findOrCreateWhere(league : league, season : entry.key[0], franchise : entry.key[1], position : entry.key[2])
                fpa.points = Mean.compute(entry.value.collect { it[4] })
                fpa.factor = fpa.points.average / mean
                fpa.save()
            }
        )
    }
    
    def computeDraftAnalysis(League league) {
        importService.process("Draft Analysis ${league}", 
            {
                def seasons = Season.list()
                def players = PlayerSeasonStats.executeQuery("\
                    select distinct(stats.player)\
                    from PlayerSeasonStats as stats\
                    where (stats.season = :lastYear\
                    or stats.season = :yearBeforeLast)\
                    ", [ lastYear : seasons[-2],yearBeforeLast : seasons[-3] ]
                )
                
                return players
            },
            { player, index ->
                def row = PlayerWeekStats.executeQuery("\
                    select stats, score.points, homeFPA, awayFPA\
                    from PlayerWeekStats as stats,\
                         Score as score,\
                         Matchup as matchup,\
                         PointsAgainst as homeFPA,\
                         PointsAgainst as awayFPA\
                    where score.league = :league\
                    and score.player = :player\
                    and score.week.number <= :maxWeek\
                    and stats.player = :player\
                    and stats.week = score.week\
                    and matchup.week = stats.week\
                    and matchup.home = homeFPA.franchise\
                    and matchup.away = awayFPA.franchise\
                    and homeFPA.league = :league\
                    and homeFPA.season = score.week.season\
                    and homeFPA.position = :position\
                    and awayFPA.league = :league\
                    and awayFPA.position = :position\
                    and awayFPA.season = score.week.season\
                    and (matchup.home = stats.franchise or matchup.away = stats.franchise)\
                    order by score.week desc\
                    ", [ 
                        league : league, 
                        player : player,
                        position : player.position,
                        maxWeek : grailsApplication.config.regularSeasonWeeks
                    ]
                )
                
                if (row.size() > 6) {
                    DraftAnalysis analysis = DraftAnalysis.findOrCreateByLeagueAndPlayer(league, player)
                    row = row.collate(16)[0]
                    
                    analysis.games = row.size()
                    analysis.opportunities = Mean.compute(row.collect {
                            return it[0].passing.attempts + it[0].rushing.attempts + it[0].receiving.targets + 
                            it[0].kickoff.attempts + it[0].punt.attempts
                        } )
                    analysis.points = Mean.compute(row.collect { it[1] })
                    analysis.correctedPoints = Mean.compute(row.collect { 
                            def fpa = it[0].franchise == it[2].franchise ? it[3] : it[2]
                            return fpa.factor * it[1]
                        })
                    analysis.save()
                }
            }
        )
    }
}
