package riso.builder.conceptNet5.URI.in.assertion;

import riso.builder.conceptNet5.URI.Constantes;
import riso.builder.conceptNet5.URI.in.URIGeral;
import riso.builder.conceptNet5.URI.in.interfaces.Complementavel;

public class ComplementoAfirmacaoConceptNet implements Complementavel{

	/*pode ser relacao ou conceito*/
	private URIGeral relacao;
	
	/*pode ser conceitos ou afirmacoes*/
	private URIGeral inicio;
	
	/*pode ser conceitos ou afirmacoes*/
	private URIGeral fim;
	
	public ComplementoAfirmacaoConceptNet(URIGeral[] uirs){
		setRelacao(uirs[0]);
		setInicio(uirs[1]);
		setFim(uirs[2]);
	}

	public URIGeral getRelacao() {
		return relacao;
	}

	public void setRelacao(URIGeral relacao) {
		this.relacao = relacao;
	}

	public URIGeral getInicio() {
		return inicio;
	}

	public void setInicio(URIGeral inicio) {
		this.inicio = inicio;
	}

	public URIGeral getFim() {
		return fim;
	}

	public void setFim(URIGeral fim) {
		this.fim = fim;
	}

	public String getComplemento() {
		return Constantes.BARRA+"["+getRelacao().getTipoEComplemento()+ ","+
	getInicio().getTipoEComplemento()+","+
				getFim().getTipoEComplemento()+"]";
	}
}
