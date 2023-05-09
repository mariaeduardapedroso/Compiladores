package compiladoreslexiconovo;

public class Token {
	tipoEnum tipo;
	String lexema;
	
	enum tipoEnum{
		OpAritMult, OpAritDiv, OpAritSoma, OpAritSub, AbrePar, FechaPar, OpRelMenor,
		OpRelMenorigual, Cadeia, OpRelIgual, OpRelMaior, OpRelMaiorigual, OpRelDif,
		OPBoolOu, PCDec, PCProg, PCIni, PCint, PCImprimir, PCLer, PCSe, OPBoolE,
		PCEnqto, PCEntao, Atrib, Delim, PCFim, Var, NumInt, NumReal, PCReal
	}
	
	public Token(){
		this.tipo = null;
		this.lexema = "";
	}
	
	public Token(tipoEnum tip, String lex){
		this.tipo = tip;
		this.lexema = lex;
	}
}
