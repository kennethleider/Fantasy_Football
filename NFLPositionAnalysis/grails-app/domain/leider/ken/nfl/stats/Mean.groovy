package leider.ken.nfl.stats

class Mean {

    static constraints = {
        median nullable : true
    }
    
    Double average
    Double standardDeviation
    Double median
    
    static compute(List values) {
        Mean retval = new Mean()
        values = values.sort()
        retval.average = values.sum() / values.size()
        
        if (values.size() > 1) {
            retval.standardDeviation = Math.sqrt((values.sum { Math.pow(it - retval.average,2) }) / values.size())
            
            int middle = values.size() / 2
            if (values.size() % 2 == 0) {
                retval.median = (values[middle] + values[middle - 1])/ 2
            } else {
                retval.median = values[middle]
            }
        } else {
            retval.standardDeviation = 0
            retval.median = retval.average
        }

        return retval;
    }
}
