package br.com.godinhowolff.algoritmo;

public enum LimiteCredito {
	ENTRE_500_1000("entre 500 - 1000"),
	ENTRE_100_500("entre 100 - 500"),
	ENTRE_1500_2000("entre 1500 - 2000"),
	ENTRE_1000_1500("entre 1000 - 1500"),
	SUPERIOR_2000("superior a 2000"),
	NEGADO_RENDA_BAIXA("Negado	renda baixa"),
    NEGADO_POLITICA_DE_CREDITO("reprovado pela política de crédito");

    private String limite;

    LimiteCredito(String limite) {
        this.limite = limite;
    }

    public String limite() {
        return limite;
    }
}
