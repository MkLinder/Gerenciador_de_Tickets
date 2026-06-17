package service;

import model.Usuario;
import persistence.UsuariosDAO;

import java.util.List;

public class LoginUsuarioService {

    private UsuariosDAO usuariosDAO =
            new UsuariosDAO();

    public Usuario autenticar(String email, String senha) {

        return usuariosDAO.autenticar(
                email,
                senha
        );
    }
}
