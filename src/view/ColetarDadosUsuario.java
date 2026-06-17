package view;

import java.util.Scanner;

import persistence.EmpresasDAO;
import utils.LeitorDadosEmpresa;
import utils.LeitorDadosUsuario;
import enums.Departamento;

import model.Cliente;
import model.Colaborador;
import persistence.UsuariosDAO;

public class ColetarDadosUsuario {

    public Cliente coletarDadosCliente(){
        Scanner scanner = new Scanner(System.in);
        UsuariosDAO usuariosDAO = new UsuariosDAO();
        EmpresasDAO empresasDAO = new EmpresasDAO();

        String nome =
                LeitorDadosUsuario.lerNome(scanner);

        String cpf =
                LeitorDadosUsuario.lerCpf(
                        scanner,
                        usuariosDAO
                );

        String telefone =
                LeitorDadosUsuario.lerTelefone(scanner);

        String cnpj =
                LeitorDadosEmpresa.lerCnpjCadastroCliente(
                        scanner,
                        empresasDAO
                );

        String email =
                LeitorDadosUsuario.lerEmail(
                        scanner,
                        usuariosDAO
                );

        String senha =
                LeitorDadosUsuario.lerSenha(scanner);

        return new Cliente(nome, cpf, telefone, cnpj, email, senha);
    }

    public Colaborador coletarDadosColaborador(Departamento departamento) {

        Scanner scanner = new Scanner(System.in);
        UsuariosDAO usuariosDAO = new UsuariosDAO();

        String nome =
                LeitorDadosUsuario.lerNome(scanner);

        String cpf =
                LeitorDadosUsuario.lerCpf(
                        scanner,
                        usuariosDAO
                );

        String telefone =
                LeitorDadosUsuario.lerTelefone(scanner);

        String endereco =
                LeitorDadosUsuario.lerEndereco(scanner);

        String email =
                LeitorDadosUsuario.lerEmail(
                        scanner,
                        usuariosDAO
                );

        String senha =
                LeitorDadosUsuario.lerSenha(scanner);

        return new Colaborador(
                nome,
                cpf,
                telefone,
                endereco,
                email,
                senha,
                departamento
        );
    }
}
