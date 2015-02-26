package riso.builder.conceptNet5.URI.in.types;

import riso.builder.conceptNet5.URI.Constantes;
import riso.builder.conceptNet5.URI.in.interfaces.Retornavel;

public enum DataSets implements Retornavel  {

	WORDNET(Constantes.DATA_WORDNET),
	DBPEDIA(Constantes.DATA_DBPEDIA),
	WIKTIONARY(Constantes.DATA_WIKTIONARY),
	REVERB(Constantes.DATA_REVERB),
	CONCEPTNET(Constantes.DATA_CONCEPTNET)
	;
	
	private String dataset;
	private DataSets(String dataset){
		this.dataset = dataset;
	}
	public String getString() {
		return dataset;
	}
}
