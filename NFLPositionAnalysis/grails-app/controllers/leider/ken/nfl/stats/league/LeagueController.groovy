package leider.ken.nfl.stats.league

class LeagueController {
    static scaffold = true
    def scoreService
    
    def score() {
        League league = League.get(params.id)
        scoreService.calculate(league)
    }
}