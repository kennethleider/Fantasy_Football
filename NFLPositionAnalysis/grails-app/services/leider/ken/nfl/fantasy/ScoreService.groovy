package leider.ken.nfl.fantasy

import leider.ken.nfl.fantasy.*
import leider.ken.nfl.stats.*

class ScoreService {
    static transactional = false
    def sessionFactory
    
    def calculateWeeklyScores(League league, boolean overwrite) {        
        def calculate = { PlayerWeekStats stats ->
            Score score = Score.findByWeekAndPlayerAndLeague(stats.week, stats.player, league)
            if (!score) {
                score = new Score(week : stats.week, player : stats.player, league : league)
            }
            
            //if (stats.player.position.equals("QB")) {
                score.points = 0
                score.points += asInt(stats.passing?.yards) * league.scoring.passing.perYard
                score.points += asInt(stats.passing?.attempts) * league.scoring.passing.perAttempt
                score.points += asInt(stats.passing?.completions) * league.scoring.passing.perCompletion
                score.points += asInt(stats.passing?.TDs) * league.scoring.passing.perTD
                score.points += asInt(stats.passing?.interceptions) * league.scoring.passing.perInt
                score.points += asInt(stats.passing?.conversions) * league.scoring.passing.perConversion
                
                score.points += asInt(stats.rushing?.attempts) * league.scoring.rushing.perAttempt
                score.points += asInt(stats.rushing?.yards) * league.scoring.rushing.perYard
                score.points += asInt(stats.rushing?.TDs) * league.scoring.rushing.perTD
                score.points += asInt(stats.rushing?.fumblesLost) * league.scoring.rushing.perFumbleLost
                score.points += asInt(stats.rushing?.conversions) * league.scoring.rushing.perConversion
                
                score.points += asInt(stats.receiving?.yards) * league.scoring.receiving.perYard
                score.points += asInt(stats.receiving?.TDs) * league.scoring.receiving.perTD
                score.points += asInt(stats.receiving?.receptions) * league.scoring.receiving.perReception
                score.points += asInt(stats.receiving?.targets) * league.scoring.receiving.perTarget
                score.points += asInt(stats.receiving?.conversions) * league.scoring.receiving.perConversion
                
                score.points += asInt(stats.kickoff?.yards) * league.scoring.kickoffReturning.perYard
                score.points += asInt(stats.kickoff?.TDs) * league.scoring.kickoffReturning.perTD
                score.points += asInt(stats.kickoff?.attempts) * league.scoring.kickoffReturning.perAttempt
                
                score.points += asInt(stats.punt?.yards) * league.scoring.puntReturning.perYard
                score.points += asInt(stats.punt?.TDs) * league.scoring.puntReturning.perTD
                score.points += asInt(stats.punt?.attempts) * league.scoring.puntReturning.perAttempt
                
                if (score.points != 0) {
                    score.points += league.scoring.baseline
                }
                
                score.save()
            //}
            
            
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
    
    
    private int asInt(int value) {
        return value == PlayerStats.NULL ? 0 : value
    }
}