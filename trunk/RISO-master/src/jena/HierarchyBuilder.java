package jena;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import riso.builder.conceptNet5.URI.Constantes;
import riso.builder.conceptNet5.URI.out.Topico;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.util.iterator.Filter;


public class HierarchyBuilder {


	protected OntModel model;

	private OntModel getModel() {
		return model;
	}

	private void setModel(OntModel model) {
		this.model = model;
	}

	private final String conceitos = "http://lsi.dsc.ufcg.edu.br/riso.owl#/c/en/";
	private final String relacoes = "http://lsi.dsc.ufcg.edu.br/riso.owl#";


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<List<Topico>> getTematicVectores(OntModel m) {
		ExtendedIterator<OntClass> i = m.listHierarchyRootClasses()
				.filterDrop( new Filter() {
					public boolean accept( Object o ) {
						return ((Resource) o).isAnon();
					}} );
		List<List<Topico>> vetores = new ArrayList<List<Topico>>();
		while (i.hasNext()) {
			List<Topico> vetor = new ArrayList<Topico>();
			OntClass raiz = i.next();
			setVector(vetor,(OntClass) raiz, new ArrayList<OntClass>());
			vetores.add(vetor);
		}

		return vetores;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<String> getListContextos(OntModel m){
		
		ExtendedIterator<OntClass> i = m.listHierarchyRootClasses()
				.filterDrop( new Filter() {
					public boolean accept( Object o ) {
						return ((Resource) o).isAnon();
					}} );
		
		List<String> vetor = new ArrayList<String>();
		while (i.hasNext()) {
			OntClass no = i.next();
			if(!no.listDeclaredProperties().hasNext()){
				vetor.add(no.getURI());
			}
		}
		return vetor;
	}


	private void setVector(List<Topico> vetor, OntClass cls, List<OntClass> occurs) {

		if (cls.canAs( OntClass.class )  &&  !occurs.contains( cls )) {
			String uri = cls.getURI();
			String elemento = cls.getURI();
			int posicaoEN = elemento.lastIndexOf("/en/")+4;
			int posicaoN = elemento.lastIndexOf("/n/");
			int posicaoA = elemento.lastIndexOf("/a/");
			int posicaoV = elemento.lastIndexOf("/v/");

			if(posicaoN > 0){
				String desamb = elemento.substring(posicaoN+3, elemento.length());
				elemento = elemento.substring(posicaoEN, posicaoN);
				elemento +="("+desamb+")";
			}else if(posicaoV > 0){
				String desamb = elemento.substring(posicaoV+3, elemento.length());
				elemento = elemento.substring(posicaoEN, posicaoV);
				elemento +="("+desamb+")";
			}else if(posicaoA > 0){
				String desamb = elemento.substring(posicaoA+3, elemento.length());
				elemento = elemento.substring(posicaoEN, posicaoA);
				elemento +="("+desamb+")";
			}else{
				elemento = elemento.substring(posicaoEN, elemento.length());
			}
			elemento = elemento.replaceAll("_", " ");

			Topico topico = new Topico(uri,elemento);
			if(!vetor.contains(topico)){
				vetor.add(topico);
			}
			for (@SuppressWarnings("rawtypes")
			Iterator i = cls.listSubClasses( true );  i.hasNext(); ) {
				OntClass sub = (OntClass) i.next();
				occurs.add( cls );
				setVector(vetor, sub, occurs);
				occurs.remove( cls );
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public OntModel getMinimalGraph(OntModel m) {
		ExtendedIterator<OntClass> i = m.listHierarchyRootClasses()
				.filterDrop( new Filter() {
					public boolean accept( Object o ) {
						return ((Resource) o).isAnon();
					}} );
		setModel(ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM));

		while (i.hasNext()) {
			buildMinimalGraph(getModel(),null,(OntClass) i.next(), new ArrayList<OntClass>(), 0 );
		}
		getModel().setNsPrefix("coneitos", conceitos);
		getModel().setNsPrefix("rel", "http://lsi.dsc.ufcg.edu.br/riso.owl#/r/");
		return getModel();
	}



	private void buildMinimalGraph(OntModel model, OntClass superior, OntClass cls, List<OntClass> occurs, int depth) {
		buildGraph(model,superior,cls);
		if (cls.canAs( OntClass.class )  &&  !occurs.contains( cls )) {
			for (@SuppressWarnings("rawtypes")
			Iterator i = cls.listSubClasses( true );  i.hasNext(); ) {
				OntClass sub = (OntClass) i.next();
				occurs.add( cls );
				buildMinimalGraph(model, cls, sub, occurs, depth + 1);
				occurs.remove( cls );
			}
		}
	}


	private void buildGraph(OntModel model, OntClass superior, OntClass obj) {

		if(superior != null){
			String sup = superior.getURI();
			sup = sup.substring(0, sup.indexOf(" "));
			OntClass objeto = model.createClass(sup.trim());
			String[] partes = obj.getURI().split(" ");
			OntClass sujeito = model.createClass(partes[0].trim());
			String relacao = partes[1].trim();
			OntProperty predicado = null;
			if(verificaRelacaoTransitiva(relacao)){
				predicado = model.createTransitiveProperty(relacoes+relacao);
			}else if(verificaRelacaoSimetrica(relacoes+relacao)){
				predicado = model.createSymmetricProperty(relacoes+relacao);
			}else{
				predicado = model.createOntProperty(relacoes+relacao);
			}
			sujeito.addProperty(predicado, objeto);
		}

	}

	private boolean verificaRelacaoSimetrica(String relacao){
		for (String rel : Constantes.RELACOES_SIMETRICAS) {
			if(relacao.contains(rel)){
				return true;
			}
		}
		return false;
	}

	private boolean verificaRelacaoTransitiva(String relacao){
		for (String rel : Constantes.RELACOES_TRANSITIVAS) {
			if(relacao.contains(rel)){
				return true;
			}
		}
		return false;
	}


	public static void main(String args[]){

		String elemento = "http://lsi.dsc.ufcg.edu.br/riso.owl#/c/en/male_bond/a/";
		int posicaoEN = elemento.lastIndexOf("/en/")+4;
		int posicaoN = elemento.lastIndexOf("/n/");
		int posicaoA = elemento.lastIndexOf("/a/");
		int posicaoV = elemento.lastIndexOf("/v/");

		if(posicaoN > 0){
			String desamb = elemento.substring(posicaoN+3, elemento.length());
			elemento = elemento.substring(posicaoEN, posicaoN);
			elemento +="("+desamb+")";
		}else if(posicaoV > 0){
			String desamb = elemento.substring(posicaoV+3, elemento.length());
			elemento = elemento.substring(posicaoEN, posicaoV);
			elemento +="("+desamb+")";
		}else if(posicaoA > 0){
			String desamb = elemento.substring(posicaoA+3, elemento.length());
			elemento = elemento.substring(posicaoEN, posicaoA);
			elemento +="("+desamb+")";
		}else{
			elemento = elemento.substring(posicaoEN, elemento.length());
		}
		elemento = elemento.replaceAll("_", " ");
		System.out.println(elemento);
	}
}

