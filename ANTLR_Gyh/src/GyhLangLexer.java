// Generated from GyhLang.g4 by ANTLR 4.7.2

   //fazer import
   import java.util.ArrayList;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GyhLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		Atrib=1, Delim=2, PCProg=3, PCDec=4, PCInt=5, PCReal=6, PCLer=7, PCFim=8, 
		NUMReal=9, NUMInt=10, OpArit=11, OpMult=12, OpRel=13, AbrePar=14, FechaPar=15, 
		PCSe=16, PCEntao=17, PCImprimir=18, PCEnqto=19, PCIni=20, PCSenao=21, 
		OPBool=22, Cadeia=23, Var=24, WS=25, Coment=26;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"Atrib", "Delim", "PCProg", "PCDec", "PCInt", "PCReal", "PCLer", "PCFim", 
			"NUMReal", "NUMInt", "OpArit", "OpMult", "OpRel", "AbrePar", "FechaPar", 
			"PCSe", "PCEntao", "PCImprimir", "PCEnqto", "PCIni", "PCSenao", "OPBool", 
			"Cadeia", "Var", "WS", "Coment"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "':='", "':'", "'PROG'", "'DEC'", "'INT'", "'REAL'", "'LER'", "'FIM'", 
			null, null, null, null, null, "'('", "')'", "'SE'", "'ENTAO'", "'IMPRIMIR'", 
			"'ENQTO'", "'INI'", "'SENAO'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "Atrib", "Delim", "PCProg", "PCDec", "PCInt", "PCReal", "PCLer", 
			"PCFim", "NUMReal", "NUMInt", "OpArit", "OpMult", "OpRel", "AbrePar", 
			"FechaPar", "PCSe", "PCEntao", "PCImprimir", "PCEnqto", "PCIni", "PCSenao", 
			"OPBool", "Cadeia", "Var", "WS", "Coment"
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


	public GyhLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "GyhLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\34\u00be\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5"+
		"\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3"+
		"\t\3\t\3\t\3\n\7\nX\n\n\f\n\16\n[\13\n\3\n\3\n\6\n_\n\n\r\n\16\n`\3\13"+
		"\6\13d\n\13\r\13\16\13e\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\5\16s\n\16\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\27"+
		"\3\27\3\27\5\27\u009e\n\27\3\30\3\30\7\30\u00a2\n\30\f\30\16\30\u00a5"+
		"\13\30\3\30\3\30\3\31\3\31\7\31\u00ab\n\31\f\31\16\31\u00ae\13\31\3\32"+
		"\3\32\3\32\3\32\3\33\3\33\7\33\u00b6\n\33\f\33\16\33\u00b9\13\33\3\33"+
		"\3\33\3\33\3\33\2\2\34\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"+
		"\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33"+
		"\65\34\3\2\13\3\2\62;\4\2--//\4\2,,\61\61\4\2>>@@\b\2\13\f\17\17\"\"\62"+
		";C\\c|\3\2c|\5\2\62;C\\c|\5\2\13\f\17\17\"\"\n\2\13\13\17\17\"\"\62;@"+
		"@C\\c|\u00fc\u00fc\2\u00c7\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2"+
		"\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2"+
		"\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3"+
		"\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2"+
		"\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\3\67"+
		"\3\2\2\2\5:\3\2\2\2\7<\3\2\2\2\tA\3\2\2\2\13E\3\2\2\2\rI\3\2\2\2\17N\3"+
		"\2\2\2\21R\3\2\2\2\23Y\3\2\2\2\25c\3\2\2\2\27g\3\2\2\2\31i\3\2\2\2\33"+
		"r\3\2\2\2\35t\3\2\2\2\37v\3\2\2\2!x\3\2\2\2#{\3\2\2\2%\u0081\3\2\2\2\'"+
		"\u008a\3\2\2\2)\u0090\3\2\2\2+\u0094\3\2\2\2-\u009d\3\2\2\2/\u009f\3\2"+
		"\2\2\61\u00a8\3\2\2\2\63\u00af\3\2\2\2\65\u00b3\3\2\2\2\678\7<\2\289\7"+
		"?\2\29\4\3\2\2\2:;\7<\2\2;\6\3\2\2\2<=\7R\2\2=>\7T\2\2>?\7Q\2\2?@\7I\2"+
		"\2@\b\3\2\2\2AB\7F\2\2BC\7G\2\2CD\7E\2\2D\n\3\2\2\2EF\7K\2\2FG\7P\2\2"+
		"GH\7V\2\2H\f\3\2\2\2IJ\7T\2\2JK\7G\2\2KL\7C\2\2LM\7N\2\2M\16\3\2\2\2N"+
		"O\7N\2\2OP\7G\2\2PQ\7T\2\2Q\20\3\2\2\2RS\7H\2\2ST\7K\2\2TU\7O\2\2U\22"+
		"\3\2\2\2VX\t\2\2\2WV\3\2\2\2X[\3\2\2\2YW\3\2\2\2YZ\3\2\2\2Z\\\3\2\2\2"+
		"[Y\3\2\2\2\\^\7\60\2\2]_\t\2\2\2^]\3\2\2\2_`\3\2\2\2`^\3\2\2\2`a\3\2\2"+
		"\2a\24\3\2\2\2bd\t\2\2\2cb\3\2\2\2de\3\2\2\2ec\3\2\2\2ef\3\2\2\2f\26\3"+
		"\2\2\2gh\t\3\2\2h\30\3\2\2\2ij\t\4\2\2j\32\3\2\2\2kl\7@\2\2ls\7?\2\2m"+
		"n\7>\2\2ns\7?\2\2os\t\5\2\2pq\7?\2\2qs\7?\2\2rk\3\2\2\2rm\3\2\2\2ro\3"+
		"\2\2\2rp\3\2\2\2s\34\3\2\2\2tu\7*\2\2u\36\3\2\2\2vw\7+\2\2w \3\2\2\2x"+
		"y\7U\2\2yz\7G\2\2z\"\3\2\2\2{|\7G\2\2|}\7P\2\2}~\7V\2\2~\177\7C\2\2\177"+
		"\u0080\7Q\2\2\u0080$\3\2\2\2\u0081\u0082\7K\2\2\u0082\u0083\7O\2\2\u0083"+
		"\u0084\7R\2\2\u0084\u0085\7T\2\2\u0085\u0086\7K\2\2\u0086\u0087\7O\2\2"+
		"\u0087\u0088\7K\2\2\u0088\u0089\7T\2\2\u0089&\3\2\2\2\u008a\u008b\7G\2"+
		"\2\u008b\u008c\7P\2\2\u008c\u008d\7S\2\2\u008d\u008e\7V\2\2\u008e\u008f"+
		"\7Q\2\2\u008f(\3\2\2\2\u0090\u0091\7K\2\2\u0091\u0092\7P\2\2\u0092\u0093"+
		"\7K\2\2\u0093*\3\2\2\2\u0094\u0095\7U\2\2\u0095\u0096\7G\2\2\u0096\u0097"+
		"\7P\2\2\u0097\u0098\7C\2\2\u0098\u0099\7Q\2\2\u0099,\3\2\2\2\u009a\u009e"+
		"\7G\2\2\u009b\u009c\7Q\2\2\u009c\u009e\7W\2\2\u009d\u009a\3\2\2\2\u009d"+
		"\u009b\3\2\2\2\u009e.\3\2\2\2\u009f\u00a3\7$\2\2\u00a0\u00a2\t\6\2\2\u00a1"+
		"\u00a0\3\2\2\2\u00a2\u00a5\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3\u00a4\3\2"+
		"\2\2\u00a4\u00a6\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a6\u00a7\7$\2\2\u00a7"+
		"\60\3\2\2\2\u00a8\u00ac\t\7\2\2\u00a9\u00ab\t\b\2\2\u00aa\u00a9\3\2\2"+
		"\2\u00ab\u00ae\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\62"+
		"\3\2\2\2\u00ae\u00ac\3\2\2\2\u00af\u00b0\t\t\2\2\u00b0\u00b1\3\2\2\2\u00b1"+
		"\u00b2\b\32\2\2\u00b2\64\3\2\2\2\u00b3\u00b7\7%\2\2\u00b4\u00b6\t\n\2"+
		"\2\u00b5\u00b4\3\2\2\2\u00b6\u00b9\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b7\u00b8"+
		"\3\2\2\2\u00b8\u00ba\3\2\2\2\u00b9\u00b7\3\2\2\2\u00ba\u00bb\7\f\2\2\u00bb"+
		"\u00bc\3\2\2\2\u00bc\u00bd\b\33\2\2\u00bd\66\3\2\2\2\16\2Y`er\u009d\u00a1"+
		"\u00a3\u00aa\u00ac\u00b5\u00b7\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}