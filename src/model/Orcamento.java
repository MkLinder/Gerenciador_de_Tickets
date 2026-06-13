package model;

public class Orcamento {
    private int id;
    private Colaborador colaborador;
    private Cliente cliente;
    private Empresa empresa;
    private String descricao;
    private float valorTotal;

    public Orcamento(Colaborador colaborador, Cliente cliente, Empresa empresa, String descricao, float valorTotal) {
        this.colaborador = colaborador;
        this.cliente = cliente;
        this.empresa = empresa;
        this.descricao = descricao;
        this.valorTotal = valorTotal;
    }

    public int getId() {
        return id;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public String getDescricao() {
        return descricao;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
