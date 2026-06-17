package validation;

public class UsuariosValidadorEntrada {

    public static boolean validarNome(String nome) {

        if (nome == null || nome.trim().length() < 5) {
            return false;
        }

        return nome.trim().contains(" ");
    }

    public static boolean validarCpf(String cpf) {

        return cpf.matches(
                "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}"
        );
    }

    public static boolean validarTelefone(String telefone) {

        return telefone.matches(
                "\\(\\d{2}\\) \\d{4,5}-\\d{4}"
        );
    }

    public static boolean validarEmail(String email) {

        return email.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        );
    }

    public static boolean validarSenha(String senha) {

        return senha.matches("\\d{6}");
    }

    public static boolean validarEndereco(String endereco) {

        return endereco != null
                && endereco.trim().length() >= 10;
    }
}
