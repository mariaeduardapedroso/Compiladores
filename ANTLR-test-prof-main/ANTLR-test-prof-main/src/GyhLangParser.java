// Generated from .\GyhLang.g4 by ANTLR 4.7.2

    import java.util.ArrayList;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GyhLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, Coment=3, MAISMENOS=4, VEZESDIV=5, OP_REL=6, TipoVar=7, 
		PCDEC=8, PCPROG=9, PCINT=10, PCLER=11, PCREAL=12, PCIMPRIMIR=13, PCSE=14, 
		PCSENAO=15, PCENTAO=16, PCENQTO=17, PCINI=18, PCFIM=19, WS=20, VAR=21, 
		NUMINT=22, NUMREAL=23, OPARIT=24, OPBOOL=25, OPMAIORIGUAL=26, OPMAIOR=27, 
		OPMENORIGUAL=28, OPMENOR=29, OPIGUAL=30, OPDIF=31, CADEIA=32, ABANAN=33, 
		ATRIB=34, AbrePar=35, FechaPar=36, DELIM=37;
	public static final int
		RULE_programa = 0, RULE_listaDeclaracoes = 1, RULE_declaracao = 2, RULE_listaComandos = 3, 
		RULE_comando = 4, RULE_comandoAtribuicao = 5, RULE_comandoEntrada = 6, 
		RULE_comandoSaida = 7, RULE_comandoCondicao = 8, RULE_comandoRepeticao = 9, 
		RULE_expressaoAritmetica = 10, RULE_termoAritmetico = 11, RULE_fatorAritmetico = 12, 
		RULE_expressaoRelacional = 13, RULE_termoRelacional = 14, RULE_subAlgoritmo = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"programa", "listaDeclaracoes", "declaracao", "listaComandos", "comando", 
			"comandoAtribuicao", "comandoEntrada", "comandoSaida", "comandoCondicao", 
			"comandoRepeticao", "expressaoAritmetica", "termoAritmetico", "fatorAritmetico", 
			"expressaoRelacional", "termoRelacional", "subAlgoritmo"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'E'", "'OU'", null, null, null, null, null, "'DEC'", "'PROG'", 
			"'INT'", "'LER'", "'REAL'", "'IMPRIMIR'", "'SE'", "'SENAO'", "'ENTAO'", 
			"'ENQTO'", "'INI'", "'FIM'", null, null, null, null, null, null, "'>='", 
			"'>'", "'<='", "'<'", null, "'!='", null, "'\u00C2\u00A8'", "':='", "'('", 
			"')'", "':'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "Coment", "MAISMENOS", "VEZESDIV", "OP_REL", "TipoVar", 
			"PCDEC", "PCPROG", "PCINT", "PCLER", "PCREAL", "PCIMPRIMIR", "PCSE", 
			"PCSENAO", "PCENTAO", "PCENQTO", "PCINI", "PCFIM", "WS", "VAR", "NUMINT", 
			"NUMREAL", "OPARIT", "OPBOOL", "OPMAIORIGUAL", "OPMAIOR", "OPMENORIGUAL", 
			"OPMENOR", "OPIGUAL", "OPDIF", "CADEIA", "ABANAN", "ATRIB", "AbrePar", 
			"FechaPar", "DELIM"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "GyhLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	    private String _varNome;
	    private String _varTipo;
	    private String _varValor;
	    private Simbolo _varSimbolo;
	    private TabelaSimbolo _varTabela = new TabelaSimbolo();

	    //===
	    private GyhProgram program = new GyhProgram();
	    private ArrayList<Comando> listCmd = new ArrayList<>();
	    private ArrayList<Comando> auxList = new ArrayList<>();
	    
	    //atrib
	    private String _varID;
	    private String _varEXP;
	    
	    //cond
	    private String _varCOND;
	    private ArrayList<Comando> _varListTrue = new ArrayList<>();
	    private ArrayList<Comando> _varListFalse = new ArrayList<>();

	    //repet
	    private String _varREPET;
	    private ArrayList<Comando> _varListRepet = new ArrayList<>();
	    


	    
	    
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
	        if(!_varTabela.exists(nome)){
	            System.out.println("\n Erro Semantico: variavel nao declarada: " + nome);
	        }
	    }

	public GyhLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramaContext extends ParserRuleContext {
		public List<TerminalNode> DELIM() { return getTokens(GyhLangParser.DELIM); }
		public TerminalNode DELIM(int i) {
			return getToken(GyhLangParser.DELIM, i);
		}
		public TerminalNode PCDEC() { return getToken(GyhLangParser.PCDEC, 0); }
		public ListaDeclaracoesContext listaDeclaracoes() {
			return getRuleContext(ListaDeclaracoesContext.class,0);
		}
		public TerminalNode PCPROG() { return getToken(GyhLangParser.PCPROG, 0); }
		public ListaComandosContext listaComandos() {
			return getRuleContext(ListaComandosContext.class,0);
		}
		public ProgramaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programa; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).enterPrograma(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).exitPrograma(this);
		}
	}

	public final ProgramaContext programa() throws RecognitionException {
		ProgramaContext _localctx = new ProgramaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_programa);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			match(DELIM);
			setState(33);
			match(PCDEC);
			setState(34);
			listaDeclaracoes();
			setState(35);
			match(DELIM);
			setState(36);
			match(PCPROG);
			setState(37);
			listaComandos();

			        program.setVarTabela(_varTabela);
			        program.setComando(listCmd);
			        program.generateTarget();
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListaDeclaracoesContext extends ParserRuleContext {
		public List<DeclaracaoContext> declaracao() {
			return getRuleContexts(DeclaracaoContext.class);
		}
		public DeclaracaoContext declaracao(int i) {
			return getRuleContext(DeclaracaoContext.class,i);
		}
		public ListaDeclaracoesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listaDeclaracoes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).enterListaDeclaracoes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).exitListaDeclaracoes(this);
		}
	}

	public final ListaDeclaracoesContext listaDeclaracoes() throws RecognitionException {
		ListaDeclaracoesContext _localctx = new ListaDeclaracoesContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_listaDeclaracoes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(40);
				declaracao();
				}
				}
				setState(43); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==VAR );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclaracaoContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(GyhLangParser.VAR, 0); }
		public TerminalNode DELIM() { return getToken(GyhLangParser.DELIM, 0); }
		public TerminalNode TipoVar() { return getToken(GyhLangParser.TipoVar, 0); }
		public DeclaracaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).enterDeclaracao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).exitDeclaracao(this);
		}
	}

	public final DeclaracaoContext declaracao() throws RecognitionException {
		DeclaracaoContext _localctx = new DeclaracaoContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declaracao);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			match(VAR);
			setState(46);
			match(DELIM);
			setState(47);
			match(TipoVar);

			            if (_varTabela.exists(_input.LT(-3).getText())){
			                System.out.println("\n\nErro semantico, variavel ja declarada: " + _input.LT(-1).getText());
			            }
			            else{
			                addTabelaSimbolo(_input.LT(-3).getText(), _input.LT(-1).getText(), null);
			            }
			        
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListaComandosContext extends ParserRuleContext {
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public ListaComandosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listaComandos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).enterListaComandos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).exitListaComandos(this);
		}
	}

	public final ListaComandosContext listaComandos() throws RecognitionException {
		ListaComandosContext _localctx = new ListaComandosContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_listaComandos);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(50);
				comando();

				            listCmd.addAll(auxList);
				            auxList.removeAll(auxList);
				        
				}
				}
				setState(55); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PCLER) | (1L << PCIMPRIMIR) | (1L << PCSE) | (1L << PCENQTO) | (1L << PCINI) | (1L << VAR))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComandoContext extends ParserRuleContext {
		public ComandoAtribuicaoContext comandoAtribuicao() {
			return getRuleContext(ComandoAtribuicaoContext.class,0);
		}
		public ComandoEntradaContext comandoEntrada() {
			return getRuleContext(ComandoEntradaContext.class,0);
		}
		public ComandoSaidaContext comandoSaida() {
			return getRuleContext(ComandoSaidaContext.class,0);
		}
		public ComandoCondicaoContext comandoCondicao() {
			return getRuleContext(ComandoCondicaoContext.class,0);
		}
		public ComandoRepeticaoContext comandoRepeticao() {
			return getRuleContext(ComandoRepeticaoContext.class,0);
		}
		public SubAlgoritmoContext subAlgoritmo() {
			return getRuleContext(SubAlgoritmoContext.class,0);
		}
		public ComandoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comando; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).enterComando(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).exitComando(this);
		}
	}

	public final ComandoContext comando() throws RecognitionException {
		ComandoContext _localctx = new ComandoContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_comando);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VAR:
				{
				setState(57);
				comandoAtribuicao();
				}
				break;
			case PCLER:
				{
				setState(58);
				comandoEntrada();
				}
				break;
			case PCIMPRIMIR:
				{
				setState(59);
				comandoSaida();
				}
				break;
			case PCSE:
				{
				setState(60);
				comandoCondicao();
				}
				break;
			case PCENQTO:
				{
				setState(61);
				comandoRepeticao();
				}
				break;
			case PCINI:
				{
				setState(62);
				subAlgoritmo();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComandoAtribuicaoContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(GyhLangParser.VAR, 0); }
		public TerminalNode ATRIB() { return getToken(GyhLangParser.ATRIB, 0); }
		public ExpressaoAritmeticaContext expressaoAritmetica() {
			return getRuleContext(ExpressaoAritmeticaContext.class,0);
		}
		public ComandoAtribuicaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comandoAtribuicao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).enterComandoAtribuicao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).exitComandoAtribuicao(this);
		}
	}

	public final ComandoAtribuicaoContext comandoAtribuicao() throws RecognitionException {
		ComandoAtribuicaoContext _localctx = new ComandoAtribuicaoContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_comandoAtribuicao);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			match(VAR);

			            if(_varTabela.exists(_input.LT(-1).getText()) == false){
			                System.out.println("\n\nVariavel nao declarada: " + _input.LT(-1).getText());
			            }
			            _varID = _input.LT(-1).getText();
			            _varEXP = "";
			        
			setState(67);
			match(ATRIB);
			setState(68);
			expressaoAritmetica();

			            auxList.add(new ComandoAtribuicao(_varID, _varEXP));
			        
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComandoEntradaContext extends ParserRuleContext {
		public TerminalNode PCLER() { return getToken(GyhLangParser.PCLER, 0); }
		public TerminalNode VAR() { return getToken(GyhLangParser.VAR, 0); }
		public ComandoEntradaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comandoEntrada; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).enterComandoEntrada(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).exitComandoEntrada(this);
		}
	}

	public final ComandoEntradaContext comandoEntrada() throws RecognitionException {
		ComandoEntradaContext _localctx = new ComandoEntradaContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_comandoEntrada);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			match(PCLER);
			setState(72);
			match(VAR);

			            verificaVar(_input.LT(-1).getText());
			            ComandoLeitura cmd = new ComandoLeitura();
			            cmd.setId(_input.LT(-1).getText());
			            auxList.add(cmd);
			        
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComandoSaidaContext extends ParserRuleContext {
		public TerminalNode PCIMPRIMIR() { return getToken(GyhLangParser.PCIMPRIMIR, 0); }
		public TerminalNode VAR() { return getToken(GyhLangParser.VAR, 0); }
		public TerminalNode CADEIA() { return getToken(GyhLangParser.CADEIA, 0); }
		public ComandoSaidaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comandoSaida; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).enterComandoSaida(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).exitComandoSaida(this);
		}
	}

	public final ComandoSaidaContext comandoSaida() throws RecognitionException {
		ComandoSaidaContext _localctx = new ComandoSaidaContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_comandoSaida);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(PCIMPRIMIR);
			setState(79);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VAR:
				{
				setState(76);
				match(VAR);

				            verificaVar(_input.LT(-1).getText());
				            ComandoEscrita cmd = new ComandoEscrita();
				            cmd.setId(_input.LT(-1).getText());
				            auxList.add(cmd);
				        
				}
				break;
			case CADEIA:
				{
				setState(78);
				match(CADEIA);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComandoCondicaoContext extends ParserRuleContext {
		public TerminalNode PCSE() { return getToken(GyhLangParser.PCSE, 0); }
		public ExpressaoRelacionalContext expressaoRelacional() {
			return getRuleContext(ExpressaoRelacionalContext.class,0);
		}
		public TerminalNode PCENTAO() { return getToken(GyhLangParser.PCENTAO, 0); }
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public TerminalNode PCSENAO() { return getToken(GyhLangParser.PCSENAO, 0); }
		public ComandoCondicaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comandoCondicao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).enterComandoCondicao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).exitComandoCondicao(this);
		}
	}

	public final ComandoCondicaoContext comandoCondicao() throws RecognitionException {
		ComandoCondicaoContext _localctx = new ComandoCondicaoContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_comandoCondicao);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			match(PCSE);

			            _varEXP = "";
			            _varCOND = "";
			        
			setState(83);
			expressaoRelacional();
			setState(84);
			match(PCENTAO);
			setState(85);
			comando();

			            _varListTrue.addAll(auxList);
			            auxList.removeAll(auxList);
			        
			setState(91);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(87);
				match(PCSENAO);
				setState(88);
				comando();

				            _varListFalse.addAll(auxList);
				            auxList.removeAll(auxList);
				        
				}
				break;
			}

			            auxList.add(new ComandoCondicao(_varCOND, _varListTrue, _varListFalse));
			        
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComandoRepeticaoContext extends ParserRuleContext {
		public TerminalNode PCENQTO() { return getToken(GyhLangParser.PCENQTO, 0); }
		public ExpressaoRelacionalContext expressaoRelacional() {
			return getRuleContext(ExpressaoRelacionalContext.class,0);
		}
		public ComandoContext comando() {
			return getRuleContext(ComandoContext.class,0);
		}
		public ComandoRepeticaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comandoRepeticao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).enterComandoRepeticao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).exitComandoRepeticao(this);
		}
	}

	public final ComandoRepeticaoContext comandoRepeticao() throws RecognitionException {
		ComandoRepeticaoContext _localctx = new ComandoRepeticaoContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_comandoRepeticao);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			match(PCENQTO);

			        _varEXP = "";
			        _varREPET = "";
			        _varCOND = "";
			    
			setState(97);
			expressaoRelacional();

			        _varREPET = _varCOND;
			    
			setState(99);
			comando();

			        _varListRepet.addAll(auxList);
			        auxList.removeAll(auxList);
			        auxList.add(new ComandoRepeticao(_varREPET, _varListRepet));
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressaoAritmeticaContext extends ParserRuleContext {
		public TermoAritmeticoContext termoAritmetico() {
			return getRuleContext(TermoAritmeticoContext.class,0);
		}
		public List<TerminalNode> MAISMENOS() { return getTokens(GyhLangParser.MAISMENOS); }
		public TerminalNode MAISMENOS(int i) {
			return getToken(GyhLangParser.MAISMENOS, i);
		}
		public List<ExpressaoAritmeticaContext> expressaoAritmetica() {
			return getRuleContexts(ExpressaoAritmeticaContext.class);
		}
		public ExpressaoAritmeticaContext expressaoAritmetica(int i) {
			return getRuleContext(ExpressaoAritmeticaContext.class,i);
		}
		public ExpressaoAritmeticaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressaoAritmetica; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).enterExpressaoAritmetica(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).exitExpressaoAritmetica(this);
		}
	}

	public final ExpressaoAritmeticaContext expressaoAritmetica() throws RecognitionException {
		ExpressaoAritmeticaContext _localctx = new ExpressaoAritmeticaContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_expressaoAritmetica);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			termoAritmetico();
			setState(108);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(103);
					match(MAISMENOS);

					            _varEXP += _input.LT(-1).getText();
					        
					setState(105);
					expressaoAritmetica();
					}
					} 
				}
				setState(110);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermoAritmeticoContext extends ParserRuleContext {
		public List<FatorAritmeticoContext> fatorAritmetico() {
			return getRuleContexts(FatorAritmeticoContext.class);
		}
		public FatorAritmeticoContext fatorAritmetico(int i) {
			return getRuleContext(FatorAritmeticoContext.class,i);
		}
		public List<TerminalNode> VEZESDIV() { return getTokens(GyhLangParser.VEZESDIV); }
		public TerminalNode VEZESDIV(int i) {
			return getToken(GyhLangParser.VEZESDIV, i);
		}
		public TermoAritmeticoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termoAritmetico; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).enterTermoAritmetico(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).exitTermoAritmetico(this);
		}
	}

	public final TermoAritmeticoContext termoAritmetico() throws RecognitionException {
		TermoAritmeticoContext _localctx = new TermoAritmeticoContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_termoAritmetico);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			fatorAritmetico();
			setState(117);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VEZESDIV) {
				{
				{
				setState(112);
				match(VEZESDIV);

				            _varEXP += _input.LT(-1).getText();
				        
				setState(114);
				fatorAritmetico();
				}
				}
				setState(119);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FatorAritmeticoContext extends ParserRuleContext {
		public TerminalNode NUMINT() { return getToken(GyhLangParser.NUMINT, 0); }
		public TerminalNode NUMREAL() { return getToken(GyhLangParser.NUMREAL, 0); }
		public TerminalNode VAR() { return getToken(GyhLangParser.VAR, 0); }
		public TerminalNode AbrePar() { return getToken(GyhLangParser.AbrePar, 0); }
		public ExpressaoAritmeticaContext expressaoAritmetica() {
			return getRuleContext(ExpressaoAritmeticaContext.class,0);
		}
		public TerminalNode FechaPar() { return getToken(GyhLangParser.FechaPar, 0); }
		public FatorAritmeticoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fatorAritmetico; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).enterFatorAritmetico(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).exitFatorAritmetico(this);
		}
	}

	public final FatorAritmeticoContext fatorAritmetico() throws RecognitionException {
		FatorAritmeticoContext _localctx = new FatorAritmeticoContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_fatorAritmetico);
		try {
			setState(132);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMINT:
				enterOuterAlt(_localctx, 1);
				{
				setState(120);
				match(NUMINT);
				_varEXP += _input.LT(-1).getText();
				}
				break;
			case NUMREAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(122);
				match(NUMREAL);
				_varEXP += _input.LT(-1).getText();
				}
				break;
			case VAR:
				enterOuterAlt(_localctx, 3);
				{
				setState(124);
				match(VAR);
				_varEXP += _input.LT(-1).getText();
				}
				break;
			case AbrePar:
				enterOuterAlt(_localctx, 4);
				{
				setState(126);
				match(AbrePar);
				_varEXP += " ( ";
				setState(128);
				expressaoAritmetica();
				setState(129);
				match(FechaPar);
				_varEXP += " ( ";
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressaoRelacionalContext extends ParserRuleContext {
		public List<TermoRelacionalContext> termoRelacional() {
			return getRuleContexts(TermoRelacionalContext.class);
		}
		public TermoRelacionalContext termoRelacional(int i) {
			return getRuleContext(TermoRelacionalContext.class,i);
		}
		public ExpressaoRelacionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressaoRelacional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).enterExpressaoRelacional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).exitExpressaoRelacional(this);
		}
	}

	public final ExpressaoRelacionalContext expressaoRelacional() throws RecognitionException {
		ExpressaoRelacionalContext _localctx = new ExpressaoRelacionalContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_expressaoRelacional);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			termoRelacional();
			setState(144);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0 || _la==T__1) {
				{
				{
				setState(139);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__0:
					{
					setState(135);
					match(T__0);

					            _varCOND += "&&";
					        
					}
					break;
				case T__1:
					{
					setState(137);
					match(T__1);

					            _varCOND += "||";
					        
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(141);
				termoRelacional();
				}
				}
				setState(146);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermoRelacionalContext extends ParserRuleContext {
		public List<ExpressaoAritmeticaContext> expressaoAritmetica() {
			return getRuleContexts(ExpressaoAritmeticaContext.class);
		}
		public ExpressaoAritmeticaContext expressaoAritmetica(int i) {
			return getRuleContext(ExpressaoAritmeticaContext.class,i);
		}
		public TerminalNode OP_REL() { return getToken(GyhLangParser.OP_REL, 0); }
		public TerminalNode AbrePar() { return getToken(GyhLangParser.AbrePar, 0); }
		public ExpressaoRelacionalContext expressaoRelacional() {
			return getRuleContext(ExpressaoRelacionalContext.class,0);
		}
		public TerminalNode FechaPar() { return getToken(GyhLangParser.FechaPar, 0); }
		public TermoRelacionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termoRelacional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).enterTermoRelacional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).exitTermoRelacional(this);
		}
	}

	public final TermoRelacionalContext termoRelacional() throws RecognitionException {
		TermoRelacionalContext _localctx = new TermoRelacionalContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_termoRelacional);
		try {
			setState(160);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(147);
				expressaoAritmetica();

				        _varCOND += _varEXP;
				        _varEXP = "";
				    
				setState(149);
				match(OP_REL);

				        _varCOND += _input.LT(-1).getText();
				    
				setState(151);
				expressaoAritmetica();

				        _varCOND += _varEXP;
				        _varEXP = "";
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(154);
				match(AbrePar);

				        _varEXP += "(";
				    
				setState(156);
				expressaoRelacional();
				setState(157);
				match(FechaPar);

				        _varEXP += ")";
				    
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SubAlgoritmoContext extends ParserRuleContext {
		public TerminalNode PCINI() { return getToken(GyhLangParser.PCINI, 0); }
		public ListaComandosContext listaComandos() {
			return getRuleContext(ListaComandosContext.class,0);
		}
		public TerminalNode PCFIM() { return getToken(GyhLangParser.PCFIM, 0); }
		public SubAlgoritmoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subAlgoritmo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).enterSubAlgoritmo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GyhLangListener ) ((GyhLangListener)listener).exitSubAlgoritmo(this);
		}
	}

	public final SubAlgoritmoContext subAlgoritmo() throws RecognitionException {
		SubAlgoritmoContext _localctx = new SubAlgoritmoContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_subAlgoritmo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			match(PCINI);
			setState(163);
			listaComandos();
			setState(164);
			match(PCFIM);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\'\u00a9\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\3\6\3,\n\3\r\3\16\3-\3\4\3\4\3\4\3\4\3\4\3\5"+
		"\3\5\3\5\6\58\n\5\r\5\16\59\3\6\3\6\3\6\3\6\3\6\3\6\5\6B\n\6\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\5\tR\n\t\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n^\n\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\f\3\f\3\f\3\f\7\fm\n\f\f\f\16\fp\13\f\3\r\3\r\3\r\3\r\7\rv"+
		"\n\r\f\r\16\ry\13\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\5\16\u0087\n\16\3\17\3\17\3\17\3\17\3\17\5\17\u008e\n\17\3"+
		"\17\7\17\u0091\n\17\f\17\16\17\u0094\13\17\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00a3\n\20\3\21\3\21\3\21"+
		"\3\21\3\21\2\2\22\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \2\2\2\u00a9"+
		"\2\"\3\2\2\2\4+\3\2\2\2\6/\3\2\2\2\b\67\3\2\2\2\nA\3\2\2\2\fC\3\2\2\2"+
		"\16I\3\2\2\2\20M\3\2\2\2\22S\3\2\2\2\24a\3\2\2\2\26h\3\2\2\2\30q\3\2\2"+
		"\2\32\u0086\3\2\2\2\34\u0088\3\2\2\2\36\u00a2\3\2\2\2 \u00a4\3\2\2\2\""+
		"#\7\'\2\2#$\7\n\2\2$%\5\4\3\2%&\7\'\2\2&\'\7\13\2\2\'(\5\b\5\2()\b\2\1"+
		"\2)\3\3\2\2\2*,\5\6\4\2+*\3\2\2\2,-\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\5\3\2"+
		"\2\2/\60\7\27\2\2\60\61\7\'\2\2\61\62\7\t\2\2\62\63\b\4\1\2\63\7\3\2\2"+
		"\2\64\65\5\n\6\2\65\66\b\5\1\2\668\3\2\2\2\67\64\3\2\2\289\3\2\2\29\67"+
		"\3\2\2\29:\3\2\2\2:\t\3\2\2\2;B\5\f\7\2<B\5\16\b\2=B\5\20\t\2>B\5\22\n"+
		"\2?B\5\24\13\2@B\5 \21\2A;\3\2\2\2A<\3\2\2\2A=\3\2\2\2A>\3\2\2\2A?\3\2"+
		"\2\2A@\3\2\2\2B\13\3\2\2\2CD\7\27\2\2DE\b\7\1\2EF\7$\2\2FG\5\26\f\2GH"+
		"\b\7\1\2H\r\3\2\2\2IJ\7\r\2\2JK\7\27\2\2KL\b\b\1\2L\17\3\2\2\2MQ\7\17"+
		"\2\2NO\7\27\2\2OR\b\t\1\2PR\7\"\2\2QN\3\2\2\2QP\3\2\2\2R\21\3\2\2\2ST"+
		"\7\20\2\2TU\b\n\1\2UV\5\34\17\2VW\7\22\2\2WX\5\n\6\2X]\b\n\1\2YZ\7\21"+
		"\2\2Z[\5\n\6\2[\\\b\n\1\2\\^\3\2\2\2]Y\3\2\2\2]^\3\2\2\2^_\3\2\2\2_`\b"+
		"\n\1\2`\23\3\2\2\2ab\7\23\2\2bc\b\13\1\2cd\5\34\17\2de\b\13\1\2ef\5\n"+
		"\6\2fg\b\13\1\2g\25\3\2\2\2hn\5\30\r\2ij\7\6\2\2jk\b\f\1\2km\5\26\f\2"+
		"li\3\2\2\2mp\3\2\2\2nl\3\2\2\2no\3\2\2\2o\27\3\2\2\2pn\3\2\2\2qw\5\32"+
		"\16\2rs\7\7\2\2st\b\r\1\2tv\5\32\16\2ur\3\2\2\2vy\3\2\2\2wu\3\2\2\2wx"+
		"\3\2\2\2x\31\3\2\2\2yw\3\2\2\2z{\7\30\2\2{\u0087\b\16\1\2|}\7\31\2\2}"+
		"\u0087\b\16\1\2~\177\7\27\2\2\177\u0087\b\16\1\2\u0080\u0081\7%\2\2\u0081"+
		"\u0082\b\16\1\2\u0082\u0083\5\26\f\2\u0083\u0084\7&\2\2\u0084\u0085\b"+
		"\16\1\2\u0085\u0087\3\2\2\2\u0086z\3\2\2\2\u0086|\3\2\2\2\u0086~\3\2\2"+
		"\2\u0086\u0080\3\2\2\2\u0087\33\3\2\2\2\u0088\u0092\5\36\20\2\u0089\u008a"+
		"\7\3\2\2\u008a\u008e\b\17\1\2\u008b\u008c\7\4\2\2\u008c\u008e\b\17\1\2"+
		"\u008d\u0089\3\2\2\2\u008d\u008b\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0091"+
		"\5\36\20\2\u0090\u008d\3\2\2\2\u0091\u0094\3\2\2\2\u0092\u0090\3\2\2\2"+
		"\u0092\u0093\3\2\2\2\u0093\35\3\2\2\2\u0094\u0092\3\2\2\2\u0095\u0096"+
		"\5\26\f\2\u0096\u0097\b\20\1\2\u0097\u0098\7\b\2\2\u0098\u0099\b\20\1"+
		"\2\u0099\u009a\5\26\f\2\u009a\u009b\b\20\1\2\u009b\u00a3\3\2\2\2\u009c"+
		"\u009d\7%\2\2\u009d\u009e\b\20\1\2\u009e\u009f\5\34\17\2\u009f\u00a0\7"+
		"&\2\2\u00a0\u00a1\b\20\1\2\u00a1\u00a3\3\2\2\2\u00a2\u0095\3\2\2\2\u00a2"+
		"\u009c\3\2\2\2\u00a3\37\3\2\2\2\u00a4\u00a5\7\24\2\2\u00a5\u00a6\5\b\5"+
		"\2\u00a6\u00a7\7\25\2\2\u00a7!\3\2\2\2\r-9AQ]nw\u0086\u008d\u0092\u00a2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}