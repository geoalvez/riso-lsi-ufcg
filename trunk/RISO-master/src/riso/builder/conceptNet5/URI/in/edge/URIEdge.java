package riso.builder.conceptNet5.URI.in.edge;

import riso.builder.conceptNet5.URI.in.URIGeral;
import riso.builder.conceptNet5.URI.in.types.ObjetosURIConceptNet;

public class URIEdge extends URIGeral {

	private String edge;
	
	public URIEdge(String edge) {
		super(ObjetosURIConceptNet.ID, edge);
		setEdge(edge);
	}

	public String getEdge() {
		return edge;
	}

	public void setEdge(String edge) {
		this.edge = edge;
	}
}
