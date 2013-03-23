import leider.ken.nfl.fantasy.League
import leider.ken.nfl.fantasy.RosterPosition

import leider.ken.nfl.Franchise

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
        
        league.scoring.puntReturning.perYard = 0.04
        league.scoring.puntReturning.perTD = 6
        
        league.scoring.kickoffReturning.perYard = 0.04
        league.scoring.kickoffReturning.perTD = 6
        
        league.save()
        
        RosterPosition.findAllByLeague(league).each { it.delete() }
        new RosterPosition(league: league, positions : [ 'QB' ], slots : 2).save()
        new RosterPosition(league: league, positions : [ 'RB' ], slots : 3).save()
        new RosterPosition(league: league, positions : [ 'WR' ], slots : 4).save()
        new RosterPosition(league: league, positions : [ 'TE' ], slots : 1).save()
        
        
        league = League.findOrSaveWhere(name : "Castle rock", url: "http://football.fantasysports.yahoo.com/f1/206440")
        league.teams = 12
  
        double mean = 407.9037968726
        double stddev = 199.6369803479
        double factor = 35/stddev
        league.scoring.baseline = 100 - (mean * factor)
        league.scoring.passing.perCompletion = 4.378799350594725 * factor
        league.scoring.passing.perAttempt = 0
        league.scoring.passing.perYard = 1 * factor
        league.scoring.passing.perTD = 60 * factor
        league.scoring.passing.perInt = 0
        league.scoring.passing.perConversion = 20 * factor
        
        league.scoring.rushing.perAttempt = 10 * factor
        league.scoring.rushing.perYard = 1 * factor
        league.scoring.rushing.perTD = 60 * factor
        league.scoring.rushing.perConversion = 20 * factor
        league.scoring.rushing.perFumbleLost = 0
        
        league.scoring.receiving.perTarget = 0
        league.scoring.receiving.perYard = 1 * factor
        league.scoring.receiving.perReception = 21.576356139486833 * factor
        league.scoring.receiving.perTD = 60 * factor
        league.scoring.receiving.perConversion = 20 * factor
        
        league.scoring.puntReturning.perYard = 1 * factor
        league.scoring.puntReturning.perTD = 60 * factor
        league.scoring.puntReturning.perAttempt = 32.58588258553947 * factor

        league.scoring.kickoffReturning.perYard = 1 * factor
        league.scoring.kickoffReturning.perTD = 60 * factor
        league.scoring.kickoffReturning.perAttempt = 37.47074552580638 * factor
        
        league.save()
        
        RosterPosition.findAllByLeague(league).each { it.delete() }
        new RosterPosition(league: league, positions : [ 'QB' ], slots : 2).save()
        new RosterPosition(league: league, positions : [ 'RB' ], slots : 3).save()
        new RosterPosition(league: league, positions : [ 'WR' ], slots : 4).save()
        new RosterPosition(league: league, positions : [ 'TE' ], slots : 1).save()
        
        initFranchises()
    }
    
    def initFranchises() {
        Franchise.findOrSaveWhere(name : "Cardinals", code : "ARI", location : "Arizona")
        Franchise.findOrSaveWhere(name : "Falcons", code : "ATL", location : "Atlanta")
        Franchise.findOrSaveWhere(name : "Ravens", code : "BAL", location : "Baltimore")
        Franchise.findOrSaveWhere(name : "Bills", code : "BUF", location : "Buffalo")
        Franchise.findOrSaveWhere(name : "Panthers", code : "CAR", location : "Carolina")
        Franchise.findOrSaveWhere(name : "Bears", code : "CHI", location : "Chicago")
        Franchise.findOrSaveWhere(name : "Bengals", code : "CIN", location : "Cincinnati")
        Franchise.findOrSaveWhere(name : "Browns", code : "CLE", location : "Cleveland")
        Franchise.findOrSaveWhere(name : "Cowboys", code : "DAL", location : "Dallas")
        Franchise.findOrSaveWhere(name : "Broncos", code : "DEN", location : "Denver")
        Franchise.findOrSaveWhere(name : "Lions", code : "DET", location : "Detroit")
        Franchise.findOrSaveWhere(name : "Packers", code : "GB", location : "Green Bay")
        Franchise.findOrSaveWhere(name : "Texans", code : "HOU", location : "Houston")
        Franchise.findOrSaveWhere(name : "Colts", code : "IND", location : "Indianapolis")
        Franchise.findOrSaveWhere(name : "Jaguars", code : "JAC", location : "Jacksonville")
        Franchise.findOrSaveWhere(name : "Chiefs", code : "KC", location : "Kansas City")
        Franchise.findOrSaveWhere(name : "Dolphins", code : "MIA", location : "Miami")
        Franchise.findOrSaveWhere(name : "Vikings", code : "MIN", location : "Minnesota")
        Franchise.findOrSaveWhere(name : "Patriots", code : "NE", location : "New England")
        Franchise.findOrSaveWhere(name : "Saints", code : "NO", location : "New Orleans")
        Franchise.findOrSaveWhere(name : "Giants", code : "NYG", location : "New York")
        Franchise.findOrSaveWhere(name : "Jets", code : "NYJ", location : "New York")
        Franchise.findOrSaveWhere(name : "Raiders", code : "OAK", location : "Oakland")
        Franchise.findOrSaveWhere(name : "Eagles", code : "PHI", location : "Philadelphia")
        Franchise.findOrSaveWhere(name : "Steelers", code : "PIT", location : "Pittsburgh")
        Franchise.findOrSaveWhere(name : "Rams", code : "STL", location : "Ssint Louis")
        Franchise.findOrSaveWhere(name : "Chargers", code : "SD", location : "San Diego")
        Franchise.findOrSaveWhere(name : "49ers", code : "SF", location : "San Francisco")
        Franchise.findOrSaveWhere(name : "Seahawks", code : "SEA", location : "Seattle")
        Franchise.findOrSaveWhere(name : "Buccaneers", code : "TB", location : "Tampa Bay")
        Franchise.findOrSaveWhere(name : "Titans", code : "TEN", location : "Tennessee")
        Franchise.findOrSaveWhere(name : "Redskins", code : "WAS", location : "Washington")
        
        // Legacy
        Franchise.findOrSaveWhere(name : "Oilers", code : "HOU", location : "Houston")

    }
    def destroy = {
    }
}
