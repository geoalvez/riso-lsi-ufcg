package validacao.reuters;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import riso.builder.conceptNet5.URI.out.ConceitoExpandido;
import riso.builder.conceptNet5.URI.out.FiltroDeResultados;

public class RISOValidatorReuters {


	public static void main(String args[]) throws IOException{
//o o nome nao existe para o ingles, o pertence a uma versao da wikipedia para o qual esta desatualizado ou nao existem
		//em nenhuma das fontes externas analisadas
		LeitorArquivo leitor = new LeitorArquivo("reuters/all-orgs-strings.lc.txt");
		leitor.geraPalavrasArquivo();
		List<String> conceitos = leitor.getPalavras();
		FileOutputStream file = new FileOutputStream(new File("reuters/Reuters_Orgs.txt"));  
		int identificados = 0;
		int qtdConceitos = conceitos.size();
		int qtdElementosIdentificados = 0;
		for (int i = 0; i < qtdConceitos; i++) {
			String con = conceitos.get(i);
			String conceito = con.replaceAll("-", " ");
			conceito = URLEncoder.encode(conceito,"UTF-8");
			ConceitoExpandido conceitoExp = new ConceitoExpandido(conceito);
			int valor = conceitoExp.getQtafirmacoesUteis(FiltroDeResultados.SEARCH_TEXT,"&");
			if(valor>0){
				identificados++;
			}
			qtdElementosIdentificados +=valor;
			String fin = con+":["+valor+"]\n";
			System.out.println((i+1)+")"+fin);
	        file.write(fin.getBytes());
			//TODO RISO completo!
			//ConceitoExpandido conceitoExp = new ConceitoExpandido(conceito);
			//conceitoExp.enriqueceConceito(FiltroDeResultados.SEARCH_TEXT,"&");
			//List<VetorTematico> vetores = vetorDAO.obtemVetoresTematicos(conceito);
			
		}
		String nomesLidos = "Qtde nomes lidos: "+qtdConceitos+"\n";
		file.write(nomesLidos.getBytes());
		String nomesIndentificados = "Qtde nomes indentificados: "+identificados+"\n";
		file.write(nomesIndentificados.getBytes());
		String porcentagem = "Porcetagem: "+(((double)identificados*100)/(double)qtdConceitos)+"%\n";
		file.write(porcentagem.getBytes());
		String quantidadeTermosEmMedia ="Qtde do enriquecimento medio:"+((double)qtdElementosIdentificados / (double)qtdConceitos)+"\n";
		file.write(quantidadeTermosEmMedia.getBytes());
		System.out.println(nomesLidos);
		System.out.println(nomesIndentificados);
		System.out.println(porcentagem);
		System.out.println(quantidadeTermosEmMedia);
		file.close();
	}
}
