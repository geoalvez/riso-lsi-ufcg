package jena;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.ontology.OntModel;


public class ConceitoExpandido {

	private String conceito;
	private List<VetorTematico> vetoresTematicos = new ArrayList<VetorTematico>(); 
	private OntModel grafoMinimo;
	
	public ConceitoExpandido(String conceito, List<VetorTematico> vetoresTematicos, OntModel grafoMinimo ){
		setConceito(conceito);
		setVetoresTematicos(vetoresTematicos);
		setGrafoMinimo(grafoMinimo);
		
	}
	public String getConceito() {
		return conceito;
	}
	public void setConceito(String conceito) {
		this.conceito = conceito;
	}
	public List<VetorTematico> getVetoresTematicos() {
		return vetoresTematicos;
	}
	public void setVetoresTematicos(List<VetorTematico> vetoresTematicos) {
		this.vetoresTematicos = vetoresTematicos;
	}
	public OntModel getGrafoMinimo() {
		return grafoMinimo;
	}
	public void setGrafoMinimo(OntModel grafoMinimo) {
		this.grafoMinimo = grafoMinimo;
	}
}
