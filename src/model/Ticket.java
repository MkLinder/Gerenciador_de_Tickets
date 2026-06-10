package model;

import java.time.LocalDateTime;
import java.util.Date;

import enums.StatusTicket;
import enums.TipoServico;


public class Ticket {
    private int id;
    private int idCliente;
    private int idColaboradorResponsavel;
    private TipoServico tipoServico;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataFechamento;
    private StatusTicket status;

    public Ticket() {
    }

    public Ticket(int idCliente, TipoServico tipoServico, LocalDateTime dataAbertura){
        this.idCliente = idCliente;
        this.tipoServico = tipoServico;
        this.dataAbertura = dataAbertura;
    }


    public int getId() {
        return id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getIdColaboradorResponsavel() {
        return idColaboradorResponsavel;
    }

    public TipoServico getTipoServico() {
        return tipoServico;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public LocalDateTime getDataFechamento() {
        return dataFechamento;
    }

    public StatusTicket getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdColaboradorResponsavel(int colaboradorResponsavel) {
        this.idColaboradorResponsavel = idColaboradorResponsavel;
    }

    public void setTipoServico(TipoServico tipoServico) {
        this.tipoServico = tipoServico;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public void setDataFechamento(LocalDateTime dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public void setStatus(StatusTicket status) {
        this.status = status;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

}
