package sumitelv2

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class OrdenCompraController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond OrdenCompra.list(params), model:[ordenCompraCount: OrdenCompra.count()]
    }

    def show(OrdenCompra ordenCompra) {
        respond ordenCompra
    }

    def create() {
        respond new OrdenCompra(params)
    }

    @Transactional
    def save(OrdenCompra ordenCompra) {
        if (ordenCompra == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (ordenCompra.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond ordenCompra.errors, view:'create'
            return
        }

        ordenCompra.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'ordenCompra.label', default: 'OrdenCompra'), ordenCompra.id])
                redirect ordenCompra
            }
            '*' { respond ordenCompra, [status: CREATED] }
        }
    }

    def edit(OrdenCompra ordenCompra) {
        respond ordenCompra
    }

    @Transactional
    def update(OrdenCompra ordenCompra) {
        if (ordenCompra == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (ordenCompra.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond ordenCompra.errors, view:'edit'
            return
        }

        ordenCompra.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ordenCompra.label', default: 'OrdenCompra'), ordenCompra.id])
                redirect ordenCompra
            }
            '*'{ respond ordenCompra, [status: OK] }
        }
    }

    @Transactional
    def delete(OrdenCompra ordenCompra) {

        if (ordenCompra == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        ordenCompra.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ordenCompra.label', default: 'OrdenCompra'), ordenCompra.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'ordenCompra.label', default: 'OrdenCompra'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
