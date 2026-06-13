package service;
import model.Usuario;
import persistence.UsuariosDAO;

import java.util.List;

public class LoginUsuarioService {
    private UsuariosDAO usuariosDAO = new UsuariosDAO();

    public Usuario autenticar(String email, String senha) {

        List<Usuario> usuarios = usuariosDAO.listarUsuarios();

        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email) && u.getSenha().equals(senha)) {
                return u;
            }
        }
        return null;
    }
}
