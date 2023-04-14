package main;

public class Token {
	tipoToken tipo;
	String lexema;

	public Token(){
		this.tipo = null;
		this.lexema = "";
	}
	
	public Token(tipoToken tip, String lex){
		this.tipo = tip;
		this.lexema = lex;
	}
}