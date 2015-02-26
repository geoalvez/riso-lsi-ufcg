package riso.builder.conceptNet5.URI.in;

import riso.builder.conceptNet5.URI.Constantes;
import riso.builder.conceptNet5.URI.in.types.IdiomasConceptNet;
import riso.builder.conceptNet5.URI.in.types.ObjetosURIConceptNet;


public class URIGeral {

	private ObjetosURIConceptNet raiz;
	private ObjetosURIConceptNet tipoElemento;
	private String complemento;


	public URIGeral(ObjetosURIConceptNet tipoElemento){
		setRaiz(ObjetosURIConceptNet.RAIZ);
		setTipoElemento(tipoElemento);
		setComplemento("");
	}

	public URIGeral(ObjetosURIConceptNet tipoElemento, String complemento){
		setRaiz(ObjetosURIConceptNet.RAIZ);
		setTipoElemento(tipoElemento);
		setComplemento(complemento);
	}

	public URIGeral(ObjetosURIConceptNet tipoElemento, IdiomasConceptNet idioma, String complemento){
		setRaiz(ObjetosURIConceptNet.RAIZ);
		setTipoElemento(tipoElemento);
		setComplemento(complemento);
	}

	public URIGeral(ObjetosURIConceptNet raiz, ObjetosURIConceptNet tipoElemento, IdiomasConceptNet idioma, String complemento){
		setRaiz(raiz);
		setTipoElemento(tipoElemento);
		setComplemento(complemento);
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public ObjetosURIConceptNet getRaiz() {
		return raiz;
	}

	public void setRaiz(ObjetosURIConceptNet raiz) {
		this.raiz = raiz;
	}

	public ObjetosURIConceptNet getTipoElemento() {
		return tipoElemento;
	}

	public void setTipoElemento(ObjetosURIConceptNet tipoElemento) {
		this.tipoElemento = tipoElemento;
	}

	public String getTipoEComplemento(){
		return Constantes.BARRA+getTipoElemento().getString()+getComplemento();
	}

	@Override
	public String toString() {

		return getRaiz().getString()+"/"+getTipoElemento().getString()+getComplemento();

	}

}
