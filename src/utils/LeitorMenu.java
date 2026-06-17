package utils;

import java.util.Arrays;
import java.util.Scanner;

public class LeitorMenu {
    public static String lerOpcaoValida(Scanner scanner, String... opcoesValidas) {

        String opcao;

        while (true) {

            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextLine();

            if (Arrays.asList(opcoesValidas).contains(opcao)) {
                return opcao;
            }

            System.out.println("\nOpção inválida!");
            ImpressaoMenu.separadorlnP();
        }
    }
}