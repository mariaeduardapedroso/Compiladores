grammar GyhLang;

@header{
	//local onde faço as imports
	import.java.util.ArrayList;
}

@members{
	private String _varNome;
	private int    _varTipo;
	private String _varValor;
	private Symbol _varSymbol;
	private TabelaSimbolos symbolTable = new TabelaSimbolos();
}

programa: Delim PCDec listaDeclaracoes Delim PCProg listaComandos;

//listaDeclaracoes: (declaracao)+;

listaDeclaracoes returns [int qtdeDec]: {$qtdeDec=0;} 
								(declaracao {$qtdeDec++;})+ 
								{System.out.println("\n Total declaracoes "+$qtdeDec);};

declaracao: Var Delim tipoVar {System.out.println(_input.LT(-3).getText());};

tipoVar: PCInt | PCReal;

listaComandos: (comando)+;

comando: comandoAtribuicao | comandoEntrada | comandoSaida;

comandoAtribuicao: Var Atrib expressaoAritmetica;

comandoEntrada: PCLer Var;

comandoSaida: PCImprimir  (Var|Cadeia);


expressaoAritmetica: (termoAritmetico OpArit)* termoAritmetico;

termoAritmetico: (fatorAritmetico OpMult)* fatorAritmetico;

fatorAritmetico: NUMInt| NUMReal | Var | AbrePar expressaoAritmetica FechaPar;






Atrib: ':=';
Delim: ':';

PCProg: 'PROG';
PCDec:  'DEC';
PCInt:  'INT';
PCReal: 'REAL';
PCLer:  'LER';
PCFim: 'FIM';

NUMReal: [0-9]*'.'[0-9]+;
NUMInt: [0-9]+;
OpArit: '-' | '+' ;
OpMult: '*' | '/';

OpRel: '>=' | '<=' | '>' | '<' | '==';  

AbrePar: '(';
FechaPar: ')';

PCSe: 'SE';
PCEntao: 'ENTAO';
PCImprimir: 'IMPRIMIR';
PCEnqto: 'ENQTO';
PCIni: 'INI';

Cadeia: '"' ([a-z] | [A-Z] | [0-9] |' '| '\t' | '\r' | '\n')* '"';

Var: [a-z] ( [a-z] | [A-Z] | [0-9] )* ;

WS: (' '| '\t' | '\r' | '\n') -> skip; 

Coment: '#' ([a-z] | [A-Z] | [0-9] | ' ' | '\t' | '\r' | '>' | 'ú' )* '\n' -> skip;
