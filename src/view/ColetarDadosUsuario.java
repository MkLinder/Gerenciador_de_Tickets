package view;

import java.util.Scanner;

import enums.Departamento;

import model.Cliente;
import model.Colaborador;
import model.Usuario;


public class ColetarDadosUsuario {

    public Usuario coletarDadosCliente(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite seu nome completo: ");
        String nome = scanner.nextLine();

        System.out.print("Digite seu CPF [xxx.xxx.xxx-xx]: ");
        String cpf = scanner.nextLine();

        System.out.print("Digite seu telefone no formato (xx) xxxx-xxxx: ");
        String telefone = scanner.nextLine();

        System.out.print("Digite o cnpj da empresa [xx.xxx.xxx/xxxx-xx]: ");
        String cnpj = scanner.nextLine();

        System.out.print("Digite seu email: "); //Adicionar restrição para email único.
        String email = scanner.nextLine();

        System.out.print("Crie uma senha com 6 caracteres: ");
        String senha = scanner.nextLine();

        return new Cliente(nome, cpf, telefone, cnpj, email, senha);
    }

    public Usuario coletarDadosColaborador(Departamento departamento){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite seu nome completo: ");
        String nome = scanner.nextLine();

        System.out.print("Digite seu CPF [xxx.xxx.xxx-xx]: ");
        String cpf = scanner.nextLine();

        System.out.print("Digite seu telefone [(xx) xxxx-xxxx]: ");
        String telefone = scanner.nextLine();

        System.out.print("Digite seu endereço [rua/trv/av, nº - bairro, cidade - sigla estado]: ");
        String endereco = scanner.nextLine();

        System.out.print("Digite seu email: ");
        String email = scanner.nextLine();

        System.out.print("Crie uma senha com 6 caracteres: ");
        String senha = scanner.nextLine();

        return new Colaborador(nome, cpf, telefone, endereco, email, senha, departamento);
    }
}
