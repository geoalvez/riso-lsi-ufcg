package riso;

import java.util.Iterator;
import java.util.List;

import jena.VetorTematico;
import riso.builder.conceptNet5.URI.out.ConceitoExpandido;
import riso.builder.conceptNet5.URI.out.FiltroDeResultados;
import riso.builder.conceptNet5.URI.out.Topico;
import riso.builder.documents.Biblioteca;
import riso.builder.documents.Desambiguador;
import riso.builder.documents.Documento;
import riso.builder.topicMap.OntoMaker;
import riso.db.biblioteca.BibliotecaDAO;
import riso.db.vetoresTematicos.VetorTematicoDAO;

public class RISO {
	
	public static void main(String args[]){
		
		Biblioteca biblioteca = new Biblioteca();

		List<Documento> documentos = biblioteca.constroiDocumentos();
		
		BibliotecaDAO bibDAO = new BibliotecaDAO(documentos);
		Iterator<Documento> it = documentos.iterator();
		
		while(it.hasNext()){
			
			Documento doc = it.next();
			String palavrasMarcadas = doc.getPalavrasMarcadas();
			
			if(!palavrasMarcadas.isEmpty()){
				boolean esseDocumentoExiste = bibDAO.verificaSeDocumentoExiste(doc);
				if(!esseDocumentoExiste){
					bibDAO.salvaDocumento(doc);
				}
				String conceitos[] = palavrasMarcadas.split(",");
				
				for (String conceito : conceitos) {
					VetorTematicoDAO vetorDAO = new VetorTematicoDAO();
					List<VetorTematico> vetores = vetorDAO.obtemVetoresTematicos(conceito);
					
					if(vetores.isEmpty()){
						System.out.println("Vetor vazio"); // george remover
						ConceitoExpandido conceitoExp = new ConceitoExpandido(conceito);
						conceitoExp.enriqueceConceito(FiltroDeResultados.SEARCH_TEXT,"&");
						vetores = vetorDAO.obtemVetoresTematicos(conceito);
					}else{
						System.out.println("Vetor nao vazio"); // george remover
					}
	 
					Desambiguador desambiguador = new Desambiguador(doc, vetores);
					desambiguador.calculaVetorTermos(conceito);
					if (desambiguador.getVetorTematico().size() > 0){
						VetorTematico vetor = desambiguador.getVetorTematico().get(0);
						Topico topico = vetor.getVetor().get(0);

						OntoMaker onto = new OntoMaker();
						onto.indexaDocumento(doc, conceito, topico);
						
					}
					
				}	
			}
			
			
		}
		
	}

}
