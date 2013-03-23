package leider.ken.nfl.armchairanalysis

import leider.ken.nfl.Player
class Punt {

    static belongsTo = [ play: Play ]
    
    static constraints = {
        returner nullable : true
    }
    
    static mapping = {
        play index: 'punt_play_idx'
    }
    
    Player punter
    Player returner
    int grossYards
    int netYards
    int returnYards
    int fairCatch
    int touchBack
}
