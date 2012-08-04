package leider.ken.nfl.stats

class Season {
    static hasMany = [ weeks : Week ]
    static constraints = {
        year unique : true, nullable : false
    }

    static mapping = {
        sort year: 'asc'
        weeks sort: 'number', order: 'asc'
    }
	
    Integer year
	
    @Override
    public String toString() {
        return year.toString()
    }
}
