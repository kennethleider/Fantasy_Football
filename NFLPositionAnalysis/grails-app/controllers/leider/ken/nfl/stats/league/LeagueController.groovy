package leider.ken.nfl.stats.league

import leider.ken.nfl.CommandHistory
class LeagueController {
    static scaffold = true
    def scoreService
    def analysisService
    
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
        
    }
}