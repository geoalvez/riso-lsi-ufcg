package riso.builder.wordnet.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import riso.builder.wordnet.JWNL;
import riso.builder.wordnet.JWNLException;
import riso.builder.wordnet.data.IndexWord;
import riso.builder.wordnet.data.IndexWordSet;
import riso.builder.wordnet.data.POS;
import riso.builder.wordnet.data.Pointer;
import riso.builder.wordnet.data.PointerTarget;
import riso.builder.wordnet.data.PointerType;
import riso.builder.wordnet.data.Synset;
import riso.builder.wordnet.dictionary.DatabaseBackedDictionary;

public class WordNetEnrichment {
	
	
	private String lemma;
	private static WordNetEnrichment instancia = null;
	private Map<POS, List<SynsetEnrichment>> grupoSentidoTipoMorfologico = new HashMap<POS, List<SynsetEnrichment>>();
	
	private WordNetEnrichment(){
		
	}
	
	public static WordNetEnrichment getInstance(){
		inicializaInformacao();		
		if(instancia == null){
			instancia = new WordNetEnrichment();
			return instancia;
		}else{
			return instancia;
		}
	}
	
	public void enriquecimentoDireto(String lemma){
		
		try {
			IndexWordSet indexWordSet = DatabaseBackedDictionary.getInstance().lookupAllIndexWords(lemma);
			IndexWord[] indWord = indexWordSet.getIndexWordArray();
			
			for (IndexWord indexWord : indWord) {
				POS pos = indexWord.getPOS();
				Synset[] sentidos = indexWord.getSenses();
				System.out.println(pos.toString() + " QuantidadeDeSentidos: "+sentidos.length);
				List<SynsetEnrichment> enriquecimentosDeConceito = new ArrayList<SynsetEnrichment>();
				for (Synset conceito : sentidos) {
					SynsetEnrichment enriquecimento = enriquecimentoConceito(conceito);
					enriquecimentosDeConceito.add(enriquecimento);
				}	
				grupoSentidoTipoMorfologico.put(pos, enriquecimentosDeConceito);
			}		
		
		} catch (JWNLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private SynsetEnrichment enriquecimentoConceito(Synset sentido) throws JWNLException{
		Map<PointerType,List<PointerTarget>> grupoRelacaoSynset = new HashMap<PointerType, List<PointerTarget>>();
		
		Pointer[] pont = sentido.getPointers();
		for (Pointer pt : pont) {
			PointerType pointerTyper = pt.getType();
			List<PointerTarget> conceitos = grupoRelacaoSynset.get(pointerTyper);
			if(conceitos == null){
				conceitos = new ArrayList<PointerTarget>();
				conceitos.add(pt.getTarget());
				grupoRelacaoSynset.put(pointerTyper,conceitos);
			}else{
				conceitos.add(pt.getTarget());
			}
		}
		return new SynsetEnrichment(sentido,grupoRelacaoSynset) ;
	}
	
	
	public String toString(){
		String saida = "";
		Map<POS, List<SynsetEnrichment>> grupoTipoSentido = getGrupoSentidoTipoMorfologico();

		for (POS pos : grupoTipoSentido.keySet()) {
			saida += pos.toString()+":\n";
			List<SynsetEnrichment> conceitos = grupoTipoSentido.get(pos);
			saida+="\n";
			for (int i = 0; i < conceitos.size(); i++) {
				saida += (i+1)+")"+conceitos.get(i).toString();
			}
		}
		return saida;
				
	}
	
	 public String getLemma() {
		return lemma;
	}

	public void setLemma(String lemma) {
		this.lemma = lemma;
	}

	public Map<POS, List<SynsetEnrichment>> getGrupoSentidoTipoMorfologico() {
		return grupoSentidoTipoMorfologico;
	}

	public void setGrupoSentidoTipoMorfologico(
			Map<POS, List<SynsetEnrichment>> grupoSentidoTipoMorfologico) {
		this.grupoSentidoTipoMorfologico = grupoSentidoTipoMorfologico;
	}

	private static void inicializaInformacao(){
		try {
			JWNL.initialize(new FileInputStream(Utilities.NOME_ARQUIVO_PROPRIEDADES));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JWNLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	 
}
