package jena;

import java.util.Iterator;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

public class JenaBasico {
	
	final String RISO_CONCEITO = "http:/";
	final String RISO_RELACAO = "http:/";
	
	public void criaModeloRDF(){
		
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
		
	}
	
	public void imprimeRDF(Model model){
		model.write(System.out);
	}
	
	public void imprimeAfirmacoesRDF(Model model){
		
		StmtIterator iter = model.listStatements();

		while (iter.hasNext()) {
			Statement stmt      = iter.nextStatement();
			Resource  subject   = stmt.getSubject();    
			Property  predicate = stmt.getPredicate();   
			RDFNode   object    = stmt.getObject();      

			System.out.print(subject.toString());
			System.out.print(" " + predicate.toString() + " ");
			if (object instanceof Resource) {
				System.out.print(object.toString());
			} else {

				System.out.print(" \"" + object.toString() + "\"");
			}

			System.out.println(" .");
		}
		
	}
	
	public void consultaRDF(Model model){
		System.out.println("Consultando...");
		String queryString = "PREFIX j.0: <http://r/> PREFIX conceito: <http://c/en/> SELECT ?o WHERE { conceito:car j.0:IsA ?o}" ;
		Query query = QueryFactory.create(queryString) ;
		QueryExecution qexec = QueryExecutionFactory.create(query, model) ;
		Iterator<QuerySolution> results = qexec.execSelect();

		try{
			while(results.hasNext()){
				QuerySolution soln = results.next() ;
//				Resource x = soln.getResource("a") ;       
//				Property p = (Property) soln.get("p");
				Resource r = soln.getResource("o") ; 
//				System.out.println(x.toString() + " IsA " + r.toString());
//				System.out.println(p.toString());
				System.out.println(r.toString());

			}
		}

		finally { 
			qexec.close() ; 
		}
	}


	public static void main(String args[]){

		System.out.println();
	}

}
