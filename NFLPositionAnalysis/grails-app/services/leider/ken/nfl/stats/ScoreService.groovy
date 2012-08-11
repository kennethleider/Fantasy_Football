package leider.ken.nfl.stats

import leider.ken.nfl.stats.league.*
class ScoreService {
    static transactional = false
    def sessionFactory
    
    def calculateWeeklyScores(League league, boolean overwrite) {        
        def calculate = { PlayerWeekStats stats ->
            Score score = Score.findByWeekAndPlayerAndLeague(stats.week, stats.player, league)
            if (!score) {
                score = new Score(week : stats.week, player : stats.player, league : league)
            }
            
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
            return score
        }
        
        if (!overwrite) {
            def results = Score.executeQuery(
            "select stats\
            from PlayerWeekStats as stats\
            inner join fetch stats.week\
            inner join fetch stats.player\
            where stats not in (\
               select stats\
               from Score as score, PlayerWeekStats as stats\
               where score.player = stats.player\
               and score.week = stats.week\
               and score.league = ?\
            ) \
            ", [league])

            def iter = results.collate(100).iterator()
            process("Weekly scores", results.size(), { iter.next() }, calculate)
        } else {    
            int offset = 0
            int max = 100
            process("Weekly scores", PlayerWeekStats.count(), 
                {
                    def list = PlayerWeekStats.list([max : max, offset : offset])
                    offset += list.size()
                    
                    return list
                },
                calculate
            )
        }
    }

    
    def calculateSeasonScores(League league) {

        def results = Score.executeQuery(
            "select score.week.season, score.player, \
            sum(score.points), avg(score.points), max(score.points), min(score.points),\
            sum(score.points * score.points), count(*)\
            from Score as score\
            where score.league = ?\
            and score.week.number < 18\
            group by score.week.season, score.player\
            order by score.player, score.week.season desc\
            ", [league])

        def iter = results.collate(100).iterator()
        process("Season scores", results.size(), 
            { 
                iter.next() 
            },
            { Object[] row -> 
                
                SeasonScore score = SeasonScore.findOrCreateWhere(season : row[0], player : row[1], league : league)
                score.total  = row[2]
                score.average = row[3]
                score.max = row[4]
                score.min = row[5]
                score.standardDeviation = row[7] > 1 ? Math.sqrt(row[6]/(row[7] - 1)) : 0
                if (row[7] > 1) {
                    double numerator = row[6] - 2 * score.average * score.total + row[7] * score.average * score.average
                    score.standardDeviation = Math.sqrt(numerator / row[7])
                } else {
                    score.standardDeviation = 0
                }

                score.save()
            }
        )               
    }
    
    private def process(String name, long total, Closure groupIterator, Closure worker) throws Exception {
        int i = 0
        def retval = []
        
        while(i < total) {
            def group = groupIterator()
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