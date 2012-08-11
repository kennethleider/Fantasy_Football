package leider.ken.nfl.stats.league.analysis

import leider.ken.nfl.stats.league.League
import leider.ken.nfl.stats.Season
import leider.ken.nfl.stats.Player
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
