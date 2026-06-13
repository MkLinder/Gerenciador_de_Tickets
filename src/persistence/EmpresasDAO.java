package persistence;

import model.Empresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpresasDAO {

    public void salvarEmpresa(Empresa empresa) {

        String sql =
                "INSERT INTO empresas(cnpj,empresa,endereco) VALUES(?,?,?)";

        try (
                Connection conn = ConexaoBD.getConexaoBD();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, empresa.getCnpj());
            stmt.setString(2, empresa.getNome());
            stmt.setString(3, empresa.getEndereco());

            stmt.executeUpdate();

            System.out.println("Empresa cadastrada!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Empresa> listarEmpresas() {

        List<Empresa> empresas = new ArrayList<>();

        String sql = "SELECT * FROM empresas";

        try (
                Connection conn = ConexaoBD.getConexaoBD();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {

            while (rs.next()) {

                Empresa empresa = new Empresa();

                empresa.setCnpj(rs.getString("cnpj"));
                empresa.setNome(rs.getString("empresa"));
                empresa.setEndereco(rs.getString("endereco"));

                empresas.add(empresa);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return empresas;
    }
}
