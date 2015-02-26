package riso.builder.conceptNet5.URI.out;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Estrutura {

	private Map<String,Set<Nodo>> relacoes = new HashMap<String, Set<Nodo>>();
	private String frase;
	
	public Map<String, Set<Nodo>> getRelacoes() {
		return relacoes;
	}
	
	public void setRelacoes(Map<String, Set<Nodo>> relacoes) {
		this.relacoes = relacoes;
	}
	
	public String getFrase() {
		return frase;
	}
	
	public void setFrase(String frase) {
		this.frase = frase;
	}
	
	public void add(String relacao, Nodo nodo){
		
		
	}
	
	
}
