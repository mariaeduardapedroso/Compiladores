grammar GyhLang;
@header{
    import java.util.ArrayList;
}

@members{
    private String _varNome;
    private String _varTipo;
    private String _varValor;
    private Simbolo _varSimbolo;
    private TabelaSimbolo _varTabela = new TabelaSimbolo();

    //===
    private GyhProgram program = new GyhProgram();

    private ArrayList<Comando> listCmd = new ArrayList<>();
    private ArrayList<Comando> auxList = new ArrayList<>();

    //cond
    private String _varCOND;
    private ArrayList<Comando> _varListTrue = new ArrayList<>();
    private ArrayList<Comando> _varListFalse = new ArrayList<>();

    //repet
    private String _varREPET;
    private ArrayList<Comando> _varListRepet = new ArrayList<>();
    
    //atrib
    private String _varID;
    private String _varEXP;

    
    
    //===


    public void addTabelaSimbolo(String nome, String tipo, String valor){
        _varSimbolo = new Simbolo(nome, tipo, valor);
        if (_varTabela.exists(nome)){
            System.out.println("\n Erro Semantico: variavel ja declarada: " + _varSimbolo.toString());
        }
        else{
            _varTabela.add(_varSimbolo);
        }
    }

    public void verificaVar(String nome){
        if(_varTabela.exists(nome) == false){
            System.out.println("\n Erro Semantico: variavel nao declarada: " + nome);
        }
    }
}


//============================================================================
//================================== REGRAS ==================================
//============================================================================

prog: DELIM PCDEC ListaDeclaracoes DELIM PCPROG ListaComandos
    {
        program.setVarTabela(_varTabela);
        program.setComandos(listCmd);
        program.generateTarget();
    } ;

ListaDeclaracoes: (Declaracao)+;

Declaracao: VAR DELIM TipoVar
        {
            if (_varTabela.exists(_input.LT(-3).getText(), _input.LT(-1).getText())){
                System.out.println("\n\nErro semantico, variavel ja declarada: " + _input.LT(-1).getText());
            }
            else{
                addTabelaSimbolo(_input.LT(-3).getText(), _input.LT(-1).getText(), null);
            }
        } ;

ListaComandos: (Comando
        {
            listCmd.addAll(auxList);
            auxList.removeAll(auxList);
        }
            )+;


Comando: (ComandoAtribuicao | ComandoEntrada | ComandoSaida | ComandoCondicao | ComandoRepeticao | SubAlgoritmo);

ComandoAtribuicao: VAR
        {
            if(_varTabela.exists(_input.LT(-1).getText()) == false){
                System.out.println("\n\nVariavel nao declarada: " + _input.LT(-1).getText());
            }
            _varID = _input.LT(-1).getText();
            _varEXP = "";
        }
    ATRIB ExpressaoAritmetica
        {
            auxList.add(new ComandoAtribuicao(_varID, _varEXP));
        } ;
ComandoEntrada: PCLER VAR
        {
            verificaVar(_input.LT(-1).getText());
            ComandoLeitura cmd = new ComandoLeitura();
            cmd.setId(_input.LT(-1).getText());
            auxList.add(cmd);
        } ;
ComandoSaida: PCIMPRIMIR (VAR 
        {
            verificaVar(_input.LT(-1).getText());
            ComandoEscrita cmd = new ComandoEscrita();
            cmd.setId(_input.LT(-1).getText());
            auxList.add(cmd);
        }
                         | CADEIA) ;
ComandoCondicao: PCSE
        {
            _varEXP = "";
            _varCOND = "";
        }
                        ExpressaoRelacional PCENTAO Comando
        {
            _varListTrue.addAll(auxList);
            auxList.removeAll(auxList);
        }
                        (PCSENAO Comando
        {
            _varListFalse.addAll(auxList);
            auxList.removeAll(auxList);
        }
                        )?
        {
            auxList.add(new ComandoCondicao(_varCOND, _varListTrue, _varListFalse));
        } ;
