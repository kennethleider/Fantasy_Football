package leider.ken.nfl.fantasy.analysis

import leider.ken.nfl.stats.Mean
import leider.ken.nfl.Season
import leider.ken.nfl.fantasy.League

class PositionSeasonAnalysis {

    static constraints = {
    }
    
    static mapping = {
        zerothPercentile cascade: "save-update,delete"
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
