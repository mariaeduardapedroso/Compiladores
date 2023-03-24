package projeto;

public class Token {
    public TipoToken padrao;
    public String lexema;

    public Token(TipoToken p, String lex) {
        this.padrao = p;
        this.lexema = lex;
    }
	
    public Token(){
        this.padrao = null;
        this.lexema = null;
    }


    @Override
    public String toString() {
        return "<" + padrao + ", " + lexema + ">";

    }
}
