package riso.builder.conceptNet5.URI.in.actions.association;

import riso.builder.conceptNet5.URI.in.actions.ComplementoRestricoes;
import riso.builder.conceptNet5.URI.in.types.IdiomasConceptNet;

public abstract class Association{

	private IdiomasConceptNet idioma;

	private ComplementoRestricoes restricoes;

	public IdiomasConceptNet getIdioma() {
		return idioma;
	}

	public void setIdioma(IdiomasConceptNet idioma) {
		this.idioma = idioma;
	}
	
	public ComplementoRestricoes getRestricoes() {
		return restricoes;
	}

	public void setRestricoes(ComplementoRestricoes restricoes) {
		this.restricoes = restricoes;
	}

}
