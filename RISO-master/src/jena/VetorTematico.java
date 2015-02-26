package jena;

import java.util.ArrayList;
import java.util.List;

import riso.builder.conceptNet5.URI.out.Topico;


public class VetorTematico implements Comparable<VetorTematico> {

	private List<Topico> vetor;
	private String conceito;
	private double desambiguador;

	public VetorTematico(List<Topico> vetor, String conceito) {
		setVetor(vetor);
		setConceito(conceito);
	}

	public double getDesambiguador() {
		return desambiguador;
	}

	public void setDesambiguador(double desambiguador) {
		this.desambiguador = desambiguador;
	}

	public void setVetor(List<Topico> vetor) {
		this.vetor = vetor;
	}

	public VetorTematico(String vetor, String conceito) {
		
		String strings[] = vetor.split(",");
		List<Topico> vector = new ArrayList<Topico>();

		for (String string : strings) {
			String partes[] = string.split("\\|");
			vector.add(new Topico(partes[1].trim(), partes[0].trim()));
		}

		setVetor(vector);
		setConceito(conceito);
	}

	public String getConceito() {
		return conceito;
	}

	public void setConceito(String conceito) {
		this.conceito = conceito;
	}

	public List<Topico> getVetor() {
		return vetor;
	}

	public String toStringEspecial(){

		String geral = "[";

		for (Topico str : getVetor()) {
			geral+=str.getConceito()+"|"+str.getUri()+",";
		}
		geral = geral.substring(0, geral.length()-1)+"]";
		return geral;
	}
	
	public String toString(){

		String geral="";
		for (Topico str : getVetor()) {
			geral+=str.getConceito()+"|"+str.getUri()+",";
		}
		geral = geral.substring(0, geral.length()-1);
		return geral;
	}
	
	public int compareTo(VetorTematico o) {
		
		if(o.getDesambiguador() == getDesambiguador()){
			return 0;
		}else if(o.getDesambiguador() > getDesambiguador()){
			return 1;
		}
		return -1;
	}

	public static void main(String args[]){

		String vetor = "[eastern_texas ,jaguar ,Marvel_Comics ,band ,Archie_Comics ,novel ,Insurgent_Comix ,a_large_spotted_feline_of_tropical_America_similar_to_the_leopard ,film ]";
		vetor = vetor.replace("[", "");
		vetor = vetor.replace("]", "");

		String strings[] = vetor.split(",");
	

		for (String string : strings) {
			System.out.println(string);		
		}


	}
}
