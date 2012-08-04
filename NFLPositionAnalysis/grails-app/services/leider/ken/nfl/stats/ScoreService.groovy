package leider.ken.nfl.stats

import leider.ken.nfl.stats.league.*
class ScoreService {

    def calculate(League league) {
        println 'here'
        for(PlayerWeekStats stats in PlayerWeekStats.list()) {
            Score score = Score.findOrCreateWhere(week : stats.week, player : stats.player, league : league)
            
            score.points = stats.passing.yards * league.scoring.passing.perYard
            score.points += stats.passing.completions * league.scoring.passing.perCompletion
            score.points += stats.passing.TDs * league.scoring.passing.perTD
            score.points += stats.passing.interceptions * league.scoring.passing.perInt
            score.points += stats.passing.conversions * league.scoring.passing.perConversion
            
            score.points += stats.rushing.yards * league.scoring.rushing.perYard
            score.points += stats.rushing.TDs * league.scoring.rushing.perTD
            score.points += stats.rushing.fumblesLost * league.scoring.rushing.perFumbleLost
            score.points += stats.rushing.conversions * league.scoring.rushing.perConversion
            println score.points
            score.save()
        }

    }
}
