package leider.ken.nfl.stats.league

class League {
    static constraints = {
    }
    
    String name
    String url

    ScoringRules scoring = new ScoringRules()
}

   