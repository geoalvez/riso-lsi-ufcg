package validacao.ESA;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;

import de.tudarmstadt.ukp.lmf.api.Uby;
import de.tudarmstadt.ukp.lmf.exceptions.UbyInvalidArgumentException;
import de.tudarmstadt.ukp.lmf.model.core.LexicalEntry;
import de.tudarmstadt.ukp.lmf.model.core.Sense;
import de.tudarmstadt.ukp.lmf.model.enums.EPartOfSpeech;
import de.tudarmstadt.ukp.lmf.transform.DBConfig;

import riso.builder.conceptNet5.URI.out.ConceitoExpandido;
import riso.builder.conceptNet5.URI.out.FiltroDeResultados;
import validacao.reuters.LeitorArquivo;

public class SelecionaConceitos {

	public static void main(String args[]) throws IOException, UbyInvalidArgumentException{
		//o o nome nao existe para o ingles, o pertence a uma versao da wikipedia para o qual esta desatualizado ou nao existem
		//em nenhuma das fontes externas analisadas

		DBConfig db = new DBConfig("localhost/uby_release_1_0","com.mysql.jdbc.Driver",
				"mysql","root","root", true, false);
		Uby uby = new Uby(db);	

		LeitorArquivo leitor = new LeitorArquivo("hunspell/en_US.txt");
		leitor.geraPalavrasParceadasBarra();
		List<String> conceitos = leitor.getPalavras();
		Collections.shuffle(conceitos);
		FileOutputStream file = new FileOutputStream(new File("ESA/ConceitosTesteTTTTTT.txt"));  
		int qtdConceitos = conceitos.size();		
		int identificados = 0;
		for (int i = 0; i < qtdConceitos; i++) {
			String con = conceitos.get(i);
			String conceito = con.replaceAll("-", " ");
			conceito = URLEncoder.encode(conceito,"UTF-8");
			ConceitoExpandido conceitoExp = new ConceitoExpandido(conceito);
			int valor = conceitoExp.eliminaInformacoesInuteis(FiltroDeResultados.SEARCH_TEXT,"&");	

			boolean iden = false;
			List<LexicalEntry> entries = uby.getLexicalEntries(conceito, EPartOfSpeech.noun, null);
			for (LexicalEntry lexicalEntry : entries) {
				List<Sense> sentidos = lexicalEntry.getSenses();
				for (Sense sense : sentidos) {
					String sentido = sense.getDefinitionText();
					if(sentido!=null){
						sentido = sentido.replaceAll("\n", "");
						if(!sentido.isEmpty()){
							iden = true;
							break;
						}
					}
				}
			}

			if(valor>0 && valor<= 200  && iden){
				identificados++;
				String fin = con+"\n";
				System.out.println(identificados+")"+fin);
				file.write(fin.getBytes());
			}

			if(identificados== 2400){
				file.close();
			}
			//TODO RISO completo!
			//ConceitoExpandido conceitoExp = new ConceitoExpandido(conceito);
			//conceitoExp.enriqueceConceito(FiltroDeResultados.SEARCH_TEXT,"&");
			//List<VetorTematico> vetores = vetorDAO.obtemVetoresTematicos(conceito);
		}
		file.close();

	}

}
