package leider.ken.nfl

class Franchise {

    static constraints = {
        location()
        name()
        code()
    }
    
    String name
    String location
    String code
    
    String toString() {
        return name
    }
}
