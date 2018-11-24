package br.com.godinhowolff.algoritmo;

import br.com.godinhowolff.model.Cliente;

public class AlgoritmoAvaliacaoCredito {

	public static Double percentual = 30.00;
	
	public void analiseDeCredito(Cliente cliente) {
		SituacaoAnaliseCredito situacaoAnaliseCredito = new SituacaoAnaliseCredito();
		
		if(cliente.getRenda() < 1000.00) {
			situacaoAnaliseCredito.setStatus(StatusCredito.NEGADO);
			situacaoAnaliseCredito.setLimiteCredito(LimiteCredito.NEGADO_RENDA_BAIXA);
		} else if(cliente.getRenda() <= 2000.00 && cliente.getRenda() >= 1000.00 && cliente.getDependentes() > 1) {
			
			
			situacaoAnaliseCredito.setStatus(StatusCredito.NEGADO);
			situacaoAnaliseCredito.setLimiteCredito(LimiteCredito.NEGADO_POLITICA_DE_CREDITO);
		} else if(cliente.getRenda() <= 2000.00 && cliente.getRenda() >= 1000.00 && cliente.getDependentes() > 1) {
			situacaoAnaliseCredito.setStatus(StatusCredito.NEGADO);
			situacaoAnaliseCredito.setLimiteCredito(LimiteCredito.NEGADO_POLITICA_DE_CREDITO);
		}
	}
	
	private void calculaPercentual() {
		
	}
}
