package riso.builder.conceptNet5.URI.in.types;

import riso.builder.conceptNet5.URI.Constantes;
import riso.builder.conceptNet5.URI.in.interfaces.Retornavel;

public enum MorfologiaConceptNet implements Retornavel {

	ISENTA(""),
	VERBO(Constantes.MORFOLOGIA_VERBO);
	
	private String tipo;

	private MorfologiaConceptNet(String tipo) {
		this.tipo = tipo;
	}

	public String getString() {
		return tipo;
	}
	
}
