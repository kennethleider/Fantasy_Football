package leider.ken.nfl.stats.league

import org.springframework.dao.DataIntegrityViolationException

import leider.ken.nfl.stats.Season

class SeasonScoreController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        
        [seasonScoreInstanceList: SeasonScore.findAllBySeason(Season.findByYear(2011), params), seasonScoreInstanceTotal: SeasonScore.countBySeason(Season.findByYear(2011))]
        //[seasonScoreInstanceList: SeasonScore.list(params), seasonScoreInstanceTotal: SeasonScore.count()]
    }

    def create() {
        [seasonScoreInstance: new SeasonScore(params)]
    }

    def save() {
        def seasonScoreInstance = new SeasonScore(params)
        if (!seasonScoreInstance.save(flush: true)) {
            render(view: "create", model: [seasonScoreInstance: seasonScoreInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'seasonScore.label', default: 'SeasonScore'), seasonScoreInstance.id])
        redirect(action: "show", id: seasonScoreInstance.id)
    }

    def show(Long id) {
        def seasonScoreInstance = SeasonScore.get(id)
        if (!seasonScoreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'seasonScore.label', default: 'SeasonScore'), id])
            redirect(action: "list")
            return
        }

        [seasonScoreInstance: seasonScoreInstance]
    }

    def edit(Long id) {
        def seasonScoreInstance = SeasonScore.get(id)
        if (!seasonScoreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'seasonScore.label', default: 'SeasonScore'), id])
            redirect(action: "list")
            return
        }

        [seasonScoreInstance: seasonScoreInstance]
    }

    def update(Long id, Long version) {
        def seasonScoreInstance = SeasonScore.get(id)
        if (!seasonScoreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'seasonScore.label', default: 'SeasonScore'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (seasonScoreInstance.version > version) {
                seasonScoreInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'seasonScore.label', default: 'SeasonScore')] as Object[],
                          "Another user has updated this SeasonScore while you were editing")
                render(view: "edit", model: [seasonScoreInstance: seasonScoreInstance])
                return
            }
        }

        seasonScoreInstance.properties = params

        if (!seasonScoreInstance.save(flush: true)) {
            render(view: "edit", model: [seasonScoreInstance: seasonScoreInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'seasonScore.label', default: 'SeasonScore'), seasonScoreInstance.id])
        redirect(action: "show", id: seasonScoreInstance.id)
    }

    def delete(Long id) {
        def seasonScoreInstance = SeasonScore.get(id)
        if (!seasonScoreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'seasonScore.label', default: 'SeasonScore'), id])
            redirect(action: "list")
            return
        }

        try {
            seasonScoreInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'seasonScore.label', default: 'SeasonScore'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'seasonScore.label', default: 'SeasonScore'), id])
            redirect(action: "show", id: id)
        }
    }
}
