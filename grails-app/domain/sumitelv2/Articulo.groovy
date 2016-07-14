package sumitelv2

class Articulo {

    String descripcionArticulo
    Long folioArticulo
    String tipoArticulo
    Long numeroTelefono
    double costoSub
    double costoPublico
    double costoUnitario

    static belongsTo = [inventario:Inventario]

    static mapping = {
      table 'sumitel_articulo'
      id column: 'id_articulo'
    }

    static constraints = {
      folioArticulo nullable: true, blank: true
      numeroTelefono nullable: true, blank: true

    }

    String toString() {
      descripcionArticulo
    }
}
