package service;
import model.Usuario;
import persistence.UsuariosBD;
import java.util.List;

public class LoginUsuarioService {
    private UsuariosBD usuariosBD = new UsuariosBD();

    public Usuario autenticar(String email, String senha) {

        List<Usuario> usuarios = usuariosBD.listarUsuario();

        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email) && u.getSenha().equals(senha)) {
                return u;
            }
        }
        return null;
    }
}
