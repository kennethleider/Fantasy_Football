package leider.ken.nfl.armchairanalysis

import org.springframework.dao.DataIntegrityViolationException

class PlayController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [playInstanceList: Play.list(params), playInstanceTotal: Play.count()]
    }

    def show(Long id) {
        def playInstance = Play.get(id)
        if (!playInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'play.label', default: 'Play'), id])
            redirect(action: "list")
            return
        }

        [playInstance: playInstance]
    }
}
