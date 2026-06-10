package model;

import enums.TipoUsuario;

public class Cliente extends Usuario {
    private String cnpjEmpresa;

    public Cliente(String nome, String cpf, String telefone, String cnpj, String email, String senha){
        super(nome, cpf, telefone, email, senha);
        this.cnpjEmpresa = cnpj;
    }

    public String getCnpjEmpresa() {
        return cnpjEmpresa;
    }

    public void setCnpjEmpresa(String cnpjEmpresa) {
        this.cnpjEmpresa = cnpjEmpresa;
    }
}
