package leider.ken.nfl.armchairanalysis

import leider.ken.nfl.stats.Player
class Pass {

    static belongsTo = [ play: Play ]
    
    static constraints = {
        target nullable : true
    }
    
    static mapping = {
        play index: 'pass_play_idx'
    }
    
    Player passer
    Player target
    String location
    int completion
    int sack
    int interception
}
