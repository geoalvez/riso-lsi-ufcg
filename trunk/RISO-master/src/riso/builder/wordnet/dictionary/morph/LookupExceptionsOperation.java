package riso.builder.wordnet.dictionary.morph;

import riso.builder.wordnet.data.POS;
import riso.builder.wordnet.data.Exc;
import riso.builder.wordnet.JWNLException;
import riso.builder.wordnet.dictionary.Dictionary;

import java.util.Map;

/** Lookup the word in the exceptions file of the given part-of-speech. */
public class LookupExceptionsOperation implements Operation {
	public Object create(Map params) throws JWNLException {
		return new LookupExceptionsOperation();
	}

	public boolean execute(POS pos, String derivation, BaseFormSet form) throws JWNLException {
		Exc exc = Dictionary.getInstance().getException(pos, derivation);
		if (exc != null) {
			String[] exceptions = exc.getExceptionArray();
			for (int i = 0; i < exceptions.length; i++) {
				form.add(exceptions[i]);
			}
			return true;
		}
		return false;
	}
}