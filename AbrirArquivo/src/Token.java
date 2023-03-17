package projeto_1;

public class Token {
    public TipoToken padrao;
    public String lexema;

    public Token(TipoToken p, String lex) {
        this.padrao = p;
        this.lexema = lex;
    }

    @Override
    public String toString() {
        return "<" + padrao + ", " + lexema + ">";

    }
}
