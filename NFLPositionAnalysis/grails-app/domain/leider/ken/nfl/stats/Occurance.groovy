package leider.ken.nfl.stats

class Occurance {

    static constraints = {

    }
    
    Integer times
    Double percentage
    
    static compute(List values, Closure condition) {
        Mean retval = new Mean()
        def matches = values.findAll(condition)
        retval.times = matches.size()
        retval.percentage = 100.0 * matches.size() / values.size()
      
        return retval;
    }
}
