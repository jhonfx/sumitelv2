package sumitelv2

class Menu implements Serializable {

    String descripcionMenu;

    static mapping = {
      table 'sumitel_menu'
      id column: 'id_menu'
    }
    
    static constraints = {
    }
}