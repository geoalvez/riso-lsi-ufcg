package riso.builder.conceptNet5.URI.in.types;

import riso.builder.conceptNet5.URI.Constantes;
import riso.builder.conceptNet5.URI.in.interfaces.Retornavel;

public enum Relacao implements Retornavel{
	IS_A(Constantes.RELACAO_IS_A),
	INSTANCE_OF(Constantes.RELACAO_INSTANCE_OF),
	PART_OF(Constantes.RELACAO_PART_OF),
	DIRECT_OBJETCT_OF(Constantes.RELACAO_DIRECT_OBJETCT_OF),
	SUBJECT_OF(Constantes.RELACAO_SUBJECT_OF),
	HAS_PROPERTY(Constantes.RELACAO_HAS_PROPERTY),
	HAS_PREQUISITE(Constantes.RELACAO_HAS_PREQUISITE),
	TRANSLATION_OF(Constantes.RELACAO_INSTANCE_OF),
	DERIVEDED_FROM(Constantes.RELACAO_DERIVEDED_FROM),
	AT_LOCATION(Constantes.RELACAO_AT_LOCATION);

	private String relacao;
	private Relacao(String relacao){
		this.relacao = relacao;
	}
	public String getString() {
		return relacao;
	}

}
