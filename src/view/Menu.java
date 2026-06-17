package view;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import utils.FormatadorDataHora;
import utils.ImpressaoMenu;
import enums.Departamento;

import enums.EstadoTicket;
import enums.TipoServico;
import model.*;
import persistence.*;
import service.DistribuicaoTicketService;
import service.LoginUsuarioService;
import utils.LeitorDadosUsuario;
import utils.LeitorMenu;

public class Menu {
    Scanner scanner = new Scanner(System.in);

    public void menu(){

        String loginUsuario;

        do {

            ImpressaoMenu.menuPrincipal();

            loginUsuario = LeitorMenu.lerOpcaoValida(scanner,"1","0");

            UsuariosDAO usuariosDAO = new UsuariosDAO();

            if(loginUsuario.equalsIgnoreCase("1")){

                String email = LeitorDadosUsuario.lerEmailLogin(scanner);

                String senha = LeitorDadosUsuario.lerSenhaLogin(scanner);

                LoginUsuarioService login = new LoginUsuarioService();
                Usuario usuarioAutenticado = login.autenticar(email, senha);

                if (usuarioAutenticado != null) {

                    if (usuarioAutenticado instanceof Cliente) {
                        String criarListarTicket;
                        ImpressaoMenu.cabecalho("Bem-vindo(a) " + usuarioAutenticado.getNome());

                        do {
                            ImpressaoMenu.menuCliente();

                            criarListarTicket = LeitorMenu.lerOpcaoValida(
                                            scanner,
                                            "1",
                                            "2",
                                            "0"
                                    );

                            if (criarListarTicket.equalsIgnoreCase("1")){

                                ImpressaoMenu.menuTipoServico();

                                String tipoServicoEscolhido =
                                        LeitorMenu.lerOpcaoValida(
                                                scanner,
                                                "1",
                                                "2",
                                                "3",
                                                "0"
                                        );

                                ImpressaoMenu.separadorlnP();

                                if (tipoServicoEscolhido.equals("0")) {
                                    continue;
                                }

                                TipoServico tipoServico =
                                        TipoServico.opcaoEscolhida(tipoServicoEscolhido);

                                System.out.print("Descreva brevemente sua solicitação: ");
                                String descricao = scanner.nextLine();

                                LocalDateTime dataHora = LocalDateTime.now();

                                Colaborador colaboradorEscolhido = new DistribuicaoTicketService().escolherColaborador();

                                Ticket ticket = new Ticket(usuarioAutenticado, colaboradorEscolhido, tipoServico, dataHora, descricao);

                                ticket.setEstado(EstadoTicket.ABERTO);

                                TicketsDAO ticketDAO = new TicketsDAO();
                                ticketDAO.salvarTicket(ticket);

                            }else if (criarListarTicket.equalsIgnoreCase("2")){
                                List<Ticket> tickets = new TicketsDAO().listarTicketsCliente(usuarioAutenticado.getId());

                                ImpressaoMenu.separadorG();
                                System.out.printf(
                                        "%-3s | %-18s | %-22s | %-10s | %-30s | %-16s%n",
                                        "ID",
                                        "COLABORADOR",
                                        "SERVIÇO",
                                        "STATUS",
                                        "OBSERVAÇÃO",
                                        "DATA ABERTURA"
                                );

                                ImpressaoMenu.separadorG();
                                for (Ticket t : tickets) {

                                    String observacao =
                                            t.getObservacao() == null
                                                    ? "-"
                                                    : t.getObservacao();

                                    System.out.printf(
                                            "%-3d | %-18s | %-22s | %-10s | %-30s | %-16s%n",
                                            t.getId(),
                                            t.getColaborador().getNome(),
                                            t.getTipoServico(),
                                            t.getEstado(),
                                            observacao,
                                            FormatadorDataHora.formatar(t.getDataAbertura())
                                    );
                                }

                                ImpressaoMenu.separadorlnG();
                            }else if (criarListarTicket.equalsIgnoreCase("0")){
                                continue;
                            }
                        }while (!criarListarTicket.equalsIgnoreCase("0"));


                    }else if (usuarioAutenticado instanceof Colaborador) {
                        String opcaoEscolhidaColaborador;

                        ImpressaoMenu.cabecalho("Bem-vindo(a) " + usuarioAutenticado.getNome());

                        do {

                            ImpressaoMenu.menuColaborador();

                            opcaoEscolhidaColaborador =
                                    LeitorMenu.lerOpcaoValida(
                                            scanner,
                                            "0", "1", "2", "3", "4", "5"
                                    );

                            if (opcaoEscolhidaColaborador.equalsIgnoreCase("1")) {

                                ColetarDadosEmpresa dadosEmpresa = new ColetarDadosEmpresa();
                                Empresa novaEmpresa = dadosEmpresa.coletarDadosEmpresa();

                                EmpresasDAO empresaDAO= new EmpresasDAO();
                                empresaDAO.salvarEmpresa(novaEmpresa);

                            }else if (opcaoEscolhidaColaborador.equalsIgnoreCase("2")) {

                                UsuariosDAO salvarCliente = new UsuariosDAO();
                                UsuariosDAO salvarColaborador = new UsuariosDAO();

                                ColetarDadosUsuario novoUsuario =
                                        new ColetarDadosUsuario();

                                ImpressaoMenu.menuCadastroUsuario();

                                String tipoUsuarioEscolhido =
                                        LeitorMenu.lerOpcaoValida(
                                                scanner,
                                                "1",
                                                "2",
                                                "0"
                                        );

                                ImpressaoMenu.separadorlnP();

                                if (tipoUsuarioEscolhido.equalsIgnoreCase("1")) {
                                    Cliente dadosCliente = novoUsuario.coletarDadosCliente();

                                    salvarCliente.salvarCliente(dadosCliente);

                                }else if (tipoUsuarioEscolhido.equalsIgnoreCase("2")) {

                                    ImpressaoMenu.menuDepartamento();

                                    String departamentoEscolhido =
                                            LeitorMenu.lerOpcaoValida(
                                                    scanner,
                                                    "1",
                                                    "2",
                                                    "0"
                                            );

                                    if (departamentoEscolhido.equals("0")) {
                                        continue;
                                    }

                                    Departamento departamento =
                                            Departamento.opcaoEscolhida(
                                                    departamentoEscolhido
                                            );

                                    Colaborador dadosColaborador =
                                            novoUsuario.coletarDadosColaborador(
                                                    departamento
                                            );

                                    salvarColaborador.salvarColaborador(
                                            dadosColaborador
                                    );

                                }else if (tipoUsuarioEscolhido.equalsIgnoreCase("0")) {
                                    continue;
                                }
                            }else if(opcaoEscolhidaColaborador.equalsIgnoreCase("3")){
                                EmpresasDAO empresasDAO = new EmpresasDAO();
                                List<Empresa> empresas = empresasDAO.listarEmpresas();

                                ImpressaoMenu.separadorM();
                                System.out.printf("%-38s | %-18s%n", "EMPRESA", "CNPJ");
                                ImpressaoMenu.separadorM();

                                for (Empresa e : empresas) {
                                    System.out.printf(
                                            "%-38s | %-18s%n",
                                            e.getNome(),
                                            e.getCnpj()
                                    );
                                }

                                ImpressaoMenu.separadorlnM();

                            }else if (opcaoEscolhidaColaborador.equalsIgnoreCase("4")) {

                                List<Usuario> usuarios = usuariosDAO.listarUsuarios();

                                ImpressaoMenu.separadorM();
                                System.out.printf("%-15s | %-30s%n", "TIPO USUÁRIO", "NOME");
                                ImpressaoMenu.separadorM();

                                for (Usuario u : usuarios) {

                                    System.out.printf(
                                            "%-15s | %-30s%n",
                                            u.getTipoUsuario(),
                                            u.getNome()
                                    );
                                }

                                ImpressaoMenu.separadorlnM();

                            }else if (opcaoEscolhidaColaborador.equalsIgnoreCase("5")) {

                                TicketsDAO ticketsDAO = new TicketsDAO();
                                List<Ticket> tickets = ticketsDAO.listarTodosTickets();

                                if (tickets.isEmpty()) {

                                    System.out.println("\nNenhum registro encontrado.");

                                }else {
                                    ImpressaoMenu.separadorG();
                                    System.out.printf("%-5s | %-20s | %-20s | %-22s | %-10s%n",
                                            "ID",
                                            "CLIENTE",
                                            "COLABORADOR",
                                            "SERVIÇO",
                                            "ESTADO");
                                    ImpressaoMenu.separadorG();
                                    for (Ticket t : tickets) {

                                        System.out.printf("%-5d | %-20s | %-20s | %-22s | %-10s%n",
                                                t.getId(),
                                                t.getCliente().getNome(),
                                                t.getColaborador().getNome(),
                                                t.getTipoServico(),
                                                t.getEstado());
                                    }

                                    ImpressaoMenu.separadorlnG();
                                }
                            }

                        }while (!opcaoEscolhidaColaborador.equalsIgnoreCase("0"));
                    }
                } else {
                    System.out.println("Email ou senha inválidos.");
                }
            }else if(loginUsuario.equalsIgnoreCase("0")) {
                System.out.println("Encerrando programa...");
            }

        }while (!loginUsuario.equalsIgnoreCase("0"));
    }
}
