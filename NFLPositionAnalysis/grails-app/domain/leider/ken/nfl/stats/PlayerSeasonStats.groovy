package leider.ken.nfl.stats

import leider.ken.nfl.Season

class PlayerSeasonStats extends PlayerStats{
    
    static constraints = {
        season unique: ['player']
    }
    
    static mapping = {
        player index: 'player_season_identity_idx'
        season index: 'player_season_identity_idx'
    }
    
    
    Season season
    int games
}
