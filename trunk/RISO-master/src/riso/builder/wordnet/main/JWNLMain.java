package riso.builder.wordnet.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Set;

import riso.builder.wordnet.JWNL;

import riso.builder.wordnet.JWNLException;
import riso.builder.wordnet.data.DictionaryElementType;
import riso.builder.wordnet.data.IndexWord;
import riso.builder.wordnet.data.IndexWordSet;
import riso.builder.wordnet.data.POS;
import riso.builder.wordnet.data.Pointer;
import riso.builder.wordnet.data.PointerTarget;
import riso.builder.wordnet.data.PointerType;
import riso.builder.wordnet.data.Synset;
import riso.builder.wordnet.data.Word;
import riso.builder.wordnet.dictionary.DatabaseBackedDictionary;

public class JWNLMain {
	
	public static void main(String args[]) throws FileNotFoundException, JWNLException{
		
		//esta linha contem o xml ou o conjunto de configuracoes utilizado para carregar as propriedades escolhidas...
		JWNL.initialize(new FileInputStream("WordNet/database_properties.xml"));
		
		/*
		 * TODO OK.
		 * lookupIndexWord(), lookupAllIndexWords(), and getIndexWordIterator()
		 * definir como utilizar cada um... para que servem... quais as particularidades de cada implementação.
		 * OK.. tarea hecha e descrita abajo...
		 * 
		 * TODO
		 *  The other methods you may be interested in Relationship.findRelationships(), and those in PointerUtils.
		 * TODO
		 *  PointerUtils.getHypernymTree() 
		 * 
		 * TODO OK.
		 *  Saber se maiusculas ou minusculas influenciam na busca.... 
		 *  No influencia.
		 */
		
		IndexWordSet indexWordSet = DatabaseBackedDictionary.getInstance().lookupAllIndexWords("greece");
		System.out.println(" == Informa�›es de INDEXWORDSET ==");
		System.out.println();
		IndexWord[] indWord = indexWordSet.getIndexWordArray();
		
		System.out.println("As possibilidades morfol—gicas da palavra...");
		
		for (IndexWord iW : indWord) {
			System.out.println(iW.getPOS());
			System.out.println("QuantidadePorSentido: "+iW.getSenseCount());
		}
		
		Set<POS> posSet = indexWordSet.getValidPOSSet();
		System.out.println("Quantidade: "+posSet.size());
		for (POS pos : posSet) {
			System.out.println(pos.getKey());
			System.out.println(pos.getLabel());
		}
		
		//Como Ž um conjunto pode vir coisas diferentes de POS...
		System.out.println("indexWordSet.getValidPOSSet(): "+indexWordSet.getValidPOSSet());
				
		System.out.println("indexWordSet.getLemma(): "+indexWordSet.getLemma());
		System.out.println("indexWordSet.getSenseCount(POS.NOUN): "+indexWordSet.getSenseCount(POS.NOUN));
		
		System.out.println("indexWordSet.isValidPOS(POS.VERB): "+indexWordSet.isValidPOS(POS.VERB));
		System.out.println("indexWordSet.size(): "+indexWordSet.size());
		System.out.println("indexWordSet.toString(): "+indexWordSet.toString());
		System.out.println();
		
		IndexWord idWord = indexWordSet.getIndexWord(POS.NOUN);
		System.out.println("> == Informa�›es de INDEXWORD ==");
		System.out.println();
		System.out.println("> idWord.getKey(): "+idWord.getKey());
		System.out.println("> idWord.getLemma(): "+idWord.getLemma());
		System.out.println("> idWord.getPOS(): "+idWord.getPOS());
		System.out.println();
		System.out.println("> idWord.getSense(1): "+idWord.getSense(1));
		System.out.println("> idWord.getSenses(): "+idWord.getSenses());
		System.out.println("> Quantidade de Sentidos: "+idWord.getSenses().length);
		System.out.println("> idWord.getSense(1): "+idWord.getSense(1));
		System.out.println("> idWord.getSenseCount(): "+idWord.getSenseCount());
		System.out.println("> idWord.getSynsetOffsets(): "+idWord.getSynsetOffsets()[0]);
		System.out.println("> idWord.getType() "+idWord.getType());
		System.out.println("> idWord.toString(): "+idWord.toString());
		
		POS pos = idWord.getPOS();
		System.out.println();
		System.out.println("> > == Informa�›es de POS ==");
		System.out.println();
		System.out.println("> > pos.getKey(): "+pos.getKey());
		System.out.println("> > pos.getLabel(): "+pos.getLabel());
		System.out.println("> > pos.toString(): "+pos.toString());
		System.out.println("> > pos.getPOSForLabel(dog): "+pos.getPOSForLabel("dog"));
		System.out.println("> > pos.getPOSForKey(dog): "+pos.getPOSForKey("dog"));
		System.out.println("> > pos.getAllPOS(): "+pos.getAllPOS());
		
		Synset syn = idWord.getSense(1);
		System.out.println();
		System.out.println("> > == Informa�›es de SYNSET ==");
		System.out.println();
		System.out.println("> > syn.containsWord(dog): "+syn.containsWord("dog"));
		System.out.println("> > syn.getGloss(): "+syn.getGloss());
		System.out.println("SAO IGUAISSS");
		System.out.println("> > syn.getKey(): "+syn.getKey());
		System.out.println("> > syn.getOffset(): "+syn.getOffset());
		System.out.println("> > syn.getLexFileId(): "+syn.getLexFileId());
		System.out.println("> > syn.getLexFileName(): "+syn.getLexFileName());
		System.out.println("> > syn.getPointers()[0]: "+syn.getPointers().length);
		
		
		/*
		 * Algunas funcionalidades utilizadas para dar el ‡rbol de sentido de
		 * la raiz hasta las hojas... 
		PointerTargetTree ptList = PointerUtils.getInstance().getCauseTree(syn);
	
		System.out.println("tamanhoDeArvore: "+ptList.toList().size());
		Iterator it5 = ptList.toList().iterator();
		while(it5.hasNext()){
			PointerTargetNodeList p = (PointerTargetNodeList) it5.next();
			System.out.println("Nodo: "+p.get(0).toString());
		}*/
		
		/*PointerTargetTreeNode ptNode = pntTree.getRootNode().getParent();
		PointerTarget pTarget = pntTree.getRootNode().getPointerTarget();
		PointerTargetNodeList ptL = pntTree.getRootNode().getPointerTreeList();*/


		/*RelationshipList rl =  RelationshipFinder.getInstance().findRelationships(idWord.getSense(5), idWord.getSense(2), PointerType.INSTANCE_HYPERNYM);
		System.out.println("tamanho: "+rl.size());
		for (int i = 0; i < rl.size(); i++) {
			System.out.println(""+i+": "+rl.get(i));
		}
		RelationshipList rl2 =  RelationshipFinder.getInstance().findRelationships(idWord.getSense(5), idWord.getSense(2), PointerType.INSTANCE_HYPERNYM, 3);
		System.out.println("rl2: "+rl2.toString());*/
		//int rl3 = RelationshipFinder.getInstance().getImmediateRelationship(idWord.getSense(5), idWord.getSense(2));
		
		Pointer[] pont = syn.getPointers();
		PointerTarget ptt = null;
		for (Pointer pt : pont) {
			System.out.println(pt.getSource().toString());
			//Tipos de rela�›es sem‰nticas...
			System.out.println(pt.getType().getLabel());
			//atŽ as palavras-conceitos que podem levar...
			System.out.println(pt.getTarget().toString());		
			ptt = pt.getTarget();
		}
		System.out.println("baixnado um n’vel...");
		
		pont = ptt.getPointers();
		System.out.println("quantidade: "+pont.length);
		for (Pointer pt : pont) {
			//Tipos de rela�›es sem‰nticas...
			System.out.println(pt.getType().getLabel());
			//atŽ as palavras-conceitos que podem levar...
			System.out.println(pt.getTarget().toString());		
			ptt = pt.getTarget();
		}
		
		
		System.out.println("> > syn.getPointers(PointerType.HYPERNYM)[0]: "+syn.getPointers(PointerType.HYPONYM).length);
		System.out.println("> > syn.getPOS(): "+syn.getPOS());
		System.out.println("> > syn.getTargets(): "+syn.getTargets().length);
		
		/*PointerTarget ptrgt[] = syn.getTargets();
		for (PointerTarget ptarget : ptrgt) {
			System.out.println(ptarget.toString());
		}*/
		System.out.println("> > syn.getTargets(PointerType.HYPERNYM): "+syn.getTargets(PointerType.HYPERNYM));
		System.out.println("> > syn.getType(): "+syn.getType());
		System.out.println(">  > syn.getVerbFrameFlags(): "+syn.getVerbFrameFlags());		
		//System.out.println("> > syn.getVerbFrameIndicies(): "+syn.getVerbFrameIndicies());
		System.out.println("> > syn.getWord(0): "+syn.getWord(0));
		System.out.println("> > syn.getWords(): "+syn.getWords().toString());
		System.out.println("> > syn.isAdjectiveCluster(): "+syn.isAdjectiveCluster());
		System.out.println("> > syn.toString(): "+syn.toString());
		System.out.println();		
		
		System.out.println();
		System.out.println("> > > == Informa�›es de WORD ==");
		System.out.println();
		Word word = syn.getWord(5);
		
		System.out.println("> > > word.getIndex(): "+word.getIndex());
		System.out.println("> > > word.getLemma(): "+word.getLemma());;
		System.out.println("> > > word.getPointers(): "+word.getPointers().length);
		
		System.out.println("> > > word.getPointers(PointerType.HYPERNYM): "+word.getPointers(PointerType.HYPERNYM));
		System.out.println("> > > word.getPOS(): "+word.getPOS());;
		System.out.println("> > > word.getSenseKey(): "+word.getSenseKey());
		System.out.println("> > > word.getSynset(): "+	word.getSynset());
		System.out.println("> > > word.getTargets(): "+word.getTargets());
		System.out.println("> > > word.getTargets(PointerType.HYPERNYM): "+word.getTargets(PointerType.HYPERNYM));
		System.out.println("> > > word.getUsageCount(): "+word.getUsageCount());
		
		System.out.println();
		System.out.println("> > > == Informa�›es de DICTIONARYELEMENTYPE ==");
		System.out.println();
		DictionaryElementType dicElem = syn.getType();
		
		System.out.println("> > > dicElem.getName(): "+dicElem.getName());
		System.out.println("> > > dicElem.getAllDictionaryElementTypes(): "+dicElem.getAllDictionaryElementTypes());
		System.out.println("> > > dicElem.toString(): "+dicElem.toString());
		System.out.println();
		
		Pointer[] pointer = syn.getPointers();
		Pointer pt = pointer[0];
		
		System.out.println();
		System.out.println("> > > == Informa�›es de POINTER ==");
		System.out.println();
		System.out.println("> > > pt.getSource(): "+pt.getSource());
		System.out.println("> > > pt.getSourceIndex(): "+pt.getSourceIndex());
		System.out.println("> > > pt.getTarget(): "+pt.getTarget());
		System.out.println("> > > pt.getTargetIndex(): "+pt.getTargetIndex());
		System.out.println("> > > pt.getTargetOffset(): "+pt.getTargetOffset());
		System.out.println("> > > pt.getTargetPOS(): "+pt.getTargetPOS());
		System.out.println("> > > pt.getTargetSynset(): "+pt.getTargetSynset());
		System.out.println("> > > pt.getType(): "+pt.getType());
		System.out.println("> > > pt.isLexical(): "+pt.isLexical());

		System.out.println();
		System.out.println("> > > > == Informa�›es de POINTERTARGET ==");
		System.out.println();
		
		PointerTarget ptarg = pt.getSource();

		System.out.println("> > > > ptarg.getPointers()[0]: "+ptarg.getPointers()[0]);		
		System.out.println("> > > > ptarg.getPointers(PointerType.HYPERNYM)[1]: "+ptarg.getPointers(PointerType.HYPERNYM)[0]);
		System.out.println("> > > > ptarg.getPOS(): "+ptarg.getPOS());
		System.out.println("> > > > ptarg.getTargets()[0]: "+ptarg.getTargets()[0]);
		System.out.println("> > > > ptarg.getTargets(PointerType.HYPERNYM)[0]: "+ptarg.getTargets(PointerType.HYPERNYM)[0]);
		System.out.println();
		
		System.out.println("> > > > == Informa�›es de POINTERTYPE ==");
		System.out.println();
		PointerType pointerType = pt.getType();
		
		System.out.println("> > > > pointerType.appliesTo(POS.NOUN): "+pointerType.appliesTo(POS.NOUN));
		System.out.println("> > > > pointerType.getKey(): "+pointerType.getKey());
		System.out.println("> > > > pointerType.getLabel(): "+pointerType.getLabel());
		System.out.println("> > > > pointerType.getSymmetricType(): "+pointerType.getSymmetricType());
		System.out.println("> > > > pointerType.isSymmetric(): "+pointerType.isSymmetric());
		System.out.println("> > > > pointerType.symmetricTo(PointerType.HYPERNYM): "+pointerType.symmetricTo(PointerType.HYPERNYM));
		System.out.println("> > > > pointerType.toString(): "+pointerType.toString());
		
		
		PointerType pType = pointerType.getSymmetricType();
	
		System.out.println("> > > > pType.appliesTo(POS.NOUN): "+pType.appliesTo(POS.NOUN));
		System.out.println("> > > > pType.getKey(): "+pType.getKey());
		System.out.println("> > > > pType.getLabel(): "+pType.getLabel());
		System.out.println("> > > > pType.getSymmetricType(): "+pType.getSymmetricType());
		System.out.println();	

		
		
		
		
		IndexWord indexWord2 = DatabaseBackedDictionary.getInstance().lookupIndexWord(POS.NOUN, "dog");
		System.out.println(indexWord2.getSenseCount());
		/*Recupera todos os nomes presentes na base de dados...
		Iterator it = DatabaseBackedDictionary.getInstance().getIndexWordIterator(POS.NOUN);
		while(it.hasNext()){
			System.out.println(it.next());
		}*/
		
		IndexWord indexWord3 = DatabaseBackedDictionary.getInstance().getIndexWord(POS.NOUN, "dog");
		Synset[] synS = indexWord3.getSenses();
		
		for (Synset synset : synS) {
			System.out.println(synset.toString());
		}
		
		
		/*Obtem lexicamente dentro do wordnet todas os lemas ou termos que possuem o segundo argumento...
		Iterator it2 = DatabaseBackedDictionary.getInstance().getIndexWordIterator(POS.NOUN, "dog");
		
		
		while(it2.hasNext()){
			System.out.println(it2.next());
		}*/
		
		
		/*MorphologicalProcessor mprocessor = DatabaseBackedDictionary.getInstance().getMorphologicalProcessor();
		mprocessor.lookupAllBaseForms(POS.NOUN,"dog");*/
		
		/*Sortea Randomicamente un synset para ser utilizado...
		IndexWord indexWord4 = DatabaseBackedDictionary.getInstance().getRandomIndexWord(POS.NOUN);
		
		Synset[] synsets = indexWord4.getSenses();
		for (Synset st : synsets) {
			System.out.println("AAAhhhhh: "+st.toString());
		}*/
		
		/* Utilizado para retornar la llave del banco...
		String key = DatabaseBackedDictionary.getInstance().getSenseKey(1, "dog");
		System.out.println("Key: "+key);
		*/
		
		//Synset synset = DatabaseBackedDictionary.getInstance().getSynsetAt(POS.NOUN, 0);
		
		Iterator it3 = DatabaseBackedDictionary.getInstance().getSynsetIterator(POS.NOUN);
		while(it3.hasNext()){
			System.out.println("SynsetIterator: "+it3.next().toString());
		}
		
		int usageCount = DatabaseBackedDictionary.getInstance().getUsageCount(0, "dog");
		System.out.println("usagecount: "+usageCount);
		//int hashCode = DatabaseBackedDictionary.getInstance().hashCode();
		//TODO ANALISAR LO QUE HACE....
		//DatabaseBackedDictionary.getInstance().uninstall();
		
	//	IndexWord[] word = indexWord.getIndexWordArray();
		//Pointer[] pointerArr = set[0].getPointers(PointerType.MEMBER_MERONYM);
				
	
	}
	

}
