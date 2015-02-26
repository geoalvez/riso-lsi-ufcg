package riso.builder.wordnet.main;

public class TestandoWordnet {
	
	public static void main(String args[]){
		
		WordNetEnrichment enriquecimento =	WordNetEnrichment.getInstance(); 
		enriquecimento.enriquecimentoDireto("dog");
		System.out.println(enriquecimento.toString());
		
	}

}
