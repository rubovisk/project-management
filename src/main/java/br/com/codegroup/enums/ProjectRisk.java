package br.com.codegroup.enums;

public enum ProjectRisk {
	BAIXO(1, "Baixo"),
    MEDIO(2, "Médio"),
    ALTO(3, "Alto");
	
	private final int code;
    private final String description;
    
    ProjectRisk(int code, String description) {
        this.code = code;
        this.description = description;
    }
    
    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static ProjectRisk fromCode(int code) {
        for (ProjectRisk risk : values()) {
            if (risk.getCode() == code) {
                return risk;
            }
        }
        throw new IllegalArgumentException("Codigo invalido: " + code);
    }

    public static ProjectRisk fromDescription(String description) {
        for (ProjectRisk risk : values()) {
            if (risk.getDescription().equals(description)) {
                return risk;
            }
        }
        throw new IllegalArgumentException("Descricao invalida: " + description);
    }
}
