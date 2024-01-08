grammar GyhLang;
@header{
   //fazer import
   import java.util.ArrayList;
}

@members{
	private String  _varNome;
	private String  _varTipo;
	private String  _varValor;
	private Simbolo _varSimbolo;
	private TabelaSimbolo _varTabela = new TabelaSimbolo();
	
	//===========
	private GyhProgram program = new GyhProgram();	
	private ArrayList<Comando> listCmd = new ArrayList<Comando>();
	private ArrayList<Comando> listCmdAux = new ArrayList<Comando>();	
	//===========
	
	//====Comando Atribuicao
	private String _varId;
	private String _varExp;	
	//=======================
	
	//====Comando Condicao
	private String _varCondicao;
	private ArrayList<Comando> _varListTrue = new ArrayList<Comando>();
	private ArrayList<Comando> _varListFalse = new ArrayList<Comando>();
	//===================

	public void addTabelaSimbolo(String nome, String tipo, String valor){
		_varSimbolo= new Simbolo(nome, tipo, valor);
		if(_varTabela.exists(nome)){
			System.out.println("\n Erro semantico, variavel jah declarada "+_varSimbolo.toString());
		}
		else{
			_varTabela.add(_varSimbolo);
		}
	}
	
	public void verificaVar(String nome){
		if(!_varTabela.exists(nome)){
			System.out.println("\n Erro semantico, variavel nao declarada"+nome);
		}
	} 
}
//==============Regras sintaticas

programa: Delim PCDec listaDeclaracoes Delim PCProg listaComandos
	      {
	        program.setVarTabela(_varTabela);
	        program.setComando(listCmd);
	      	program.generateTarget();} 
	      ;

listaDeclaracoes:(declaracao)+ ;


declaracao: Var Delim tipoVar
				{   addTabelaSimbolo(_input.LT(-3).getText(), _input.LT(-1).getText(), null); };

tipoVar: PCInt | PCReal;

listaComandos: (comando 
					{
					   listCmd.addAll(listCmdAux);
					   listCmdAux.removeAll(listCmdAux);
					 }
			   )+;

comando: comandoAtribuicao | comandoEntrada | comandoSaida |comandoCondicao | subAlgoritmo;

comandoCondicao:  PCSe {_varExp=""; _varCondicao="";}
				  expressaoRelacional PCEntao comando
				  {
				  	_varListTrue.addAll(listCmdAux);
				  	listCmdAux.removeAll(listCmdAux);
				  } 
				  (PCSenao comando
				  {
				  	_varListFalse.addAll(listCmdAux);
				  	listCmdAux.removeAll(listCmdAux);
				  }
				  )?
				  {ComandoCondicao cmd= new ComandoCondicao(_varCondicao, _varListTrue, _varListFalse);
				   listCmdAux.add(cmd); };

expressaoRelacional: (termoRelacional OPBool   {_varCondicao+=" "+_input.LT(-1).getText()+" ";}
					)* termoRelacional ;

termoRelacional: expressaoAritmetica    {_varCondicao+=_varExp; _varExp="";}
				 OpRel                  {_varCondicao+=_input.LT(-1).getText();}
				 expressaoAritmetica    {_varCondicao+=_varExp; _varExp="";} 
				 | 
				 AbrePar                {_varCondicao+=" ( ";}
				 expressaoRelacional 
				 FechaPar               {_varCondicao+=" ) ";};

subAlgoritmo: PCIni (comando)+ PCFim;



comandoAtribuicao:  Var 
					{
					  if(_varTabela.exists(_input.LT(-1).getText())==false){
						System.out.println("\n\nVariavel nao declarada "+_input.LT(-1).getText());
					  }
					  _varId=_input.LT(-1).getText();
					  _varExp="";
					}  
					Atrib expressaoAritmetica
					{
					   ComandoAtribuicao cmd=new ComandoAtribuicao(_varId, _varExp);
					   listCmdAux.add(cmd); 	
					};

comandoEntrada: PCLer Var
						 { verificaVar(_input.LT(-1).getText());
						    ComandoLeitura cmd = new ComandoLeitura();
						    cmd.setId(_input.LT(-1).getText());
						    listCmdAux.add(cmd);
						   };

comandoSaida: PCImprimir  (Var
						   { verificaVar(_input.LT(-1).getText());
						     ComandoEscrita cmd = new ComandoEscrita();
						     cmd.setId(_input.LT(-1).getText());
						     listCmdAux.add(cmd);
						   }
						    
							|Cadeia);


expressaoAritmetica: (termoAritmetico OpArit  
						{_varExp += _input.LT(-1).getText();}
                      )* termoAritmetico;

termoAritmetico: (fatorAritmetico OpMult
						{_varExp += _input.LT(-1).getText();}
				 )* fatorAritmetico;

fatorAritmetico: NUMInt   {_varExp += _input.LT(-1).getText();}
				| NUMReal {_varExp += _input.LT(-1).getText();}
				| Var     {_varExp += _input.LT(-1).getText();}
				| AbrePar {_varExp += " ( ";}
				   expressaoAritmetica 
				  FechaPar {_varExp += " ) ";};






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
PCSenao: 'SENAO';
OPBool: 'E' | 'OU';

Cadeia: '"' ([a-z] | [A-Z] | [0-9] |' '| '\t' | '\r' | '\n')* '"';

Var: [a-z] ( [a-z] | [A-Z] | [0-9] )* ;

WS: (' '| '\t' | '\r' | '\n') -> skip; 

Coment: '#' ([a-z] | [A-Z] | [0-9] | ' ' | '\t' | '\r' | '>' | 'ú' )* '\n' -> skip;


/*listaDeclaracoes returns [int qtdeDec]: {$qtdeDec=0;}
				 (declaracao
				  {$qtdeDec++;}		
				  )+
				  {System.out.println("\n\nA quantidade de declracoes foi: "+$qtdeDec);}
				  ;

*/