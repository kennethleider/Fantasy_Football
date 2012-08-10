package leider.ken.nfl.stats.league

import leider.ken.nfl.CommandHistory
import leider.ken.nfl.stats.league.analysis.*
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
    
    def computeScores() {
        League league = League.get(params.id)
        params.overwrite = params.overwrite ?: false
        scoreService.calculateWeeklyScores(league, params.overwrite)
        scoreService.calculateSeasonScores(league)
        CommandHistory history = CommandHistory.findOrCreateWhere(controllerAction : "${params.controller}.${params.action}.${params.id}")
        history.lastPerformed = new Date()
        history.save()
        redirect (action : 'show', id : params.id)
    }
    
    def analyze() {
        League league = League.get(params.id)
        analysisService.analyzePositions(league)
        analysisService.analyzePlayers(league)
        
    }
}