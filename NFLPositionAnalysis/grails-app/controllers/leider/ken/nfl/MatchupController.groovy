package leider.ken.nfl

import org.springframework.dao.DataIntegrityViolationException

class MatchupController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [matchupInstanceList: Matchup.list(params), matchupInstanceTotal: Matchup.count()]
    }

    def create() {
        [matchupInstance: new Matchup(params)]
    }

    def save() {
        def matchupInstance = new Matchup(params)
        if (!matchupInstance.save(flush: true)) {
            render(view: "create", model: [matchupInstance: matchupInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'matchup.label', default: 'Matchup'), matchupInstance.id])
        redirect(action: "show", id: matchupInstance.id)
    }

    def show(Long id) {
        def matchupInstance = Matchup.get(id)
        if (!matchupInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'matchup.label', default: 'Matchup'), id])
            redirect(action: "list")
            return
        }

        [matchupInstance: matchupInstance]
    }

    def edit(Long id) {
        def matchupInstance = Matchup.get(id)
        if (!matchupInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'matchup.label', default: 'Matchup'), id])
            redirect(action: "list")
            return
        }

        [matchupInstance: matchupInstance]
    }

    def update(Long id, Long version) {
        def matchupInstance = Matchup.get(id)
        if (!matchupInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'matchup.label', default: 'Matchup'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (matchupInstance.version > version) {
                matchupInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'matchup.label', default: 'Matchup')] as Object[],
                          "Another user has updated this Matchup while you were editing")
                render(view: "edit", model: [matchupInstance: matchupInstance])
                return
            }
        }

        matchupInstance.properties = params

        if (!matchupInstance.save(flush: true)) {
            render(view: "edit", model: [matchupInstance: matchupInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'matchup.label', default: 'Matchup'), matchupInstance.id])
        redirect(action: "show", id: matchupInstance.id)
    }

    def delete(Long id) {
        def matchupInstance = Matchup.get(id)
        if (!matchupInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'matchup.label', default: 'Matchup'), id])
            redirect(action: "list")
            return
        }

        try {
            matchupInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'matchup.label', default: 'Matchup'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'matchup.label', default: 'Matchup'), id])
            redirect(action: "show", id: id)
        }
    }
}
