package jena;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public class JenaOWL {



	public OntModel lerOntologia(String url){
		/*
		 * Utilizando uma ontologia existente...
		 */
		OntModel model;
		model=ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		model.read(url);
		return model;
	}

	private void printInstancesInfo(OntModel model) {

		Iterator<OntClass> itClass = model.listClasses();

		while(itClass.hasNext()){
			OntClass classe = itClass.next();
			System.out.println("Instancia de Classe: "+classe.getURI());
			ExtendedIterator<? extends OntResource> indiduo = classe.listInstances();
			ExtendedIterator<OntProperty> itPropriedades = classe.listDeclaredProperties();

			while(indiduo.hasNext()){

				Individual indiv = (Individual) indiduo.next();
				System.out.println("\t"+indiv);

				while(itPropriedades.hasNext()){
					OntProperty propertie = itPropriedades.next();
					System.out.print("\t\t"+propertie.getURI()+": ");
					System.out.print(indiv.getPropertyValue(propertie)+"\n");
				}

			}	


		}
	}

	public void imprimeInformacoesOntologia(OntModel model){

		ExtendedIterator<OntClass> classes = model.listClasses();

		while(classes.hasNext()){

			OntClass cls = classes.next();

			ExtendedIterator<? extends OntResource> it = cls.listInstances();
			ExtendedIterator<? extends OntResource> itp = cls.listDeclaredProperties();

			System.out.println("Instances for class:"+cls.getURI());

			while(it.hasNext()){
				System.out.println("\t"+((Individual)it.next()).getURI());
			}

			System.out.println("Properties for class:"+cls.getURI());
			while(itp.hasNext()){
				System.out.println("\t"+(OntProperty)itp.next());
			}
		}
	}


	public OntModel criaOntoligia(String sujeito, String predicado, String objeto, OntModel base){

		if(base == null){
			base = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		}

		String baseUri="http://lsi.dsc.ufcg.edu.br/riso.owl#";

		OntClass suj = base.createClass(baseUri+sujeito.trim()+" "+predicado.trim());
		OntClass obj = base.createClass(baseUri+objeto.trim()+" "+predicado.trim());
		suj.addSuperClass(obj);

		return base;
	}
	
	public OntModel criaGrafos(String sujeito, String predicado, String objeto, OntModel base){

		if(base == null){
			base = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		}

		//String baseUri="http://lsi.dsc.ufcg.edu.br/riso.owl#";

		OntClass suj = base.createClass(sujeito);
		OntClass obj = base.createClass(objeto);
		suj.addSuperClass(obj);

		return base;
	}

	public static void main(String args[]){

		/*
		 *Criando uma ontologia... 
		 */
		JenaOWL jena = new JenaOWL();
		OntModel model = jena.criaOntoligia("/c/en/jaguar_xf", "/r/IsA", "/c/en/car",null);
		jena.criaOntoligia("/c/en/jaguar_xf", "/r/IsA ", "/c/en/mean_of_transportation",model);
		jena.criaOntoligia("/c/en/jaguar_xf", "/r/IsA", "/c/en/jaguar",model);

		jena.criaOntoligia("/c/en/jaguar_xf", "/r/IsA", "/c/en/product",model);

		jena.criaOntoligia("/c/en/car", "/r/IsA", "/c/en/mean_of_transportation",model);
		jena.criaOntoligia("/c/en/car", "/r/IsA", "/c/en/product",model);
		jena.criaOntoligia("/c/en/mean_of_transportation", "/r/IsA","/c/en/product" ,model);
		jena.criaOntoligia("/c/en/door", "/r/PartOf", "/c/en/car",model);
		jena.criaOntoligia("/c/en/window", "/r/PartOf", "/c/en/door",model);
		jena.criaOntoligia("/c/en/auto", "/r/UsedFor", "/c/en/drive",model);

		jena.criaOntoligia("/c/en/jaguar_cat", "/r/IsA", "/c/en/Panthera",model);
		jena.criaOntoligia("/c/en/Panthera", "/r/MemberOf", "/c/en/mammal",model);

		jena.criaOntoligia("/c/en/vertebrado", "/r/IsA", "/c/en/animal",model);
		jena.criaOntoligia("/c/en/jaguar_cat", "/r/IsA", "/c/en/mammal",model);
		jena.criaOntoligia("/c/en/jaguar_cat", "/r/IsA", "/c/en/vertebrado",model);
		jena.criaOntoligia("/c/en/jaguar_cat", "/r/IsA", "/c/en/jaguar",model);

		jena.criaOntoligia("/c/en/mammal", "/r/IsA", "/c/en/animal",model);
		jena.criaOntoligia("/c/en/mammal", "/r/IsA", "/c/en/vertebrado",model);
		
		
		OntModel m = jena.criaGrafos("/c/en/jaguar_xf", "/r/IsA", "/c/en/car",null);
		jena.criaGrafos("/c/en/jaguar_xf", "/r/IsA", "/c/en/mean_of_transportation",m);
		jena.criaGrafos("/c/en/jaguar_xf", "/r/IsA", "/c/en/product",m);
		jena.criaGrafos("/c/en/jaguar_xf", "/r/IsA", "/c/en/jaguar",m);

		jena.criaGrafos("/c/en/car", "/r/IsA", "/c/en/mean_of_transportation",m);
		jena.criaGrafos("/c/en/car", "/r/IsA", "/c/en/product",m);
		jena.criaGrafos("/c/en/mean_of_transportation", "/r/IsA","/c/en/product" ,m);
		jena.criaGrafos("/c/en/door", "/r/PartOf", "/c/en/car",m);
		jena.criaGrafos("/c/en/window", "/r/PartOf", "/c/en/door",m);
		jena.criaGrafos("/c/en/auto", "/r/UsedFor", "/c/en/drive",m);

		jena.criaGrafos("/c/en/jaguar_cat", "/r/IsA", "/c/en/eucarionte",m);
		jena.criaGrafos("/c/en/jaguar_cat", "/r/IsA", "/c/en/Panthera",m);
		jena.criaGrafos("/c/en/Panthera", "/r/MemberOf", "/c/en/mammal",m);
		jena.criaGrafos("/c/en/mammal", "/r/IsA", "/c/en/eucarionte",m);

		jena.criaGrafos("/c/en/vertebrado", "/r/IsA", "/c/en/animal",m);
		jena.criaGrafos("/c/en/jaguar_cat", "/r/IsA", "/c/en/mammal",m);
		jena.criaGrafos("/c/en/baleia", "/r/IsA", "/c/en/mammal",m);
		jena.criaGrafos("/c/en/perro", "/r/IsA", "/c/en/mammal",m);

		jena.criaGrafos("/c/en/jaguar_cat", "/r/IsA", "/c/en/vertebrado",m);
		jena.criaGrafos("/c/en/jaguar_cat", "/r/IsA", "/c/en/jaguar",m);

		jena.criaGrafos("/c/en/mammal", "/r/IsA", "/c/en/animal",m);
		jena.criaGrafos("/c/en/mammal", "/r/IsA", "/c/en/vertebrado",m);
		
		model.write(System.out);
		//OntResource res= model.getOntResource("http://lsi.dsc.ufcg.edu.br/riso.owl#/c/en/jaguar");

		HierarchyBuilder h = new HierarchyBuilder();
		OntModel minimalGraph = h.getMinimalGraph(model);
		minimalGraph.write(System.out);
		
//		//List<List<String>> grafo = h.getTematicVectores(m);
//
//		List<VetorTematico> vetoresTematicos = new ArrayList<VetorTematico>();
//		for (List<String> list : grafo) {
//			vetoresTematicos.add(new VetorTematico(list,"jaguar"));
//		}
		
	//	ConceitoExpandido conceito = new ConceitoExpandido("jaguar", vetoresTematicos, minimalGraph);
		
//		List<VetorTematico> vetores = conceito.getVetoresTematicos();
//		
//		for (VetorTematico vT : vetores) {
//			System.out.println(vT);
//		}

	}
}
