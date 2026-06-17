package enums;

public enum Departamento {

    SUPORTE(1),
    FINANCEIRO(2);

    private final int id;

    Departamento(int id) {
        this.id = id;
    }

    public int pegarId() {
        return id;
    }

    public static Departamento buscarPorId(int id) {

        for (Departamento d : values()) {
            if (d.id == id) {
                return d;
            }
        }

        throw new IllegalArgumentException(
                "Departamento inválido: " + id
        );
    }

    public static Departamento opcaoEscolhida(String opcao) {

        switch (opcao) {
            case "1":
                return SUPORTE;

            case "2":
                return FINANCEIRO;

            default:
                return null;
        }
    }
}