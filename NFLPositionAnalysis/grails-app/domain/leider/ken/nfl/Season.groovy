package leider.ken.nfl

class Season {
    static hasMany = [ weeks : Week ]
    static constraints = {
        year unique : true, nullable : false
    }

    static mapping = {
        sort year: 'asc'
        weeks sort: 'number', order: 'asc', lazy : false
    }
	
    Integer year
	
    @Override
    public String toString() {
        return year.toString()
    }
}
