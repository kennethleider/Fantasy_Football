package leider.ken.nfl.fantasy.analysis



import org.junit.*
import grails.test.mixin.*

@TestFor(PointsAgainstController)
@Mock(PointsAgainst)
class PointsAgainstControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/pointsAgainst/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.pointsAgainstInstanceList.size() == 0
        assert model.pointsAgainstInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.pointsAgainstInstance != null
    }

    void testSave() {
        controller.save()

        assert model.pointsAgainstInstance != null
        assert view == '/pointsAgainst/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/pointsAgainst/show/1'
        assert controller.flash.message != null
        assert PointsAgainst.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/pointsAgainst/list'

        populateValidParams(params)
        def pointsAgainst = new PointsAgainst(params)

        assert pointsAgainst.save() != null

        params.id = pointsAgainst.id

        def model = controller.show()

        assert model.pointsAgainstInstance == pointsAgainst
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/pointsAgainst/list'

        populateValidParams(params)
        def pointsAgainst = new PointsAgainst(params)

        assert pointsAgainst.save() != null

        params.id = pointsAgainst.id

        def model = controller.edit()

        assert model.pointsAgainstInstance == pointsAgainst
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/pointsAgainst/list'

        response.reset()

        populateValidParams(params)
        def pointsAgainst = new PointsAgainst(params)

        assert pointsAgainst.save() != null

        // test invalid parameters in update
        params.id = pointsAgainst.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/pointsAgainst/edit"
        assert model.pointsAgainstInstance != null

        pointsAgainst.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/pointsAgainst/show/$pointsAgainst.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        pointsAgainst.clearErrors()

        populateValidParams(params)
        params.id = pointsAgainst.id
        params.version = -1
        controller.update()

        assert view == "/pointsAgainst/edit"
        assert model.pointsAgainstInstance != null
        assert model.pointsAgainstInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/pointsAgainst/list'

        response.reset()

        populateValidParams(params)
        def pointsAgainst = new PointsAgainst(params)

        assert pointsAgainst.save() != null
        assert PointsAgainst.count() == 1

        params.id = pointsAgainst.id

        controller.delete()

        assert PointsAgainst.count() == 0
        assert PointsAgainst.get(pointsAgainst.id) == null
        assert response.redirectedUrl == '/pointsAgainst/list'
    }
}
