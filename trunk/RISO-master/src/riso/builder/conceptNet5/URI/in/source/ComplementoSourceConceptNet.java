package riso.builder.conceptNet5.URI.in.source;

import riso.builder.conceptNet5.URI.Constantes;
import riso.builder.conceptNet5.URI.in.interfaces.Complementavel;
import riso.builder.conceptNet5.URI.in.types.Sources;

public class ComplementoSourceConceptNet implements Complementavel {

	private Sources source;
	private String complementacao;
	
	public ComplementoSourceConceptNet(Sources source){
		setSource(source);
		setComplemento("");
	}
	
	public ComplementoSourceConceptNet(Sources source, String complemento){
		setSource(source);
		setComplemento(complemento);
	}
	
	public Sources getSource() {
		return source;
	}

	public void setSource(Sources source) {
		this.source = source;
	}

	public void setComplemento(String complemento) {
		this.complementacao = complemento;
	}
	
	public String getComplementacao(){
		return "/"+complementacao;
	}

	public String getComplemento() {
		return Constantes.BARRA+getSource().getString()+getComplementacao();
	}

	
}
