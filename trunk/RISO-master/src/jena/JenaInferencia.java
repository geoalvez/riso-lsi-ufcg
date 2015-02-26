package jena;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.ProfileRegistry;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.util.PrintUtil;

public class JenaInferencia {

	public void printStatements(Model m, Resource s, Property p, Resource o) {
		
		for (StmtIterator i = m.listStatements(s,p,o); i.hasNext(); ) {
			Statement stmt = i.nextStatement();
			System.out.println(" - " + PrintUtil.print(stmt));
		}
	}

		public static void main(String args[]){

			OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_LITE_MEM_RULES_INF);
			OntModel data = ModelFactory.createOntologyModel(ProfileRegistry.OWL_LITE_LANG);

			Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
			reasoner = reasoner.bindSchema(model);
			InfModel infmodel = ModelFactory.createInfModel(reasoner, model);

			String URN = "http://max.uma.pt/~a2018601/RUDI.owl";
			Resource res = infmodel.getResource(URN+"#SW");
			System.out.println("Semantic Web *:");
			JenaInferencia jenaInf = new JenaInferencia();
			jenaInf.printStatements(infmodel, res, null, null); 


		}
	}
