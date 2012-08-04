package leider.ken.nfl.stats.league

import leider.ken.nfl.stats.*
class Score {
    static belongsTo = [ league: League ]  
    static constraints = {
        league unique: ['week', 'player']
    }
    
    Week week
    Player player
    
    double points
}
