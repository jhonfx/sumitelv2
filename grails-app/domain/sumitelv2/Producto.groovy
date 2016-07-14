package sumitelv2

class Producto implements Serializable {
    String descripcion
    double costoSub
    double costoUnitario
    double precioPublico
    Date fechaCreacion = new Date()
    String usuarioCreacion
    Date fechaModificacion
    String usuarioModificacion
    Boolean activo


    //static belongsTo = [inventario:Inventario]

    static mapping = {
      table 'sumitel_producto'
      id column: 'id_producto', sqlType: "mediumint"
    }

    static constraints = {
      fechaCreacion display: false
      usuarioCreacion display: false
      fechaModificacion nullable:true, display: false
      usuarioModificacion nullable: true, display: false

    }

    String toString() {
      descripcion
    }
}
