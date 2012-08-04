package leider.ken.nfl.armchairanalysis

import leider.ken.nfl.stats.Player
class ArmchairPlayerRef {

    static constraints = {
        player()
        code()
    }
      
    static mapping = {
        id generator:'assigned'
        code index: 'armchair_ref_code_idx'
    }
    
    Player player;
    String code
}
