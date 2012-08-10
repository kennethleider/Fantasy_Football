package leider.ken.nfl.stats.league.analysis



import org.junit.*
import grails.test.mixin.*

@TestFor(PlayerSeasonAnalysisController)
@Mock(PlayerSeasonAnalysis)
class PlayerSeasonAnalysisControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/playerSeasonAnalysis/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.playerSeasonAnalysisInstanceList.size() == 0
        assert model.playerSeasonAnalysisInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.playerSeasonAnalysisInstance != null
    }

    void testSave() {
        controller.save()

        assert model.playerSeasonAnalysisInstance != null
        assert view == '/playerSeasonAnalysis/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/playerSeasonAnalysis/show/1'
        assert controller.flash.message != null
        assert PlayerSeasonAnalysis.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/playerSeasonAnalysis/list'

        populateValidParams(params)
        def playerSeasonAnalysis = new PlayerSeasonAnalysis(params)

        assert playerSeasonAnalysis.save() != null

        params.id = playerSeasonAnalysis.id

        def model = controller.show()

        assert model.playerSeasonAnalysisInstance == playerSeasonAnalysis
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/playerSeasonAnalysis/list'

        populateValidParams(params)
        def playerSeasonAnalysis = new PlayerSeasonAnalysis(params)

        assert playerSeasonAnalysis.save() != null

        params.id = playerSeasonAnalysis.id

        def model = controller.edit()

        assert model.playerSeasonAnalysisInstance == playerSeasonAnalysis
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/playerSeasonAnalysis/list'

        response.reset()

        populateValidParams(params)
        def playerSeasonAnalysis = new PlayerSeasonAnalysis(params)

        assert playerSeasonAnalysis.save() != null

        // test invalid parameters in update
        params.id = playerSeasonAnalysis.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/playerSeasonAnalysis/edit"
        assert model.playerSeasonAnalysisInstance != null

        playerSeasonAnalysis.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/playerSeasonAnalysis/show/$playerSeasonAnalysis.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        playerSeasonAnalysis.clearErrors()

        populateValidParams(params)
        params.id = playerSeasonAnalysis.id
        params.version = -1
        controller.update()

        assert view == "/playerSeasonAnalysis/edit"
        assert model.playerSeasonAnalysisInstance != null
        assert model.playerSeasonAnalysisInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/playerSeasonAnalysis/list'

        response.reset()

        populateValidParams(params)
        def playerSeasonAnalysis = new PlayerSeasonAnalysis(params)

        assert playerSeasonAnalysis.save() != null
        assert PlayerSeasonAnalysis.count() == 1

        params.id = playerSeasonAnalysis.id

        controller.delete()

        assert PlayerSeasonAnalysis.count() == 0
        assert PlayerSeasonAnalysis.get(playerSeasonAnalysis.id) == null
        assert response.redirectedUrl == '/playerSeasonAnalysis/list'
    }
}
