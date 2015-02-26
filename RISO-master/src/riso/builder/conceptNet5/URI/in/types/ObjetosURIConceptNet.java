package riso.builder.conceptNet5.URI.in.types;

import riso.builder.conceptNet5.URI.Constantes;
import riso.builder.conceptNet5.URI.in.interfaces.Retornavel;

public enum ObjetosURIConceptNet implements Retornavel {

	RAIZ(Constantes.RAIZ),
	CONCEITOS(Constantes.CONCEITOS),
	AFIRMACOES(Constantes.AFIRMACOES),
	CONTEXTO(Constantes.CONTEXTOS),
	CONTEXTOS_TOTAL(Constantes.CONTEXTOS_TOTAL),
	DATASETS(Constantes.CONJUNTO_DADOS),
	RELACOES(Constantes.RELACOES),
	ID(Constantes.ID),
	SEARCH(Constantes.SEARCH),
	ASSOC(Constantes.ASSOC),
	FONTE(Constantes.TIPO_CONTRIBUICAO);
	
	private String objeto;

	private ObjetosURIConceptNet(String objeto) {
		this.objeto = objeto;
	}

	public String getString() {
		return objeto;	
	}
}
