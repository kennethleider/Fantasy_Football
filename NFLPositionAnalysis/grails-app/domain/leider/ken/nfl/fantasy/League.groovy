package leider.ken.nfl.fantasy

class League {
    static hasMany = [roster : RosterPosition]
    
    static constraints = {
        teams nullable : true        
    }
    
    String name
    String url

    ScoringRules scoring = new ScoringRules()
    Integer teams
}
