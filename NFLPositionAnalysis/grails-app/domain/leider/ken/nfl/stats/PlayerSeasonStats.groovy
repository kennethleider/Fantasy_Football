package leider.ken.nfl.stats

class PlayerSeasonStats extends PlayerStats{

	static embedded = ['passing', 'rushing']
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
