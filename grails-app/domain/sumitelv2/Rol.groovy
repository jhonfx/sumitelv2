package sumitelv2

class Rol implements Serializable {

    String tipoRol
    Character caracterRol
    String usuarioCreacion
    Date fechaCreacion = new Date()
    String usuarioModificacion
    Date fechaModificacion

    static mapping = {
      table 'sumitel_rol'
      id column : 'id_rol'
    }

    static constraints = {
      fechaCreacion nullable: true, display: false
      usuarioCreacion nullable: true, display: false
      fechaModificacion nullable: true, display: false
      usuarioModificacion nullable: true, display: false
    }

    String toString() {
      tipoRol
    }
}