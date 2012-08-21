package leider.ken.nfl.stats

import leider.ken.nfl.Week
import leider.ken.nfl.Franchise

class PlayerWeekStats extends PlayerStats {

    static constraints = {
        week unique: ['player']
        franchise nullable : true
    }
		
    static mapping = {
        week index: 'player_week_identity_idx'
        player index: 'player_week_identity_idx'
    }
	
	
    Week week
    Franchise franchise
}
