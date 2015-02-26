package validacao.pos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import validacao.reuters.LeitorArquivo;

public class CriarArquivosPOS {

	public static void main(String args[]) throws IOException{

		LeitorArquivo leitor = new LeitorArquivo("pos/part-of-speech.txt");
		for (int j = 0; j < 10; j++) {
			FileOutputStream file = new FileOutputStream(new File("pos/PartOfSpeech"+(j+1)+".txt"));
			leitor.geraPalavrasShiftadasdas();
			List<String> conceitos = leitor.getPalavras();
			Collections.shuffle(conceitos);
			conceitos = conceitos.subList(0, 97);
			for (String con : conceitos) {
				con +="\n";
				file.write(con.getBytes());
			}
			file.close();
		}
	}
}
