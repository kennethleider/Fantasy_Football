package leider.ken.nfl.stats

class Ranking extends Occurance {
    
    static constraints = {
    }
    
    static mapping = {
        sort rank: 'asc'
    }
    
    int rank
    
    public String toString() {
        return "${rank} - ${super.toString()}"
    }
}
