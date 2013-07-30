package leider.ken.nfl.fantasy.analysis

import leider.ken.nfl.stats.Mean
import leider.ken.nfl.Season
import leider.ken.nfl.fantasy.League

class PositionSeasonAnalysis {    
    
    static hasMany = [averages: Double]
    static constraints = {
        season unique : ['league', 'position']
        averages nullable : false
    }
    
    
    League league
    String position
    Season season
    List<Double> averages
}
