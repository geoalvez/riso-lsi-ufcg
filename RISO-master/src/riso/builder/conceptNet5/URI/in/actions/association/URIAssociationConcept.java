package riso.builder.conceptNet5.URI.in.actions.association;

import riso.builder.conceptNet5.URI.in.URIGeral;
import riso.builder.conceptNet5.URI.in.types.ObjetosURIConceptNet;

public class URIAssociationConcept extends URIGeral {

	private ComponenteAssociationConcept conceito;
	
	public URIAssociationConcept(ComponenteAssociationConcept conceito){
		super(ObjetosURIConceptNet.ASSOC, conceito.getComplemento());
	}

	public ComponenteAssociationConcept getConceito() {
		return conceito;
	}

	public void setConceito(ComponenteAssociationConcept conceito) {
		this.conceito = conceito;
	}
}
