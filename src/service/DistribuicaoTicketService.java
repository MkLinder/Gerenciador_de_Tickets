package service;

import java.util.ArrayList;
import java.util.List;

import enums.Departamento;
import model.Colaborador;
import model.Ticket;
import model.Usuario;
import persistence.TicketsDAO;
import persistence.UsuariosDAO;

public class DistribuicaoTicketService {

    public Colaborador escolherColaborador() {

        UsuariosDAO usuariosDAO = new UsuariosDAO();
        TicketsDAO ticketsDAO = new TicketsDAO();

        List<Usuario> usuarios = usuariosDAO.listarUsuarios();
        List<Ticket> tickets = ticketsDAO.listarTodosTickets();

        // Filtrar apenas colaboradores de SUPORTE
        List<Colaborador> suportes = new ArrayList<>();

        for (Usuario u : usuarios) {
            if (u instanceof Colaborador) {
                Colaborador c = (Colaborador) u;

                if (c.getDepartamento() == Departamento.SUPORTE) {
                    suportes.add(c);
                }
            }
        }

        // Se não houver suporte
        if (suportes.isEmpty()) {
            System.out.println("Nenhum colaborador de suporte disponível!");
            return null;
        }

        // Encontrar quem tem menos tickets
        Colaborador colaboradorEscolhido = null;
        int menorQuantidade = Integer.MAX_VALUE;

        for (Colaborador c : suportes) {

            int contador = 0;

            for (Ticket t : tickets) {
                if (t.getColaborador().getId() == c.getId()) {
                    contador++;
                }
            }

            if (contador < menorQuantidade) {
                menorQuantidade = contador;
                colaboradorEscolhido = c;
            }
        }

        return colaboradorEscolhido;
    }
}
