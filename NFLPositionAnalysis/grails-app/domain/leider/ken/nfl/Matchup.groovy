package leider.ken.nfl

class Matchup {

    static constraints = {
        week()
        away()
        awayScore nullable : true
        home()
        homeScore nullable : true
    }
    
    Week week
    Franchise away
    Franchise home
    Integer awayScore
    Integer homeScore
}
