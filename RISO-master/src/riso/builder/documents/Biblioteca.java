package riso.builder.documents;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import riso.builder.conceptNet5.URI.Constantes;


public class Biblioteca {

	private final String PASTA_FINAL = "documents//";
	private final String EXPRESSAO_CONCEITOS =  "(\\[(.+?)\\])+";
	private final String EXPRESSAO_TOKENS = "[^\\w\\s]";

	public List<Documento> listDirectoryAppend(File dir, List<Documento> lista) {  

		String texto = "";
		if (dir.isDirectory()) {  
			String[] filhos = dir.list();  
			
			for (int i = 0; i < filhos.length; i++) {  
				File nome = new File(dir, filhos[i]);  
				
				if (nome.isFile()) {  
					texto = obtemTextoDocumento(nome.getPath());
					lista.add(new Documento(getMarcacoes(texto),getVetorDeDocumentos(texto),nome.getPath()));  

				} else if (nome.isDirectory()) {  
					listDirectoryAppend(nome, lista);  
				}  
			}  
		} else {  
			texto = obtemTextoDocumento(dir.getPath());
			lista.add(new Documento(getMarcacoes(texto),getVetorDeDocumentos(texto),dir.getPath())); 
		}  
		return lista;  
	} 

	private String obtemTextoDocumento(String nomeArquivo) {
		String str = "";

		try {
			BufferedReader in = new BufferedReader(new FileReader(nomeArquivo));
			while (in.ready()) {
				str += in.readLine();
			}
			in.close();
		} catch (IOException e) {
			
		}

		return str;
	}

	public String getMarcacoes(String texto){

		String marcacoes = "";
		Pattern p = Pattern.compile(EXPRESSAO_CONCEITOS);  
		Matcher m = p.matcher(texto);  
		while (m.find()) {  
			marcacoes += m.group(2)+",";
		} 
		if(marcacoes.length() > 0){
			marcacoes = marcacoes.substring(0, marcacoes.length()-1);
		}
		
		return marcacoes;
	}

	public String getVetorDeDocumentos(String texto){

		String vetor = "";
		texto = texto.replaceAll(EXPRESSAO_TOKENS, "");
		String palavras[] = texto.split(" ");
		if(palavras.length > 0){	
			for (String str : palavras) {
				if(!isStopWord(str)){
					vetor += str+",";
				}
			}
		}
		if(vetor.length() > 0){
			vetor = vetor.substring(0, vetor.length()-1);
		}
		return vetor;
	}
	
	private boolean isStopWord(String str){
		for (String stop : Constantes.STOP_WORDS) {
			if(str.equalsIgnoreCase(stop)){
				return true;
			}
		}
		return false;
	}

	public List<Documento> constroiDocumentos(){

		return listDirectoryAppend(new File(PASTA_FINAL), new ArrayList<Documento>());
	}

	public static void main(String args[]){

		Biblioteca bib = new Biblioteca();
		List<Documento> documentos = bib.constroiDocumentos();
		for (Documento documento : documentos) {
			System.out.println(documento.getNomeArquivo());
			System.out.println(documento.getPalavrasMarcadas());
			System.out.println(documento.getVetorParagrafo());
		}
		
	}
}
