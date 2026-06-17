package enums;

public enum TipoServico {

    ATUALIZACAO_SOFTWARE,
    SUPORTE_REMOTO,
    SUPORTE_PRESENCIAL;

    public static TipoServico opcaoEscolhida(String opcao) {

        switch (opcao) {
            case "1":
                return ATUALIZACAO_SOFTWARE;

            case "2":
                return SUPORTE_REMOTO;

            case "3":
                return SUPORTE_PRESENCIAL;

            default:
                throw new IllegalArgumentException("Opção inválida");
        }
    }
}
