package riso.builder.conceptNet5.URI.out;

import java.util.Set;

import riso.builder.topicMap.OntoMaker;

public class ConceitoExpandido {

	private String conceito;
 
	public ConceitoExpandido(String conceito){		
		setConceito(conceito);
	}

	public String getConceito() {
		return conceito;
	}

	public void setConceito(String conceito) {
		this.conceito = conceito;
	}

	public void enriqueceConceito(String fonte, String caracter){

		FiltroDeResultados filtro = new FiltroDeResultados();
		Set<ArestaConceptNet> afirmacoesUteis = filtro.eliminarAfirmacoesInuteis(fonte, caracter,getConceito());
		Set<ArestaConceptNet> afirmacoes = filtro.obtemAfirmacoesOcultas(afirmacoesUteis);
		parentescoPorFilho(afirmacoes, getConceito());

	}

	public int getQtafirmacoesUteis(String fonte, String caracter){

		FiltroDeResultados filtro = new FiltroDeResultados();
		return filtro.qtRecursos(fonte, caracter,getConceito());

	}

	public int eliminaInformacoesInuteis(String fonte, String caracter){

		FiltroDeResultados filtro = new FiltroDeResultados();
		return filtro.eliminarAfirmacoesInuteis(fonte, caracter,getConceito()).size();

	}

	private void parentescoPorFilho(Set<ArestaConceptNet> afirmacoes, String conceito){

		OntoMaker onto =  new OntoMaker();
		for (ArestaConceptNet afirmacao : afirmacoes) {

			onto.adicionaAfirmacao(afirmacao);
		}
		
		onto.criaVetorTematico(conceito);
		onto.criaGrafoMinimal(conceito);
	}


