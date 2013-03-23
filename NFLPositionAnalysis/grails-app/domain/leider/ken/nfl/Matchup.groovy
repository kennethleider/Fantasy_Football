package leider.ken.nfl

class Matchup {

    static constraints = {
        week()
        away()
        awayScore nullable : true
        home()
        homeScore nullable : true
    }
    
    static mapping = {
        week index: 'matchup_away_idx,matchup_home_idx'
        away index: 'matchup_away_idx'
        home index: 'matchup_home_idx'
    }
    
    
    Week week
    Franchise away
    Franchise home
    Integer awayScore
    Integer homeScore
}
