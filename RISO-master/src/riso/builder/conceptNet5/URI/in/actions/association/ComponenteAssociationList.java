package riso.builder.conceptNet5.URI.in.actions.association;

import java.util.List;

import riso.builder.conceptNet5.URI.Constantes;
import riso.builder.conceptNet5.URI.in.actions.ComplementoRestricoes;
import riso.builder.conceptNet5.URI.in.interfaces.Complementavel;
import riso.builder.conceptNet5.URI.in.types.IdiomasConceptNet;

public class ComponenteAssociationList extends Association implements Complementavel {

	
	/**
	 * Lista de componentes cada componente pode ser um termo ou uma frase nominal
	 */
	private List<ComponenteAssociacao> componentes;
		
	public ComponenteAssociationList(List<ComponenteAssociacao> componentes, IdiomasConceptNet idioma, ComplementoRestricoes restricoes){
		setComponentes(componentes);
		setRestricoes(restricoes);
		setIdioma(idioma);
	}
	
	public ComponenteAssociationList(List<ComponenteAssociacao> componentes, ComplementoRestricoes restricoes){
		setComponentes(componentes);
		setRestricoes(restricoes);
		setIdioma(IdiomasConceptNet.INGLES);
	}
	
	public ComponenteAssociationList(List<ComponenteAssociacao> componentes){
		setComponentes(componentes);
		setIdioma(IdiomasConceptNet.INGLES);
	}

	public List<ComponenteAssociacao> getComponentes() {
		return componentes;
	}

	public void setComponentes(List<ComponenteAssociacao> componentes) {
		this.componentes = componentes;
	}


	public String getComplemento() {
		String list = Constantes.BARRA+Constantes.ASSOC_LIST+Constantes.BARRA+getIdioma().getString()+Constantes.BARRA;
		for (ComponenteAssociacao componente : getComponentes()) {
			list+= componente.toString()+",";
		}
		list = list.substring(0, list.length()-1);
		
		if(getRestricoes()!= null){
			list+=getRestricoes().getComplemento();
		}
		return list;
	}
	
	
	
}
