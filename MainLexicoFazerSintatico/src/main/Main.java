package main;

import java.util.ArrayList;
import java.util.List;

//	INTEGRANTES:
// Vitor Luis de Queiroz Batista - 2104679
// Maria Eduarda Pedroso - 2150336

public class Main {
    public static void main(String[] args) {
		// List<Token> listaTokens = new ArrayList<>();
		String programa = "programa7.gyh";
		
		//TODO:
		// fazer superclasse o qual dá inheritance para lexico, semantico e sintatico:
		//   1-botar extends em cada um dos 3 (2 atualmente)
		//   2-colocar as variaveis em comum em cada um dos 3 na superclasse (2 atualmente)
		//   3-utilizar apenas a superclasse para criar os objetos de lexico e etc
		//      exemplo: superclasse = new lexico
		//      depois:  superclasse = new semantico
		//      e entao: superclasse = new sintatico
		
        Lexico objLexico = new Lexico(programa);
        objLexico.verificadorLexico();
		
		objLexico.printaListaTokens();
		
		Semantico semantico = new Semantico(programa, objLexico.listaTokens);
		semantico.verificadorSemantico();
    }
}
