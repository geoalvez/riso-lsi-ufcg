package riso.builder.conceptNet5.URI.in.relacoes;

import riso.builder.conceptNet5.URI.Constantes;
import riso.builder.conceptNet5.URI.in.interfaces.Complementavel;
import riso.builder.conceptNet5.URI.in.types.Relacao;

public class ComplementoRelacaoConceptNet implements Complementavel {

	private Relacao relacao;

	public ComplementoRelacaoConceptNet(Relacao relacao){
		setRelacao(relacao);	

	}
	
	public Relacao getRelacao() {
		return relacao;
	}

	public void setRelacao(Relacao relacao) {
		this.relacao = relacao;
	}

	public String getComplemento() {
		return Constantes.BARRA+getRelacao().getString()+Constantes.BARRA;
	}
}
