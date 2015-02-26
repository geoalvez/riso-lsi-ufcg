package riso.builder.conceptNet5.URI.in.types;

import riso.builder.conceptNet5.URI.Constantes;
import riso.builder.conceptNet5.URI.in.interfaces.Retornavel;

public enum Sources implements Retornavel {

	SOURCE_CONSTRIBUICAO_HUMANA(Constantes.SOURCE_CONSTRIBUICAO_HUMANA),
	SOURCE_WEB_SITES(Constantes.SOURCE_WEB_SITES),
	SOURCE_REGRA(Constantes.SOURCE_REGRA),
	SOURCE_WORDNET(Constantes.SOURCE_WORDNET),
	SOURCE_DBPEDIA(Constantes.SOURCE_DBPEDIA)
	;
	
	private String source;
	private Sources(String sourcer){
		this.source = sourcer;
	}
	public String getString() {
		
		return source ;
	}
	
	

}
