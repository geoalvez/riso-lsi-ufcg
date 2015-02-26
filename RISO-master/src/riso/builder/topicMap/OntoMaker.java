package riso.builder.topicMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jena.HierarchyBuilder;
import jena.VetorTematico;
import riso.builder.conceptNet5.URI.out.ArestaConceptNet;
import riso.builder.conceptNet5.URI.out.Topico;
import riso.builder.documents.Documento;
import riso.db.RisoDAO;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class OntoMaker {

	private OntModel baseMinimal =  ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
	private OntModel baseVetorTematico =  ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
	private OntModel modeloMinimal =  ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
	private final String BASE ="http://lsi.dsc.ufcg.edu.br/riso.owl#";
	private final String RELACOES = "http://lsi.dsc.ufcg.edu.br/riso.owl#/r/";
	private RisoDAO risoDAO = new RisoDAO();
	private final String HAS_CONTEXT = "hasContext";
	private List<VetorTematico> vetoresTematicos = new ArrayList<VetorTematico>();

	public List<VetorTematico> getVetoresTematicos() {
		return vetoresTematicos;
	}

	public void setVetoresTematicos(List<VetorTematico> vetoresTematicos) {
		this.vetoresTematicos = vetoresTematicos;
	}

	public RisoDAO getRisoDAO() {
		return risoDAO;
	}

	public void setRisoDAO(RisoDAO risoDAO) {
		this.risoDAO = risoDAO;
	}

	public OntModel getBaseMinimal() {
		return baseMinimal;
	}

	public void setBaseMinimal(OntModel baseMinimal) {
		this.baseMinimal = baseMinimal;
	}

	public OntModel getBaseVetorTematico() {
		return baseVetorTematico;
	}

	public void setBaseVetorTematico(OntModel baseVetorTematico) {
		this.baseVetorTematico = baseVetorTematico;
	}

	public OntModel getModeloMinimal() {
		return modeloMinimal;
	}

	public void setModeloMinimal(OntModel modeloMinimal) {
		this.modeloMinimal = modeloMinimal;
	}

	public void adicionaAfirmacao(ArestaConceptNet afirmacao){

		String s = BASE+afirmacao.getStart().trim();
		String p = afirmacao.getRel().trim();
		String o = BASE+afirmacao.getEnd().trim();

		OntClass suj = getBaseMinimal().createClass(s+" "+p);
		OntClass obj = getBaseMinimal().createClass(o+" "+p);
		suj.addSuperClass(obj);

		OntClass sujeito = getBaseVetorTematico().createClass(s);
		OntClass objeto = getBaseVetorTematico().createClass(o);
		sujeito.addSuperClass(objeto);
	}

	private void adicionaContexto(String conceito, OntModel mini){
		
		OntModel minimal = getModeloMinimal();
		String classe = BASE+"/c/en/"+conceito+"@CONTEXT";
		OntClass contexto = minimal.createClass(classe);
		OntProperty propHasContext = minimal.createOntProperty(RELACOES+HAS_CONTEXT);

		for (VetorTematico vetor : getVetoresTematicos()) {
			Topico topico = vetor.getVetor().get(0);
			String uri = topico.getUri();
			OntClass conc = minimal.getOntClass(uri);
			if (propHasContext!= null){
				System.out.println(propHasContext);
			}else{
				System.out.println("propHasContext nulo!!! ");
			}
			
			
			if (null != conc ){
				System.out.println(conc);
			}else{
				System.out.println("conc nulo!!! ");
			}
			
			if (null != propHasContext && null != conc){
				contexto.addProperty(propHasContext, conc);
			}
			
		}
		setModeloMinimal(minimal);
	}

	public void criaGrafoMinimal(String conceito){
		getBaseMinimal().setNsPrefix("base", BASE);
		try{
			FileOutputStream outInicio = new FileOutputStream(new File("reuters/results/"+conceito+"_ontoInicio.txt"));
			FileOutputStream outMinimal = new FileOutputStream(new File("reuters/results/"+conceito+"_ontoMinimal.txt"));

			OntModel minimal = getBaseMinimal();
			getBaseMinimal().write(outInicio);
			HierarchyBuilder hierarquia = new HierarchyBuilder();
			OntModel grafoMinimo = hierarquia.getMinimalGraph(minimal);
			setModeloMinimal(grafoMinimo);
			adicionaContexto(conceito, grafoMinimo);
			getRisoDAO().insereGrafoNomeado(getModeloMinimal());
			getModeloMinimal().write(outMinimal);

		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void criaVetorTematico(String conceito){
		HierarchyBuilder hierarquia = new HierarchyBuilder();
		List<List<Topico>> vetorTematico = hierarquia.getTematicVectores(getBaseVetorTematico());
		List<VetorTematico> vetoresTematicos = new ArrayList<VetorTematico>();
		for (List<Topico> list : vetorTematico) {
			vetoresTematicos.add(new VetorTematico(list,conceito));
		}
		setVetoresTematicos(vetoresTematicos);

		getRisoDAO().salvarVetores(vetoresTematicos);
	}

	
	private String documentoSemExtensao(String nomeArquivo){
		
		return nomeArquivo.substring(0, nomeArquivo.indexOf(".")).trim();
		
	}
	public void indexaDocumento(Documento doc, String texto, Topico topico){

		final String HAS_TEXT = "hasText";

		String documento = BASE+"/"+doc.getNomeArquivo().trim();

		Model informacaoAtual = getRisoDAO().recuperaModeloNomeado();
		OntModelSpec spec = new OntModelSpec( OntModelSpec.OWL_MEM );
		OntModel indexador = ModelFactory.createOntologyModel(spec, informacaoAtual);
		

		OntClass sujDocument = indexador.createClass(documento);
		OntProperty propHasText = indexador.createOntProperty(RELACOES+HAS_TEXT);
		OntProperty propHasContext = indexador.createOntProperty(RELACOES+HAS_CONTEXT);


		OntClass contextoTexto = indexador.getOntClass(topico.getUri());
		texto = BASE+"/@"+texto;
		
		sujDocument.addProperty(propHasText, texto);
		sujDocument.addProperty(propHasContext, contextoTexto);	


		FileOutputStream teste2;

		try {
			teste2 = new FileOutputStream(new File("reuters/results/teste2.txt"));
			indexador.write(teste2);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		getRisoDAO().insereGrafoNomeado(indexador);


	}



}
