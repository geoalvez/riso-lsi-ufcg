package riso.consulta.entity;

public class Consulta {

	private String texto;
	
	public Consulta(String texto){
		setTexto(texto);		
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public void padroniza(){
		if(getTexto() != null){
			setTexto(getTexto().toLowerCase());
		}
	}
	
	
	
	
}