	public static void main(String args[]){

		//String conceito = "jaguar";
		//ConceitoExpandido t = new ConceitoExpandido();
		//
		//
		//		Nodo jaguar = new Nodo("/c/en/jaguar");
		//		Nodo animal = new Nodo("/c/en/animal");
		//		Nodo mamifero = new Nodo("/c/en/mammal");
		//
		//		Set<Nodo> nodosDiretos = new HashSet<Nodo>();
		//		Map<String, Set<Nodo>> relacoesDiretas = new HashMap<String, Set<Nodo>>();
		//		nodosDiretos.add(animal);
		//		relacoesDiretas.put("IsA", nodosDiretos);
		//
		//		Estrutura estruDireta = new Estrutura();
		//		estruDireta.setRelacoes(relacoesDiretas);
		//		jaguar.setEstrutura(estruDireta);
		//
		//		Set<Nodo> nodosInversos = new HashSet<Nodo>();
		//		Map<String, Set<Nodo>> relacoesInversas = new HashMap<String, Set<Nodo>>();
		//		nodosInversos.add(jaguar);
		//		relacoesInversas.put("IsA-1", nodosInversos);
		//
		//		Estrutura estruInversa = new Estrutura();
		//		estruInversa.setRelacoes(relacoesInversas);
		//		animal.setEstrutura(estruInversa);
		//
		//
		//		t.constroiRelacoes(jaguar, mamifero);
		//		t.constroiRelacoes(mamifero,animal);
		//		t.constroiRelacoes(jaguar, mamifero);
		//t.enriqueceConceito(FiltroDeResultados.SEARCH_TEXT,"&",conceito);

		/*Aresta aresta = prepara(searchText+"jaguar&limit="+Constantes.ZERO);
		System.out.println("O que deve ser: "+aresta.getNumFound());
		Aresta arestaAux = null;


		long inicio = System.currentTimeMillis();
		if(aresta.getNumFound() > JANELA){
			int qtIt = aresta.getNumFound() / JANELA;
			int rest = aresta.getNumFound() % JANELA;
			int i = 1;
			aresta = prepara(searchText+"jaguar&limit="+JANELA);
			while(i < qtIt){
				arestaAux = prepara(searchText+"jaguar&limit="+JANELA+"&offset="+i*JANELA);
				aresta.addSetEdge(arestaAux.getEdges());
				i++;
			}
			arestaAux = prepara(searchText+"jaguar&limit="+rest+"&offset="+i*JANELA);
			aresta.addSetEdge(arestaAux.getEdges());
		}else{
			aresta = prepara(searchText+"jaguar&limit="+aresta.getNumFound());
		}
		System.out.println("Tempo gasto:"+ ((System.currentTimeMillis() - inicio) / 1000)+ "segundos");
		System.out.println("O que veio: "+aresta.getEdges().size());*/




		//
		//		Set<Nodo> nodos = new HashSet<Nodo>();
		//		Map<String, Set<Nodo>> relacoes = new HashMap<String, Set<Nodo>>();
		//		nodos.add(life);
		//		nodos.add(organism);
		//		relacoes.put("IsA", nodos);
		//
		//		Estrutura estru = new Estrutura();
		//		estru.setRelacoes(relacoes);
		//		plant.setEstrutura(estru);
		//		constroiRelacoes(plant, liveThing);


		//Caso 1 - Jaguar Animal
		//t.constroiRelacoes(animal, jaguar);
		//t.constroiRelacoes(jaguar, animal);



		//Caso 3 - OK -  troca de posicao entre jaguar-animal para jaguar-mamifero
		/*Set<Nodo> nodos = new HashSet<Nodo>();
		Map<String, Set<Nodo>> relacoes = new HashMap<String, Set<Nodo>>();
		nodos.add(mamifero);
		relacoes.put("IsA", nodos);

		Estrutura estru = new Estrutura();
		estru.setRelacoes(relacoes);
		jaguar.setEstrutura(estru);
		constroiRelacoes(jaguar, animal);*/

		//Caso 4 - OK -  adicionar elementos de mesma rela��o que n�o possuem 
		//rela��o hierarquica com os que j� estao adicionados. 
		//Colocar n�s semanticamente de mesmo n�vel
		/*Set<Nodo> nodos = new HashSet<Nodo>();
		Map<String, Set<Nodo>> relacoes = new HashMap<String, Set<Nodo>>();
		nodos.add(life);
		relacoes.put("IsA", nodos);

		Estrutura estru = new Estrutura();
		estru.setRelacoes(relacoes);
		plant.setEstrutura(estru);
		constroiRelacoes(plant, organism);*/


		/*Nodo organism = new Nodo("/c/en/organism/n/a_living_thing_that_has_the_ability_to_act_or_function_independently/");

		Nodo liveThing = new Nodo("/c/en/live_thing/n/a_living_entity/");

		Nodo life = new Nodo("/c/en/life/n/living_things_collectively/");
		Nodo plant = new Nodo("/c/en/plant/");

		Set<Nodo> nodos = new HashSet<Nodo>();
		Map<String, Set<Nodo>> relacoes = new HashMap<String, Set<Nodo>>();
		nodos.add(life);
		nodos.add(organism);
		relacoes.put("IsA", nodos);

		Estrutura estru = new Estrutura();
		estru.setRelacoes(relacoes);
		plant.setEstrutura(estru);
		constroiRelacoes(plant, liveThing);*/

		//		List<RelacoesValoradas> relacoes = relacoesHierarquicas("big");
		//
		//		for (RelacoesValoradas relacoesValoradas : relacoes) {
		//			System.out.println(relacoesValoradas.getRelacao());
		//		}
		//System.out.println("Antes ============== :"+listlookUp.size());

		//Set<ArestaConceptNet>  listSearchText = montaAncestrais(searchText,"&",conceito);

		//Set<ArestaConceptNet>  listSearchSurface = montaAncestrais(searchSurface,"&",conceito);

		//verificaElementosExclusivos(listlookUp,listSearchText);
		//System.out.println("Despues =============== :"+listlookUp.size());

		//verificaElementosExclusivos(listSearchText,listlookUp);

		//verificaElementosExclusivos(listlookUp,listSearchSurface);
		//verificaElementosExclusivos(listSearchSurface,listlookUp);

		//verificaElementosExclusivos(listSearchSurface,listSearchText);
		//verificaElementosExclusivos(listSearchText,listSearchSurface);

	}
}
