package leider.ken.nfl.stats

import leider.ken.nfl.CommandHistory

class StatsController {
    def yahooStatScraperService
    def armchairAnalysisImportService
    //def yahooPlayerScraperService
    
    def index() { 
        return [
            playerCount : Player.count(),
            weeklyStatCount : PlayerWeekStats.count(),
            seasonStatCount : PlayerSeasonStats.count(),
            seasons : Season.list(),
            lastWeek : Week.list().last(),
            commandHistory : CommandHistory.list().collectEntries { [ it.controllerAction , it.lastPerformed ]}
        ]
    }
    
    def yahoo() {
        //yahooStatScraperService.load()
        CommandHistory history = CommandHistory.findOrCreateWhere(controllerAction : "${params.controller}.${params.action}")
        history.lastPerformed = new Date()
        history.save()
        redirect (action : 'index')
        
    }
    
    def armchair() {
        armchairAnalysisImportService.load()
        CommandHistory history = CommandHistory.findOrCreateWhere(controllerAction : "${params.controller}.${params.action}")
        history.lastPerformed = new Date()
        history.save()
        redirect (action : 'index')
    }
    
    def playerDetails() {
        //yahooStatScraperService.loadPlayerDetails()
    }
    
}
