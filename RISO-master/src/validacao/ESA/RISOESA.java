package validacao.ESA;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import jena.VetorTematico;
import riso.builder.conceptNet5.URI.out.ConceitoExpandido;
import riso.builder.conceptNet5.URI.out.FiltroDeResultados;
import riso.db.RisoDAO;
import validacao.reuters.LeitorArquivo;

public class RISOESA {

	public static void main(String args[]) throws IOException{
		//o o nome nao existe para o ingles, o pertence a uma versao da wikipedia para o qual esta desatualizado ou nao existem
		//em nenhuma das fontes externas analisadas

		RisoDAO risoDAO = new RisoDAO();
		LeitorArquivo leitor = new LeitorArquivo("ESA/ConceitosTesteTT.txt");
		leitor.geraPalavrasArquivo();
		List<String> conceitos = leitor.getPalavras();
		FileOutputStream file = new FileOutputStream(new File("ESA/RISOT_ConceitosTestTT_V.txt"));  
		int qtdConceitos = conceitos.size();
		for (int i = 0; i < qtdConceitos; i++) {
			String con = conceitos.get(i);
			System.out.println(i+1+")"+con);
			String conceito = con.replaceAll("-", " ");
			conceito = URLEncoder.encode(conceito,"UTF-8");
			ConceitoExpandido conceitoExp = new ConceitoExpandido(conceito);
			conceitoExp.enriqueceConceito(FiltroDeResultados.SEARCH_TEXT,"&");
			List<VetorTematico>  vetorTematico  = risoDAO.obtemVetoresTematicos(conceito);
			String fin = con+":"+vetorTematico+"\n";
			file.write(fin.getBytes());
		}
		file.close();
	}
}
