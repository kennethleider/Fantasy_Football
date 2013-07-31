package leider.ken.nfl.fantasy.analysis

import org.springframework.dao.DataIntegrityViolationException

class PlayerSeasonAnalysisController {
    def exportService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
       if(params?.format && params.format != "html"){
           response.contentType = grailsApplication.config.grails.mime.types[params.format]
           response.setHeader("Content-disposition", "attachment; filename=player_ranks.${params.extension}") 
           def fields = [ "player.name", "player.position", "season.year", 
                          "rank.average", "rank.median", "rank.standardDeviation",
                          "adjustedRank.average", "adjustedRank.median", "adjustedRank.standardDeviation" ]
           def labels = [ "player.name" : "Player", "player.position" : "Position", "season.year" : "Year", 
                          "rank.average" : "Rank - Average", "rank.median" : "Rank - Median", "rank.standardDeviation" : "Rank - STDDEV",
                          "adjustedRank.average" : "Adjusted Rank - Average", "adjustedRank.median" : "Adjusted Rank - Median", "adjustedRank.standardDeviation" : "Adjusted Rank - STTDEV" ]

            def records = PlayerSeasonAnalysis.findAllByGamesGreaterThan(3)
            exportService.export(params.format,response.outputStream, records, fields, labels, [:], [:])
       }
        [playerSeasonAnalysisInstanceList: PlayerSeasonAnalysis.list(params), playerSeasonAnalysisInstanceTotal: PlayerSeasonAnalysis.count()]
    }

    def create() {
        [playerSeasonAnalysisInstance: new PlayerSeasonAnalysis(params)]
    }

    def save() {
        def playerSeasonAnalysisInstance = new PlayerSeasonAnalysis(params)
        if (!playerSeasonAnalysisInstance.save(flush: true)) {
            render(view: "create", model: [playerSeasonAnalysisInstance: playerSeasonAnalysisInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'playerSeasonAnalysis.label', default: 'PlayerSeasonAnalysis'), playerSeasonAnalysisInstance.id])
        redirect(action: "show", id: playerSeasonAnalysisInstance.id)
    }

    def show(Long id) {
        def playerSeasonAnalysisInstance = PlayerSeasonAnalysis.get(id)
        println playerSeasonAnalysisInstance.rankings
        if (!playerSeasonAnalysisInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'playerSeasonAnalysis.label', default: 'PlayerSeasonAnalysis'), id])
            redirect(action: "list")
            return
        }

        [playerSeasonAnalysisInstance: playerSeasonAnalysisInstance]
    }

    def edit(Long id) {
        def playerSeasonAnalysisInstance = PlayerSeasonAnalysis.get(id)
        if (!playerSeasonAnalysisInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'playerSeasonAnalysis.label', default: 'PlayerSeasonAnalysis'), id])
            redirect(action: "list")
            return
        }

        [playerSeasonAnalysisInstance: playerSeasonAnalysisInstance]
    }

    def update(Long id, Long version) {
        def playerSeasonAnalysisInstance = PlayerSeasonAnalysis.get(id)
        if (!playerSeasonAnalysisInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'playerSeasonAnalysis.label', default: 'PlayerSeasonAnalysis'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (playerSeasonAnalysisInstance.version > version) {
                playerSeasonAnalysisInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'playerSeasonAnalysis.label', default: 'PlayerSeasonAnalysis')] as Object[],
                          "Another user has updated this PlayerSeasonAnalysis while you were editing")
                render(view: "edit", model: [playerSeasonAnalysisInstance: playerSeasonAnalysisInstance])
                return
            }
        }

        playerSeasonAnalysisInstance.properties = params

        if (!playerSeasonAnalysisInstance.save(flush: true)) {
            render(view: "edit", model: [playerSeasonAnalysisInstance: playerSeasonAnalysisInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'playerSeasonAnalysis.label', default: 'PlayerSeasonAnalysis'), playerSeasonAnalysisInstance.id])
        redirect(action: "show", id: playerSeasonAnalysisInstance.id)
    }

    def delete(Long id) {
        def playerSeasonAnalysisInstance = PlayerSeasonAnalysis.get(id)
        if (!playerSeasonAnalysisInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'playerSeasonAnalysis.label', default: 'PlayerSeasonAnalysis'), id])
            redirect(action: "list")
            return
        }

        try {
            playerSeasonAnalysisInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'playerSeasonAnalysis.label', default: 'PlayerSeasonAnalysis'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'playerSeasonAnalysis.label', default: 'PlayerSeasonAnalysis'), id])
            redirect(action: "show", id: id)
        }
    }
}
