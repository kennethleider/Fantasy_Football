package leider.ken.nfl.stats.league.analysis

import leider.ken.nfl.stats.Mean
import leider.ken.nfl.stats.Season
import leider.ken.nfl.stats.league.League

class PositionSeasonAnalysis {

    static constraints = {
    }
    
    League league
    String position
    Season season
    Mean twentyFifthPercentile
    Mean fiftiethPercentile
    Mean hundrethPercentile
    Mean playables
}
