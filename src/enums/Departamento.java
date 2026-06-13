package enums;

public enum Departamento {

    SUPORTE(1),
    FINANCEIRO(2);

    private final int id;

    Departamento(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Departamento fromId(int id) {

        for (Departamento d : values()) {
            if (d.id == id) {
                return d;
            }
        }

        throw new IllegalArgumentException(
                "Departamento inválido: " + id
        );
    }
}