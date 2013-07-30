package leider.ken.nfl

class PlayerReconciliationService {

    def reconsile() {
        def player
                    
        def code = grailsApplication.config.yahooLookup[id]
        if (code != null) {
            player = ArmchairPlayerRef.findByCode(code)?.player
            player.name = name
        }
                    
        if (player == null) {
            def matches = Player.findAllByName(name)
            if (matches.size() == 1) {
                player = matches[0]
            } else if (matches.size() > 1) {
                matches = matches.findAll { it.position == position }
                            
                if (matches.size() == 1) {
                    player = matches[0]
                }
            }
        }
                    
    }
}
