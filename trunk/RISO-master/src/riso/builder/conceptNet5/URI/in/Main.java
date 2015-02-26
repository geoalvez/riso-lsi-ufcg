package riso.builder.conceptNet5.URI.in;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import riso.builder.conceptNet5.URI.Constantes;
import riso.builder.conceptNet5.URI.in.actions.ComplementoRestricoes;
import riso.builder.conceptNet5.URI.in.actions.association.ComponenteAssociacao;
import riso.builder.conceptNet5.URI.in.actions.association.ComponenteAssociationConcept;
import riso.builder.conceptNet5.URI.in.actions.association.ComponenteAssociationList;
import riso.builder.conceptNet5.URI.in.actions.association.URIAssociationConcept;
import riso.builder.conceptNet5.URI.in.actions.association.URIAssociationList;
import riso.builder.conceptNet5.URI.in.actions.search.Search;
import riso.builder.conceptNet5.URI.in.assertion.ComplementoAfirmacaoConceptNet;
import riso.builder.conceptNet5.URI.in.assertion.URIAfirmacoes;
import riso.builder.conceptNet5.URI.in.concept.ComplementoConceitoConceptNet;
import riso.builder.conceptNet5.URI.in.concept.URIConceito;
import riso.builder.conceptNet5.URI.in.datasets.ComplementoDataSetConceptNet;
import riso.builder.conceptNet5.URI.in.datasets.URIDataSets;
import riso.builder.conceptNet5.URI.in.edge.URIEdge;
import riso.builder.conceptNet5.URI.in.relacoes.ComplementoRelacaoConceptNet;
import riso.builder.conceptNet5.URI.in.relacoes.URIRelacao;
import riso.builder.conceptNet5.URI.in.source.ComplementoSourceConceptNet;
import riso.builder.conceptNet5.URI.in.source.URISource;
import riso.builder.conceptNet5.URI.in.types.DataSets;
import riso.builder.conceptNet5.URI.in.types.IdiomasConceptNet;
import riso.builder.conceptNet5.URI.in.types.MorfologiaConceptNet;
import riso.builder.conceptNet5.URI.in.types.Relacao;
import riso.builder.conceptNet5.URI.in.types.Sources;

public class Main {

	public static void main(String[] args){
		
		
		ComplementoRelacaoConceptNet relacao = new ComplementoRelacaoConceptNet(Relacao.PART_OF);
		URIGeral relac = new URIRelacao(relacao);
		ComplementoConceitoConceptNet conc = new ComplementoConceitoConceptNet("car");
		URIGeral conceito = new URIConceito(conc);
		//Restricoes
		Map<String, String> restricoes = new HashMap<String, String>();
		restricoes.put(Constantes.SEARCH_LIMIT, "5");
		restricoes.put(Constantes.SEARCH_OFFSET, "3");
		restricoes.put(Constantes.SEARCH_REL,relac.getTipoEComplemento());
		restricoes.put(Constantes.SEARCH_END,conceito.getTipoEComplemento());

		/*
		 * SEARCH
		 */
		ComplementoRestricoes complemento = new ComplementoRestricoes(restricoes);
		Search search = new Search(complemento);
		System.out.println(search.toString());
		
		/*
		 * Fonte
		 */
		ComplementoSourceConceptNet source = new ComplementoSourceConceptNet(Sources.SOURCE_REGRA);
		URISource src = new URISource(source);
		System.out.println(src.toString());
		
		/*
		 * Relacoes
		 */
		ComplementoRelacaoConceptNet rel = new ComplementoRelacaoConceptNet(Relacao.IS_A);
		URIRelacao relation = new URIRelacao(rel);
		System.out.println(relation.toString());
		
		/*
		 * Edge
		 */
		URIEdge edge = new URIEdge("83c8a138d03f6d2787042a23093a551f1e3804f9");
		System.out.println(edge.toString());
		
		/* 
		 * DataSet 
		 */
		ComplementoDataSetConceptNet dataset = new ComplementoDataSetConceptNet(DataSets.WORDNET);
		URIDataSets datasets = new URIDataSets(dataset);
		System.out.println(datasets.toString());
		
		/*
		 * Conceitos 
		 */
		ComplementoConceitoConceptNet complementoCCN = new ComplementoConceitoConceptNet("big",MorfologiaConceptNet.ISENTA, IdiomasConceptNet.INGLES);
		URIConceito con = new URIConceito(complementoCCN);
		System.out.println(con);
		
		/*
		 * Afirmacoes 
		 */
		ComplementoRelacaoConceptNet r = new ComplementoRelacaoConceptNet(Relacao.HAS_PROPERTY);
		URIGeral re = new URIRelacao(r);
		ComplementoConceitoConceptNet conc1 = new ComplementoConceitoConceptNet("big");
		URIGeral c = new URIConceito(conc1);
		ComplementoConceitoConceptNet conc2 = new ComplementoConceitoConceptNet("large");
		URIGeral con2 = new URIConceito(conc2);
		URIGeral afirm[] = {re,c,con2};
		ComplementoAfirmacaoConceptNet afirmacao = new ComplementoAfirmacaoConceptNet(afirm);
		System.out.println(afirmacao.getComplemento());
		URIGeral af = new URIAfirmacoes(afirmacao);
		System.out.println(af.toString());

		/*
		 *Associacoes 
		 */
		//Por conceito
		ComponenteAssociationConcept assCon = new ComponenteAssociationConcept(con,complemento);
		URIGeral ac = new URIAssociationConcept(assCon);
		System.out.println(ac.toString());
		
		//Por Lista
		ComponenteAssociacao comp1 =  new ComponenteAssociacao("boy");
		ComponenteAssociacao comp2 = new ComponenteAssociacao("girl","-1");
		List<ComponenteAssociacao> listComp = new ArrayList<ComponenteAssociacao>();
		listComp.add(comp1);
		listComp.add(comp2);
		ComponenteAssociationList asslist = new ComponenteAssociationList(listComp, complemento);
		URIGeral uriList = new URIAssociationList(asslist);
		System.out.println(uriList.toString());
		
		/*
		 *Testando jaguar... 	
		 */
		ComplementoConceitoConceptNet complCCN = new ComplementoConceitoConceptNet("jaguar");
		URIConceito conceitojaguar = new URIConceito(complCCN);
		System.out.println(conceitojaguar);
	}
}
