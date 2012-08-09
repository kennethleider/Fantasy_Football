package leider.ken.nfl.stats.league.analysis

import leider.ken.nfl.stats.league.*
import leider.ken.nfl.stats.Mean
import leider.ken.nfl.stats.Season
class AnalysisService {
    static transactional = false
    def sessionFactory
    def grailsApplication
    
    def analyzePositions(League league) {
        computeDepths(league.roster, league.teams)
        //analyzePosition(league, "WR", league.teams * league.wrPositions)
    }
    
    private Map<String, Double> computeDepths(List<RosterPosition> roster, int teams) {
        Map<String, Double> retval = [:]
        
        for (def rosterPosition in roster) {
            double depth = rosterPosition.count * teams / rosterPosition.positions.size()
            for (def position in rosterPosition.positions) {
                retval[position] += depth
            }
        }
        println roster
        println retval
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
                lists[playables].addAll(scores.take(depth))
            }
            
            PositionSeasonAnalysis analysis = PositionSeasonAnalysis.findOrCreateWhere(season : season, league : league, position : position)
            analysis.twentyFifthPercentile = Mean.compute(lists[p25])
            analysis.fiftiethPercentile = Mean.compute(lists[p50])
            analysis.hundrethPercentile = Mean.compute(lists[p100])
            analysis.playables = Mean.compute(lists[playables])
            analysis.save()
        }

    }
}