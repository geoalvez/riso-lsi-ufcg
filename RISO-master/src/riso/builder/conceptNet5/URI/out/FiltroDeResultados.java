package riso.builder.conceptNet5.URI.out;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import riso.builder.conceptNet5.URI.Constantes;

public class FiltroDeResultados {

	public static final String SEARCH_TEXT = "http://conceptnet5.media.mit.edu/data/5.2/search?text=";
	public static final String SEARCH_SURFACE = "http://conceptnet5.media.mit.edu/data/5.2/search?surfaceText=";
	public static final String LOOK_UP = "http://conceptnet5.media.mit.edu/data/5.2/c/en/";

	private Map<String, String> conceitosAcrescentados = new HashMap<String, String>();

	public Map<String, String> getConceitosAcrescentados() {
		return conceitosAcrescentados;
	}

	public void setConceitosAcrescentados(Map<String, String> conceitosAcrescentados) {
		this.conceitosAcrescentados = conceitosAcrescentados;
	}

	private Set<ArestaConceptNet> afirmacoes =  new HashSet<ArestaConceptNet>();
	//TODO..
	private final String RELACAO = "/"+Constantes.RELACOES+"/";

	private Map<String, ArestaConceptNet> controleRelacoes = new HashMap<String, ArestaConceptNet>();

	public Map<String, ArestaConceptNet> getControleRelacoes() {
		return controleRelacoes;
	}

	public void setControleRelacoes(Map<String, ArestaConceptNet> controleRelacoes) {
		this.controleRelacoes = controleRelacoes;
	}

	private GerenciadorDeConsultasConceptnet5 gerenciador = new GerenciadorDeConsultasConceptnet5();

	public GerenciadorDeConsultasConceptnet5 getGerenciador() {
		return gerenciador;
	}

	public void setGerenciador(GerenciadorDeConsultasConceptnet5 gerenciador) {
		this.gerenciador = gerenciador;
	}
	private Set<ArestaConceptNet> getAfirmacoes() {
		return afirmacoes;
	}

	private ArestaConceptNet verificaExistenciaDeAlgumaRelacao(String conceitoInicio, String conceitoFim){

		String finall = "http://conceptnet5.media.mit.edu/data/5.2/search?start="+conceitoInicio+"/&end="+conceitoFim+"/&limit=1";
		Aresta aresta = getGerenciador().consultaConceptnet5(finall);

		if(aresta.getNumFound() > 0){

			return verificaConstrucaoDeRelacao(aresta, conceitoInicio, conceitoFim);
		}

		return null;
	}


	private ArestaConceptNet verificaExistenciaDeRelacoesFortes(String conceitoInicio, String conceitoFim){

		Date dataInicio = new Date();
		int count = 0;
		for (String relacao : Constantes.RELACOES_SEMANTICAS_FORTES) {

//			Date dataFim = new Date();
//			long numDiferenca = dataFim.getTime() - dataInicio.getTime();
//			if (count > 1000 && numDiferenca < 60000){
//				try {
//					System.out.println("Atingiu limite de requisicoes por minuto.");
//					System.out.println("Esperando por "+numDiferenca/6000+" segundos.....");
//					Thread.sleep(60000-numDiferenca);
//					System.out.println("ok!");
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
			String finall = "http://conceptnet5.media.mit.edu/data/5.2/search?uri=/a/[/r/"+relacao+"/,"+conceitoInicio+"/,"+conceitoFim+"/]&limit=1";
			Aresta aresta = getGerenciador().consultaConceptnet5(finall);

//			try {
//				Thread.sleep(3000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

			if(aresta.getNumFound() > 0){

				return aresta.getEdges().iterator().next();
			}
		}
		return null;
	}

	//	private String constroeRelacaoForte(Aresta arestaDireta){
	//
	//		String relacao = ((ArestaConceptNet) arestaDireta.getEdges()
	//				.toArray()[0]).getRel();
	//
	//		relacao = relacao.replaceAll(RELACAO, "");
	//
	//		return relacao;
	//
	//	}

	private ArestaConceptNet verificaConstrucaoDeRelacao(Aresta arestaDireta, String conceitoInicio, String conceitoFim){

		ArestaConceptNet arestaConcept = arestaDireta.getEdges().iterator().next();
		String conceitoIn = arestaConcept.getStart();
		String conceitoEnd = arestaConcept.getEnd();

		if(!conceitoEnd.equals(conceitoFim)){

			addRelacao(conceitoEnd,conceitoFim);
		}

		if(!conceitoIn.equals(conceitoInicio)){

			addRelacao(conceitoIn,conceitoInicio);
		}

		return arestaConcept;

	}

