package validacao.reuters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeitorArquivo {

	private String nomeArquivo;
	private List<String> palavras;
	
	public LeitorArquivo(String nomeArquivo){
		setNomeArquivo(nomeArquivo);
	}

	public void geraPalavrasArquivo() {

		List<String> listaDeTipos = new ArrayList<String>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(getNomeArquivo()));
			while (in.ready()) {
				listaDeTipos.add(new String(in.readLine().getBytes(), "UTF-8").trim());
			}
			in.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		setPalavras(listaDeTipos);
	}
	
	public void geraPalavrasShiftadasdas() {

		List<String> listaDeTipos = new ArrayList<String>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(getNomeArquivo()));
			while (in.ready()) {
				String termo = in.readLine();
				String primeiroTermo = termo.split("\t")[0];
				listaDeTipos.add(new String(primeiroTermo.getBytes(), "UTF-8").trim());
			}
			in.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		setPalavras(listaDeTipos);
	}
	
	public void geraPalavrasEspaco() {

		List<String> listaDeTipos = new ArrayList<String>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(getNomeArquivo()));
			while (in.ready()) {
				String termo = in.readLine();
				String primeiroTermo = termo.split(" ")[0];
				listaDeTipos.add(new String(primeiroTermo.getBytes(), "UTF-8").trim());
			}
			in.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		setPalavras(listaDeTipos);
	}
	
	public void geraPalavrasParceadasBarra() {

		List<String> listaDeTipos = new ArrayList<String>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(getNomeArquivo()));
			while (in.ready()) {
				String termo = in.readLine();
				String primeiroTermo = termo.split("/")[0];
				listaDeTipos.add(new String(primeiroTermo.getBytes(), "UTF-8").trim());
			}
			in.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		setPalavras(listaDeTipos);
	}
	
	public void geraPalavrasParceadasDoisPontos() {

		List<String> listaDeTipos = new ArrayList<String>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(getNomeArquivo()));
			while (in.ready()) {
				String termo = in.readLine();
				String primeiroTermo = termo.split(":")[0];
				listaDeTipos.add(new String(primeiroTermo.getBytes(), "UTF-8").trim());
			}
			in.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		setPalavras(listaDeTipos);
	}
	
	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}


	
	public List<String> getPalavras() {
		return palavras;
	}

	public void setPalavras(List<String> palavras) {
		this.palavras = palavras;
	}

	public static void main(String args[]){
		LeitorArquivo leitor = new LeitorArquivo("pos/part-of-speech.txt");
		leitor.geraPalavrasShiftadasdas();
		for (String str : leitor.getPalavras()) {
			System.out.println(str);
		}
		
	}
}
