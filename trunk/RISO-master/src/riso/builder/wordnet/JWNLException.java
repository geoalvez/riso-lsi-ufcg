/**
 * Java WordNet Library (JWNL)
 * See the documentation for copyright information.
 */
package riso.builder.wordnet;



/** Base level exception used by JWNL. Tries to resolve the message using JWNL.resolveMessage. */
public class JWNLException extends Exception {
	
	//INICIO - zegildo@copin.ufcg.edu.br
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//FIM - zegildo@copin.ufcg.edu.br
	public JWNLException(String key) {
		super(JWNL.resolveMessage(key));
	}

	public JWNLException(String key, Object arg) {
		super(JWNL.resolveMessage(key, arg));
	}

	public JWNLException(String key, Object[] args) {
		super(JWNL.resolveMessage(key, args));
	}

	public JWNLException(String key, Throwable cause) {
		super(JWNL.resolveMessage(key), cause);
	}

	public JWNLException(String key, Object[] args, Throwable cause) {
		super(JWNL.resolveMessage(key, args), cause);
	}

	public JWNLException(String key, Object arg, Throwable cause) {
		super(JWNL.resolveMessage(key, arg), cause);
	}
}