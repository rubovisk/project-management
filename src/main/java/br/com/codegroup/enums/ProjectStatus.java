package br.com.codegroup.enums;

public enum ProjectStatus {
    EM_ANALISE(1, "Em Análise"),
    ANALISE_REALIZADA(2, "Análise Realizada"),
    ANALISE_APROVADA(3, "Análise Aprovada"),
    INICIADO(4, "Iniciado"),
    PLANEJADO(5, "Planejado"),
    EM_ANDAMENTO(6, "Em Andamento"),
    ENCERRADO(7, "Encerrado"),
    CANCELADO(8, "Cancelado");

    private final int code;
    private final String description;

    ProjectStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static ProjectStatus fromCode(int code) {
        for (ProjectStatus status : values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Codigo invalido: " + code);
    }

    public static ProjectStatus fromDescription(String description) {
        for (ProjectStatus status : values()) {
            if (status.getDescription().equals(description)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Descricao invalida: " + description);
    }
}

