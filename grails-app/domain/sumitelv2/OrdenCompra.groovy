package sumitelv2

class OrdenCompra implements Serializable{

    Date fechaCompra
    Long numeroFactura
    Long folioFicha
    Long numeroTelefono
    double precioSub
    double precioPublico
    double costoUnitario
    Date fechaEntrega
    String estatus

    Proveedor proveedor
    Articulo articulo
    Inventario inventario

    static mapping = {
      table 'sumitel_orden_compra'
      id column: 'id_orden_compra'
      proveedor column : 'id_proveedor'
      articulo column: 'id_articulo'
      inventario column: 'id_inventario'
    }

    static constraints = {
      folioFicha nullable: true, blank: true
      numeroFactura nullable: true, blank: true
      numeroTelefono nullable: true, blank: true
      precioSub nullable: true, blank: true
      precioPublico nullable: true, blank: true
      costoUnitario nullable: true, blank: true
      estatus nullable: true, blank: true
    }
}