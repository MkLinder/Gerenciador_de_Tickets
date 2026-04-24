package model;

public class Colaborador extends Usuario {
    private String departamento;

    public Colaborador(String nome, String cpf, String telefone, String endereco, String email, String senha, String departamento) {
        super(nome, cpf, telefone, endereco, email, senha);
        this.departamento = departamento;
    }

    // Implementar posteriormente
    public void colaboradorCadastrado(String nome, String departamento){
        System.out.println("Colaborador " + nome + " [" + departamento + "]" + " cadastrado com sucesso!");
    }

    public String getDepartamento() {
        return departamento;
    }
}
