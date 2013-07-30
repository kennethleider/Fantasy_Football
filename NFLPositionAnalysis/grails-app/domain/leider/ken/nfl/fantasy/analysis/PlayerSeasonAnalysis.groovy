package leider.ken.nfl.fantasy.analysis

import leider.ken.nfl.fantasy.League
import leider.ken.nfl.Season
import leider.ken.nfl.Player
import leider.ken.nfl.stats.Ranking
import leider.ken.nfl.stats.Mean

class PlayerSeasonAnalysis {

    static constraints = {
        player unique : ['season', 'league']
        season()
    }
    
    static mapping = {
        rank cascade: "save-update,all-delete-orphan"
        adjustedRank cascade: "save-update,all-delete-orphan"
    }
   
    League league
    Player player
    Season season
    Mean rank
    Mean adjustedRank
    int games

}
