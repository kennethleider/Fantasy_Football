package leider.ken.nfl.stats.league.analysis

import leider.ken.nfl.stats.league.*
import leider.ken.nfl.stats.Mean
import leider.ken.nfl.stats.Season
class AnalysisService {
    static transactional = false
    def sessionFactory
    def grailsApplication
    
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
        int p25 = depth / 4 -1
        int p50 = depth / 2 -1
        int p100 = depth -1
        
        for (def season in Season.list()) {
            def lists = [ (p25) : [], (p50) : [], (p100) : [], playables : []]
            
            for (def week in season.weeks) {
                if (week.number > grailsApplication.config.regularSeasonWeeks) {
                    continue
                }
                
                def scores = Score.executeQuery("\
                    select score.points\
                    from Score as score\
                    where score.league = :league\
                    and score.week = :week\
                    and score.player.position = :position\
                    order by score.points desc\
                    ", [league : league, week : week, position : position])
                
                lists[p25].add(scores[p25])
                lists[p50].add(scores[p50])
                lists[p100].add(scores[p100])
                lists['playables'].addAll(scores.take(depth))
            }
            
            PositionSeasonAnalysis analysis = PositionSeasonAnalysis.findOrCreateWhere(season : season, league : league, position : position)
            analysis.twentyFifthPercentile = Mean.compute(lists[p25])
            analysis.fiftiethPercentile = Mean.compute(lists[p50])
            analysis.hundrethPercentile = Mean.compute(lists[p100])
            analysis.playables = Mean.compute(lists['playables'])
            analysis.save()
        }
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
                break
            }
            break
        }
    }
    
    def analyzePlayers(PositionSeasonAnalysis positionAnalysis) {
        def scores = Score.executeQuery("\
                    select score.player, score.points\
                    from Score as score\
                    where score.league = :league\
                    and score.week.season = :season\
                    and score.player.position = :position\
                    order by score.player\
                    ", [league : positionAnalysis.league, 
                        season : positionAnalysis.season, 
                        position : positionAnalysis.position]
                )
        
        for (def playerScores in scores.groupBy { it[0] }) {
            def player = playerScores.key
            def points = playerScores.value.collect { it[1] }
            
            println "${player} - ${points}"
        }
    }
}