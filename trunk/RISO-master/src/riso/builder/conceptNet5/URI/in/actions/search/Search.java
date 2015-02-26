package riso.builder.conceptNet5.URI.in.actions.search;

import riso.builder.conceptNet5.URI.in.URIGeral;
import riso.builder.conceptNet5.URI.in.actions.ComplementoRestricoes;
import riso.builder.conceptNet5.URI.in.types.ObjetosURIConceptNet;


public class Search extends URIGeral{
	
	
	private ComplementoRestricoes search;
	
	public Search(ComplementoRestricoes search){
		super(ObjetosURIConceptNet.SEARCH, search.getComplemento());
	}

	public ComplementoRestricoes getSearch() {
		return search;
	}

	public void setSearch(ComplementoRestricoes search) {
		this.search = search;
	}
	
}