	private void verificaNovaAfirmacao(String conceito){

		if(!getConceitosAcrescentados().containsKey(conceito)){
			if(conceito.contains("/n/")){
				int posicao = conceito.lastIndexOf("/n/");
				String subConceito = conceito.substring(0,posicao);
				String superConceito = conceito.substring(posicao, conceito.length());
				ArestaConceptNet aresta = new ArestaConceptNet();
				aresta.setStart(subConceito);
				aresta.setRel(RELACAO+Constantes.RELACAO_IS_A);
				aresta.setEnd(superConceito);
				aresta.setUri("/a/[/r/IsA/,"+subConceito+"/,"+superConceito+"/]");
				getAfirmacoes().add(aresta);		
			}
			getConceitosAcrescentados().put(conceito, "");
		}
	}

	private void addRelacao(String especializacao, String generalizacao){
		ArestaConceptNet aresta = new ArestaConceptNet();
		aresta.setStart(especializacao);
		aresta.setRel(RELACAO+Constantes.RELACAO_IS_A);
		aresta.setEnd(generalizacao);
		aresta.setUri("/a/[/r/IsA/,"+especializacao+","+generalizacao+"]");
		getAfirmacoes().add(aresta);

	}



	public ArestaConceptNet constroiRelacoes(String conceitoInicio, String conceitoFim){

		ArestaConceptNet relacao = null;
		boolean naoPossui = false;

		if((!getControleRelacoes().containsKey(conceitoInicio+"|"+conceitoFim)) && 
				(!getControleRelacoes().containsKey(conceitoFim+"|"+conceitoInicio))){

			relacao = verificaExistenciaDeRelacoesFortes(conceitoInicio, conceitoFim);
			naoPossui = true;

			if(relacao == null){

				relacao = verificaExistenciaDeRelacoesFortes(conceitoFim, conceitoInicio);

				//				if(relacao == null){
				//
				//					relacao = verificaExistenciaDeAlgumaRelacao(conceitoInicio, conceitoFim);
				//
				//					if(relacao == null){
				//
				//						relacao = verificaExistenciaDeAlgumaRelacao(conceitoFim, conceitoInicio);
				//					}	
				//				}	
			}
		}

		if(naoPossui){
			getControleRelacoes().put(conceitoInicio+"|"+conceitoFim, relacao);
		}

		return relacao;
	}




	public Set<ArestaConceptNet> obtemAfirmacoesOcultas(Set<ArestaConceptNet> arestas){
		Map<String, Set<ArestaConceptNet>> agrupar = new HashMap<String, Set<ArestaConceptNet>>();

		long inicio = System.currentTimeMillis();
		for (ArestaConceptNet aresta : arestas) {
			getAfirmacoes().add(aresta);
			String conceito = aresta.getStart();
			//verificaNovaAfirmacao(conceito);
			String concFim = aresta.getEnd();
			//verificaNovaAfirmacao(concFim);
			Set<ArestaConceptNet> edgeList = agrupar.get(conceito);
			if(edgeList == null){
				edgeList = new HashSet<ArestaConceptNet>();
				agrupar.put(conceito,edgeList);
			}else{
				for (ArestaConceptNet arest : edgeList) {
					String conceitoFim = arest.getEnd();
					//TODO verificar relacao no concepnet antes de perguntar...
					ArestaConceptNet relacao = constroiRelacoes(concFim, conceitoFim);

					if(relacao != null){
						getAfirmacoes().add(relacao);
					}
				}
			}
			edgeList.add(aresta);
		}
		System.out.println(((System.currentTimeMillis() - inicio) / 1000)+ " segundos");
		colocaAfirmacoesEmArquivo();

		return getAfirmacoes();
	}

