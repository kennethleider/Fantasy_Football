package leider.ken.nfl.stats

import leider.ken.nfl.Player

class PlayerStats {

    static final int NULL = -23879
    
    static constraints = {
    }
    
    static embedded = ['passing', 'rushing', 'receiving', 'kickoff', 'punt']
    Player player
    
    PassingStats passing = new PassingStats()
    RushingStats rushing = new RushingStats()
    ReceivingStats receiving = new ReceivingStats()
    ReturnStats kickoff = new ReturnStats()
    ReturnStats punt = new ReturnStats()
}

class PassingStats {
    double qbRating
    int completions = PlayerStats.NULL
    int attempts = PlayerStats.NULL
    int yards = PlayerStats.NULL
    int TDs = PlayerStats.NULL
    int interceptions = PlayerStats.NULL
    int sacks = PlayerStats.NULL
    int sackYards = PlayerStats.NULL
    int conversions = PlayerStats.NULL
	
}

class RushingStats {
    int attempts = PlayerStats.NULL
    int yards = PlayerStats.NULL
    int TDs = PlayerStats.NULL
    int fumbles = PlayerStats.NULL
    int fumblesLost = PlayerStats.NULL
    int conversions = PlayerStats.NULL
}

class ReceivingStats {
    int targets = PlayerStats.NULL
    int receptions = PlayerStats.NULL
    int yards = PlayerStats.NULL
    int TDs = PlayerStats.NULL
    int conversions = PlayerStats.NULL
}

class ReturnStats {
    int attempts = PlayerStats.NULL
    int yards = PlayerStats.NULL
    int TDs = PlayerStats.NULL
}

