package persistence;

import enums.Departamento;
import model.Cliente;
import model.Colaborador;
import model.Usuario;
import utils.ImpressaoMenu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDAO {

    public void inicializarAdmin() {

        String verificaAdmin =
                "SELECT COUNT(*) FROM colaboradores WHERE email = ?";

        String insereAdmin =
                "INSERT INTO colaboradores " +
                        "(departamento_id, colaborador, cpf, telefone, endereco, email, senha) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection conn = ConexaoBD.getConexaoBD();
                PreparedStatement stmtVerifica = conn.prepareStatement(verificaAdmin)
        ) {

            stmtVerifica.setString(1, "admin");

            ResultSet rs = stmtVerifica.executeQuery();

            if (rs.next() && rs.getInt(1) == 0) {

                try (PreparedStatement stmtInsere =
                             conn.prepareStatement(insereAdmin)) {

                    stmtInsere.setInt(1, 1);
                    stmtInsere.setString(2, "Admin");
                    stmtInsere.setString(3, "00000000000");
                    stmtInsere.setString(4, "(00)0000-0000");
                    stmtInsere.setString(5, "Sistema");
                    stmtInsere.setString(6, "admin");
                    stmtInsere.setString(7, "admin");

                    stmtInsere.executeUpdate();

                    ImpressaoMenu.separadorP();
                    System.out.println("Usuário Admin criado.");
                    ImpressaoMenu.separadorlnP();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void salvarCliente(Cliente cliente) {

        String sql = """
                INSERT INTO clientes
                (cliente,cpf,telefone, cnpj_empresa,email,senha)
                VALUES (?,?,?,?,?,?)
                """;

        try (
                Connection conn = ConexaoBD.getConexaoBD();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getCnpjEmpresa());
            stmt.setString(5, cliente.getEmail());
            stmt.setString(6, cliente.getSenha());


            stmt.executeUpdate();

            ImpressaoMenu.separadorP();
            System.out.println("Cliente registrado com sucesso!");
            ImpressaoMenu.separadorlnP();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void salvarColaborador(Colaborador colaborador) {

        String sql = """
                INSERT INTO colaboradores
                (departamento_id,colaborador,cpf, telefone, endereco,email,senha)
                VALUES (?,?,?,?,?,?,?)
                """;

        try (
                Connection conn = ConexaoBD.getConexaoBD();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, colaborador.getDepartamento().pegarId());
            stmt.setString(2, colaborador.getNome());
            stmt.setString(3, colaborador.getCpf());
            stmt.setString(4, colaborador.getTelefone());
            stmt.setString(5, colaborador.getEndereco());
            stmt.setString(6, colaborador.getEmail());
            stmt.setString(7, colaborador.getSenha());

            stmt.executeUpdate();

            ImpressaoMenu.separadorP();
            System.out.println("Colaborador registrado!");
            ImpressaoMenu.separadorlnP();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listarClientes(List<Usuario> usuarios) {

        String sql = "SELECT * FROM clientes";

        try (
                Connection conn = ConexaoBD.getConexaoBD();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {

            while (rs.next()) {

                Cliente cliente = new Cliente(
                        rs.getString("cliente"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getString("cnpj_empresa"),
                        rs.getString("email"),
                        rs.getString("senha")
                );

                cliente.setId(rs.getInt("id_cliente"));

                usuarios.add(cliente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listarColaboradores(List<Usuario> usuarios) {

        String sql = "SELECT * FROM colaboradores";

        try (
                Connection conn = ConexaoBD.getConexaoBD();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {

            while (rs.next()) {

                int idDepartamento = rs.getInt("departamento_id");

                Departamento departamento =
                        Departamento.buscarPorId(idDepartamento);

                Colaborador colaborador = new Colaborador(
                        rs.getString("colaborador"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getString("endereco"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        departamento
                );

                colaborador.setId(rs.getInt("id_colaborador"));

                usuarios.add(colaborador);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> listarUsuarios() {

        List<Usuario> usuarios = new ArrayList<>();

        listarClientes(usuarios);
        listarColaboradores(usuarios);

        return usuarios;
    }

    public Cliente buscarClientePorId(int id) {

        String sql = "SELECT * FROM clientes WHERE id_cliente = ?";

        try (
                Connection conn = ConexaoBD.getConexaoBD();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Cliente cliente = new Cliente(
                        rs.getString("cliente"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getString("cnpj_empresa"),
                        rs.getString("email"),
                        rs.getString("senha")
                );

                cliente.setId(rs.getInt("id_cliente"));

                return cliente;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Colaborador buscarColaboradorPorId(int id) {

        String sql = "SELECT * FROM colaboradores WHERE id_colaborador = ?";

        try (
                Connection conn = ConexaoBD.getConexaoBD();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Departamento departamento =
                        Departamento.buscarPorId(
                                rs.getInt("departamento_id")
                        );

                Colaborador colaborador = new Colaborador(
                        rs.getString("colaborador"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getString("endereco"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        departamento
                );

                colaborador.setId(rs.getInt("id_colaborador"));

                return colaborador;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean emailExiste(String email) {

        String sqlClientes =
                "SELECT 1 FROM clientes WHERE email = ?";

        String sqlColaboradores =
                "SELECT 1 FROM colaboradores WHERE email = ?";

        try (
                Connection conn = ConexaoBD.getConexaoBD()
        ) {

            // Verifica clientes
            try (PreparedStatement stmt = conn.prepareStatement(sqlClientes)) {

                stmt.setString(1, email);

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    return true;
                }
            }

            // Verifica colaboradores
            try (PreparedStatement stmt = conn.prepareStatement(sqlColaboradores)) {

                stmt.setString(1, email);

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean cpfExiste(String cpf) {

        String sqlClientes =
                "SELECT 1 FROM clientes WHERE cpf = ?";

        String sqlColaboradores =
                "SELECT 1 FROM colaboradores WHERE cpf = ?";

        try (
                Connection conn = ConexaoBD.getConexaoBD()
        ) {

            // Procura em clientes
            try (PreparedStatement stmt = conn.prepareStatement(sqlClientes)) {

                stmt.setString(1, cpf);

                ResultSet rs = stmt.executeQuery();


                if (rs.next()) {
                    return true;
                }
            }

            // Procura em colaboradores
            try (PreparedStatement stmt = conn.prepareStatement(sqlColaboradores)) {

                stmt.setString(1, cpf);

                ResultSet rs = stmt.executeQuery();


                if (rs.next()) {
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private Cliente buscarCliente(String email, String senha) {

        String sql =
                "SELECT * FROM clientes " +
                        "WHERE email = ? AND senha = ?";

        try (
                Connection conn = ConexaoBD.getConexaoBD();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Cliente cliente = new Cliente(
                        rs.getString("cliente"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getString("cnpj_empresa"),
                        rs.getString("email"),
                        rs.getString("senha")
                );

                cliente.setId(
                        rs.getInt("id_cliente")
                );

                return cliente;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Colaborador buscarColaborador(String email, String senha) {

        String sql =
                "SELECT * FROM colaboradores " +
                        "WHERE email = ? AND senha = ?";

        try (
                Connection conn = ConexaoBD.getConexaoBD();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Departamento departamento =
                        Departamento.buscarPorId(
                                rs.getInt("departamento_id")
                        );

                Colaborador colaborador =
                        new Colaborador(
                                rs.getString("colaborador"),
                                rs.getString("cpf"),
                                rs.getString("telefone"),
                                rs.getString("endereco"),
                                rs.getString("email"),
                                rs.getString("senha"),
                                departamento
                        );

                colaborador.setId(
                        rs.getInt("id_colaborador")
                );

                return colaborador;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Usuario autenticar(String email, String senha) {

        Usuario usuario = buscarCliente(email, senha);

        if (usuario != null) {
            return usuario;
        }

        return buscarColaborador(email, senha);
    }
}