package jena;

import java.sql.Connection;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.sdb.SDBFactory;
import com.hp.hpl.jena.sdb.Store;
import com.hp.hpl.jena.sdb.StoreDesc;
import com.hp.hpl.jena.sdb.sql.SDBConnection;


public class JenaPersist {

	static final String RISO_CONCEITO = "http:/";
	static final String RISO_RELACAO = "http:/";

	public static Model criaModeloRDF(){

		String conceitoSujeito = RISO_CONCEITO+"/c/en/car";
		String relacao = RISO_RELACAO+"/r/IsA";
		String conceitoObjeto = RISO_CONCEITO+"/c/en/automobile/";
		String conceitoObjeto2 = RISO_CONCEITO+"/c/en/product/";

		Model model = ModelFactory.createDefaultModel();

		Resource cSujeito = model.createResource(conceitoSujeito);
		Property rel = model.createProperty(relacao);
		Resource cObjeto = model.createResource(conceitoObjeto);
		Resource cObjeto2 = model.createResource(conceitoObjeto2);

		cSujeito.addProperty(rel, cObjeto);
		cObjeto.addProperty(rel, cObjeto2);
		return model;
	}
	public static Model criaGrafoFamilia(){
		// URI declarations
		String familyUri = "http://family/";
		String relationshipUri = "http://purl.org/vocab/relationship/";

		// Create an empty Model
		Model model = ModelFactory.createDefaultModel();

		// Create a Resource for each family member, identified by their URI
		Resource adam = model.createResource(familyUri+"adam");
		Resource beth = model.createResource(familyUri+"beth");
		Resource chuck = model.createResource(familyUri+"chuck");
		Resource dotty = model.createResource(familyUri+"dotty");
		Resource edward = model.createResource(familyUri+"edward");
		Resource fran = model.createResource(familyUri+"fran");


		// and so on for other family members

		// Create properties for the different types of relationship to represent
		Property childOf = model.createProperty(relationshipUri,"childOf");
		Property parentOf = model.createProperty(relationshipUri,"parentOf");
		Property siblingOf = model.createProperty(relationshipUri,"siblingOf");
		Property spouseOf = model.createProperty(relationshipUri,"spouseOf");

		// Add properties to adam describing relationships to other family members
		adam.addProperty(siblingOf,beth);
		adam.addProperty(spouseOf,dotty);
		adam.addProperty(parentOf,edward);
		adam.addProperty(childOf,fran);
		fran.addProperty(parentOf, chuck);

		// Can also create statements directly . . .
		Statement statement = model.createStatement(adam,parentOf,fran);

		// but remember to add the created statement to the model
		model.add(statement);

		return model;
	}

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

	public void criaGrafoNomeado(Model grafo){

		Store store = SDBFactory.connectStore("src/jena/sdb.ttl");
		Model model = SDBFactory.connectNamedModel(store, "c/en/familia") ;

		try{
			model.begin();
			model.add(grafo);
			model.setNsPrefix("family","http://family/");
			model.setNsPrefix("rel","http://purl.org/vocab/relationship/");
			model.commit();
		}catch(Exception e){
			model.abort();
		}finally{
			model.close();
			store.close();
		}

	}

	public void criaModeloSemNome(){

		Store store = SDBFactory.connectStore("src/jena/sdb.ttl");
		Model model = SDBFactory.connectDefaultModel(store);
		String relacoes = "http://r//";
		try{
			model.begin();
			Resource vovo = model.createResource("http://vovo");
			Property parente = model.createProperty(relacoes, "parenteDe");
			Resource jul = model.createProperty("http://julinho");
			jul.addProperty(parente, vovo);
			vovo.addProperty(parente, jul);
			model.setNsPrefix("rel", relacoes);
			model.commit();
		}catch(Exception e){
			System.out.println("Entrei...");
			model.abort();
		}
	}

	public void recuperaModeloNomeado(){
		Store store = SDBFactory.connectStore("src/jena/sdb.ttl");
		Model model = SDBFactory.connectNamedModel(store,"c/en/familia");

		model.write(System.out);

		StmtIterator sIter = model.listStatements() ;
		while(sIter.hasNext())
		{
			Statement stmt = sIter.nextStatement() ;
			System.out.println(stmt) ;
		}
		sIter.close() ;
		store.close() ;

	}


	public void recuperaModeloSemNome(){
		Store store = SDBFactory.connectStore("src/jena/sdb.ttl");
		Model model = SDBFactory.connectDefaultModel(store);

		model.write(System.out);

		StmtIterator sIter = model.listStatements() ;
		while(sIter.hasNext())
		{
			Statement stmt = sIter.nextStatement() ;
			System.out.println(stmt) ;
		}
		sIter.close() ;
		store.close() ;

	}

	public static void main(String args[]){

		JenaPersist jena = new JenaPersist();

		Model modelo = JenaPersist.criaGrafoFamilia();

		jena.criaGrafoNomeado(modelo);
		jena.recuperaModeloNomeado();

		jena.criaModeloSemNome();
		jena.recuperaModeloSemNome();

	}
}
