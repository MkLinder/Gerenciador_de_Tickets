package persistence;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.Colaborador;
import model.Usuario;

public class UsuariosBD {

    private static final String CAMINHO = "usuarios.txt";

    public void salvarNovoUsuario(Usuario usuario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO, true))) {

            String linha;

            if (usuario instanceof Colaborador) {
                Colaborador c = (Colaborador) usuario;

                linha = "COLABORADOR;" +
                        c.getNome() + ";" +
                        c.getCpf() + ";" +
                        c.getTelefone() + ";" +
                        c.getEndereco() + ";" +
                        c.getEmail() + ";" +
                        c.getSenha() + ";" +
                        c.getDepartamento();
            } else {
                Cliente c = (Cliente) usuario;
                linha = "CLIENTE;" +
                        c.getNome() + ";" +
                        c.getCpf() + ";" +
                        c.getTelefone() + ";" +
                        c.getEndereco() + ";" +
                        c.getEmail() + ";" +
                        c.getSenha()+ ";" +
                        c.getCategoria();
            }

            writer.write(linha);
            writer.newLine();

            System.out.println("\n_____________________________");
            System.out.println("Usuário cadastrado com sucesso!");

        } catch (IOException e) {
            System.err.println("Erro ao salvar usuário: " + e.getMessage());
        }
    }


    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO))) {

            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");

                String tipo = dados[0];

                if (tipo.equals("COLABORADOR") && dados.length >= 8) {

                    Usuario usuario = new Colaborador(
                            dados[1],
                            dados[2],
                            dados[3],
                            dados[4],
                            dados[5],
                            dados[6],
                            dados[7]
                    );

                    usuarios.add(usuario);

                } else if (tipo.equals("CLIENTE") && dados.length >= 7) {

                    Usuario usuario = new Cliente(
                            dados[1],
                            dados[2],
                            dados[3],
                            dados[4],
                            dados[5],
                            dados[6],
                            dados[7]
                    );

                    usuarios.add(usuario);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler usuários: " + e.getMessage());
        }

        return usuarios;
    }
}

