package sumitelv2

class Proveedor {

    String nombreProveedor
    Boolean estatusProveedor


    static mapping = {
      table 'sumitel_proveedor'
      id column: 'id_proveedor'
    }
    
    static constraints = {
    }

    String toString() {
      nombreProveedor
    }

}