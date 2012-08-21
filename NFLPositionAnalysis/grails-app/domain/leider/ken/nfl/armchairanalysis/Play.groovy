package leider.ken.nfl.armchairanalysis



class Play {
    static belongsTo = [ game : Game ]
    
    static constraints = {
    }
    
    static mapping = {
        id generator:'assigned'
    }

    String offense
    String defense
    String type
    int duration
    int yards
    int successful
    int firstDown
    int points
    int td
    int conversion
}
