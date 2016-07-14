package sumitelv2

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.converters.JSON
import sumitelv2.TestSave

@Transactional(readOnly = false)
class ArticuloController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    /*
        Alta masiva de articulos 
    */
    def altamasiva() {
        def name = 'juan'
        render(view: 'altamasiva', model: [name: name])
    }

    def list() {
        def inventario = Inventario.findAll()
        return [inventario: inventario]
    }

    def saveDataTest = {

        log.debug("ya debuggea???")
        log.debug(params);

        def tuplas_articulos = JSON.parse(params.tuplas)
        log.debug "Total de tuplas a guardar" + tuplas_articulos.size()

        try {
          tuplas_articulos.each{tupla->
            TestSave testSave = new TestSave();
            testSave.factura = new String(tupla.factura)
            testSave.serie = new String(tupla.series)
            testSave.descripcion = new String(tupla.producto)
            testSave.save()
            log.debug("id de objetos que se estan guardando "+ testSave.id)
          
          }
        }
        catch(Exception e) {
          log.debug(e)
        }
        
        //render params as JSON
        log.debug("vamonos a la gsp ver articulos")
        render(view: "index")
        
    }

    def verarticulos = { }

    def obtenerArticulos = {
        StringBuilder sql = new StringBuilder()
        sql.append(" SELECT m from TestSave m");
        log.debug(sql)
        def resultSQL = TestSave.executeQuery(sql.toString())
        log.debug(resultSQL.toString())

        def tuplasJson = resultSQL.collect {
          tuplas: [
            factura: it.factura,
            serie: it.serie,
            descripcion: it.descripcion
          ]
        }
        log.debug(tuplasJson)
        def jsonData = [rows: tuplasJson]
        render jsonData as JSON
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
