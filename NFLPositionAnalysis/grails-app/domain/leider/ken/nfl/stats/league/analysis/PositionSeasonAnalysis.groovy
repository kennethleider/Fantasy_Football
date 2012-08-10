package leider.ken.nfl.stats.league.analysis

import leider.ken.nfl.stats.Mean
import leider.ken.nfl.stats.Season
import leider.ken.nfl.stats.league.League

class PositionSeasonAnalysis {

    static constraints = {
    }
    
    static mapping = {
        twentyFifthPercentile cascade: "save-update,delete"
        fiftiethPercentile cascade: "save-update,delete"
        hundrethPercentile cascade: "save-update,delete"
        playables cascade: "save-update,delete"
    }
    
    League league
    String position
    Season season
    Mean zerothPercentile
    Mean twentyFifthPercentile
    Mean fiftiethPercentile
    Mean hundrethPercentile
    Mean playables
}
