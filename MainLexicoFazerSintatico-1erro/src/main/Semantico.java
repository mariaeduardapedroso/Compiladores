package main;

import java.util.ArrayList;
import java.util.List;

public class Semantico {
    LeitorArquivo dataRead;
	List<Token> listaTokens;
	
	public Semantico(String arquivoUtilizado, List inputListaTokens){
		try {
            this.dataRead = new LeitorArquivo(arquivoUtilizado);
		} catch (Exception e) {
            System.out.print("File not found;\n");
            throw e;
        }
		
		this.listaTokens = inputListaTokens;
	}
	
	public void verificadorSemantico(){
		
	}
}
