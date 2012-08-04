package leider.ken.nfl.armchairanalysis

import org.springframework.dao.DataIntegrityViolationException

class ArmchairPlayerRefController {
    def scaffold = true
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [armchairPlayerRefInstanceList: ArmchairPlayerRef.list(params), armchairPlayerRefInstanceTotal: ArmchairPlayerRef.count()]
    }

    def create() {
        [armchairPlayerRefInstance: new ArmchairPlayerRef(params)]
    }

    def save() {
        def armchairPlayerRefInstance = new ArmchairPlayerRef(params)
        if (!armchairPlayerRefInstance.save(flush: true)) {
            render(view: "create", model: [armchairPlayerRefInstance: armchairPlayerRefInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'armchairPlayerRef.label', default: 'ArmchairPlayerRef'), armchairPlayerRefInstance.id])
        redirect(action: "show", id: armchairPlayerRefInstance.id)
    }

    def show(Long id) {
        def armchairPlayerRefInstance = ArmchairPlayerRef.get(id)
        if (!armchairPlayerRefInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'armchairPlayerRef.label', default: 'ArmchairPlayerRef'), id])
            redirect(action: "list")
            return
        }

        [armchairPlayerRefInstance: armchairPlayerRefInstance]
    }

    def edit(Long id) {
        def armchairPlayerRefInstance = ArmchairPlayerRef.get(id)
        if (!armchairPlayerRefInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'armchairPlayerRef.label', default: 'ArmchairPlayerRef'), id])
            redirect(action: "list")
            return
        }

        [armchairPlayerRefInstance: armchairPlayerRefInstance]
    }

    def update(Long id, Long version) {
        def armchairPlayerRefInstance = ArmchairPlayerRef.get(id)
        if (!armchairPlayerRefInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'armchairPlayerRef.label', default: 'ArmchairPlayerRef'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (armchairPlayerRefInstance.version > version) {
                armchairPlayerRefInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'armchairPlayerRef.label', default: 'ArmchairPlayerRef')] as Object[],
                          "Another user has updated this ArmchairPlayerRef while you were editing")
                render(view: "edit", model: [armchairPlayerRefInstance: armchairPlayerRefInstance])
                return
            }
        }

        armchairPlayerRefInstance.properties = params

        if (!armchairPlayerRefInstance.save(flush: true)) {
            render(view: "edit", model: [armchairPlayerRefInstance: armchairPlayerRefInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'armchairPlayerRef.label', default: 'ArmchairPlayerRef'), armchairPlayerRefInstance.id])
        redirect(action: "show", id: armchairPlayerRefInstance.id)
    }

    def delete(Long id) {
        def armchairPlayerRefInstance = ArmchairPlayerRef.get(id)
        if (!armchairPlayerRefInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'armchairPlayerRef.label', default: 'ArmchairPlayerRef'), id])
            redirect(action: "list")
            return
        }

        try {
            armchairPlayerRefInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'armchairPlayerRef.label', default: 'ArmchairPlayerRef'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'armchairPlayerRef.label', default: 'ArmchairPlayerRef'), id])
            redirect(action: "show", id: id)
        }
    }
}
