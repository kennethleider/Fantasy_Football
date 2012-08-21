package leider.ken.nfl.stats.league



import org.junit.*
import grails.test.mixin.*

@TestFor(SeasonScoreController)
@Mock(SeasonScore)
class SeasonScoreControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/seasonScore/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.seasonScoreInstanceList.size() == 0
        assert model.seasonScoreInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.seasonScoreInstance != null
    }

    void testSave() {
        controller.save()

        assert model.seasonScoreInstance != null
        assert view == '/seasonScore/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/seasonScore/show/1'
        assert controller.flash.message != null
        assert SeasonScore.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/seasonScore/list'

        populateValidParams(params)
        def seasonScore = new SeasonScore(params)

        assert seasonScore.save() != null

        params.id = seasonScore.id

        def model = controller.show()

        assert model.seasonScoreInstance == seasonScore
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/seasonScore/list'

        populateValidParams(params)
        def seasonScore = new SeasonScore(params)

        assert seasonScore.save() != null

        params.id = seasonScore.id

        def model = controller.edit()

        assert model.seasonScoreInstance == seasonScore
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/seasonScore/list'

        response.reset()

        populateValidParams(params)
        def seasonScore = new SeasonScore(params)

        assert seasonScore.save() != null

        // test invalid parameters in update
        params.id = seasonScore.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/seasonScore/edit"
        assert model.seasonScoreInstance != null

        seasonScore.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/seasonScore/show/$seasonScore.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        seasonScore.clearErrors()

        populateValidParams(params)
        params.id = seasonScore.id
        params.version = -1
        controller.update()

        assert view == "/seasonScore/edit"
        assert model.seasonScoreInstance != null
        assert model.seasonScoreInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/seasonScore/list'

        response.reset()

        populateValidParams(params)
        def seasonScore = new SeasonScore(params)

        assert seasonScore.save() != null
        assert SeasonScore.count() == 1

        params.id = seasonScore.id

        controller.delete()

        assert SeasonScore.count() == 0
        assert SeasonScore.get(seasonScore.id) == null
        assert response.redirectedUrl == '/seasonScore/list'
    }
}
