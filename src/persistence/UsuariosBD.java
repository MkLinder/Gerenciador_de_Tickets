package persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import enums.Departamento;
import enums.TipoUsuario;

import model.Cliente;
import model.Colaborador;
import model.Usuario;

public class UsuariosBD {

    private static final String CAMINHO = "usuarios.txt";

    public UsuariosBD(){
        inicializarArquivoComAdmin();
    }

    private void inicializarArquivoComAdmin() {
        try {
            File file = new File(CAMINHO);

            // Se o arquivo ainda não existe OU está vazio
            if (!file.exists() || file.length() == 0) {

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO))) {

                    String admin = "1;COLABORADOR;Admin;00000000000;(00)0000-0000;Sistema;admin;admin;SUPORTE";

                    writer.write(admin);
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            System.err.println("Erro ao inicializar arquivo: " + e.getMessage());
        }
    }


    public int gerarProximoIdUsuario() {
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

    public void salvarNovoUsuario(Usuario usuario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO, true))) {

            int id = gerarProximoIdUsuario();
            usuario.setId(id);

            String linha;

            if (usuario instanceof Colaborador) {
                Colaborador c = (Colaborador) usuario;

                linha = c.getId() + ";" +
                        TipoUsuario.COLABORADOR.name() + ";" +
                        c.getNome() + ";" +
                        c.getCpf() + ";" +
                        c.getTelefone() + ";" +
                        c.getEndereco() + ";" +
                        c.getEmail() + ";" +
                        c.getSenha() + ";" +
                        c.getDepartamento();
            } else {
                Cliente c = (Cliente) usuario;

                linha = c.getId() + ";" +
                        TipoUsuario.CLIENTE.name() + ";" +
                        c.getNome() + ";" +
                        c.getCpf() + ";" +
                        c.getTelefone() + ";" +
                        c.getCnpjEmpresa() + ";" +
                        c.getEmail() + ";" +
                        c.getSenha();
            }

            writer.write(linha);
            writer.newLine();

            System.out.println("\n_____________________________");
            System.out.println("Usuário cadastrado com sucesso!");

        } catch (IOException e) {
            System.err.println("Erro ao salvar usuário: " + e.getMessage());
        }
    }

    public List<Usuario> listarUsuario() {
        List<Usuario> usuarios = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO))) {

            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                for (int i = 0; i < dados.length; i++) {
                    dados[i] = dados[i].trim();
                }

                int id = Integer.parseInt(dados[0]);
                TipoUsuario tipoDeUsuario;

                try {
                    tipoDeUsuario = TipoUsuario.valueOf(dados[1]);
                } catch (IllegalArgumentException e) {
                    System.out.println("Tipo inválido: " + dados[1]);
                    continue;
                }

                if (tipoDeUsuario == TipoUsuario.COLABORADOR && dados.length >= 9) {

                    Departamento departamento;

                    try {
                        departamento = Departamento.valueOf(dados[8].trim().toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Departamento inválido: " + dados[8]);
                        continue;
                    }

                    Usuario usuario = new Colaborador(
                            dados[2],
                            dados[3],
                            dados[4],
                            dados[5],
                            dados[6],
                            dados[7],
                            departamento
                    );

                    usuario.setId(id);
                    usuarios.add(usuario);

                } else if (tipoDeUsuario == TipoUsuario.CLIENTE && dados.length >= 8) {

                    Usuario usuario = new Cliente(
                            dados[2],
                            dados[3],
                            dados[4],
                            dados[5],
                            dados[6],
                            dados[7]
                    );

                    usuario.setId(id);
                    usuarios.add(usuario);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler usuários: " + e.getMessage());
        }

        return usuarios;
    }
}

