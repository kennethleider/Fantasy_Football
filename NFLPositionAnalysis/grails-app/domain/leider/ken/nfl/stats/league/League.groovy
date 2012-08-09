package leider.ken.nfl.stats.league

class League {
    static constraints = {
        teams nullable : true        
    }
    
    String name
    String url

    ScoringRules scoring = new ScoringRules()
    Integer teams
    List<RosterPosition> roster = []
}

   class RosterPosition {
       List<String> positions
       int count
   }