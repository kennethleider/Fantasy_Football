package leider.ken.nfl.stats.league

class RosterPosition {
    static belongsTo = [ league : League ]
    static hasMany = [positions : String]
   
    static constraints = {
    }
    
    Integer slots
}
