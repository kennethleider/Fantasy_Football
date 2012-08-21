package leider.ken.nfl.armchairanalysis

import leider.ken.nfl.Week
class Game {

    static constraints = {
    }
    
    static mapping = {
        id generator:'assigned'
    }
    
    Week week
    String day 
    String visitor 
    String host 
    String stadium 
    String temperature 
    String humidity 
    String windSpeed 
    String windDirection 
    String weatherConditions 
    String surface 
    Double overUnder 
    Double visitorPointSpread 
    Integer visitorScore 
    Integer hostScore 
}
