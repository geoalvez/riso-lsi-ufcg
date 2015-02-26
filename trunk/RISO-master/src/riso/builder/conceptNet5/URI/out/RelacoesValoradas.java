package riso.builder.conceptNet5.URI.out;

public class RelacoesValoradas implements Comparable<RelacoesValoradas> {

	private String relacao;
	private int valor;
	
	public RelacoesValoradas(String relacao, int valor){
		setRelacao(relacao);
		setValor(valor);
	}
	public String getRelacao() {
		return relacao;
	}
	public void setRelacao(String relacao) {
		this.relacao = relacao;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}

	public int compareTo(RelacoesValoradas o) {
		if((o == null) || (o.getValor() < getValor())){
			return 1;
		}else if(o.getValor() > getValor()){
			return -1;
		}
		return 0;
	}
	
	
}
