package riso.builder.conceptNet5.URI.in.datasets;

import riso.builder.conceptNet5.URI.in.URIGeral;
import riso.builder.conceptNet5.URI.in.types.ObjetosURIConceptNet;

public class URIDataSets extends URIGeral {
	
	
	private ComplementoDataSetConceptNet dataset;

	public URIDataSets(ComplementoDataSetConceptNet dataset){
		super(ObjetosURIConceptNet.DATASETS, dataset.getComplemento());
		setDataset(dataset);
	}

	public ComplementoDataSetConceptNet getDataset() {
		return dataset;
	}

	public void setDataset(ComplementoDataSetConceptNet dataset) {
		this.dataset = dataset;
	}
	
}
