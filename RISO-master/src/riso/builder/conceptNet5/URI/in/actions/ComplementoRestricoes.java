package riso.builder.conceptNet5.URI.in.actions;

import java.util.Map;

import riso.builder.conceptNet5.URI.in.interfaces.Complementavel;

public class ComplementoRestricoes implements Complementavel {

	private Map<String, String> restricoes;
	
	public ComplementoRestricoes(Map<String, String> restricoes){
		setRestricoes(restricoes);
	}
	public Map<String, String> getRestricoes() {
		return restricoes;
	}
	public void setRestricoes(Map<String, String> restricoes) {
		this.restricoes = restricoes;
	}
	
	/**
	 * Solicitacao GET HTTP ?,&
	 */
	public String getComplemento(){
		String restricao="?";
		for (String str : getRestricoes().keySet()) {
			restricao+=str+"="+getRestricoes().get(str)+"&";
		}
		restricao = restricao.substring(0, restricao.length()-1);
		return restricao;
	} 
}
