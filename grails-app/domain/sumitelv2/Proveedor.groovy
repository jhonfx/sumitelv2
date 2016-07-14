package sumitelv2

class Proveedor implements Serializable{

    String nombreProveedor
    Boolean estatusProveedor
    Date fechaCreacion = new Date()
    String usuarioCreacion
    Date fechaModificacion
    String usuarioModificacion


    static mapping = {
      table 'sumitel_proveedor'
      id column: 'id_proveedor'
    }
    
    static constraints = {
      fechaCreacion nullable: true, display: false
      usuarioCreacion nullable: true, display: false
      fechaModificacion nullable: true, display: false
      usuarioModificacion nullable: true, display: false
    }

    String toString() {
      nombreProveedor
    }

}