	private void colocaAfirmacoesEmArquivo() {

		File arquivo = new File("afirmacoes.txt");
		try{
			FileOutputStream file = new FileOutputStream(arquivo);  
			for (ArestaConceptNet aresta : getAfirmacoes()) {
				String texto = aresta.getStart()+" "+aresta.getRel()+" "+aresta.getEnd()+"\n";    	
				file.write(texto.getBytes());   
			}
			file.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}


	public Map<String, Set<ArestaConceptNet>> criaGrupoDeFilhos(Set<ArestaConceptNet> arestas){
		Map<String, Set<ArestaConceptNet>> agrupar = new HashMap<String, Set<ArestaConceptNet>>();

		for (ArestaConceptNet aresta : arestas) {
			String conceito = aresta.getEnd();
			Set<ArestaConceptNet> edgeList = agrupar.get(conceito);
			if(edgeList == null){
				edgeList = new HashSet<ArestaConceptNet>();
				agrupar.put(conceito,edgeList);
			}
			edgeList.add(aresta);
		}

		return agrupar;
	}



	public void imprimeAfirmacoes(Set<ArestaConceptNet> afirmacoes){

		for (ArestaConceptNet afirmacao : afirmacoes) {

			System.out.println(afirmacao.getStart()+" "+afirmacao.getRel()+" "+afirmacao.getEnd());
		}


	}
	//OK
	public void imprimeGrupos(Map<String, Set<ArestaConceptNet>> agrupa){
		for (String str : agrupa.keySet()) {
			String st = str+"{";
			for (ArestaConceptNet ar : agrupa.get(str)) {
				st+=ar.getEndLemmas()+",";
			}
			st = st.substring(0, st.length()-1);
			st +="}";
			System.out.println(st);

		}
	}

	public void contaImprimeVetor(int entrada,int saida){

		System.out.println("Entrada: "+entrada);
		System.out.println("Saida: "+saida);
		System.out.println("Filtro: "+(entrada - saida));

	}

	public int qtRecursos(String fonte, String caracter, String termo){
		Aresta aresta = getGerenciador().gerenciaSolicitacoesOnlyNumFound(fonte, caracter, termo);
		return aresta.getNumFound();

	}

	public Set<ArestaConceptNet> eliminarAfirmacoesInuteis(String fonte, String caracter, String termo){

		Set<ArestaConceptNet> afirmacoesUteis = new HashSet<ArestaConceptNet>();

		final String RELACAO_TRL_OF = "/"+Constantes.RELACOES+"/"+Constantes.RELACAO_TRANSLATION_OF;
		final String PADRAO_INGLES = "/"+Constantes.IDIOMA_INGLES+"/";
		Aresta aresta = getGerenciador().gerenciaSolicitacoes(fonte, caracter, termo);
		Set<ArestaConceptNet> edges = aresta.getEdges();

		for (ArestaConceptNet conceito : edges) {
			String conceitoInicial = conceito.getStart();
			String conceitoFinal = conceito.getEnd();

			if(!conceito.getRel().equals(RELACAO_TRL_OF) && 
					(conceitoInicial.contains(PADRAO_INGLES) && 
							(conceitoFinal.contains(PADRAO_INGLES)))){

				String relacao = conceito.getRel();

				if(verificaRelacao(relacao)){
					afirmacoesUteis.add(conceito);
				}
			}
		}

		return afirmacoesUteis;
		//		paisEfilhos.add(termoEhEspecializacao);
		//		paisEfilhos.add(afirmacoesUteis);
		//		//TODO imprime vetor final
		//		contaImprimeVetor(edges.size(), termoEhEspecializacao.size() + afirmacoesUteis.size());
		//		//
		//		System.out.println("Pais e filhos...");
		//		System.out.println("Pais: "+termoEhEspecializacao.size());
		//
		//		for (ArestaConceptNet ar : termoEhEspecializacao) {
		//			System.out.println(ar.getUri());
		//		}
		//
		//		System.out.println("Filhos: "+afirmacoesUteis.size());
		//
		//		for (ArestaConceptNet are : afirmacoesUteis) {
		//			System.out.println(are.getUri());
		//		}
		//		//
		//		Set<ArestaConceptNet> conceitos = new HashSet<ArestaConceptNet>();
		//		conceitos.addAll(termoEhEspecializacao);
		//		conceitos.addAll(afirmacoesUteis);
		//		verificaElementosExclusivos(edges, conceitos);
		//
		//		return paisEfilhos;
	}


	private boolean verificaRelacao(String relacao){
		final String RELACAO = "/"+Constantes.RELACOES+"/";

		return relacao.startsWith(RELACAO);
	}


	public void verificaElementosExclusivos(Set<ArestaConceptNet> atual, Set<ArestaConceptNet> comparada){

		Set<ArestaConceptNet> elementosIguais = new HashSet<ArestaConceptNet>();

		Iterator<ArestaConceptNet> elemento = atual.iterator();

		while(elemento.hasNext()){
			ArestaConceptNet arestaAtual = elemento.next();
			Iterator<ArestaConceptNet> comparado = comparada.iterator();

			while(comparado.hasNext()){
				ArestaConceptNet arestaComparada = comparado.next();
				if(arestaAtual.equals(arestaComparada)){
					elementosIguais.add(arestaAtual);
					break;
				}
			}
		}
		atual.removeAll(elementosIguais);
		System.out.println("QtIguais:"+elementosIguais.size());
		System.out.println("QtExclusivos:"+atual.size());

		System.out.println("Verifica Elementos Exclusivos...");
		for (ArestaConceptNet aresta : atual) {
			System.out.println(aresta.getUri());
		}

	}
}
