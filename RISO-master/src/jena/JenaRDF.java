package jena;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

public class JenaRDF {


	public Model criaGrafoFamilia(){
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

		model.setNsPrefix("rel", relationshipUri);
		model.setNsPrefix("family", familyUri);
		// Can also create statements directly . . .
		Statement statement = model.createStatement(adam,parentOf,fran);

		// but remember to add the created statement to the model
		model.add(statement);

		model.write(System.out);

		return model;
	}

	public void listaSujeitosComPropriedade(Model model, Property propriedade){
		// List everyone in the model who has a child:
		ResIterator parents = model.listSubjectsWithProperty(propriedade);

		// Because subjects of statements are Resources, the method returned a ResIterator
		while (parents.hasNext()) {

			// ResIterator has a typed nextResource() method
			Resource person = parents.nextResource();

			// Print the URI of the resource
			System.out.println(person.getURI());
		}

	}
	public void listaObjetosComPropriedade(Model model, Resource resorc, Property propriedade){

		NodeIterator parents = null;
		if(resorc == null){
			// List everyone in the model who has a child:
			parents = model.listObjectsOfProperty(propriedade);
		}else{
			// To find all the siblings of a specific person, the model itself can be queried 
			parents = model.listObjectsOfProperty(resorc, propriedade);
		}

		// Because subjects of statements are Resources, the method returned a ResIterator
		while (parents.hasNext()) {

			// ResIterator has a typed nextResource() method
			Resource person = (Resource) parents.next();

			// Print the URI of the resource
			System.out.println(person.getURI());
		}
	}

	public void obtemInformacaoRecurso(Resource resorc, Property propriedade){

		// But it's more elegant to ask the Resource directly
		// This method yields an iterator over Statements
		StmtIterator moreSiblings = resorc.listProperties(propriedade);
		while(moreSiblings.hasNext()){
			Statement st = moreSiblings.next();
			System.out.println(st.getPredicate().getURI());
		}
	}

	public void consultaAfirmacaoGenerica(Model model, Resource sujeito, Property predicado, Resource objeto){
		if(sujeito != null || predicado != null || objeto != null){
			StmtIterator stmIT = 	model.listStatements(sujeito,predicado,objeto);
			while(stmIT.hasNext()){
				System.out.println(stmIT.next().toString());
			}
		}
	}


	public static void main(String args[]){


		JenaRDF jena = new JenaRDF();
		Model grafo = jena.criaGrafoFamilia();
		Property p = grafo.getProperty("http://purl.org/vocab/relationship/parentOf");
		jena.listaSujeitosComPropriedade(grafo, p);
		jena.listaObjetosComPropriedade(grafo, null, p);
		jena.consultaAfirmacaoGenerica(grafo, null, p, null);

	}
}
