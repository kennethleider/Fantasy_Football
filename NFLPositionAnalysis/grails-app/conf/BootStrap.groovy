import leider.ken.nfl.stats.league.League
import leider.ken.nfl.stats.league.RosterPosition
class BootStrap {

    def init = { servletContext ->
        League league = League.findOrSaveWhere(name : "Battle School", url: "http://fantasy.nfl.com/league/386519")
        league.teams = 12
  
        league.scoring.passing.perCompletion = 0.1
        league.scoring.passing.perYard = 0.04
        league.scoring.passing.perTD = 4
        league.scoring.passing.perInt = -2
        league.scoring.passing.perConversion = 2
        
        league.scoring.rushing.perYard = 0.1
        league.scoring.rushing.perTD = 6
        league.scoring.rushing.perConversion = 2
        league.scoring.rushing.perFumbleLost = -2
        
        league.scoring.receiving.perYard = 0.1
        league.scoring.receiving.perReception = 1
        league.scoring.receiving.perTD = 6
        league.scoring.receiving.perConversion = 2
        
        league.scoring.returning.perYard = 0.04
        league.scoring.returning.perTD = 6
        
        league.save()
        
        RosterPosition.findAllByLeague(league).each { it.delete() }
        new RosterPosition(league: league, positions : [ 'QB' ], slots : 2).save()
        new RosterPosition(league: league, positions : [ 'RB' ], slots : 3).save()
        new RosterPosition(league: league, positions : [ 'WR' ], slots : 4).save()
        new RosterPosition(league: league, positions : [ 'TE' ], slots : 1).save()
    }
    def destroy = {
    }
}
