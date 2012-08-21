package leider.ken.nfl.fantasy.analysis

import leider.ken.nfl.fantasy.*
import leider.ken.nfl.stats.Mean
import leider.ken.nfl.Season
import leider.ken.nfl.stats.Occurance
import leider.ken.nfl.stats.Ranking
import leider.ken.nfl.stats.PlayerSeasonStats

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
            if (season.year < 2009) continue
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
}