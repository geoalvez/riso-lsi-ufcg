package riso.builder.conceptNet5.URI.in.concept;

import riso.builder.conceptNet5.URI.in.URIGeral;
import riso.builder.conceptNet5.URI.in.types.IdiomasConceptNet;
import riso.builder.conceptNet5.URI.in.types.ObjetosURIConceptNet;

/**
 * Cada conceito tem ao menos 3 componentes:
 * o inicio /c que indica conceito
 * a linguagem escolhida
 * e a parte com o conceito que se deseja saber
 * @author zegildo
 *
 */
public class URIConceito extends URIGeral {

	
	private ComplementoConceitoConceptNet complementoCCN;

	public URIConceito(ComplementoConceitoConceptNet complemento){
		super(ObjetosURIConceptNet.CONCEITOS, IdiomasConceptNet.INGLES,complemento.getComplemento());
		setComplementoCCN(complemento);
	}
	
	public ComplementoConceitoConceptNet getComplementoCCN() {
		return complementoCCN;
	}

	public void setComplementoCCN(ComplementoConceitoConceptNet complementoCCN) {
		this.complementoCCN = complementoCCN;
	}
	
}
