package leider.ken.nfl.fantasy

class ScoringRules {
    static belongsTo = League
    static embedded = [ 'passing', 'rushing', 'receiving', 'puntReturning', 'kickoffReturning', 'defending', 'kicking' ]
    
    Passing passing = new Passing()
    Rushing rushing = new Rushing()
    Receiving receiving = new Receiving()
    Returning puntReturning = new Returning()
    Returning kickoffReturning = new Returning()
    Defending defending = new Defending()
    Kicking kicking = new Kicking()
    
    boolean useFractional = true
    boolean useNegative = true
    double baseline
}

class Passing {
    double perAttempt
    double perCompletion
    double perYard
    double perTD
    double perInt
    double perConversion
}

class Rushing {
    double perAttempt
    double perYard
    double perTD
    double perFumbleLost
    double perConversion
}

class Receiving {
    double perTarget
    double perReception
    double perYard
    double perTD
    double perConversion
}

class Returning {
    double perAttempt
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
