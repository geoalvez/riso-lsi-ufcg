package riso.builder.conceptNet5.URI.out;

import java.util.Set;



public class Nodo {

	private Estrutura estrutura;
	private String conceito;


	public Nodo(String conceito){
		setConceito(conceito);
		setEstrutura(new Estrutura());
	}

	public Estrutura getEstrutura() {
		return estrutura;
	}

	public void setEstrutura(Estrutura estrutura) {
		this.estrutura = estrutura;
	}

	public String getConceito() {
		return conceito;
	}


	public void setConceito(String conceito) {
		this.conceito = conceito;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((conceito == null) ? 0 : conceito.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nodo other = (Nodo) obj;
		if (conceito == null) {
			if (other.conceito != null)
				return false;
		} else if (!conceito.equals(other.conceito))
			return false;
		return true;
	}

	public String toString(){

		String no = getConceito()+" :{\n";
		String relacoes = "";

		for (String relacao : getEstrutura().getRelacoes().keySet()) {
			relacoes+=relacao+" :{";
			Set<Nodo> nodos = getEstrutura().getRelacoes().get(relacao);
			for (Nodo nodo : nodos) {
				relacoes+=nodo+",";
			}
			relacoes=relacoes.substring(0, relacoes.length()-1);
			relacoes+="}\n";
		} 

		String frase = getEstrutura().getFrase();
		
		no += relacoes+"\n"+frase;

		return no;

	}


}
