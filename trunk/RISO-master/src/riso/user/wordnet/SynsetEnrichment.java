package riso.user.wordnet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.didion.jwnl.data.PointerTarget;
import net.didion.jwnl.data.PointerType;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.data.Word;

public class SynsetEnrichment {

	private Synset conceito;
	
	private Map<PointerType,List<PointerTarget>> enriquecimento = new HashMap<PointerType, List<PointerTarget>>();
	
	private Set<Word> sinonimos;

	public SynsetEnrichment(Synset conceito, Map<PointerType,List<PointerTarget>> enriquecimento){
		this.conceito = conceito;
		setSinonimos(getConceito());
		this.enriquecimento = enriquecimento;
	}
	
	public Set<Word> getSinonimos() {
		return sinonimos;
	}

	private void setSinonimos(Synset conceito) {
		Set<Word> sin = new HashSet<Word>();
		for (Word word : conceito.getWords()) {
			sin.add(word);
		}
		this.sinonimos = sin;
	}

	public Synset getConceito() {
		return conceito;
	}
	
	public void setConceito(Synset conceito) {
		this.conceito = conceito;
	}
	
	public Map<PointerType, List<PointerTarget>> getEnriquecimento() {
		return enriquecimento;
	}
	
	public void setEnriquecimento(
			Map<PointerType, List<PointerTarget>> enriquecimento) {
		this.enriquecimento = enriquecimento;
	}
	
	public String toString(){
		String saida = "    ¶[Detalhes do Conceito] \n";
		saida += "    Conceito: "+conceito.toString()+" :\n";
		saida += "    Sinonimos: "+obtemLemmas()+"\n";
		saida += "    [Relações Semanticas]: \n";
		saida += obtemRelacoesSemanticas();
		return saida;
	}
	
	
	private String obtemRelacoesSemanticas(){
		String relacoes = "";
		for (PointerType pt :getEnriquecimento().keySet()) {
			List<PointerTarget> targets = getEnriquecimento().get(pt);
			relacoes+="      "+pt.getLabel()+" = "+targets.size()+": \n";
			for(PointerTarget pointerT: targets){
				relacoes+="        ø"+pointerT.toString()+"\n"; 
			}
			relacoes+="\n";
		}
		return relacoes;
	}
	
	private String obtemLemmas(){
		String lemmas = "";
		Set<Word> words = getSinonimos();
		for (Word word : words) {
			lemmas+=word.getLemma()+",";
		}
		lemmas = lemmas.substring(0, lemmas.length()-1);
		return lemmas;
	}
	
}
