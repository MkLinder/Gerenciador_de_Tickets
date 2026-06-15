package Utils;

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
        System.out.printf("---------------------------------%n");
    }

    public static void separadorM() {
        System.out.printf("--------------------------------------------------------------");
    }

    public static void separadorlnM() {
        System.out.printf("--------------------------------------------------------------%n%n");
    }

    public static void separadorG() {
        System.out.printf("------------------------------------------------------------------------------------------------------------------");

    }

    public static void separadorlnG() {
        System.out.printf("------------------------------------------------------------------------------------------------------------------%n%n");

    }
}