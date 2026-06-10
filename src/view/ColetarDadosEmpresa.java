package view;

import model.Empresa;

import java.util.Scanner;

public class ColetarDadosEmpresa {

    public Empresa coletarDadosEmpresa(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o cnpj da empresa [xx.xxx.xxx/xxxx-xx]: ");
        String cnpj = scanner.nextLine();

        System.out.print("Digite o nome da empresa: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o endereço da empresa [Rua|Trv|Av, nº - Bairro, cidade - Sigla Estado]: ");
        String endereco = scanner.nextLine();

        return new Empresa(cnpj, nome, endereco);
    }
}
