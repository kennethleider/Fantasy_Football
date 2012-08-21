package leider.ken.nfl.fantasy.analysis

import leider.ken.nfl.fantasy.League
import leider.ken.nfl.Season
import leider.ken.nfl.Player
import leider.ken.nfl.stats.Ranking

class PlayerSeasonAnalysis {
    static hasMany = [ rankings : Ranking]
    static constraints = {
        player unique : ['season', 'league']
        season()
    }
    
    static mapping = {
        ranking cascade: "save-update,all-delete-orphan"
    }
   
    League league
    Player player
    Season season

}
