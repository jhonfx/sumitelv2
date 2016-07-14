package sumitelv2

class User implements Serializable {

    private static final long serialVersionUID = 1

    String login
    String password
    String name

    static constraints = {
      login(unique: true)
      password(password: true)
      name()
    }

    String toString() {
      name
    }
}