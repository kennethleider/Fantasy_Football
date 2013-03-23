package leider.ken.nfl.fantasy.analysis

import leider.ken.nfl.stats.Mean
import leider.ken.nfl.Player
import leider.ken.nfl.fantasy.League
class DraftAnalysis {

    static constraints = {
    }
    
     static mapping = {
        points cascade: "save-update,all-delete-orphan"
        correctedPoints cascade: "save-update,all-delete-orphan"
        opportunities cascade: "save-update,all-delete-orphan"
        
        season index: 'draft_analysis_identity_idx'
        player index: 'draft_analysis_identity_idx'
    }
    
    League league
    Player player
    int games
    Mean opportunities
    Mean points
    Mean correctedPoints
    
}
