package riso.builder.conceptNet5.URI.in.concept;

import riso.builder.conceptNet5.URI.Constantes;
import riso.builder.conceptNet5.URI.in.interfaces.Complementavel;
import riso.builder.conceptNet5.URI.in.types.IdiomasConceptNet;
import riso.builder.conceptNet5.URI.in.types.MorfologiaConceptNet;

public class ComplementoConceitoConceptNet implements Complementavel {

	private String palavra;
	private MorfologiaConceptNet morfologia;
	private IdiomasConceptNet idioma;

	public ComplementoConceitoConceptNet(String palavra, MorfologiaConceptNet morfologiaConceptNet, IdiomasConceptNet idioma){
		setPalavra(palavra);
		setMorfologia(morfologiaConceptNet);
		setIdioma(idioma);
	}
	public ComplementoConceitoConceptNet(String palavra, IdiomasConceptNet idioma){
		setPalavra(palavra);
		setMorfologia(MorfologiaConceptNet.ISENTA);
		setIdioma(idioma);
	}
	
	public ComplementoConceitoConceptNet(String palavra){
		setPalavra(palavra);
		setMorfologia(MorfologiaConceptNet.ISENTA);
		setIdioma(IdiomasConceptNet.INGLES);
	}
	
	public IdiomasConceptNet getIdioma() {
		return idioma;
	}

	public void setIdioma(IdiomasConceptNet idioma) {
		this.idioma = idioma;
	}
	
	public MorfologiaConceptNet getMorfologia() {
		return morfologia;
	}

	public void setMorfologia(MorfologiaConceptNet morfologia) {
		this.morfologia = morfologia;
	}

	public String getPalavra() {
		return palavra;
	}
	
	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}

	public String getComplemento() {
		return Constantes.BARRA+getIdioma().getString()+Constantes.BARRA+getPalavra()+Constantes.BARRA+getMorfologia().getString();
	}
}
