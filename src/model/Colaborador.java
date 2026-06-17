package model;

import enums.Departamento;

public class Colaborador extends Usuario {
    private Departamento departamento;
    private String endereco;

    public Colaborador(String nome, String cpf, String telefone, String endereco, String email, String senha, Departamento departamento) {
        super(nome, cpf, telefone, email, senha);
        this.departamento = departamento;
        this.endereco = endereco;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public String getEndereco() {
        return endereco;
    }
}
