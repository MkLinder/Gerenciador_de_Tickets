package view;

import model.Cliente;
import model.Colaborador;
import model.Usuario;
import java.util.Scanner;

public class ColetarDadosUsuario {

    public Usuario coletarDadosCliente(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite seu nome completo: ");
        String nome = scanner.nextLine();

        System.out.print("Digite seu CPF [xxx.xxx.xxx-xx]: ");
        String cpf = scanner.nextLine();

        System.out.print("Digite seu telefone no formato (xx) xxxx-xxxx: ");
        String telefone = scanner.nextLine();

        System.out.print("Digite seu endereço no formato rua/trv/av, nº - bairro, cidade - sigla estado: ");
        String endereco = scanner.nextLine();

        System.out.print("Digite seu email: ");
        String email = scanner.nextLine();

        System.out.print("Crie uma senha com 6 caracteres: ");
        String senha = scanner.nextLine();

        String categoria = "Cliente";

        return new Cliente(nome, cpf, telefone, endereco, email, senha, categoria);
    }

    public Usuario coletarDadosColaborador(String departamento){
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
