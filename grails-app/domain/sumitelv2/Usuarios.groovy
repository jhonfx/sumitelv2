package sumitelv2

class Usuarios implements Serializable {

    String nombre
    String apellido
    String login
    String password
    String usuarioCreacion
    Date fechaCreacion = new Date()
    String usuarioModificacion
    Date fechaModificacion

    static belongsTo = [rol:Rol]

    static mapping = {
      id column: 'id_usuario'
      rol column: 'id_rol'
    }
    
    static constraints = {
      login(unique: true)
      password(password: true)
      nombre()
      apellido nullable: true, display: false
      fechaCreacion nullable: true, display: false
      usuarioCreacion nullable: true, display: false
      fechaModificacion nullable: true, display: false
      usuarioModificacion nullable: true, display: false
    }

    String toString() {
      nombre
    }
}