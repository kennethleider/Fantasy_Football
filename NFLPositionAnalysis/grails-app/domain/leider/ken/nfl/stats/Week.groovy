package leider.ken.nfl.stats

class Week {
    static belongsTo = [ season : Season ]
    static constraints = {
        season unique : ['number']
        number()
    }

    static mapping = {
        sort season: 'asc', number: 'asc'
        season lazy : false
    }
	
    Integer number
	
    String toString() {
        return "${season.toString()} - ${number}"
    }
}