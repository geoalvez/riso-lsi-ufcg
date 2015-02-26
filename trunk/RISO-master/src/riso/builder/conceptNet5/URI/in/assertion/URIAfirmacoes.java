package riso.builder.conceptNet5.URI.in.assertion;

import riso.builder.conceptNet5.URI.in.URIGeral;
import riso.builder.conceptNet5.URI.in.types.ObjetosURIConceptNet;


public class URIAfirmacoes extends URIGeral {


	private ComplementoAfirmacaoConceptNet afirmacao;
	
	public URIAfirmacoes(ComplementoAfirmacaoConceptNet afirmacao) {
		super(ObjetosURIConceptNet.AFIRMACOES, afirmacao.getComplemento());
		setAfirmacao(afirmacao);
	}
	
	public ComplementoAfirmacaoConceptNet getAfirmacao() {
		return afirmacao;
	}
	
	public void setAfirmacao(ComplementoAfirmacaoConceptNet afirmacao) {
		this.afirmacao = afirmacao;
	}
	
}
