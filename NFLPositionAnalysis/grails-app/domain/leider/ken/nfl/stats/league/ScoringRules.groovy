package leider.ken.nfl.stats.league

class ScoringRules {
    static belongsTo = League
    static embedded = [ 'passing', 'rushing', 'receiving', 'returning', 'defending', 'kicking' ]
    
    Passing passing = new Passing()
    Rushing rushing = new Rushing()
    Receiving receiving = new Receiving()
    Returning returning = new Returning()
    Defending defending = new Defending()
    Kicking kicking = new Kicking()
    
    boolean useFractional = true
    boolean useNegative = true
}

class Passing {
    double perCompletion
    double perYard
    double perTD
    double perInt
    double perConversion
}

class Rushing {
    double perYard
    double perTD
    double perFumbleLost
    double perConversion
}

class Receiving {
    double perReception
    double perYard
    double perTD
    double perConversion
}

class Returning {
    double perYard
    double perTD
}

class Defending {
    //static hasMay = [ pointsAllowed : PointsAllowed ]
    
    double perTD
    double perSack
    double perInt
    double perSafety
    double perReturnTD
    
}

class Kicking {
    //static hasMany = [ distances : Distance ]
    double perPAT
}

class Distance {
    static belongsTo = Kicking
    int maximum
    double perMade
    double perMiss
}

class PointsAllowed {
    static belongsTo = Defending
    int maximum
    double points
}
