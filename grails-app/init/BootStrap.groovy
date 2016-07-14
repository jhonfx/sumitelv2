import sumitelv2.Usuarios
import sumitelv2.User
import sumitelv2.Menu

class BootStrap {

    def init = { servletContext ->
      def user = new User(login: 'user', password: 'user', name: 'user jhon').save(flush: true)
      def admin = new User(login: 'admin', password: 'user', name: 'admin jhon').save(flush: true)

      def user2 = new Usuarios(login: 'user', password: 'user', name: 'user jhon').save(flush: true)
      def admin2 = new Usuarios(login: 'admin', password: 'user', name: 'admin jhon').save(flush: true)

      def menu = new Menu(descripcionMenu: 'Compras').save(flush: true)
    }

    def destroy = {
    }
}
