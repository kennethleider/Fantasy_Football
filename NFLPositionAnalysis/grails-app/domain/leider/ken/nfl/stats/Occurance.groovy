package leider.ken.nfl.stats

class Occurance {

    static constraints = {
        times()
        percentage()
    }
    
    Integer times
    Double percentage
    
    static compute(List values, Closure condition) {
        Occurance retval = new Occurance()
        def matches = values.findAll(condition)
        retval.times = matches.size()
        retval.percentage = 100.0 * matches.size() / values.size()
      
        return retval;
    }
    
    public String toString() {
        return times + "(${percentage})"
    }
}
