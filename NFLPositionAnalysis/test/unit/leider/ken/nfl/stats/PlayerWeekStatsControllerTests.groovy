package leider.ken.nfl.stats



import org.junit.*
import grails.test.mixin.*

@TestFor(PlayerWeekStatsController)
@Mock(PlayerWeekStats)
class PlayerWeekStatsControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/playerWeekStats/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.playerWeekStatsInstanceList.size() == 0
        assert model.playerWeekStatsInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.playerWeekStatsInstance != null
    }

    void testSave() {
        controller.save()

        assert model.playerWeekStatsInstance != null
        assert view == '/playerWeekStats/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/playerWeekStats/show/1'
        assert controller.flash.message != null
        assert PlayerWeekStats.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/playerWeekStats/list'

        populateValidParams(params)
        def playerWeekStats = new PlayerWeekStats(params)

        assert playerWeekStats.save() != null

        params.id = playerWeekStats.id

        def model = controller.show()

        assert model.playerWeekStatsInstance == playerWeekStats
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/playerWeekStats/list'

        populateValidParams(params)
        def playerWeekStats = new PlayerWeekStats(params)

        assert playerWeekStats.save() != null

        params.id = playerWeekStats.id

        def model = controller.edit()

        assert model.playerWeekStatsInstance == playerWeekStats
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/playerWeekStats/list'

        response.reset()

        populateValidParams(params)
        def playerWeekStats = new PlayerWeekStats(params)

        assert playerWeekStats.save() != null

        // test invalid parameters in update
        params.id = playerWeekStats.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/playerWeekStats/edit"
        assert model.playerWeekStatsInstance != null

        playerWeekStats.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/playerWeekStats/show/$playerWeekStats.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        playerWeekStats.clearErrors()

        populateValidParams(params)
        params.id = playerWeekStats.id
        params.version = -1
        controller.update()

        assert view == "/playerWeekStats/edit"
        assert model.playerWeekStatsInstance != null
        assert model.playerWeekStatsInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/playerWeekStats/list'

        response.reset()

        populateValidParams(params)
        def playerWeekStats = new PlayerWeekStats(params)

        assert playerWeekStats.save() != null
        assert PlayerWeekStats.count() == 1

        params.id = playerWeekStats.id

        controller.delete()

        assert PlayerWeekStats.count() == 0
        assert PlayerWeekStats.get(playerWeekStats.id) == null
        assert response.redirectedUrl == '/playerWeekStats/list'
    }
}
