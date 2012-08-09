package leider.ken.nfl.stats.league

import leider.ken.nfl.stats.*

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
