package leider.ken.nfl.fantasy

import leider.ken.nfl.Season
import leider.ken.nfl.Player

class SeasonScore {
    static belongsTo = [ league: League ]  
    static constraints = {
        season()
        player()
        total()
        average()
        standardDeviation()
        max()
        league unique: ['season', 'player']
    }
    
    static mapping = {
        season index: 'season_score_identity_idx'
        player index: 'season_score_identity_idx'
        league index: 'season_score_index_idx'
    }
    
    
    Season season
    Player player
    
    Double total
    Double average
    Double standardDeviation
    Double max
    Double min
}
