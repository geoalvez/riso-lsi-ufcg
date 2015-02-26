package riso.builder.conceptNet5.URI.in.types;

import riso.builder.conceptNet5.URI.Constantes;
import riso.builder.conceptNet5.URI.in.interfaces.Retornavel;

public enum IdiomasConceptNet implements Retornavel {
 
	INGLES(Constantes.IDIOMA_INGLES);
	
	private String idioma;

	private IdiomasConceptNet(String idioma) {
		this.idioma = idioma;
	}

	public String getString() {
		return idioma;
	}
	
}
