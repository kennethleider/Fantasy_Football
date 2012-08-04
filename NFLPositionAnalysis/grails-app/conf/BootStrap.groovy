import leider.ken.nfl.stats.league.League
class BootStrap {

    def init = { servletContext ->
        League league = League.findOrSaveWhere(name : "Battle School", url: "http://fantasy.nfl.com/league/386519")
        
        league.scoring.passing.perCompletion = 0.1
        league.scoring.passing.perYard = 0.04
        league.scoring.passing.perTD = 4
        league.scoring.passing.perInt = -2
        league.scoring.passing.perConversion = 2
        
        league.scoring.rushing.perYard = 0.1
        league.scoring.rushing.perTD = 6
        league.scoring.rushing.perConversion = 2
        league.scoring.rushing.perFumbleLost = -2
        
        
    }
    def destroy = {
    }
}
