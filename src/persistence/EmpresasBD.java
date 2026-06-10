package persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import model.Empresa;


public class EmpresasBD {

    private static final String CAMINHO = "empresas.txt";

    public void salvarNovaEmpresa(Empresa empresa){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO, true))){

            String linha = empresa.getCnpj() + ";" +
                        empresa.getNome() + ";" +
                        empresa.getEndereco();

            writer.write(linha);
            writer.newLine();

            System.out.println("\n_____________________________");
            System.out.println("Empresa registrada com sucesso!");

        }catch (IOException e){
            System.err.println("Erro ao salvar Empresa: " + e.getMessage());
        }
    }

    public List<Empresa> listarEmpresas(){
        List<Empresa> empresas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO))){

            String linha;

            while ((linha = reader.readLine()) != null){
                String[] dados = linha.split(";");

                Empresa e = new Empresa();

                e.setCnpj(dados[0]);
                e.setNome(dados[1]);
                e.setEndereco(dados[2]);

                empresas.add(e);
            }

        }catch (IOException e){
            System.out.println("Erro ao ler Empresas: " + e.getMessage());
        }

        return empresas;
    }
}
