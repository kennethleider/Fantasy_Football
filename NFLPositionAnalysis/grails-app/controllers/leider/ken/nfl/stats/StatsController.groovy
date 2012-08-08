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
            commandHistoy : CommandHistory.list().collectEntries { [ it.commandAction , it.lastPerformed ]}
        ]
    }
    
    def yahoo() {
        yahooStatScraperService.load()
        
    }
    
    def armchair() {
        armchairAnalysisImportService.load()
    }
    
    def playerDetails() {
        //yahooStatScraperService.loadPlayerDetails()
    }
    
}
