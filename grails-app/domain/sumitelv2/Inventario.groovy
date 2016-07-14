package sumitelv2

class Inventario implements Serializable{

    String descripcionInventario

    static mapping = {
      table 'sumitel_inventario'
      id column: 'id_inventario'
    }

    static constraints = {
    }

    String toString() {
      descripcionInventario
    }

}