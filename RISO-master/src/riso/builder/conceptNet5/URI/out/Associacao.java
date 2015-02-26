package riso.builder.conceptNet5.URI.out;

import java.util.List;

public class Associacao {

	private List<List<String>> terms;
	private List<List<String>> similar;

	public List<List<String>> getSimilar() {
		return similar;
	}

	public void setSimilar(List<List<String>> similar) {
		this.similar = similar;
	}

	public List<List<String>> getTerms() {
		return terms;
	}

	public void setTerms(List<List<String>> terms) {
		this.terms = terms;
	}
	
	private String montaString(String elemento, List<List<String>> estrutura){
		String st = elemento+"{";
		for (List<String> list : estrutura) {
			st+= "[";
			for (String str : list) {
				st+=str+",";
			}
			st=st.substring(0, st.length()-1);
			st+="]";
		}
		st+="}";
		return st;
	}

	public String toString(){
		
		return montaString("termos", getTerms()) + "\n"+montaString("similar", getSimilar());
	}
	
}
