package riso.builder.documents;


public class Documento {
	
	private String palavrasMarcadas;
	private String vetorParagrafo;
	private String nomeArquivo;


	public Documento(String palavrasMarcadas, String vetorParagrafo, String nomeArquivo){

		setPalavrasMarcadas(palavrasMarcadas);
		setVetorParagrafo(vetorParagrafo);
		setNomeArquivo(nomeArquivo);
	}


	public String getPalavrasMarcadas() {
		return palavrasMarcadas;
	}


	public void setPalavrasMarcadas(String palavrasMarcadas) {
		this.palavrasMarcadas = palavrasMarcadas;
	}


	public String getVetorParagrafo() {
		return vetorParagrafo;
	}


	public void setVetorParagrafo(String vetorParagrafo) {
		this.vetorParagrafo = vetorParagrafo;
	}


	public String getNomeArquivo() {
		return nomeArquivo;
	}


	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

}
