package leider.ken.nfl



import org.junit.*
import grails.test.mixin.*

@TestFor(MatchupController)
@Mock(Matchup)
class MatchupControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/matchup/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.matchupInstanceList.size() == 0
        assert model.matchupInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.matchupInstance != null
    }

    void testSave() {
        controller.save()

        assert model.matchupInstance != null
        assert view == '/matchup/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/matchup/show/1'
        assert controller.flash.message != null
        assert Matchup.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/matchup/list'

        populateValidParams(params)
        def matchup = new Matchup(params)

        assert matchup.save() != null

        params.id = matchup.id

        def model = controller.show()

        assert model.matchupInstance == matchup
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/matchup/list'

        populateValidParams(params)
        def matchup = new Matchup(params)

        assert matchup.save() != null

        params.id = matchup.id

        def model = controller.edit()

        assert model.matchupInstance == matchup
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/matchup/list'

        response.reset()

        populateValidParams(params)
        def matchup = new Matchup(params)

        assert matchup.save() != null

        // test invalid parameters in update
        params.id = matchup.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/matchup/edit"
        assert model.matchupInstance != null

        matchup.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/matchup/show/$matchup.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        matchup.clearErrors()

        populateValidParams(params)
        params.id = matchup.id
        params.version = -1
        controller.update()

        assert view == "/matchup/edit"
        assert model.matchupInstance != null
        assert model.matchupInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/matchup/list'

        response.reset()

        populateValidParams(params)
        def matchup = new Matchup(params)

        assert matchup.save() != null
        assert Matchup.count() == 1

        params.id = matchup.id

        controller.delete()

        assert Matchup.count() == 0
        assert Matchup.get(matchup.id) == null
        assert response.redirectedUrl == '/matchup/list'
    }
}
