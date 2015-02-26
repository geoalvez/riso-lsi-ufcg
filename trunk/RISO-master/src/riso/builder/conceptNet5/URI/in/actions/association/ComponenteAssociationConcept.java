package riso.builder.conceptNet5.URI.in.actions.association;

import riso.builder.conceptNet5.URI.in.actions.ComplementoRestricoes;
import riso.builder.conceptNet5.URI.in.concept.URIConceito;
import riso.builder.conceptNet5.URI.in.interfaces.Complementavel;

public class ComponenteAssociationConcept extends Association implements Complementavel {

	
	private URIConceito uri;
	
	public ComponenteAssociationConcept(URIConceito uri, ComplementoRestricoes restricoes){
		setUri(uri);
		setIdioma(getUri().getComplementoCCN().getIdioma());
		setRestricoes(restricoes);
	}
	public ComponenteAssociationConcept(URIConceito uri){
		setUri(uri);
		setIdioma(getUri().getComplementoCCN().getIdioma());

	}
	
	public URIConceito getUri() {
		return uri;
	}

	public void setUri(URIConceito uri) {
		this.uri = uri;
	}

	public String getComplemento() {
		String compl = getUri().getTipoEComplemento();
		if(getRestricoes() == null){
			return compl;
		}
		return compl + getRestricoes().getComplemento();
	}

	
}
