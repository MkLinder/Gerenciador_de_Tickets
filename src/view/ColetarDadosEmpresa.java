package view;

import utils.LeitorDadosEmpresa;
import model.Empresa;

import java.util.Scanner;

public class ColetarDadosEmpresa {

    public Empresa coletarDadosEmpresa(){

        Scanner scanner = new Scanner(System.in);

        String cnpj =
                LeitorDadosEmpresa.lerCnpjCadastroEmpresa(scanner);

        String nome =
                LeitorDadosEmpresa.lerNomeEmpresa(scanner);

        String endereco =
                LeitorDadosEmpresa.lerEnderecoEmpresa(scanner);

        return new Empresa(
                cnpj,
                nome,
                endereco
        );
    }
}
