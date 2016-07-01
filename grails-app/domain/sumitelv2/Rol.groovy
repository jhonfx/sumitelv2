package sumitelv2

class Rol implements Serializable {

    String tipoRol;
    Character caracterRol;

    static mapping = {
      table 'sumitel_rol'
      id column : 'id_rol'
    }

    static constraints = {
    }

    String toString() {
      tipoRol
    }
}