package model;

public class Cliente extends Usuario {

    private String categoria;

    public Cliente(String nome, String cpf, String telefone, String endereco, String email, String senha, String categoria){
        super(nome, cpf, telefone, endereco, email, senha);
        this.categoria = categoria;
    }

    // Implementar posteriormente
    public void clienteCadastrado(String nome){
        System.out.println(categoria + " " + nome + " cadastrado com sucesso!");
    }

    public String getCategoria() {
        return categoria;
    }
}
