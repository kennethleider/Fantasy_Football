package leider.ken.nfl.fantasy

import leider.ken.nfl.CommandHistory
import leider.ken.nfl.fantasy.analysis.*
import leider.ken.nfl.*
import leider.ken.nfl.stats.*

class LeagueController {
    static scaffold = true
    def scoreService
    def analysisService
    
    def position(Long id) {
        def leagueInstance = League.get(id)
        if (!leagueInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'score.label', default: 'League'), id])
            redirect(action: "list")
            return
        }

        def analysisLookup = PositionSeasonAnalysis.findAllWhere(league : leagueInstance)
        analysisLookup = analysisLookup.groupBy { it.position }
        for (def positionList in analysisLookup) {
            analysisLookup[positionList.key] = positionList.value.groupBy { it.season.year }
        }
        
        [
            leagueInstance: leagueInstance,
            seasonInstanceList : Season.list(),
            positionAnalysisLookup : analysisLookup
        ]
    }
    
    def draft(Long id) {
        def leagueInstance = League.get(id)
        if (!leagueInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'score.label', default: 'League'), id])
            redirect(action: "list")
            return
        }

        def analysisLookup = DraftAnalysis.findAllByLeague(leagueInstance)

        [
            leagueInstance: leagueInstance,
            draftAnalysisInstanceList : analysisLookup
        ]
    }
    
    def player(Long id) {
        def leagueInstance = League.get(id)
        if (!leagueInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'score.label', default: 'League'), id])
            redirect(action: "list")
            return
        }

        def analysisLookup = PlayerSeasonAnalysis.findAllWhere(league : leagueInstance)
        
        [
            leagueInstance: leagueInstance,
            playerAnalysisInstanceList : analysisLookup
        ]
    }
    
    def depth(Long id) {
        def leagueInstance = League.get(id)
        if (!leagueInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'score.label', default: 'League'), id])
            redirect(action: "list")
            return
        }
        
        def opps = PlayerWeekStats.executeQuery("\
                   select stats.passing.completions, stats.rushing.attempts,\
                          stats.receiving.receptions, stats.kickoff.attempts,\
                          stats.punt.attempts\
                   from PlayerWeekStats as stats\
                   where stats.rushing.attempts + stats.receiving.targets\
                   + stats.passing.attempts + stats.kickoff.attempts\
                   + stats.punt.attempts >= 5\
                    and stats.week.number <= :maxWeek\
                    and stats.week.season.year = 2011 \
            ", [maxWeek : grailsApplication.config.regularSeasonWeeks])
        
        def labels = [ 'Passing', 'Rushing', 'Receiving', 'Kickoff', 'Punt']
        def attempts = []
        for (int i in 0..4) {
            attempts[i]  = Mean.compute(opps.collect { it[i] }.findAll{ it > 0 })
            println "${labels[i]} attempts - ${attempts[i].properties.sort()}"
        }
        
        for (int i in 0..4) {
            println "${labels[i]} factor - ${10 * attempts[1].average / attempts[i].average}"
        }
        
        def scores = Score.executeQuery("\
                    select score.points, score.player.position\
                    from Score as score\
                    where score.league = :league\
                    and score.week.number <= :maxWeek\
                    and score.week.season.year = 2011 \
                    and score.points != 0\
                    ", [league : leagueInstance, 
                maxWeek : grailsApplication.config.regularSeasonWeeks]
        )

        Integer max = scores.collect { it[0] }.max()
        Integer min = scores.collect { it[0] }.min()
        scores = scores.groupBy { it[1] }
        for (String position in [ 'QB', 'RB', 'WR', 'TE' ]) {
            def mean = Mean.compute(scores.get(position).collect { it[0] })
            println "${position} - ${mean.properties.sort()}"
        }
        
  

        println "Points,QB,RB,WR,TE"
        int granularity = 5
        for (int i in (min - 10)..(max + 10)) {
            if (i % granularity != 0) continue
            def row = []
            for (String position in [ 'QB', 'RB', 'WR', 'TE' ]) {
                //println scores.get(i)?.get('QB')
                
                def counts = scores.get(position).count { granularity*Math.round(it[0]/granularity) == i}
                row.add(counts/ scores.get(position).size())
            }
            
            println "${i},${row[0]},${row[1]},${row[2]},${row[3]}"
        }
    }
    
    def computeScores() {
        League league = League.get(params.id)
        params.overwrite = params.overwrite == "true"
        scoreService.calculateWeeklyScores(league, true)
        scoreService.calculateSeasonScores(league)
        CommandHistory history = CommandHistory.findOrCreateWhere(controllerAction : "${params.controller}.${params.action}.${params.id}")
        history.lastPerformed = new Date()
        history.save()
        redirect (action : 'show', id : params.id)
    }
    
    def analyze() {
        League league = League.get(params.id)
        //analysisService.computePointsAgainst(league)
        //analysisService.computeDraftAnalysis(league)
        //analysisService.analyzePositions(league)
        analysisService.analyzePlayers(league)
        //analysisService.analyzePercentileCounts(league)
    }
}