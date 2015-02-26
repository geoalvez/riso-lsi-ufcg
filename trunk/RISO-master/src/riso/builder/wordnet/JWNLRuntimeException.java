package riso.builder.wordnet;


/**
 * Java WordNet Library (JWNL)
 * See the documentation for copyright information.
 */

/** Base level runtime exception used by JWNL. Tries to resolve the message using JWNL.resolveMessage. */
public class JWNLRuntimeException extends RuntimeException {
	//INICIO - zegildo@copin.ufcg.edu.br
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//FIM - zegildo@copin.ufcg.edu.br
	public JWNLRuntimeException(String key) {
		super(JWNL.resolveMessage(key));
	}

	public JWNLRuntimeException(String key, Object arg) {
		super(JWNL.resolveMessage(key, arg));
	}

	public JWNLRuntimeException(String key, Object[] args) {
		super(JWNL.resolveMessage(key, args));
	}

	public JWNLRuntimeException(String key, Throwable cause) {
		super(JWNL.resolveMessage(key), cause);
	}

	public JWNLRuntimeException(String key, Object[] args, Throwable cause) {
		super(JWNL.resolveMessage(key, args), cause);
	}

	public JWNLRuntimeException(String key, Object arg, Throwable cause) {
		super(JWNL.resolveMessage(key, arg), cause);
	}
}