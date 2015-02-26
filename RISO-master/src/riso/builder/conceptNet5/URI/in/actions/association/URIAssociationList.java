package riso.builder.conceptNet5.URI.in.actions.association;

import riso.builder.conceptNet5.URI.in.URIGeral;
import riso.builder.conceptNet5.URI.in.types.ObjetosURIConceptNet;

public class URIAssociationList extends URIGeral{

	private ComponenteAssociationList lista;

	public URIAssociationList(ComponenteAssociationList lista) {
		super(ObjetosURIConceptNet.ASSOC, lista.getComplemento());
		setLista(lista);
	}

	public ComponenteAssociationList getLista() {
		return lista;
	}

	public void setLista(ComponenteAssociationList lista) {
		this.lista = lista;
	}
}
