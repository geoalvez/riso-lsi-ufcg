package riso.db;

import java.util.List;

import jena.VetorTematico;
import riso.db.topicMap.TopicMapDAO;
import riso.db.vetoresTematicos.VetorTematicoDAO;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;

public class RisoDAO {

	private VetorTematicoDAO vetorDAO = new VetorTematicoDAO();
	private TopicMapDAO topicDAO  = new TopicMapDAO();

	public VetorTematicoDAO getVetorDAO() {
		return vetorDAO;
	}
	public void setVetorDAO(VetorTematicoDAO vetorDAO) {
		this.vetorDAO = vetorDAO;
	}
	public TopicMapDAO getTopicDAO() {
		return topicDAO;
	}
	public void setTopicDAO(TopicMapDAO topicDAO) {
		this.topicDAO = topicDAO;
	}

	public void	salvarVetores(List<VetorTematico> vetoresTematicos){
		getVetorDAO().salvarVetores(vetoresTematicos);
	}
	
	public List<VetorTematico> obtemVetoresTematicos(String conceito){
		return getVetorDAO().obtemVetoresTematicos(conceito);
	}
	
	public void insereGrafoNomeado(OntModel grafo){
		getTopicDAO().addGrafoNomeado(grafo);
	}
	
	public Model recuperaModeloNomeado(){
		return getTopicDAO().recuperaModeloNomeado();
	}

}
