package validation;

public class EmpresaValidadorEntrada {

    public static boolean validarCnpj(String cnpj) {

        return cnpj.matches(
                "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}"
        );
    }

    public static boolean validarNome(String nome) {

        return nome != null
                && nome.trim().length() >= 3;
    }

    public static boolean validarEndereco(String endereco) {

        return endereco != null
                && endereco.trim().length() >= 10;
    }
}
