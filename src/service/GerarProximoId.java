package service;

import java.io.BufferedReader;
import java.io.FileReader;

public class GerarProximoId {

    public static int gerarProximoId(String CAMINHO) {
        int maiorId = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO))) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");

                int id = Integer.parseInt(dados[0]);

                if (id > maiorId) {
                    maiorId = id;
                }
            }

        } catch (Exception e) {

        }

        return maiorId + 1;
    }
}
