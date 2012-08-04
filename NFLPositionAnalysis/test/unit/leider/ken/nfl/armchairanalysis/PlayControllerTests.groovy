package leider.ken.nfl.armchairanalysis



import org.junit.*
import grails.test.mixin.*

@TestFor(PlayController)
@Mock(Play)
class PlayControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/play/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.playInstanceList.size() == 0
        assert model.playInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.playInstance != null
    }

    void testSave() {
        controller.save()

        assert model.playInstance != null
        assert view == '/play/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/play/show/1'
        assert controller.flash.message != null
        assert Play.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/play/list'

        populateValidParams(params)
        def play = new Play(params)

        assert play.save() != null

        params.id = play.id

        def model = controller.show()

        assert model.playInstance == play
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/play/list'

        populateValidParams(params)
        def play = new Play(params)

        assert play.save() != null

        params.id = play.id

        def model = controller.edit()

        assert model.playInstance == play
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/play/list'

        response.reset()

        populateValidParams(params)
        def play = new Play(params)

        assert play.save() != null

        // test invalid parameters in update
        params.id = play.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/play/edit"
        assert model.playInstance != null

        play.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/play/show/$play.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        play.clearErrors()

        populateValidParams(params)
        params.id = play.id
        params.version = -1
        controller.update()

        assert view == "/play/edit"
        assert model.playInstance != null
        assert model.playInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/play/list'

        response.reset()

        populateValidParams(params)
        def play = new Play(params)

        assert play.save() != null
        assert Play.count() == 1

        params.id = play.id

        controller.delete()

        assert Play.count() == 0
        assert Play.get(play.id) == null
        assert response.redirectedUrl == '/play/list'
    }
}
