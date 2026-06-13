package model;

import java.time.LocalDateTime;

import enums.EstadoTicket;
import enums.TipoServico;


public class Ticket {
    private int id;
    private Usuario cliente;
    private Colaborador colaborador;
    private TipoServico tipoServico;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataFechamento;
    private EstadoTicket estado;
    private String descricao;
    private String observacao;

    public Ticket() {
    }

    public Ticket(Usuario cliente, Colaborador colaborador,TipoServico tipoServico, LocalDateTime dataAbertura, String descricao){
        this.cliente = cliente;
        this.colaborador = colaborador;
        this.tipoServico = tipoServico;
        this.dataAbertura = dataAbertura;
        this.descricao = descricao;
    }

    public Ticket(LocalDateTime dataFechamento, EstadoTicket estado, String descricao, String observacao){
        this.dataFechamento = dataFechamento;
        this.estado = estado;
        this.descricao = descricao;
        this.observacao = observacao;
    }


    public int getId() {
        return id;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public Colaborador getColaborador() {
        return colaborador;
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

    public EstadoTicket getEstado() {
        return estado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
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

    public void setEstado(EstadoTicket estado) {
        this.estado = estado;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
