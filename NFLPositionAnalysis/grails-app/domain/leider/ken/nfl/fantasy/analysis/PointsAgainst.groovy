package leider.ken.nfl.fantasy.analysis

import leider.ken.nfl.Franchise
import leider.ken.nfl.Season
import leider.ken.nfl.fantasy.League
import leider.ken.nfl.stats.Mean
class PointsAgainst {

    static constraints = {
         franchise unique : ['season', 'league', 'position']
    } 
        
    static mapping = {
        points cascade: "save-update,all-delete-orphan"
        
        season index: 'points_against_identity_idx'
        league index: 'points_against_identity_idx'
        franchise index: 'points_against_identity_idx'
        position index: 'points_against_identity_idx'
    }
    
    Season season
    League league
    Franchise franchise
    String position
    Mean points
    double factor
}
