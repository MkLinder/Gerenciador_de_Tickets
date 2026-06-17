package utils;

import persistence.UsuariosDAO;
import validation.UsuariosValidadorEntrada;

import java.util.Scanner;

public class LeitorDadosUsuario {

    public static String lerNome(Scanner scanner) {

        String nome;

        do {

            System.out.print("Digite seu nome completo: ");

            nome = scanner.nextLine();

            if (!UsuariosValidadorEntrada.validarNome(nome)) {

                System.out.println(
                        "Nome inválido. Informe nome e sobrenome."
                );
            }

        } while (!UsuariosValidadorEntrada.validarNome(nome));

        return nome;
    }

    public static String lerCpf(Scanner scanner, UsuariosDAO usuariosDAO) {

        String cpf;

        do {

            System.out.print(
                    "Digite seu CPF [xxx.xxx.xxx-xx]: "
            );

            cpf = scanner.nextLine();

            if (!UsuariosValidadorEntrada.validarCpf(cpf)) {

                System.out.println(
                        "CPF inválido."
                );

            } else if (usuariosDAO.cpfExiste(cpf)) {

                System.out.println(
                        "CPF já cadastrado."
                );

                cpf = "";
            }

        } while (cpf.isEmpty());

        return cpf;
    }

    public static String lerTelefone(Scanner scanner) {

        String telefone;

        do {

            System.out.print(
                    "Digite seu telefone [(xx) xxxxx-xxxx]: "
            );

            telefone = scanner.nextLine();

            if (!UsuariosValidadorEntrada.validarTelefone(telefone)) {

                System.out.println(
                        "Telefone inválido."
                );
            }

        } while (!UsuariosValidadorEntrada.validarTelefone(telefone));

        return telefone;
    }

    public static String lerEmail(Scanner scanner, UsuariosDAO usuariosDAO) {

        String email;

        do {

            System.out.print("Digite seu email: ");

            email = scanner.nextLine();

            if (!UsuariosValidadorEntrada.validarEmail(email)) {

                System.out.println(
                        "Email inválido."
                );

                email = "";

            } else if (usuariosDAO.emailExiste(email)) {

                System.out.println(
                        "Email já cadastrado."
                );

                email = "";
            }

        } while (email.isEmpty());

        return email;
    }

    public static String lerEmailLogin(Scanner scanner) {

        while (true) {

            System.out.print("Digite seu email: ");

            String email = scanner.nextLine();

            if (email.equalsIgnoreCase("admin")) {
                return email;
            }

            if (UsuariosValidadorEntrada.validarEmail(email)) {
                return email;
            }

            System.out.println("Email inválido.");
        }
    }

    public static String lerSenha(Scanner scanner) {

        String senha;

        do {

            System.out.print(
                    "Crie uma senha com 6 números: "
            );

            senha = scanner.nextLine();

            if (!UsuariosValidadorEntrada.validarSenha(senha)) {

                System.out.println(
                        "Senha inválida."
                );
            }

        } while (!UsuariosValidadorEntrada.validarSenha(senha));

        return senha;
    }

    public static String lerSenhaLogin(Scanner scanner) {

        System.out.print("Digite sua senha: ");

        return scanner.nextLine();
    }

    public static String lerEndereco(Scanner scanner) {

        String endereco;

        do {

            System.out.print(
                    "Digite seu endereço [Rua/Av, nº - Bairro, Cidade - UF]: "
            );

            endereco = scanner.nextLine();

            if (!UsuariosValidadorEntrada.validarEndereco(endereco)) {

                System.out.println(
                        "Endereço inválido."
                );
            }

        } while (!UsuariosValidadorEntrada.validarEndereco(endereco));

        return endereco;
    }

}