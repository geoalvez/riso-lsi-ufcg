package riso.builder.conceptNet5.URI.out;

public class RelacaoSemantica {

	private String relacao;
	private String inversa;
	
	public RelacaoSemantica(String relacao, String inversa){
		setRelacao(relacao);
		setInversa(inversa);
	}
	
	public String getRelacao() {
		return relacao;
	}
	
	public void setRelacao(String relacao) {
		this.relacao = relacao;
	}
	
	public String getInversa() {
		return inversa;
	}
	
	public void setInversa(String inversa) {
		this.inversa = inversa;
	}
}
