package br.com.godinhowolff.algoritmo;

public enum StatusCredito {
    APROVADO(""),
    NEGADO("");

    private String status;

    StatusCredito(String status) {
        this.status = status;
    }

    public String status() {
        return status;
    }
	

}
