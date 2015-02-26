package riso.builder.wordnet.princeton.data;

import riso.builder.wordnet.data.Synset;
import riso.builder.wordnet.data.Verb;

import java.util.BitSet;

/**
 * Wrapper for a verb that allows the VerbFrame flags to be set after the Verb
 * is created.
 */
class MutableVerb extends Verb {
	public MutableVerb(Synset synset, int index, String lemma) {
		super(synset, index, lemma, new BitSet());
	}

	public void setVerbFrameFlag(int fnum) {
		getVerbFrameFlags().set(fnum);
	}
}
