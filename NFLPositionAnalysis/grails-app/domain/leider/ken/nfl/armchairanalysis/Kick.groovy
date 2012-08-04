package leider.ken.nfl.armchairanalysis

import leider.ken.nfl.stats.Player
class Kick {

    static belongsTo = [ play: Play ]
    
    static constraints = {
        returner nullable : true
    }
    
    static mapping = {
        play index: 'kick_play_idx'
    }
    
    Player kicker
    Player returner
    int grossYards
    int netYards
    int returnYards
    int touchBack
}
