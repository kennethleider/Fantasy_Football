package leider.ken.nfl.armchairanalysis

import leider.ken.nfl.Player
class Rush {

    static belongsTo = [ play: Play ]
    
    static constraints = {
    }
    
    static mapping = {
        play index: 'rush_play_idx'
    }
    
    Player player
    String direction
}
