package leider.ken.nfl.armchairanalysis



import org.junit.*
import grails.test.mixin.*

@TestFor(ArmchairPlayerRefController)
@Mock(ArmchairPlayerRef)
class ArmchairPlayerRefControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/armchairPlayerRef/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.armchairPlayerRefInstanceList.size() == 0
        assert model.armchairPlayerRefInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.armchairPlayerRefInstance != null
    }

    void testSave() {
        controller.save()

        assert model.armchairPlayerRefInstance != null
        assert view == '/armchairPlayerRef/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/armchairPlayerRef/show/1'
        assert controller.flash.message != null
        assert ArmchairPlayerRef.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/armchairPlayerRef/list'

        populateValidParams(params)
        def armchairPlayerRef = new ArmchairPlayerRef(params)

        assert armchairPlayerRef.save() != null

        params.id = armchairPlayerRef.id

        def model = controller.show()

        assert model.armchairPlayerRefInstance == armchairPlayerRef
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/armchairPlayerRef/list'

        populateValidParams(params)
        def armchairPlayerRef = new ArmchairPlayerRef(params)

        assert armchairPlayerRef.save() != null

        params.id = armchairPlayerRef.id

        def model = controller.edit()

        assert model.armchairPlayerRefInstance == armchairPlayerRef
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/armchairPlayerRef/list'

        response.reset()

        populateValidParams(params)
        def armchairPlayerRef = new ArmchairPlayerRef(params)

        assert armchairPlayerRef.save() != null

        // test invalid parameters in update
        params.id = armchairPlayerRef.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/armchairPlayerRef/edit"
        assert model.armchairPlayerRefInstance != null

        armchairPlayerRef.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/armchairPlayerRef/show/$armchairPlayerRef.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        armchairPlayerRef.clearErrors()

        populateValidParams(params)
        params.id = armchairPlayerRef.id
        params.version = -1
        controller.update()

        assert view == "/armchairPlayerRef/edit"
        assert model.armchairPlayerRefInstance != null
        assert model.armchairPlayerRefInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/armchairPlayerRef/list'

        response.reset()

        populateValidParams(params)
        def armchairPlayerRef = new ArmchairPlayerRef(params)

        assert armchairPlayerRef.save() != null
        assert ArmchairPlayerRef.count() == 1

        params.id = armchairPlayerRef.id

        controller.delete()

        assert ArmchairPlayerRef.count() == 0
        assert ArmchairPlayerRef.get(armchairPlayerRef.id) == null
        assert response.redirectedUrl == '/armchairPlayerRef/list'
    }
}
