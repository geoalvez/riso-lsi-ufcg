package riso.builder.conceptNet5.URI.in.datasets;

import riso.builder.conceptNet5.URI.Constantes;
import riso.builder.conceptNet5.URI.in.interfaces.Complementavel;
import riso.builder.conceptNet5.URI.in.types.DataSets;

public class ComplementoDataSetConceptNet implements Complementavel {
	
	private DataSets dataset;
	
	public DataSets getDataset() {
		return dataset;
	}

	public void setDataset(DataSets dataset) {
		this.dataset = dataset;
	}

	public ComplementoDataSetConceptNet(DataSets dataset){
		setDataset(dataset);
	}

	public String getComplemento() {
		return Constantes.BARRA+getDataset().getString();
	}
	
}
