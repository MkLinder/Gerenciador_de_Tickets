package view;

import model.Cliente;
import model.Colaborador;
import model.Usuario;
import persistence.UsuariosBD;

import java.util.List;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);

    public void menu(){

        System.out.println("\nGERENCIADOR DE TICKETS");
        System.out.println("______________________________");
        System.out.println("[1] Login" +
                "\n[2] Cadastrar novo usuário ");
        System.out.println("------------------------------");
        System.out.print("Escolha uma opção: ");
        String loginCadastrar = scanner.nextLine();

        UsuariosBD usuariosBD = new UsuariosBD();

        if(loginCadastrar.equalsIgnoreCase("1")){
            System.out.println("Usuário Logado.");
            System.out.println("------------------------------");
            System.out.println("[1] Listar Usuários");
            System.out.println("[2] Listar Tickets");
            System.out.println("------------------------------");
            System.out.print("Escolha uma opção: ");

            String listarUsuarios = scanner.next();

            System.out.println("GERENCIADOR DE TICKETS");
            System.out.println("______________________________");

            if (listarUsuarios.equalsIgnoreCase("1")){
                List<Usuario> usuarios = usuariosBD.listar();

                for (Usuario u : usuarios) {
                    if(u instanceof Colaborador){
                        System.out.println(((Colaborador) u).getDepartamento() + " - " + u.getNome());
                    }else if(u instanceof Cliente){
                        System.out.println(((Cliente) u).getCategoria() + " - " + u.getNome());
                    }
                }
            }

        }else if(loginCadastrar.equalsIgnoreCase("2")){

            ColetarDadosUsuario novoUsuario = new ColetarDadosUsuario();

            System.out.println("CADASTRO DE USUÁRIO: ");
            System.out.println("------------------------------");
            System.out.println("[1] Sou Cliente" +
                    "\n[2] Sou Colaborador");
            System.out.println("------------------------------");
            System.out.print("Escolha uma opção: ");
            String opcaoUsuario = scanner.nextLine();

            if(opcaoUsuario.equalsIgnoreCase("1")){
                Usuario dadosUsuario = novoUsuario.coletarDadosCliente();
                UsuariosBD salvarUsuario = new UsuariosBD();
                salvarUsuario.salvarNovoUsuario(dadosUsuario);

            }else if(opcaoUsuario.equalsIgnoreCase("2")){
                System.out.println("------------------------------");
                System.out.println("[1] Departamento de Suporte" +
                        "\n[2] Departamento Financeiro");
                System.out.println("------------------------------");
                System.out.print("Escolha uma opção: ");
                String opcaoDepartamento = scanner.nextLine();

                if (opcaoDepartamento.equals("1")) {
                    opcaoDepartamento = "Suporte";
                } else {
                    opcaoDepartamento = "Financeiro";
                }

                Usuario dadosUsuario = novoUsuario.coletarDadosColaborador(opcaoDepartamento);
                UsuariosBD salvarUsuario = new UsuariosBD();
                salvarUsuario.salvarNovoUsuario(dadosUsuario);
            }
        }
    }
}
