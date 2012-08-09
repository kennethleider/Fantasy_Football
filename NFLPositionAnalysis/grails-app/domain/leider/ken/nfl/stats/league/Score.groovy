package leider.ken.nfl.stats.league

import leider.ken.nfl.stats.*
class Score {
    static belongsTo = [ league: League ]  
    static constraints = {
        league unique: ['week', 'player']
    }
    
    static mapping = {
        week index: 'score_identity_idx'
        player index: 'score_identity_idx'
        league index: 'score_index_idx'
    }
    
    Week week
    Player player
    
    double points
}
