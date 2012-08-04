package leider.ken.nfl.stats

import org.springframework.dao.DataIntegrityViolationException

class PlayerWeekStatsController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [playerWeekStatsInstanceList: PlayerWeekStats.list(params), playerWeekStatsInstanceTotal: PlayerWeekStats.count()]
    }

    def create() {
        [playerWeekStatsInstance: new PlayerWeekStats(params)]
    }

    def save() {
        def playerWeekStatsInstance = new PlayerWeekStats(params)
        if (!playerWeekStatsInstance.save(flush: true)) {
            render(view: "create", model: [playerWeekStatsInstance: playerWeekStatsInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'playerWeekStats.label', default: 'PlayerWeekStats'), playerWeekStatsInstance.id])
        redirect(action: "show", id: playerWeekStatsInstance.id)
    }

    def show(Long id) {
        def playerWeekStatsInstance = PlayerWeekStats.get(id)
        if (!playerWeekStatsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'playerWeekStats.label', default: 'PlayerWeekStats'), id])
            redirect(action: "list")
            return
        }

        [playerWeekStatsInstance: playerWeekStatsInstance]
    }

    def edit(Long id) {
        def playerWeekStatsInstance = PlayerWeekStats.get(id)
        if (!playerWeekStatsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'playerWeekStats.label', default: 'PlayerWeekStats'), id])
            redirect(action: "list")
            return
        }

        [playerWeekStatsInstance: playerWeekStatsInstance]
    }

    def update(Long id, Long version) {
        def playerWeekStatsInstance = PlayerWeekStats.get(id)
        if (!playerWeekStatsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'playerWeekStats.label', default: 'PlayerWeekStats'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (playerWeekStatsInstance.version > version) {
                playerWeekStatsInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'playerWeekStats.label', default: 'PlayerWeekStats')] as Object[],
                          "Another user has updated this PlayerWeekStats while you were editing")
                render(view: "edit", model: [playerWeekStatsInstance: playerWeekStatsInstance])
                return
            }
        }

        playerWeekStatsInstance.properties = params

        if (!playerWeekStatsInstance.save(flush: true)) {
            render(view: "edit", model: [playerWeekStatsInstance: playerWeekStatsInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'playerWeekStats.label', default: 'PlayerWeekStats'), playerWeekStatsInstance.id])
        redirect(action: "show", id: playerWeekStatsInstance.id)
    }

    def delete(Long id) {
        def playerWeekStatsInstance = PlayerWeekStats.get(id)
        if (!playerWeekStatsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'playerWeekStats.label', default: 'PlayerWeekStats'), id])
            redirect(action: "list")
            return
        }

        try {
            playerWeekStatsInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'playerWeekStats.label', default: 'PlayerWeekStats'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'playerWeekStats.label', default: 'PlayerWeekStats'), id])
            redirect(action: "show", id: id)
        }
    }
}
