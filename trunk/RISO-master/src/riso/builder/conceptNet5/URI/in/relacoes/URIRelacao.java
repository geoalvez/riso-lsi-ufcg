package riso.builder.conceptNet5.URI.in.relacoes;

import riso.builder.conceptNet5.URI.in.URIGeral;
import riso.builder.conceptNet5.URI.in.types.ObjetosURIConceptNet;

public class URIRelacao extends URIGeral  {


	private ComplementoRelacaoConceptNet relacao;

	public URIRelacao(ComplementoRelacaoConceptNet relacao) {
		super(ObjetosURIConceptNet.RELACOES, relacao.getComplemento());
		setRelacao(relacao);
	}

	public ComplementoRelacaoConceptNet getRelacao() {
		return relacao;
	}

	public void setRelacao(ComplementoRelacaoConceptNet relacao) {
		this.relacao = relacao;
	}	
}
