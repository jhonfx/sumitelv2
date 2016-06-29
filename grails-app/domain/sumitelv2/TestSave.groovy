package sumitelv2

class TestSave {

    String factura
    String serie
    String descripcion

    //static belongsTo = [inventario:Inventario]

    static mapping = {
      table 'sumitel_test_save'
      id column: 'id_orden_generada'
    }

}
