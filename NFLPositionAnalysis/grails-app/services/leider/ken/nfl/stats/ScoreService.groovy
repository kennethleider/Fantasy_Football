package leider.ken.nfl.stats

import leider.ken.nfl.stats.league.*
class ScoreService {
    def sessionFactory
    
    def calculateWeeklyScores(League league) {
        int offset = 0
        int max = 100
        
        process("Weekly Stats", PlayerWeekStats.count(), 
            {
                def list = PlayerWeekStats.list([max : max, offset : offset])
                offset += list.size()

                return list
            },
            { PlayerStats stats ->
                Score score = Score.findOrCreateWhere(week : stats.week, player : stats.player, league : league)
                
                score.points = stats.passing.yards * league.scoring.passing.perYard
                score.points += stats.passing.completions * league.scoring.passing.perCompletion
                score.points += stats.passing.TDs * league.scoring.passing.perTD
                score.points += stats.passing.interceptions * league.scoring.passing.perInt
                score.points += stats.passing.conversions * league.scoring.passing.perConversion
                
                score.points += stats.rushing.yards * league.scoring.rushing.perYard
                score.points += stats.rushing.TDs * league.scoring.rushing.perTD
                score.points += stats.rushing.fumblesLost * league.scoring.rushing.perFumbleLost
                score.points += stats.rushing.conversions * league.scoring.rushing.perConversion
                
                score.points += stats.receiving.yards * league.scoring.receiving.perYard
                score.points += stats.receiving.TDs * league.scoring.receiving.perTD
                score.points += stats.receiving.receptions * league.scoring.receiving.perReception
                score.points += stats.receiving.conversions * league.scoring.receiving.perConversion
                
                score.points += stats.kickoff.yards * league.scoring.returning.perYard
                score.points += stats.kickoff.TDs * league.scoring.returning.perTD
                
                score.points += stats.punt.yards * league.scoring.returning.perYard
                score.points += stats.punt.TDs * league.scoring.returning.perTD

                score.save()
            }
        )
    }
    
    private def process(String name, long total, Closure groupIterator, Closure worker) throws Exception {
        int i = 0
        def retval = []
        def group = groupIterator()
        while(i < total) {
            long start = System.currentTimeMillis()
            for (def value in group) {
                retval.add(worker(value))
            }
            i += group.size()
            sessionFactory.currentSession.flush()
            sessionFactory.currentSession.clear()
            org.codehaus.groovy.grails.plugins.DomainClassGrailsPlugin.PROPERTY_INSTANCE_MAP.get().clear()
            
            println "=== ${name}: ${i} of ${total}: ${(System.currentTimeMillis() - start)/group.size()}"
        }

        return retval
    }
    
}