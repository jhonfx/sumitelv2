package sumitelv2

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = false)
class InventarioController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Inventario.list(params), model:[inventarioCount: Inventario.count()]
    }

    def show(Inventario inventario) {
        respond inventario
    }

    def create() {
        respond new Inventario(params)
    }

    def list() {
        log.debug "lista de inventario"
        List <Inventario> inventario = Inventario.getAll()
        return inventario
    }

    @Transactional
    def save(Inventario inventario) {
        if (inventario == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (inventario.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond inventario.errors, view:'create'
            return
        }

        inventario.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'inventario.label', default: 'Inventario'), inventario.id])
                redirect inventario
            }
            '*' { respond inventario, [status: CREATED] }
        }
    }

    def edit(Inventario inventario) {
        respond inventario
    }

    @Transactional
    def update(Inventario inventario) {
        if (inventario == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (inventario.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond inventario.errors, view:'edit'
            return
        }

        inventario.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'inventario.label', default: 'Inventario'), inventario.id])
                redirect inventario
            }
            '*'{ respond inventario, [status: OK] }
        }
    }

    @Transactional
    def delete(Inventario inventario) {

        if (inventario == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        inventario.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'inventario.label', default: 'Inventario'), inventario.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'inventario.label', default: 'Inventario'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
