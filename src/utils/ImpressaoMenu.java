package utils;

public class ImpressaoMenu {

    public static void cabecalho(String titulo) {
        System.out.println("\n\n=================================");
        System.out.printf(" %-31s%n", titulo);
        System.out.println("=================================");
    }

    public static void separadorP() {
        System.out.println("---------------------------------");
    }

    public static void separadorlnP() {
        System.out.printf("---------------------------------%n%n");
    }

    public static void separadorM() {
        System.out.println("--------------------------------------------------------------");
    }

    public static void separadorlnM() {
        System.out.printf("--------------------------------------------------------------%n%n");
    }

    public static void separadorG() {
        System.out.println("------------------------------------------------------------------------------------------------------------------");

    }

    public static void separadorlnG() {
        System.out.printf("------------------------------------------------------------------------------------------------------------------%n%n");

    }

    public static void menuPrincipal() {

        cabecalho("GERENCIADOR DE TICKETS");

        System.out.println(
                "[1] Login" +
                        "\n[0] Sair"
        );

        separadorlnP();
    }

    public static void menuCadastroUsuario() {

        cabecalho("CADASTRO DE USUÁRIO");

        System.out.println(
                "[1] Cadastrar Cliente" +
                        "\n[2] Cadastrar Colaborador" +
                        "\n[0] Cancelar"
        );

        separadorlnP();
    }

    public static void menuCliente() {

        System.out.println(
                "[1] Criar Ticket" +
                        "\n[2] Listar Tickets" +
                        "\n[0] Sair"
        );

        separadorlnP();
    }

    public static void menuTipoServico() {

        cabecalho("TIPO DE SERVIÇO");

        System.out.println(
                "[1] Atualização de softwares" +
                        "\n[2] Suporte remoto" +
                        "\n[3] Suporte presencial" +
                        "\n[0] Cancelar"
        );

        separadorlnP();
    }

    public static void menuColaborador() {

        System.out.println("[1] Cadastrar Empresa" +
                "\n[2] Cadastrar novo Usuário" +
                "\n[3] Listar Empresas" +
                "\n[4] Listar Usuários" +
                "\n[5] Listar Tickets" +
                "\n[0] Sair");

        separadorlnP();
    }

    public static void menuDepartamento() {

        cabecalho("DEPARTAMENTO");

        System.out.println(
                "[1] Departamento de Suporte" +
                        "\n[2] Departamento Financeiro" +
                        "\n[0] Cancelar"
        );

        separadorlnP();
    }
}