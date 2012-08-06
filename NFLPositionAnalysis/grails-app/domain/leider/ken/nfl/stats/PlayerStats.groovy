package leider.ken.nfl.stats

class PlayerStats {

    static constraints = {
    }
    
    static embedded = ['passing', 'rushing', 'receiving']
    Player player
    
    PassingStats passing = new PassingStats()
    RushingStats rushing = new RushingStats()
    ReceivingStats receiving = new ReceivingStats()
}

class PassingStats {
    double qbRating
    int completions
    int attempts
    int yards
    int TDs
    int interceptions
    int sacks
    int sackYards
    int conversions
	
}

class RushingStats {
    int attempts
    int yards
    int TDs
    int fumbles
    int fumblesLost
    int conversions
}

class ReceivingStats {
    int targets
    int receptions
    int yards
    int TDs
    int conversions
}
