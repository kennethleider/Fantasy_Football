package leider.ken.nfl.yahoo

import leider.ken.nfl.Player
class YahooPlayerRef {

    static constraints = {
    }
      
    static mapping = {
        id generator:'assigned'
    }
    
    Player player;
}
