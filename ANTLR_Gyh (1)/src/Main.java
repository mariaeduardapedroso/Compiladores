import java.io.IOException;
import java.util.HashMap;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class Main {

	public static void main(String[] args) throws IOException {
		
		//Analise Sintatica
	    CharStream cs = CharStreams.fromFileName("programa1.gyh");
		GyhLangLexer lexer = new GyhLangLexer(cs);
		CommonTokenStream token = new CommonTokenStream(lexer);
		GyhLangParser parser = new GyhLangParser(token);
		
		parser.programa();
		
		
		/*
		// Analise Léxica
		CharStream cs = CharStreams.fromFileName("programa1.gyh");
		GyhLangLexer lexer = new GyhLangLexer(cs);
		Token t;
		
		while((t=lexer.nextToken()).getType() !=Token.EOF) {
			System.out.println("< "+lexer.VOCABULARY.getSymbolicName(t.getType())+" , "+t.getText()+" >");
		}		
	*/	
	}

}

/*
 * 
 * HashMap<Integer, String> carro = new HashMap<Integer, String>();
		
		carro.put(123, "Santana");	
		carro.put(124, "Gurgel");
		carro.put(125, "Fusca");
		carro.put(126, "Kadet");
		carro.put(127, "Camaro");
		
		for(int i= 123; i<128; i++) {
			System.out.println(carro.get(i));
		}
		System.out.println("-------------------");
		carro.remove(126);
		for(String c: carro.values()) {
			System.out.println(c);
		}
		
 */
