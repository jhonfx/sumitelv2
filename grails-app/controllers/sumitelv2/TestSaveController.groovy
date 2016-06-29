package sumitelv2

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TestSaveController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond TestSave.list(params), model:[testSaveCount: TestSave.count()]
    }

    def show(TestSave testSave) {
        respond testSave
    }

    def create() {
        respond new TestSave(params)
    }

    @Transactional
    def save(TestSave testSave) {
        if (testSave == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (testSave.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond testSave.errors, view:'create'
            return
        }

        testSave.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'testSave.label', default: 'TestSave'), testSave.id])
                redirect testSave
            }
            '*' { respond testSave, [status: CREATED] }
        }
    }

    def edit(TestSave testSave) {
        respond testSave
    }

    @Transactional
    def update(TestSave testSave) {
        if (testSave == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (testSave.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond testSave.errors, view:'edit'
            return
        }

        testSave.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'testSave.label', default: 'TestSave'), testSave.id])
                redirect testSave
            }
            '*'{ respond testSave, [status: OK] }
        }
    }

    @Transactional
    def delete(TestSave testSave) {

        if (testSave == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        testSave.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'testSave.label', default: 'TestSave'), testSave.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'testSave.label', default: 'TestSave'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