ComandoRepeticao: PCENQTO 
    {
        _varEXP = "";
        _varREPET = "";
        _varCOND = "";
    }
                  ExpressaoRelacional
    {
        _varREPET = _varCOND;
    }
                  Comando
    {
        _varListRepet.addAll(auxList);
        auxList.removeAll(auxList);
        auxList.add(new ComandoRepeticao(_varREPET, _varListRepet));
    };



MAISMENOS: '+' | '-';
VEZESDIV:  '*' | '/';
ExpressaoAritmetica: TermoAritmetico ( MAISMENOS
        {
            _varEXP += _input.LT(-1).getText();
        }
                                       ExpressaoAritmetica)*;
TermoAritmetico : FatorAritmetico ( VEZESDIV
        {
            _varEXP += _input.LT(-1).getText();
        }
                                    FatorAritmetico)*;
FatorAritmetico: NUMINT {_varEXP += _input.LT(-1).getText();}
               | NUMREAL {_varEXP += _input.LT(-1).getText();}
               | VAR {_varEXP += _input.LT(-1).getText();}
               | '(' {_varEXP += " ( ";}
                 ExpressaoAritmetica 
                 ')' {_varEXP += " ( ";} ;
ExpressaoRelacional : TermoRelacional ( ('E'
        {
            _varCOND += "&&";
        } 
                                      | 'OU'
        {
            _varCOND += "||";
        }
                                      ) TermoRelacional)*;
TermoRelacional : ExpressaoAritmetica
    {
        _varCOND += _varEXP;
        _varEXP = "";
    }
                  OP_REL
    {
        _varCOND += _input.LT(-1).getText();
    }
                  ExpressaoAritmetica
    {
        _varCOND += _varEXP;
        _varEXP = "";
    }
                  | '('
    {
        _varEXP += "(";
    }
                  ExpressaoRelacional ')'
    {
        _varEXP += ")";
    } ;
SubAlgoritmo: PCINI ListaComandos PCFIM;

OP_REL: OPMENORIGUAL | OPIGUAL | OPMAIOR | OPMAIORIGUAL | OPDIF | OPMENOR;





TipoVar: PCINT | PCREAL;


PCDEC: 'DEC';
PCPROG : 'PROG';
PCINT : 'INT';
PCLER : 'LER';
PCREAL : 'REAL';
PCIMPRIMIR : 'IMPRIMIR';
PCSE : 'SE';
PCSENAO : 'SENAO';
PCENTAO : 'ENTAO';
PCENQTO : 'ENQTO';
PCINI : 'INI';
PCFIM : 'FIM';

WS: (' ' | '\t' | '\r' | '\n' ) -> skip;


VAR: [a-z]([a-z]|[0-9]|[A-Z])*;

NUMINT: [0-9]+;
NUMREAL: [0-9]* ('.') [0-9]+;

OPARIT: '*' | '/' | '+' | '-';
OPBOOL: 'E' | 'OU';
OPMAIORIGUAL: '>=';
OPMAIOR: '>';
OPMENORIGUAL: '<=';
OPMENOR: '<';
OPIGUAL: ('=')('=');
OPDIF: '!=';

CADEIA: '"' ([A-Z] | [a-z] | [0-9] | ' ' | '\t' | '<' | '\r' | '>')* '"';

ATRIB : ':=';

AbrePar: '(';
FechaPar: ')';

ABANAN: '¨';

DELIM: ':';

COMMENT: '#' ([A-Z] | [a-z] | [0-9] | ' ' | '\t' | '<' | '\r' | '>'
    | 'á' | 'Á' | 'é' | 'É' | 'í' | 'Í' | 'ó' | 'Ó' | 'ú' | 'Ú'
    | 'ã' | 'Ã' | 'õ' | 'Õ' | 'â' | 'Â' | 'ê' | 'Ê' | 'ô' | 'Ô')* '\n' -> skip;

