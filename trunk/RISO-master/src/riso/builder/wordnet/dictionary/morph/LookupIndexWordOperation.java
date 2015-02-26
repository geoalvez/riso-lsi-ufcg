package riso.builder.wordnet.dictionary.morph;

import riso.builder.wordnet.data.POS;
import riso.builder.wordnet.JWNLException;
import riso.builder.wordnet.dictionary.Dictionary;

import java.util.Map;

public class LookupIndexWordOperation implements Operation {
	public Object create(Map params) throws JWNLException {
		return new LookupIndexWordOperation();
	}

	public boolean execute(POS pos, String lemma, BaseFormSet baseForms) throws JWNLException {
		if (Dictionary.getInstance().getIndexWord(pos, lemma) != null) {
			baseForms.add(lemma);
			return true;
		}
		return false;
	}
}