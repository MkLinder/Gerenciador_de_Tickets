package utils;

import persistence.EmpresasDAO;
import validation.EmpresaValidadorEntrada;

import java.util.Scanner;

public class LeitorDadosEmpresa {

    public static String lerCnpjCadastroEmpresa(Scanner scanner) {

        String cnpj;

        do {

            System.out.print(
                    "Digite o CNPJ [xx.xxx.xxx/xxxx-xx]: "
            );

            cnpj = scanner.nextLine();

            if (!EmpresaValidadorEntrada.validarCnpj(cnpj)) {

                System.out.println(
                        "CNPJ inválido. Utilize o formato xx.xxx.xxx/xxxx-xx."
                );
            }

        } while (!EmpresaValidadorEntrada.validarCnpj(cnpj));

        return cnpj;
    }

    public static String lerCnpjCadastroCliente(Scanner scanner, EmpresasDAO empresasDAO) {

        String cnpj;

        do {

            System.out.print(
                    "Digite o cnpj da empresa [xx.xxx.xxx/xxxx-xx]: "
            );

            cnpj = scanner.nextLine();

            if (!EmpresaValidadorEntrada.validarCnpj(cnpj)) {

                System.out.println(
                        "CNPJ inválido."
                );

                cnpj = "";

            } else if (!empresasDAO.cnpjExiste(cnpj)) {

                System.out.println(
                        "Empresa não cadastrada."
                );

                cnpj = "";
            }

        } while (cnpj.isEmpty());

        return cnpj;
    }

    public static String lerNomeEmpresa(Scanner scanner) {

        String nome;

        do {

            System.out.print(
                    "Digite o nome da empresa: "
            );

            nome = scanner.nextLine();

            if (!EmpresaValidadorEntrada.validarNome(nome)) {

                System.out.println(
                        "Nome da empresa inválido."
                );
            }

        } while (!EmpresaValidadorEntrada.validarNome(nome));

        return nome;
    }

    public static String lerEnderecoEmpresa(Scanner scanner) {

        String endereco;

        do {

            System.out.print(
                    "Digite o endereço da empresa: "
            );

            endereco = scanner.nextLine();

            if (!EmpresaValidadorEntrada.validarEndereco(endereco)) {

                System.out.println(
                        "Endereço inválido."
                );
            }

        } while (!EmpresaValidadorEntrada.validarEndereco(endereco));

        return endereco;
    }
}
