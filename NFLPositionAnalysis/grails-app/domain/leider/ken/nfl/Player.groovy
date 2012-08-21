package leider.ken.nfl

class Player {

    static constraints = {
        name()
        position()
        team nullable : true
        firstYear nullable: true
        draftPick nullable: true
        alternatePosition nullable : true
    }
    
    String name
    String position
    String alternatePosition
    String team
    List<String> teamHistory
    
    String firstYear	
    Integer draftPick

    @Override
    public String toString() {
        return name
    }
}
