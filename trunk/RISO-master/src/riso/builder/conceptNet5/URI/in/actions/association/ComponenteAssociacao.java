package riso.builder.conceptNet5.URI.in.actions.association;

import riso.builder.conceptNet5.URI.Constantes;

public class ComponenteAssociacao {

	private String termo;
	private String peso;


	public ComponenteAssociacao(String termo, String peso){
		setTermo(termo);
		setPeso(peso);
	}

	public ComponenteAssociacao(String termo){
		setTermo(termo);
	}

	public String getTermo() {
		return termo;
	}

	public void setTermo(String termo) {
		this.termo = termo;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}
	
	public String getPesoConf(){
		if(getPeso() == null){
			return"";
		}
		return Constantes.ARROBA+getPeso();
	}
	
	public String toString(){
		
		return getTermo()+getPesoConf();
	}
}
