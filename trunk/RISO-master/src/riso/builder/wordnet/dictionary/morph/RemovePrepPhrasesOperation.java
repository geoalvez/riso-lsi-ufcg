package riso.builder.wordnet.dictionary.morph;

import riso.builder.wordnet.data.POS;
import riso.builder.wordnet.JWNLException;

import java.util.Map;

/** yet to be implemented */
public class RemovePrepPhrasesOperation implements Operation {
	public Object create(Map params) throws JWNLException {
		return new RemovePrepPhrasesOperation();
	}

	public boolean execute(POS pos, String lemma, BaseFormSet baseForm) {
		return false;
	}
}