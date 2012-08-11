package leider.ken.nfl.stats.league

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
