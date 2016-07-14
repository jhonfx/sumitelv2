package sumitelv2

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ProveedorController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Proveedor.list(params), model:[proveedorCount: Proveedor.count()]
    }

    def show(Proveedor proveedor) {
        respond proveedor
    }

    def create() {
        respond new Proveedor(params)
    }

    @Transactional
    def save(Proveedor proveedor) {
        if (proveedor == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (proveedor.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond proveedor.errors, view:'create'
            return
        }

        proveedor.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), proveedor.id])
                redirect proveedor
            }
            '*' { respond proveedor, [status: CREATED] }
        }
    }

    def edit(Proveedor proveedor) {
        respond proveedor
    }

    @Transactional
    def update(Proveedor proveedor) {
        if (proveedor == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (proveedor.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond proveedor.errors, view:'edit'
            return
        }

        proveedor.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), proveedor.id])
                redirect proveedor
            }
            '*'{ respond proveedor, [status: OK] }
        }
    }

    @Transactional
    def delete(Proveedor proveedor) {

        if (proveedor == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        proveedor.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), proveedor.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
