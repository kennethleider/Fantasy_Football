package leider.ken.nfl.fantasy.analysis

import org.springframework.dao.DataIntegrityViolationException

class PointsAgainstController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [pointsAgainstInstanceList: PointsAgainst.list(params), pointsAgainstInstanceTotal: PointsAgainst.count()]
    }

    def create() {
        [pointsAgainstInstance: new PointsAgainst(params)]
    }

    def save() {
        def pointsAgainstInstance = new PointsAgainst(params)
        if (!pointsAgainstInstance.save(flush: true)) {
            render(view: "create", model: [pointsAgainstInstance: pointsAgainstInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'pointsAgainst.label', default: 'PointsAgainst'), pointsAgainstInstance.id])
        redirect(action: "show", id: pointsAgainstInstance.id)
    }

    def show(Long id) {
        def pointsAgainstInstance = PointsAgainst.get(id)
        if (!pointsAgainstInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pointsAgainst.label', default: 'PointsAgainst'), id])
            redirect(action: "list")
            return
        }

        [pointsAgainstInstance: pointsAgainstInstance]
    }

    def edit(Long id) {
        def pointsAgainstInstance = PointsAgainst.get(id)
        if (!pointsAgainstInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pointsAgainst.label', default: 'PointsAgainst'), id])
            redirect(action: "list")
            return
        }

        [pointsAgainstInstance: pointsAgainstInstance]
    }

    def update(Long id, Long version) {
        def pointsAgainstInstance = PointsAgainst.get(id)
        if (!pointsAgainstInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pointsAgainst.label', default: 'PointsAgainst'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (pointsAgainstInstance.version > version) {
                pointsAgainstInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'pointsAgainst.label', default: 'PointsAgainst')] as Object[],
                          "Another user has updated this PointsAgainst while you were editing")
                render(view: "edit", model: [pointsAgainstInstance: pointsAgainstInstance])
                return
            }
        }

        pointsAgainstInstance.properties = params

        if (!pointsAgainstInstance.save(flush: true)) {
            render(view: "edit", model: [pointsAgainstInstance: pointsAgainstInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'pointsAgainst.label', default: 'PointsAgainst'), pointsAgainstInstance.id])
        redirect(action: "show", id: pointsAgainstInstance.id)
    }

    def delete(Long id) {
        def pointsAgainstInstance = PointsAgainst.get(id)
        if (!pointsAgainstInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pointsAgainst.label', default: 'PointsAgainst'), id])
            redirect(action: "list")
            return
        }

        try {
            pointsAgainstInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'pointsAgainst.label', default: 'PointsAgainst'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'pointsAgainst.label', default: 'PointsAgainst'), id])
            redirect(action: "show", id: id)
        }
    }
}
