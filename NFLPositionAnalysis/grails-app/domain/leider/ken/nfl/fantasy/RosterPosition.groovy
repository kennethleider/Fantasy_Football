package leider.ken.nfl.fantasy

class RosterPosition {
    static belongsTo = [ league : League ]
    static hasMany = [positions : String]
   
    static constraints = {
    }
    
    Integer slots
}
