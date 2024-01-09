/* Classe criada para ler os arquivos de teste linguagem gyh 
obs.: Como esta parte não é o intuito principal do programa foi feito baseado no código da dupla Cristian e Adalberto
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LeitorArquivo {
	
	public ArrayList<String> ler(String palavra){
		ArrayList<String> lido = new ArrayList<String>();
        //Lista que vai conter todas as linhas do programa
		
		try {
			Scanner leitor = new Scanner(new File(palavra)); //realiza a abertura do arquivo
			
			while(leitor.hasNextLine()) { //verifica se existe próxima linha
				lido.add(leitor.nextLine()); //insere toda a linha como string em uma lista
			}
		} catch(FileNotFoundException e){
			e.printStackTrace();
		}
		return lido;
	}
}