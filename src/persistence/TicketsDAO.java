package persistence;

import utils.ImpressaoMenu;
import enums.EstadoTicket;
import enums.TipoServico;
import model.Cliente;
import model.Colaborador;
import model.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketsDAO {

    public void salvarTicket(Ticket ticket) {

        String sql =
                "INSERT INTO tickets " +
                        "(cliente_id,colaborador_id,tipo_servico,data_abertura,data_fechamento, estado, descricao, observacoes) " +
                        "VALUES (?,?,?,?,?,?,?,?)";

        try (
                Connection conn = ConexaoBD.getConexaoBD();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, ticket.getCliente().getId());

            stmt.setInt(2, ticket.getColaborador().getId());

            stmt.setString(3,
                    ticket.getTipoServico().name());

            stmt.setTimestamp(
                    4,
                    java.sql.Timestamp.valueOf(
                            ticket.getDataAbertura()
                    )
            );
            stmt.setString(6,
                    ticket.getEstado().name());
            stmt.setString(7, ticket.getDescricao());
            stmt.setString(8, ticket.getObservacao());

            if (ticket.getDataFechamento() == null) {

                stmt.setNull(5, java.sql.Types.TIMESTAMP);

            } else {

                stmt.setTimestamp(
                        5,
                        java.sql.Timestamp.valueOf(
                                ticket.getDataFechamento()
                        )
                );
            }

            stmt.executeUpdate();

            System.out.println("Ticket registrado!");
            ImpressaoMenu.separadorlnP();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Ticket> listarTodosTickets() {

        List<Ticket> tickets = new ArrayList<>();

        String sql = "SELECT t.id_ticket, " +
                "           t.cliente_id, " +
                "           t.colaborador_id, " +
                "           t.tipo_servico, " +
                "           t.estado, " +
                "           t.observacoes, " +
                "           t.data_abertura, " +
                "           t.data_fechamento " +
                "FROM tickets t " +
                "ORDER BY id_ticket";

        try (
                Connection conn = ConexaoBD.getConexaoBD();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {

            UsuariosDAO usuarioDAO = new UsuariosDAO();

            while (rs.next()) {

                int clienteId = rs.getInt("cliente_id");
                int colaboradorId = rs.getInt("colaborador_id");

                Cliente cliente =
                        usuarioDAO.buscarClientePorId(clienteId);

                Colaborador colaborador =
                        usuarioDAO.buscarColaboradorPorId(colaboradorId);

                Ticket ticket = new Ticket();

                ticket.setId(rs.getInt("id_ticket"));
                ticket.setCliente(cliente);
                ticket.setColaborador(colaborador);

                ticket.setTipoServico(
                        TipoServico.valueOf(
                                rs.getString("tipo_servico")
                        )
                );

                ticket.setEstado(
                        EstadoTicket.valueOf(
                                rs.getString("estado")
                        )
                );

                ticket.setObservacao(
                        rs.getString("observacoes")
                );

                ticket.setDataAbertura(
                        rs.getTimestamp("data_abertura")
                                .toLocalDateTime()
                );

                Timestamp fechamento =
                        rs.getTimestamp("data_fechamento");

                if (fechamento != null) {
                    ticket.setDataFechamento(
                            fechamento.toLocalDateTime()
                    );
                }

                tickets.add(ticket);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tickets;
    }

    public List<Ticket> listarTicketsCliente(int idCliente) {
        List<Ticket> tickets = new ArrayList<>();

        String sql = "SELECT t.id_ticket, " +
                "           t.cliente_id, " +
                "           t.colaborador_id, " +
                "           t.tipo_servico, " +
                "           t.estado, " +
                "           t.observacoes, " +
                "           t.data_abertura, " +
                "           t.data_fechamento " +
                "FROM tickets t " +
                "WHERE t.cliente_id = ? " +
                "ORDER BY t.id_ticket";
        try (
                Connection conn = ConexaoBD.getConexaoBD();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, idCliente);

            ResultSet rs = stmt.executeQuery();

            UsuariosDAO usuarioDAO = new UsuariosDAO();

            while (rs.next()) {

                int clienteId = rs.getInt("cliente_id");
                int colaboradorId = rs.getInt("colaborador_id");


                Cliente cliente =
                        usuarioDAO.buscarClientePorId(clienteId);

                Colaborador colaborador =
                        usuarioDAO.buscarColaboradorPorId(colaboradorId);

                Ticket ticket = new Ticket();

                ticket.setId(rs.getInt("id_ticket"));

                ticket.setCliente(cliente);
                ticket.setColaborador(colaborador);

                ticket.setTipoServico(
                        TipoServico.valueOf(
                                rs.getString("tipo_servico")
                        )
                );

                ticket.setEstado(
                        EstadoTicket.valueOf(
                                rs.getString("estado")
                        )
                );

                ticket.setObservacao(
                        rs.getString("observacoes")
                );

                ticket.setDataAbertura(
                        rs.getTimestamp("data_abertura")
                                .toLocalDateTime()
                );

                tickets.add(ticket);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tickets;
    }
}
