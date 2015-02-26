package riso.builder.conceptNet5.URI.out;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import riso.builder.conceptNet5.URI.Constantes;
import riso.builder.wordnet.JWNL;
import riso.builder.wordnet.JWNLException;
import riso.builder.wordnet.data.IndexWord;
import riso.builder.wordnet.data.IndexWordSet;
import riso.builder.wordnet.data.POS;
import riso.builder.wordnet.dictionary.DatabaseBackedDictionary;

public final class UsuarioWordNet {

	public static final int POSICAO_GERAL = 1;
	public static final String ESPACO = " ";

	private static final UsuarioWordNet USUARIOWORDNET = new UsuarioWordNet();

	private UsuarioWordNet(){
		carregaInformacoesWordNet();
	}

	public static UsuarioWordNet getInstance(){
		return USUARIOWORDNET;
	}


	private void carregaInformacoesWordNet(){
		try {
			JWNL.initialize(new FileInputStream("src/config/wordnet/database_properties.xml"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JWNLException e) {
			e.printStackTrace();
		}
	}


	private boolean verificaExistenciaMorfologica(POS pos, String termo){
		IndexWordSet indexWordSet = null;
		try {
			indexWordSet = DatabaseBackedDictionary.getInstance().lookupAllIndexWords(termo);
			IndexWord[] indWord = indexWordSet.getIndexWordArray();
			if(indWord.length == Constantes.ZERO){
				return false;
			}
		} catch (JWNLException e) {
			e.printStackTrace();
		}
		return indexWordSet.isValidPOS(pos);
	}

	//	public void agregaSinonimos(Nodo no){
	//		String termo = no.getConceito();
	//		IndexWordSet indexWordSet = null;
	//		try {
	//
	//			indexWordSet = DatabaseBackedDictionary.getInstance().lookupAllIndexWords(termo);
	//			IndexWord idWord = indexWordSet.getIndexWord(POS.NOUN);
	//
	//			Synset synset = idWord.getSense(POSICAO_GERAL);
	//			for (Word word : synset.getWords()) {
	//				no.getSinonimos().add(word.getLemma());
	//			}
	//
	//		} catch (JWNLException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//
	//
	//	}


	//	public void criaHierarquia(Nodo no){
	//
	//		IndexWordSet indexWordSet = null;
	//		final int ZERO = Constantes.ZERO;
	//
	//		try {
	//			indexWordSet = DatabaseBackedDictionary.getInstance().lookupAllIndexWords(no.getConceito());
	//			IndexWord idWord = indexWordSet.getIndexWord(POS.NOUN);
	//
	//			if(idWord.getSenseCount() > ZERO){
	//
	//				Synset syn = idWord.getSense(POSICAO_GERAL);
	//				Pointer[] pontos = syn.getPointers(PointerType.HYPERNYM);
	//				Nodo noDaVez = no;
	//				while(pontos.length > ZERO){
	//					Pointer ponto = pontos[ZERO];
	//					Word words[] = ponto.getTargetSynset().getWords();
	//					Nodo noPai = new Nodo(words[ZERO].getLemma());
	//					for (int i = 1; i < words.length; i++) {
	//						noPai.getSinonimos().add(words[i].getLemma());
	//					}
	//					noPai.setFrase(ponto.getTargetSynset().getGloss());
	//					noDaVez.getPai().add(noPai);
	//					noPai.getFilho().add(noDaVez);
	//
	//					pontos = ponto.getTargetSynset().getPointers(PointerType.HYPERNYM);
	//					noDaVez = noPai;
	//				}
	//			}
	//		} catch (JWNLException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//
	//	}

	public boolean verificaRelacao(String relacao){
		final String RELACAO = "/"+Constantes.RELACOES+"/";

		return relacao.startsWith(RELACAO);
//		if(termoDaVez.startsWith(termo+ESPACO)){
//			return true;
//			
//		}else if(termoDaVez.endsWith(ESPACO+termo)){
//			
//			int ultimaOcorrenciaEspaco = termoDaVez.lastIndexOf(ESPACO);
//			String termoComposto = termoDaVez.substring(0, ultimaOcorrenciaEspaco) ;
//			boolean validade = verificaExistenciaMorfologica(POS.ADJECTIVE,termoComposto);
//			
//			return validade;
//			
//		}else if(termoDaVez.equals(termo)){
//			return true;
//		}
//		return false;
	}


}
