package riso.db.topicMap;

import java.sql.Connection;
import java.util.Map;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.sdb.SDBFactory;
import com.hp.hpl.jena.sdb.Store;
import com.hp.hpl.jena.sdb.StoreDesc;
import com.hp.hpl.jena.sdb.sql.SDBConnection;

public class TopicMapDAO {

	private final String ARQUIVO_CONFIG = "src/riso/db/sdb.ttl";
	private final String TOPIC_MAP = "topicMap";


	public static void query(String queryString,
			StoreDesc storeDesc,
			Connection jdbcConnection)
	{
		Query query = QueryFactory.create(queryString) ;

		SDBConnection conn = SDBFactory.createConnection(jdbcConnection) ;

		Store store = SDBFactory.connectStore(conn, storeDesc) ;

		Dataset ds = SDBFactory.connectDataset(store) ;
		QueryExecution qe = QueryExecutionFactory.create(query, ds) ;
		try {
			ResultSet rs = qe.execSelect() ;
			ResultSetFormatter.out(rs) ;
		} finally { 
			qe.close() ; 
		}
		store.close() ;
	}

	public void addGrafoNomeado(Model grafo){

		Store store = SDBFactory.connectStore(ARQUIVO_CONFIG);
		Model model = SDBFactory.connectNamedModel(store, TOPIC_MAP) ;
		
		try{
			model.begin();
			model.add(grafo);
			for (String relacao : grafo.getNsPrefixMap().keySet()) {
				model.setNsPrefix(relacao, grafo.getNsPrefixMap().get(relacao));
			}
			model.commit();
		}catch(Exception e){
			e.printStackTrace();
			model.abort();
		}finally{
			model.close();
			store.close();
		}

	}

	public void criaModeloSemNome(Model grafo, Map<String,String> prefixos){

		Store store = SDBFactory.connectStore(ARQUIVO_CONFIG);
		Model model = SDBFactory.connectDefaultModel(store);
		try{
			model.begin();
			for (String relacao : prefixos.keySet()) {
				model.setNsPrefix(relacao, prefixos.get(relacao));
			}
			model.commit();
		}catch(Exception e){
			e.printStackTrace();
			model.abort();
		}
	}

	public Model recuperaModeloNomeado(){
		Store store = SDBFactory.connectStore(ARQUIVO_CONFIG);
		Model model = SDBFactory.connectNamedModel(store, TOPIC_MAP);

		return model;
	}


	public Model recuperaModeloSemNome(){
		Store store = SDBFactory.connectStore(ARQUIVO_CONFIG);
		Model model = SDBFactory.connectDefaultModel(store);

		return model;
	}

}
