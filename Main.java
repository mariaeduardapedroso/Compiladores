//Link arquivos sobre compilador (gramatica corrigida e diagrama UML) : https://drive.google.com/drive/folders/1UlxgAERSOlSmuvbqvVjgodj3hBQiT0Xb?usp=sharing

// Link do video analisador lexico: https://drive.google.com/file/d/1px_7L1-UZno5zvwUp8_0rnL-LFYQZBhG/view?usp=sharing

// Link do video analisador sintatico: https://youtu.be/DN2W-Jk7WOk

//Link do video analisador semantico: https://youtu.be/-v2m7XMPr-U

/**  Compilador desenvolvido em linguagem Java
 *   o Analisador foi desenvolvido para interpretar a liguagem gyh 
 */

/**
 * @author Felipe Galvão Gregorio e João Pedro Moreto Lourenção
 * 	
 * */

public class Main {
  public static void main(String[] args) {

    GyhLex lex = new GyhLex(); //objeto do analisador lexico
    lex.analisarLexico("programa1.gyh");

    GyhSint sint = new GyhSint();//objeto do analisador sintatico
    sint.Sintatico(lex.getLista());

    GyhSem sem = new GyhSem();
    sem.Semantico(sint.getVarUsada(), sint.getVarDec(), sint.getTipoDec(), sint.getTipoCheck());
  }
}