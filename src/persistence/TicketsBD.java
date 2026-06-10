package persistence;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import enums.StatusTicket;
import enums.TipoServico;
import model.Colaborador;
import model.Ticket;
import service.DistribuicaoTicketService;
import service.GerarProximoId;


public class TicketsBD {

    private static final String CAMINHO = "tickets.txt";

    int idTicket = GerarProximoId.gerarProximoId(CAMINHO);

    public void salvarNovoTicket(Ticket ticket){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO, true))){

            ticket.setId(idTicket);

            DistribuicaoTicketService colabDisponivel = new DistribuicaoTicketService();

            Colaborador colabResponsavel = colabDisponivel.escolherColaborador();

            if (colabResponsavel == null) {
                System.out.println("Não foi possível criar ticket, nenhum colaborar disponível.");
                return;
            }

            ticket.setIdColaboradorResponsavel(colabResponsavel.getId());

            String linha = ticket.getId() + ";" +
                    ticket.getStatus() + ";" +
                    ticket.getIdCliente() + ";" +
                    ticket.getIdColaboradorResponsavel() + ";" +
                    ticket.getTipoServico() + ";" +
                    ticket.getDataAbertura() + ";" +
                    ticket.getDataFechamento();

            writer.write(linha);
            writer.newLine();

            System.out.println("\n_____________________________");
            System.out.println("Ticket Criado com sucesso!");

        } catch (IOException e) {
            System.err.println("Erro ao salvar ticket: " + e.getMessage());
        }
    }

    public List<Ticket> listarTickets() {
        List<Ticket> tickets = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO))) {

            String linha;
            //DateTimeFormatter dataHoraFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");

                Ticket t = new Ticket();

                t.setId(Integer.parseInt(dados[0]));
                t.setStatus(StatusTicket.valueOf(dados[1]));
                t.setIdCliente(Integer.parseInt(dados[2]));
                t.setIdColaboradorResponsavel(Integer.parseInt(dados[3]));
                t.setTipoServico(TipoServico.valueOf(dados[4]));
                t.setDataAbertura(LocalDateTime.parse(dados[5]));

                if (dados.length >= 8 && !dados[7].equals("-")) {
                    t.setDataFechamento(LocalDateTime.parse(dados[7]));
                }

                tickets.add(t);
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler tickets: " + e.getMessage());
        }

        return tickets;
    }
}
