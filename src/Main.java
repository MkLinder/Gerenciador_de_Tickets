import view.Menu;
import persistence.UsuariosDAO;

public class Main {

    public void main(String[] args) {

        UsuariosDAO usuarioDAO = new UsuariosDAO();
        usuarioDAO.inicializarAdmin();

        Menu menu = new Menu();
        menu.menu();
    }
}