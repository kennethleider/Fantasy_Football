package leider.ken.nfl.stats.league.analysis

import leider.ken.nfl.stats.league.League
import leider.ken.nfl.stats.Season
import leider.ken.nfl.stats.Player
import leider.ken.nfl.stats.Occurance
class PlayerSeasonAnalysis {
    
    static constraints = {
        player()
        season()
        twentyFifthPercentile()
        fiftiethPercentile()
        hundrethPercentile()
        playables()
    }
    
    static mapping = {
        twentyFifthPercentile cascade: "save-update,delete"
        fiftiethPercentile cascade: "save-update,delete"
        hundrethPercentile cascade: "save-update,delete"
        playables cascade: "save-update,delete"
    }
   
    League league
    Player player
    Season season
    
    Occurance twentyFifthPercentile
    Occurance fiftiethPercentile
    Occurance hundrethPercentile
    Occurance playables
}
