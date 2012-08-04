package leider.ken.nfl.stats

class Week {
    static belongsTo = [ season : Season ]
    static constraints = {
        season unique : ['number']
        number()
    }

    static mapping = {
        sort season: 'desc', number: 'asc'
    }
	
    Integer number
	
    String toString() {
        return "${season.toString()} - ${number}"
    }
}