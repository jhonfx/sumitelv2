package sumitelv2

class Usuarios implements Serializable {

    String nombre;
    String apellido;
    String userName;
    String contraseniaUsuario;

    static belongsTo = [rol:Rol]

    static mapping = {
      table 'sumitel_usuarios'
      id column: 'id_usuario'
      rol column: 'id_rol'
    }
    
    static constraints = {
    }
}