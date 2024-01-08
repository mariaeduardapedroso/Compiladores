// Generated from .\GyhLang.g4 by ANTLR 4.7.2

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
		T__0=1, T__1=2, Coment=3, MAISMENOS=4, VEZESDIV=5, OP_REL=6, TipoVar=7, 
		PCDEC=8, PCPROG=9, PCINT=10, PCLER=11, PCREAL=12, PCIMPRIMIR=13, PCSE=14, 
		PCSENAO=15, PCENTAO=16, PCENQTO=17, PCINI=18, PCFIM=19, WS=20, VAR=21, 
		NUMINT=22, NUMREAL=23, OPARIT=24, OPBOOL=25, OPMAIORIGUAL=26, OPMAIOR=27, 
		OPMENORIGUAL=28, OPMENOR=29, OPIGUAL=30, OPDIF=31, CADEIA=32, ABANAN=33, 
		ATRIB=34, AbrePar=35, FechaPar=36, DELIM=37;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "Coment", "MAISMENOS", "VEZESDIV", "OP_REL", "TipoVar", 
			"PCDEC", "PCPROG", "PCINT", "PCLER", "PCREAL", "PCIMPRIMIR", "PCSE", 
			"PCSENAO", "PCENTAO", "PCENQTO", "PCINI", "PCFIM", "WS", "VAR", "NUMINT", 
			"NUMREAL", "OPARIT", "OPBOOL", "OPMAIORIGUAL", "OPMAIOR", "OPMENORIGUAL", 
			"OPMENOR", "OPIGUAL", "OPDIF", "CADEIA", "ABANAN", "ATRIB", "AbrePar", 
			"FechaPar", "DELIM"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\'\u0119\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\7\4}\n\4\f\4\16\4\u0080\13\4\3\4\3\4\3\4\3\4\3\5\3\5\3"+
		"\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u0090\n\7\3\b\3\b\5\b\u0094\n\b\3\t"+
		"\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3"+
		"\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3"+
		"\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3"+
		"\25\3\25\3\25\3\25\3\26\3\26\7\26\u00d8\n\26\f\26\16\26\u00db\13\26\3"+
		"\27\6\27\u00de\n\27\r\27\16\27\u00df\3\30\7\30\u00e3\n\30\f\30\16\30\u00e6"+
		"\13\30\3\30\3\30\6\30\u00ea\n\30\r\30\16\30\u00eb\3\31\3\31\3\32\3\32"+
		"\3\32\5\32\u00f3\n\32\3\33\3\33\3\33\3\34\3\34\3\35\3\35\3\35\3\36\3\36"+
		"\3\37\3\37\3\37\3 \3 \3 \3!\3!\7!\u0107\n!\f!\16!\u010a\13!\3!\3!\3\""+
		"\3\"\3\"\3#\3#\3#\3$\3$\3%\3%\3&\3&\2\2\'\3\3\5\4\7\5\t\6\13\7\r\b\17"+
		"\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+"+
		"\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'\3\2\n\n"+
		"\2\13\13\17\17\"\"\62;>>@@C\\c|\4\2--//\4\2,,\61\61\5\2\13\f\17\17\"\""+
		"\3\2c|\5\2\62;C\\c|\3\2\62;\5\2,-//\61\61\2\u0139\2\3\3\2\2\2\2\5\3\2"+
		"\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3"+
		"\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3"+
		"\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2"+
		"\2\3M\3\2\2\2\5O\3\2\2\2\7R\3\2\2\2\t\u0085\3\2\2\2\13\u0087\3\2\2\2\r"+
		"\u008f\3\2\2\2\17\u0093\3\2\2\2\21\u0095\3\2\2\2\23\u0099\3\2\2\2\25\u009e"+
		"\3\2\2\2\27\u00a2\3\2\2\2\31\u00a6\3\2\2\2\33\u00ab\3\2\2\2\35\u00b4\3"+
		"\2\2\2\37\u00b7\3\2\2\2!\u00bd\3\2\2\2#\u00c3\3\2\2\2%\u00c9\3\2\2\2\'"+
		"\u00cd\3\2\2\2)\u00d1\3\2\2\2+\u00d5\3\2\2\2-\u00dd\3\2\2\2/\u00e4\3\2"+
		"\2\2\61\u00ed\3\2\2\2\63\u00f2\3\2\2\2\65\u00f4\3\2\2\2\67\u00f7\3\2\2"+
		"\29\u00f9\3\2\2\2;\u00fc\3\2\2\2=\u00fe\3\2\2\2?\u0101\3\2\2\2A\u0104"+
		"\3\2\2\2C\u010d\3\2\2\2E\u0110\3\2\2\2G\u0113\3\2\2\2I\u0115\3\2\2\2K"+
		"\u0117\3\2\2\2MN\7G\2\2N\4\3\2\2\2OP\7Q\2\2PQ\7W\2\2Q\6\3\2\2\2R~\7%\2"+
		"\2S}\t\2\2\2TU\7\u00c5\2\2U}\7\u00a3\2\2VW\7\u00c5\2\2W}\7\uffff\2\2X"+
		"Y\7\u00c5\2\2Y}\7\u00ab\2\2Z[\7\u00c5\2\2[}\7\u2032\2\2\\]\7\u00c5\2\2"+
		"]}\7\u00af\2\2^_\7\u00c5\2\2_}\7\uffff\2\2`a\7\u00c5\2\2a}\7\u00b5\2\2"+
		"bc\7\u00c5\2\2c}\7\u201e\2\2de\7\u00c5\2\2e}\7\u00bc\2\2fg\7\u00c5\2\2"+
		"g}\7\u0163\2\2hi\7\u00c5\2\2i}\7\u00a5\2\2jk\7\u00c5\2\2k}\7\u0194\2\2"+
		"lm\7\u00c5\2\2m}\7\u00b7\2\2no\7\u00c5\2\2o}\7\u2024\2\2pq\7\u00c5\2\2"+
		"q}\7\u00a4\2\2rs\7\u00c5\2\2s}\7\u201c\2\2tu\7\u00c5\2\2u}\7\u00ac\2\2"+
		"vw\7\u00c5\2\2w}\7\u0162\2\2xy\7\u00c5\2\2y}\7\u00b6\2\2z{\7\u00c5\2\2"+
		"{}\7\u201f\2\2|S\3\2\2\2|T\3\2\2\2|V\3\2\2\2|X\3\2\2\2|Z\3\2\2\2|\\\3"+
		"\2\2\2|^\3\2\2\2|`\3\2\2\2|b\3\2\2\2|d\3\2\2\2|f\3\2\2\2|h\3\2\2\2|j\3"+
		"\2\2\2|l\3\2\2\2|n\3\2\2\2|p\3\2\2\2|r\3\2\2\2|t\3\2\2\2|v\3\2\2\2|x\3"+
		"\2\2\2|z\3\2\2\2}\u0080\3\2\2\2~|\3\2\2\2~\177\3\2\2\2\177\u0081\3\2\2"+
		"\2\u0080~\3\2\2\2\u0081\u0082\7\f\2\2\u0082\u0083\3\2\2\2\u0083\u0084"+
		"\b\4\2\2\u0084\b\3\2\2\2\u0085\u0086\t\3\2\2\u0086\n\3\2\2\2\u0087\u0088"+
		"\t\4\2\2\u0088\f\3\2\2\2\u0089\u0090\59\35\2\u008a\u0090\5=\37\2\u008b"+
		"\u0090\5\67\34\2\u008c\u0090\5\65\33\2\u008d\u0090\5? \2\u008e\u0090\5"+
		";\36\2\u008f\u0089\3\2\2\2\u008f\u008a\3\2\2\2\u008f\u008b\3\2\2\2\u008f"+
		"\u008c\3\2\2\2\u008f\u008d\3\2\2\2\u008f\u008e\3\2\2\2\u0090\16\3\2\2"+
		"\2\u0091\u0094\5\25\13\2\u0092\u0094\5\31\r\2\u0093\u0091\3\2\2\2\u0093"+
		"\u0092\3\2\2\2\u0094\20\3\2\2\2\u0095\u0096\7F\2\2\u0096\u0097\7G\2\2"+
		"\u0097\u0098\7E\2\2\u0098\22\3\2\2\2\u0099\u009a\7R\2\2\u009a\u009b\7"+
		"T\2\2\u009b\u009c\7Q\2\2\u009c\u009d\7I\2\2\u009d\24\3\2\2\2\u009e\u009f"+
		"\7K\2\2\u009f\u00a0\7P\2\2\u00a0\u00a1\7V\2\2\u00a1\26\3\2\2\2\u00a2\u00a3"+
		"\7N\2\2\u00a3\u00a4\7G\2\2\u00a4\u00a5\7T\2\2\u00a5\30\3\2\2\2\u00a6\u00a7"+
		"\7T\2\2\u00a7\u00a8\7G\2\2\u00a8\u00a9\7C\2\2\u00a9\u00aa\7N\2\2\u00aa"+
		"\32\3\2\2\2\u00ab\u00ac\7K\2\2\u00ac\u00ad\7O\2\2\u00ad\u00ae\7R\2\2\u00ae"+
		"\u00af\7T\2\2\u00af\u00b0\7K\2\2\u00b0\u00b1\7O\2\2\u00b1\u00b2\7K\2\2"+
		"\u00b2\u00b3\7T\2\2\u00b3\34\3\2\2\2\u00b4\u00b5\7U\2\2\u00b5\u00b6\7"+
		"G\2\2\u00b6\36\3\2\2\2\u00b7\u00b8\7U\2\2\u00b8\u00b9\7G\2\2\u00b9\u00ba"+
		"\7P\2\2\u00ba\u00bb\7C\2\2\u00bb\u00bc\7Q\2\2\u00bc \3\2\2\2\u00bd\u00be"+
		"\7G\2\2\u00be\u00bf\7P\2\2\u00bf\u00c0\7V\2\2\u00c0\u00c1\7C\2\2\u00c1"+
		"\u00c2\7Q\2\2\u00c2\"\3\2\2\2\u00c3\u00c4\7G\2\2\u00c4\u00c5\7P\2\2\u00c5"+
		"\u00c6\7S\2\2\u00c6\u00c7\7V\2\2\u00c7\u00c8\7Q\2\2\u00c8$\3\2\2\2\u00c9"+
		"\u00ca\7K\2\2\u00ca\u00cb\7P\2\2\u00cb\u00cc\7K\2\2\u00cc&\3\2\2\2\u00cd"+
		"\u00ce\7H\2\2\u00ce\u00cf\7K\2\2\u00cf\u00d0\7O\2\2\u00d0(\3\2\2\2\u00d1"+
		"\u00d2\t\5\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d4\b\25\2\2\u00d4*\3\2\2\2"+
		"\u00d5\u00d9\t\6\2\2\u00d6\u00d8\t\7\2\2\u00d7\u00d6\3\2\2\2\u00d8\u00db"+
		"\3\2\2\2\u00d9\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da,\3\2\2\2\u00db"+
		"\u00d9\3\2\2\2\u00dc\u00de\t\b\2\2\u00dd\u00dc\3\2\2\2\u00de\u00df\3\2"+
		"\2\2\u00df\u00dd\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0.\3\2\2\2\u00e1\u00e3"+
		"\t\b\2\2\u00e2\u00e1\3\2\2\2\u00e3\u00e6\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e4"+
		"\u00e5\3\2\2\2\u00e5\u00e7\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e7\u00e9\7\60"+
		"\2\2\u00e8\u00ea\t\b\2\2\u00e9\u00e8\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb"+
		"\u00e9\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec\60\3\2\2\2\u00ed\u00ee\t\t\2"+
		"\2\u00ee\62\3\2\2\2\u00ef\u00f3\7G\2\2\u00f0\u00f1\7Q\2\2\u00f1\u00f3"+
		"\7W\2\2\u00f2\u00ef\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f3\64\3\2\2\2\u00f4"+
		"\u00f5\7@\2\2\u00f5\u00f6\7?\2\2\u00f6\66\3\2\2\2\u00f7\u00f8\7@\2\2\u00f8"+
		"8\3\2\2\2\u00f9\u00fa\7>\2\2\u00fa\u00fb\7?\2\2\u00fb:\3\2\2\2\u00fc\u00fd"+
		"\7>\2\2\u00fd<\3\2\2\2\u00fe\u00ff\7?\2\2\u00ff\u0100\7?\2\2\u0100>\3"+
		"\2\2\2\u0101\u0102\7#\2\2\u0102\u0103\7?\2\2\u0103@\3\2\2\2\u0104\u0108"+
		"\7$\2\2\u0105\u0107\t\2\2\2\u0106\u0105\3\2\2\2\u0107\u010a\3\2\2\2\u0108"+
		"\u0106\3\2\2\2\u0108\u0109\3\2\2\2\u0109\u010b\3\2\2\2\u010a\u0108\3\2"+
		"\2\2\u010b\u010c\7$\2\2\u010cB\3\2\2\2\u010d\u010e\7\u00c4\2\2\u010e\u010f"+
		"\7\u00aa\2\2\u010fD\3\2\2\2\u0110\u0111\7<\2\2\u0111\u0112\7?\2\2\u0112"+
		"F\3\2\2\2\u0113\u0114\7*\2\2\u0114H\3\2\2\2\u0115\u0116\7+\2\2\u0116J"+
		"\3\2\2\2\u0117\u0118\7<\2\2\u0118L\3\2\2\2\17\2|~\u008f\u0093\u00d7\u00d9"+
		"\u00df\u00e4\u00eb\u00f2\u0106\u0108\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}