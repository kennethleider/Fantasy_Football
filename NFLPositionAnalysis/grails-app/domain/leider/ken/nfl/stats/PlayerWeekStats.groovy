package leider.ken.nfl.stats

class PlayerWeekStats extends PlayerStats {

    static constraints = {
        week unique: ['player']
    }
		
    static mapping = {
        week index: 'player_week_identity_idx'
        player index: 'player_week_identity_idx'
    }
	
	
    Week week
}
