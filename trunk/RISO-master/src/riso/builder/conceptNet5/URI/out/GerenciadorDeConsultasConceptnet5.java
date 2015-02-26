package riso.builder.conceptNet5.URI.out;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;

import org.hibernate.criterion.CountProjection;

import riso.builder.conceptNet5.URI.Constantes;

import com.google.gson.Gson;

public class GerenciadorDeConsultasConceptnet5 {

	public static final int JANELA = 5000;
	
	public static Date dataInicioIntervalo = new Date();
	
	public static Date dataPrimeiraRequisicao = new Date();
	
	public static int count = 0;

	public static int countDiferentes = 0;

	public static int countPorMinuto = 0;
	public Aresta consultaConceptnet5(String request){

		Gson gson = new Gson();
		Aresta aresta = new Aresta();

		countDiferentes ++;
		countPorMinuto++;
		boolean sucesso = false;
		boolean deuErro = false;
		Date dataFim = null;
		int numTentativas = 0;
		// System.out.println("Nova Consulta...");
		while (!sucesso){
			numTentativas++;
			try {
				
				if (deuErro){
					System.out.println("Reenviando Requisicao");
					System.out.println("Num tentativas: "+numTentativas);
				}
				
				dataFim = new Date();
				count++;

				long numDiferenca = dataFim.getTime() - dataInicioIntervalo.getTime();
				
					
				if (count > 1000 && numDiferenca < 60000){
					try {
						System.out.println("Atingiu limite de 1000 requisicoes.");
						System.out.println("Esperando por 1 minuto....." + new Date());
						Thread.sleep(60000);
						System.out.println("ok! " + new Date());
						dataInicioIntervalo = new Date();
						count = 1;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				

				if (numDiferenca > 60000){
					dataInicioIntervalo = new Date();
					System.out.println("+1min "+new Date());
					System.out.println("Num Requests desde a pausa: "+count);
					System.out.println("Num Requests no ultimo minuto: "+countPorMinuto);
					System.out.println("Num Requests Total: "+countDiferentes);
					countPorMinuto = 0;
				}

				URL url = new URL(request.replace(" ", "%"));
				/*
				 * Como realizar a chamada a uma lista de associacao
				 * URL url = new URL("http://conceptnet5.media.mit.edu/data/5.1/assoc/list/en/boy,girl@-1?limit=5&rel=/r/PartOf/&offset=3&end=/c/en/car/");
				 * Associacao ass = gson.fromJson(br, Associacao.class);
				 * */
				BufferedReader br = new BufferedReader(
						new InputStreamReader(url.openStream()));
				aresta = gson.fromJson(br, Aresta.class);
				
				if (deuErro){
					System.out.println("Requisicao enviada com sucesso apos "+numTentativas+" tentativas.");
				}
				
				sucesso = true;
				
			} catch (Exception e) {
				deuErro = true;
				System.out.println("Erro: "+e.getMessage());
				System.out.println("Num Requests: "+count);
				System.out.println("Num Requests Distintos: "+countDiferentes);
				System.out.println("Segundos desde corte: "+ (dataFim.getTime() - dataInicioIntervalo.getTime())/1000);
				System.out.println("Segundos deste a primeira requisicao: "+ (dataFim.getTime() - dataPrimeiraRequisicao.getTime())/1000);
				e.printStackTrace();
			}
			if(!sucesso){
				try {
					System.out.println("Aguardando tempo para nova requisicao....");
//					Thread.sleep(60000);
					Thread.sleep(5000);
					System.out.println("Tentando mandar nova requisicao....");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
		return aresta;
	}

	public Aresta gerenciaSolicitacoes(String fonte, String caracter, String conceito){

		String uriFinal = fonte+conceito+caracter+Constantes.LIMIT;
		Aresta aresta = consultaConceptnet5(uriFinal+Constantes.ZERO);
		Aresta arestaAux = null;
		if(aresta.getNumFound() > JANELA){
			int qtIt = aresta.getNumFound() / JANELA;
			int rest = aresta.getNumFound() % JANELA;
			int i = 1;
			aresta = consultaConceptnet5(uriFinal+JANELA);
			while(i < qtIt){
				arestaAux = consultaConceptnet5(uriFinal+JANELA+Constantes.OFFSET+i*JANELA);
				aresta.addSetEdge(arestaAux.getEdges());
				i++;
			}
			arestaAux = consultaConceptnet5(uriFinal+rest+Constantes.OFFSET+i*JANELA);
			aresta.addSetEdge(arestaAux.getEdges());
		}else{
			aresta = consultaConceptnet5(uriFinal+aresta.getNumFound());
		}
		return aresta;
	}
	
	public Aresta gerenciaSolicitacoesOnlyNumFound(String fonte, String caracter, String conceito){
		String uriFinal = fonte+conceito+caracter+Constantes.LIMIT;
		return consultaConceptnet5(uriFinal+Constantes.ZERO);
	}

}
