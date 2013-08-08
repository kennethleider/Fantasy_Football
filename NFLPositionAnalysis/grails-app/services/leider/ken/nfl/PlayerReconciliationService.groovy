package leider.ken.nfl

import leider.ken.nfl.yahoo.YahooPlayerRef

class PlayerReconciliationService {
    def grailsApplication

    def reconcile(String source, String name, String id, String position, String team, Season season) {
        def sourceMaps = grailsApplication.config.player.reconciliation[source]

        for (Map.Entry entry in sourceMaps.entrySet()) {
            def otherId = entry.value[id]
            if (otherId) {
                if (entry.key == "yahoo") {
                    def ref = YahooPlayerRef.get(otherId.toLong())
                    if (ref != null) {
                        println "Matched yahoo player"
                        return ref.player
                    }
                }
            }
        }

        def matches = Player.findAllByName(name)
        if (matches.size() == 1) {
            return matches[0]
        } else if (matches.size() > 1) {
            matches = matches.findAll { it.position == position }

            if (matches.size() == 1) {
                return matches[0]
            } else {
                throw new IllegalStateException("Found multiple possible players for ${position} ${name} on ${team} in ${season}.  Searching with ${source} id '${id}'")
            }
        }

        return null
    }
}
