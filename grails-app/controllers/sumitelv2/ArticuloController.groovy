package sumitelv2

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.converters.JSON
import sumitelv2.TestSave

@Transactional(readOnly = true)
class ArticuloController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    /*
        Alta masiva de articulos 
    */
    def altamasiva() {
        render(view: 'altamasiva')
    }


    @Transactional
    def saveDataTest() {

        log.debug("ya debuggea???")
        log.debug(params);

        def tuplas_articulos = JSON.parse(params.tuplas)
        

        TestSave testSave = new TestSave();

        tuplas_articulos.each{
            testSave.factura = new String("${it.factura}")
            testSave.serie = new String("${it.series}")
            testSave.descripcion = new String("${it.producto}")
            /*log.debug("${it.factura}")
            log.debug("${it.series}")
            log.debug("${it.producto}")*/
        }

        /*['Cat', 'Dog', 'Elephant'].each {
            println "Animal ${it}"
        }*/


        /*println "ahora vamos a guardar un test"
        
        testSave.factura = new String("902016");
        testSave.serie = new String("0750100369688");
        testSave.descripcion = new String("FICHA AMIGO 100");*/

        testSave.save(flush: true)

        render params as JSON


        /*StringBuilder sql = new StringBuilder()
        sql.append("SELECT sts from sumitel_test_save sts");
        log.debug(sql)
        def resultSQL = TestSave.executeQuery(sql.toString())*/
        
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Articulo.list(params), model:[articuloCount: Articulo.count()]
    }

    def show(Articulo articulo) {
        respond articulo
    }

    def create() {
        respond new Articulo(params)
    }

    @Transactional
    def save(Articulo articulo) {
        if (articulo == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (articulo.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond articulo.errors, view:'create'
            return
        }

        articulo.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'articulo.label', default: 'Articulo'), articulo.id])
                redirect articulo
            }
            '*' { respond articulo, [status: CREATED] }
        }
    }

    def edit(Articulo articulo) {
        respond articulo
    }

    @Transactional
    def update(Articulo articulo) {
        if (articulo == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (articulo.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond articulo.errors, view:'edit'
            return
        }

        articulo.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'articulo.label', default: 'Articulo'), articulo.id])
                redirect articulo
            }
            '*'{ respond articulo, [status: OK] }
        }
    }

    @Transactional
    def delete(Articulo articulo) {

        if (articulo == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        articulo.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'articulo.label', default: 'Articulo'), articulo.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'articulo.label', default: 'Articulo'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
