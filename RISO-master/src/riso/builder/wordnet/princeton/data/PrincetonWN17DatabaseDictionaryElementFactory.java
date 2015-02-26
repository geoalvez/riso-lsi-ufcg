package riso.builder.wordnet.princeton.data;

import riso.builder.wordnet.JWNLException;

import java.util.Map;

public class PrincetonWN17DatabaseDictionaryElementFactory extends AbstractPrincetonDatabaseDictionaryElementFactory {
	public Object create(Map params) throws JWNLException {
		return new PrincetonWN17DatabaseDictionaryElementFactory();
	}
}
