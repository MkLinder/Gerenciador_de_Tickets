package view;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import enums.Departamento;

import enums.StatusTicket;
import enums.TipoServico;
import model.*;
import persistence.EmpresasBD;
import persistence.TicketsBD;
import persistence.UsuariosBD;
import service.LoginUsuarioService;


public class Menu {
    Scanner scanner = new Scanner(System.in);

    public void menu(){

        String loginCadastroUsuario;

        do {
            System.out.println("\nGERENCIADOR DE TICKETS");
            System.out.println("______________________________");
            System.out.println("[1] Login" +
                            "\n[0] Sair ");
            System.out.println("------------------------------");
            System.out.print("Escolha uma opção: ");
            loginCadastroUsuario = scanner.nextLine();

            UsuariosBD usuariosBD = new UsuariosBD();

            if(loginCadastroUsuario.equalsIgnoreCase("1")){

                System.out.println("------------------------------");
                System.out.print("Digite seu email: ");
                String email = scanner.nextLine();

                System.out.print("Digite sua senha: ");
                String senha = scanner.nextLine();

                LoginUsuarioService login = new LoginUsuarioService();
                Usuario usuarioAutenticado = login.autenticar(email, senha);

                if (usuarioAutenticado != null) {

                    if (usuarioAutenticado instanceof Cliente) {
                        String criarListarTicket;
                        System.out.println("______________________________");
                        System.out.println("\nBem-vindo(a) " + usuarioAutenticado.getNome());

                        do {
                            System.out.println("------------------------------");
                            System.out.println("[1] Criar Ticket" +
                                    "\n[2] Listar Tickets" + //Adicionar restrição para tickets somente do próprio usuário.
                                    "\n[0] Sair");
                            System.out.println("------------------------------");
                            System.out.print("Escolha uma opção: ");
                            criarListarTicket = scanner.nextLine();

                            if (criarListarTicket.equalsIgnoreCase("1")){
                                System.out.println("\n------ TIPO DE SERVIÇO -------");
                                System.out.println("[1] Atualização de softwares" +
                                        "\n[2] Suporte remoto" + //Adicionar restrição para tickets somente do próprio usuário.
                                        "\n[3] Suporte presencial" +
                                        "\n[0] Cancelar");
                                System.out.println("------------------------------");
                                System.out.print("Escolha uma opção: ");
                                String tipoServicoEscolhido = scanner.nextLine();

                                LocalDateTime dataHora = LocalDateTime.now();

                                TipoServico tipoServico;

                                switch (tipoServicoEscolhido) {
                                    case "1":
                                        tipoServico = TipoServico.ATUALIZACAO_SOFTWARE;
                                        break;
                                    case "2":
                                        tipoServico = TipoServico.SUPORTE_REMOTO;
                                        break;
                                    case "3":
                                        tipoServico = TipoServico.SUPORTE_PRESENCIAL;
                                        break;
                                    case "0":
                                        return;
                                    default:
                                        System.out.println("Opção inválida!");
                                        return;
                                }

                                Ticket ticket = new Ticket(usuarioAutenticado.getId(), tipoServico, dataHora);

                                ticket.setStatus(StatusTicket.ABERTO);

                                TicketsBD dadosTicket = new TicketsBD();
                                dadosTicket.salvarNovoTicket(ticket);
                            }
                        }while (!criarListarTicket.equalsIgnoreCase("0"));


                    } else if (usuarioAutenticado instanceof Colaborador) {
                        String opcaoEscolhidaColaborador;
                        System.out.println("\nBem-vindo(a) " + usuarioAutenticado.getNome());

                        do {

                            System.out.println("------------------------------");
                            System.out.println("[1] Cadastrar Empresa" +
                                    "\n[2] Cadastrar novo Usuário" +
                                    "\n[3] Listar Empresas" +
                                    "\n[4] Listar Usuários" +
                                    "\n[5] Listar Tickets" +
                                    "\n[0] Sair");
                            System.out.println("------------------------------");
                            System.out.print("Escolha uma opção: ");
                            opcaoEscolhidaColaborador = scanner.nextLine();

                            if (opcaoEscolhidaColaborador.equalsIgnoreCase("1")) {

                                ColetarDadosEmpresa dadosEmpresa = new ColetarDadosEmpresa();
                                Empresa novaEmpresa = dadosEmpresa.coletarDadosEmpresa();
                                EmpresasBD salvarEmpresa = new EmpresasBD();
                                salvarEmpresa.salvarNovaEmpresa(novaEmpresa);

                            } else if (opcaoEscolhidaColaborador.equalsIgnoreCase("2")) {

                                ColetarDadosUsuario novoUsuario = new ColetarDadosUsuario();
                                UsuariosBD salvarUsuario = new UsuariosBD();

                                System.out.println("CADASTRO DE USUÁRIO: ");
                                System.out.println("------------------------------");
                                System.out.println("[1] Cadastrar Cliente" +
                                        "\n[2] Cadastrar Colaborador" +
                                        "\n[0] Cancelar");
                                System.out.println("------------------------------");
                                System.out.print("Escolha uma opção: ");
                                String tipoUsuarioEscolhido = scanner.nextLine();
                                System.out.println("------------------------------");


                                if (tipoUsuarioEscolhido.equalsIgnoreCase("1")) {
                                    Usuario dadosUsuario = novoUsuario.coletarDadosCliente();
                                    salvarUsuario.salvarNovoUsuario(dadosUsuario);

                                } else if (tipoUsuarioEscolhido.equalsIgnoreCase("2")) {
                                    System.out.println("-------------- DEPARTAMENTO --------------");
                                    System.out.println("[1] Departamento de Suporte" +
                                            "\n[2] Departamento Financeiro" +
                                            "\n[0] Cancelar");
                                    System.out.println("------------------------------");
                                    System.out.print("Escolha uma opção: ");
                                    String departamentoEscolhido = scanner.nextLine();

                                    Departamento departamento;

                                    switch (departamentoEscolhido) {
                                        case "1":
                                            departamento = Departamento.SUPORTE;
                                            break;
                                        case "2":
                                            departamento = Departamento.FINANCEIRO;
                                            break;
                                        case "0":
                                            return;
                                        default:
                                            System.out.println("Opção inválida!");
                                            return;
                                    }

                                    Usuario dadosUsuario = novoUsuario.coletarDadosColaborador(departamento);
                                    salvarUsuario.salvarNovoUsuario(dadosUsuario);

                                } else if (tipoUsuarioEscolhido.equalsIgnoreCase("0")) {
                                    break;
                                }
                            } else if(opcaoEscolhidaColaborador.equalsIgnoreCase("3")){
                                EmpresasBD empresasBD = new EmpresasBD();
                                List<Empresa> empresas = empresasBD.listarEmpresas();

                                System.out.println("\n--- NOME EMPRESA - CNPJ ------");

                                for (Empresa e : empresas){
                                    System.out.println(e.getNome() + " - " + e.getCnpj());
                                }

                            } else if (opcaoEscolhidaColaborador.equalsIgnoreCase("4")) {
                                List<Usuario> usuarios = usuariosBD.listarUsuario();
                                System.out.println("-- TIPO USUÁRIO - Nome usuário -------");

                                for (Usuario u : usuarios) {
                                    if (u instanceof Colaborador) {
                                        System.out.println((u).getTipoUsuario() + " - " + u.getNome());
                                    } else if (u instanceof Cliente) {
                                        System.out.println((u).getTipoUsuario() + " - " + u.getNome());
                                    }
                                }
                            } else if (opcaoEscolhidaColaborador.equalsIgnoreCase("5")) {
                                TicketsBD ticketsBD = new TicketsBD();
                                List<Ticket> tickets = ticketsBD.listarTickets();

                                if (tickets.isEmpty()) {
                                    System.out.println("\nNenhum registro encontrado.");
                                } else {
                                    for (Ticket t : tickets) {
                                        System.out.println("ID: " + t.getId() +
                                                " | Cliente: " + t.getIdCliente() +
                                                " | Colaborador: " + t.getIdColaboradorResponsavel() +
                                                " | Status: " + t.getStatus());
                                    }
                                }
                            }
                        } while (!opcaoEscolhidaColaborador.equalsIgnoreCase("0"));
                    }
                } else {
                    System.out.println("Email ou senha inválidos.");
                }
            }else if(loginCadastroUsuario.equalsIgnoreCase("0")) {
                System.out.println("Encerrando programa...");
            }else {
                System.out.println("Opção inválida!");
            }
        } while (!loginCadastroUsuario.equalsIgnoreCase("0"));
    }
}
