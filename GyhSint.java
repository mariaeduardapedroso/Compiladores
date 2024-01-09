 /* Analisador Sintatico feito para interpretar a linguaguem GYH como o metodo TDP descendente recursivo*/
import java.util.*;

public class GyhSint {

	private int alterna = 0; // Variavel que alterna entre operador relacional e operador booleano ( 0 rel, 1 bool)
	ArrayList<Token> listaToken = new ArrayList<>(); //Lista que recebe os Tokens do Analisador Lexico 

	ArrayList<String> VarDec = new ArrayList<String>();
    ArrayList<Token> TipoDec = new ArrayList<Token>();
    ArrayList<String> VarUsada = new ArrayList<String>();
    ArrayList<Token> TipoCheck = new ArrayList<Token>();
  
	public GyhSint() {
		// TODO Auto-generated constructor stub
	}//GyhSint

  
  // Funcao principal responsavel por analisar sintaticamente o codigo, chamando as funcoes necessarias para isso
	public void Sintatico(ArrayList<Token> listaToken) {


    System.out.println("========== Analise Sintatica ==========");
    
		ArrayList<Token> comparaToken = new ArrayList<Token>(); // Lista que vai receber os Tokens a serem comparados
		ArrayList<Token> listaComandos = new ArrayList<Token>(); // Lista que vai armazenar os Tokens de comando
		int aux = 0; 
    // Verificacao do primeiro Delim
		if (listaToken.get(0).getTipo() == TipoToken.Delim) {
			//System.out.println("\nDelim validado!\n"); 
		} else {
			System.out.println("ERRO: Delim!\n"); 
			System.exit(1);
		}//else
    // Verificacao do primeiro PCDec, que da início ao programa
		if (listaToken.get(1).getTipo() == TipoToken.PCDec) {
			//System.out.println("PCDec validado!\n");
		} else {
			System.out.println("ERRO: PCDec\n");
			System.exit(1);
		}//else
    // Verificacao do procimo delimitador PCDec
		if (listaToken.get(2).getTipo() == TipoToken.Delim) {
			System.out.println("ERRO: Nao existem declaracoes na linha: "+ Integer.toString(listaComandos.get(0).getLinha()) + "\n");
			System.exit(1);
		}
		// Verificacao das declaracoes após o inicio do programa 
		for (int i = 2; i < listaToken.size(); i++) {
			if (listaToken.get(i).getTipo() == TipoToken.Delim && listaToken.get(i + 1).getTipo() == TipoToken.PCProg) {
				//System.out.println("Declaracoes validadas!\n");
				aux = i;
				for (int j = 2; j < aux; j++) {
					comparaToken.add(listaToken.get(j));
				}//for
			}//if
		}//for
    // verificacao da existencia de comandos 
		if (aux + 1 == listaToken.size()) {
			System.out.println("ERRO: Nao existem comandos na linha: " + Integer.toString(listaComandos.get(0).getLinha()) + "\n");
			System.exit(1);
		}//if
		// insere os comandos na lista de comandos 
		for (int i = aux + 2; i < listaToken.size(); i++) {
			listaComandos.add(listaToken.get(i));
			// listaToken.remove(0);
		}//for
		//System.out.println(comparaToken); //mostra a lista que contam os Tokens a serem comparados
    // Chamada da funcao que valida a lista de declaracoes 
		if (parseDec(comparaToken) == 1) {
			System.out.println("ERRO: Declaracao na linha: " + Integer.toString(comparaToken.get(0).getLinha()) + "\n");
			System.exit(1);
		} else {
			//System.out.println("Lista de Declaracoes Validada!\n");
		}//else
    // Chamada da funcao que valida a lista de comandos
		if (parseCom(listaComandos) == 1) {
			System.out.p(rintln("ERRO: Comando na linha: " + Integer.toString(listaComandos.get(0).getLinha()) + "\n");
			System.exit(1);
		} else {
			//System.out.println("Lista de Comandos Validada!\n");
		}//else
    System.out.println("\u001B[32m");
		System.out.println("Sintatica Validada!!!");// Mostra na tela que o programa foi validado
    System.out.println("\u001B[0m");
    
	}//Sintatico

  // Funcao responsavel por validar a lista de declaracoes presentes na lista de Tokens de comando,
  //verificando se esta vazia, se nao estiver adiciona na lista de declaracoes e remove da lista de comparacoes,
  //chamando a funcao de comandos para valida
	public int parseDec(ArrayList<Token> comparaToken) {
		ArrayList<Token> listaDec = new ArrayList<Token>();
		if (comparaToken.isEmpty()) {
			return 0;
		}//if
		for (int i = 0; i < 3; i++) {
			listaDec.add(comparaToken.get(0));
			comparaToken.remove(0);
		}//for
		//System.out.println(listaDec);
		if (Dec(listaDec) == 0) {
			if (parseDec(comparaToken) == 0) {
				return 0;
			}//if
			//System.out.println("Declaracao Validada!\n");
		}//if
		return 1;
	}//parseDec

  // Funcao que verifica as declaracoes da lista de declaracoes formada pela funcao acima e verifica se 
  //pertence a linguagem gyh
	public int Dec(ArrayList<Token> listaDec) {
		if (listaDec.get(0).getTipo() == TipoToken.Var) {
			if (listaDec.get(1).getTipo() == TipoToken.Delim) {
				if (listaDec.get(2).getTipo() == TipoToken.PCInt || listaDec.get(2).getTipo() == TipoToken.PCReal) {
					VarDec.add(listaDec.get(0).getNome());
					TipoDec.add(listaDec.get(2));
					return 0;
				} else {
					System.out.println("ERRO: Esperado PCReal ou PCInt na linha: "
							+ Integer.toString(listaDec.get(0).getLinha()) + "\n");
					System.exit(1);
				}//else
			} else {
				System.out.println(
						"ERRO: Esperado Delim na linha: " + Integer.toString(listaDec.get(0).getLinha()) + "\n");
				System.exit(1);
			}//else
		} else {
			System.out.println("ERRO: Esperado Var na linha: " + Integer.toString(listaDec.get(0).getLinha()) + "\n");
			System.exit(1);
		}//else
		return 1;
	}//Dec


  // Funcao que verifica a lista de Comandos, enviando para a funcao Com() que analizara uma por uma
	public int parseCom(ArrayList<Token> listaComandos) {
		if (listaComandos.isEmpty()) {
			return 0;
		}//if
		//System.out.println(listaComandos);
		if (Com(listaComandos) == 0) {
			if (parseCom(listaComandos) == 0){
				return 0;
        }//if
		}//if

		return 1;
	}//parseCom

  // Funcao responsavel por analisar cada comando individualmente, chamando cada funcao especifica
	public int Com(ArrayList<Token> listaComandos) {
		if (ComAtrib(listaComandos) == 0) {
			//System.out.println("Atribuicao Validada!\n");
			return 0;
		} else if (ComEnt(listaComandos) == 0) {
			//System.out.println("Entrada Validada!\n");
			return 0;
		} else if (ComSaida(listaComandos) == 0) {
			//System.out.println("Saida Validada!\n");
			return 0;
		} else if (ComCond(listaComandos) == 0) {
			//System.out.println("Condicao Validada!\n");
			return 0;
		} else if (ComRepet(listaComandos) == 0) {
			//System.out.println("Repeticao Validada!\n");
			return 0;
		} else if (SubAlg(listaComandos) == 0) {
			//System.out.println("Subalgoritmo Validado!\n");
			return 0;
		}//if
		return 1;
	}//Com

  // Funcao responsavel por verificar o comando de atribuição
	public int ComAtrib(ArrayList<Token> listaComandos) {
		int r = 1;
		int linha = listaComandos.get(0).getLinha();
		ArrayList<Token> listaExp = new ArrayList<Token>();
		if (listaComandos.get(0).getTipo() == TipoToken.Var) {
			VarUsada.add(listaComandos.get(0).getNome());
			TipoCheck.add(listaComandos.get(0));
			listaComandos.remove(0);
			if (listaComandos.get(0).getTipo() == TipoToken.Atrib) {
				listaComandos.remove(0);
				r = 0;
			} else {
				System.out.println("ERRO: Token inesperado na linha: " + Integer.toString(listaComandos.get(0).getLinha()) + "\n");
				System.exit(1);
			}//else
		}//if
		if (r == 0) {
			int numIteracoes = 0;
			for (int i = 0; i < listaComandos.size(); i++) {
				if (linha == listaComandos.get(i).getLinha()) {
					listaExp.add(listaComandos.get(i));
					// listaComandos.remove(0);
					numIteracoes++;
				} else {
					break;
				}//else
			}//for
			for (int i = 0; i < numIteracoes; i++) {
				listaComandos.remove(0);
			}//for
		} else
			return r;
		if (ExpArit(listaExp) == 0) {
			r = 0;
		}//if
		return r;
	}//ComAtrib

  // Funcao responsavel por verificar o comando de entrada
	public int ComEnt(ArrayList<Token> listaComandos) {
		if (listaComandos.get(0).getTipo() == TipoToken.PCLer) {
			listaComandos.remove(0);
			if (listaComandos.get(0).getTipo() == TipoToken.Var) {
				VarUsada.add(listaComandos.get(0).getNome());
				listaComandos.remove(0);
				return 0;
			}//if
		}//if
		return 1;
	}//ComEnt

  // Funcao responsavel por verificar o comando de saida
	public int ComSaida(ArrayList<Token> listaComandos) {
    if (listaComandos.get(0).getTipo() == TipoToken.PCImprimir) {
			listaComandos.remove(0);
			if ((listaComandos.get(0).getTipo() == TipoToken.Var )|| (listaComandos.get(0).getTipo() == TipoToken.Cadeia)) {
				//System.out.println(listaComandos);
				listaComandos.remove(0);
				return 0;
			}//if
		}//if
		return 1;
	}//ComSaida

  // Funcao responsavel por verificar o comando Condicional
	public int ComCond(ArrayList<Token> listaComandos) {
    ArrayList<Token> listaCondicao = new ArrayList<Token>();
		if (listaComandos.get(0).getTipo() == TipoToken.PCSe) {
			//System.out.println(listaComandos);
			int linha = listaComandos.get(0).getLinha();
			listaComandos.remove(0);
			while (listaComandos.get(0).getTipo() != TipoToken.PCEntao) {
				listaCondicao.add(listaComandos.get(0));
				listaComandos.remove(0);
			}//while
			//System.out.println(listaComandos);
			if (listaComandos.get(0).getTipo() == TipoToken.PCEntao && listaComandos.get(0).getLinha() == linha) {
				listaComandos.remove(0);
			} else {
				return 1;
			}//else
			//System.out.println(listaCondicao);
			if (ExpRel(listaCondicao) == 0) {
				if (!listaCondicao.isEmpty()) {
					System.out.println("ERRO: Lexema Inesperado: " +listaCondicao.get(0).getNome() + " na linha: "
							+ Integer.toString(listaCondicao.get(0).getLinha()) + "\n");
					System.exit(1);
				}//if
				if (listaComandos.isEmpty()) {
					System.out.println("ERRO: Comando esperado apos PCEntao na linha:"+Integer.toString(listaComandos.get(0).getLinha()) + "\n");
					System.exit(1);
				}//if
				//System.out.println(listaComandos);
				if (Com(listaComandos) == 0)
					if (listaComandos.get(0).getTipo() == TipoToken.PCSenao
					&& listaComandos.get(0).getLinha() == linha) {
						listaComandos.remove(0);
						if (Com(listaComandos) == 0)
							return 0;
					} else {
						return 0;
					}//else

			}//if
		}//if
		return 1;
	}//ComCond

  //Comando Repetição - Valida o comando de repetição ENQTO
	public int ComRepet(ArrayList<Token> listaComandos) {

		if (listaComandos.get(0).getTipo() == TipoToken.PCEnqto){
			listaComandos.remove(0);
			if (ExpRel(listaComandos) == 0) {
				if (Com(listaComandos) == 0)
					return 0;
			}//if
		}//if
		return 1;
	}//ComRepet

    /*----------- Expressões Aritmeticas -----------*/
    // Funções aritmeticas baseadas na gramatica GYH com as devidas correções
    
    //Expressão Aritmetica - Função que valida expressão aritmetica
	public int ExpArit(ArrayList<Token> listaExp) {
		if (listaExp.isEmpty()) {
			return 0;
		}//if
		if (termoArit(listaExp) == 0) {
			if (expAritLeft(listaExp) == 0) {
				//System.out.println("Expressao Aritmetica Validada!\n");
				return 0;
			}
		}//if
		return 1;
  }//ExpArit

     //Termo Aritmetico - Função que valida Termo aritmetico
	public int termoArit(ArrayList<Token> listaExp) {
		int r = 1;
		if (listaExp.isEmpty()) {
			return 0;
		}//if
		if (fatorArit(listaExp) == 0) {
			if (termoAritLeft(listaExp) == 0) {
				r = 0;
			}//if
		}//if
		return r;
	}//termoArit
    
    //Termo Aritmetico recursivo a esquerda- Função que valida termo artimetico recursivo a esquerda
	public int termoAritLeft(ArrayList<Token> listaExp) {
		if (listaExp.isEmpty()) {
			return 0;
		}//if
		if (listaExp.get(0).getTipo() == TipoToken.OpAritMult) {
			listaExp.remove(0);
			if (fatorArit(listaExp) == 0) {
				termoAritLeft(listaExp);
			}//if
		} else if (listaExp.get(0).getTipo() == TipoToken.OpAritDiv) {
			listaExp.remove(0);
			if (fatorArit(listaExp) == 0) {
				termoAritLeft(listaExp);
			}//if
		} else if (listaExp.get(0).getTipo() == TipoToken.FechaPar) {
			System.out.println("Token FechaPar inesperado na linha: " + Integer.toString(listaExp.get(0).getLinha()));
			System.exit(1);
		} else {
			//System.out.println("Palavra Vazia!\n");
			return 0;
		}//else
		return 1;
	}//termoAritLeft

    //Expressão Aritmetica recursiva a esquerda - Função que valida expressão aritmetica recursiva a esquerda
	public int expAritLeft(ArrayList<Token> listaExp) {
		int r = 1;
		if (listaExp.isEmpty()) {
			return 0;
		}//if
		if (listaExp.get(0).getTipo() == TipoToken.OpAritSoma) {
			listaExp.remove(0);
			if (termoArit(listaExp) == 0)
				if (expAritLeft(listaExp) == 0) {
					r = 0;
				}//if
		} else if (listaExp.get(0).getTipo() == TipoToken.OpAritSub) {
			listaExp.remove(0);
			if (termoArit(listaExp) == 0)
				if (expAritLeft(listaExp) == 0)
					r = 0;
		} else {
			//System.out.println("Palavra Vazia!\n");
			r = 0;
		}//else
		return r;
	}//expAritLeft

    //Fator Aritmetico - Função que valida fator aritmetico
	public int fatorArit(ArrayList<Token> listaExp) {
		int r = 1;
		boolean fechaPar = false;
		ArrayList<Token> listaFatorArit = new ArrayList<Token>();
		if (listaExp.isEmpty()) {
			return 0;
		}//if
		if (listaExp.get(0).getTipo() == TipoToken.Var) {
			VarUsada.add(listaExp.get(0).getNome());
			TipoCheck.add(listaExp.get(0));
			listaExp.remove(0);
			r = 0;
		} else if (listaExp.get(0).getTipo() == TipoToken.NumInt || listaExp.get(0).getTipo() == TipoToken.NumReal) {
			TipoCheck.add(listaExp.get(0));
			listaExp.remove(0);
			r = 0;
		} else if (listaExp.get(0).getTipo() == TipoToken.AbrePar) {
			int linha = listaExp.get(0).getLinha();
			listaExp.remove(0);
			while ((!listaExp.isEmpty()) && listaExp.get(0).getLinha() == linha) {
				if (listaExp.get(0).getTipo() != TipoToken.FechaPar) {
					listaFatorArit.add(listaExp.get(0));
					if(listaExp.get(0).getTipo() == TipoToken.Var || listaExp.get(0).getTipo() == TipoToken.NumInt || listaExp.get(0).getTipo() == TipoToken.NumReal) {
						TipoCheck.add(listaExp.get(0));
					}
					listaExp.remove(0);
				} else {
					fechaPar = true;
					listaExp.remove(0);
					break;
				}//else
			}//while
			if (!fechaPar) {
				System.out.println("Token FechaPar esperado na linha: " + Integer.toString(linha) + "\n");
				System.exit(1);
			}//if
			if (ExpArit(listaFatorArit) == 0) {
				r = 0;
			}//if
		} else if (listaToken.get(0).getTipo() == TipoToken.FechaPar) {
			System.out.println("Token FechaPar inesperado na linha: " + Integer.toString(listaExp.get(0).getLinha()));
			System.exit(1);
		}//if

		return r;
	}//fatorArit

    //Sub Algoritmo - Função que valida um Subalgoritmo , consideramos um SubAlg quando aparece um INI e FIM de acordo com a gramatica GYH
	public int SubAlg(ArrayList<Token> listaComandos) {

		int r = 1; 

		ArrayList<Token> listaSubAlg = new ArrayList<Token>();
		if (listaComandos.get(0).getTipo() == TipoToken.PCIni) {
			listaComandos.remove(0);
			while (listaComandos.get(0).getTipo() != TipoToken.PCFim) {
				listaSubAlg.add(listaComandos.get(0));
				listaComandos.remove(0);
			}//while
			if (listaComandos.get(0).getTipo() == TipoToken.PCFim) {
				listaComandos.remove(0);
				if (parseCom(listaSubAlg) == 0)
					r = 0;
			} else {
				System.out.println("ERRO: Subalgoritmo na linha: " + Integer.toString(listaComandos.get(0).getLinha()) + "\n");
				System.exit(1);
			}//else
		}//if
		return r;
	}//SubAlg


    /*----------- Expressões Relacionais -----------*/
    // Funções relacionais baseadas na gramatica GYH com as devidas correções
    
    //Expressão Relacional - Função que valida a Expressão relacional
	public int ExpRel(ArrayList<Token> listaComandos) {
		alterna = 0; // Relacional
		if (listaComandos.isEmpty()) { 
			return 0;
		}//if
		if (termoRel(listaComandos) == 0) {
			if (expRelLeft(listaComandos) == 0) {
				//System.out.println("Expressao Relacional Validada!\n");
				return 0;
			}//if
		}//if
		return 1;
	}//ExpRel

    //Termo Relacional - Função que valida o termo relacional
	public int termoRel(ArrayList<Token> listaComandos) {
        
		boolean fechaPar = false;
		ArrayList<Token> listaTermoRel = new ArrayList<Token>();
		if (ExpArit(listaComandos) == 0) {
			if (termoRelLeft(listaComandos) == 0) {
				return 0;
			}//if
		} else if (listaComandos.get(0).getTipo() == TipoToken.AbrePar) {
			int linha = listaComandos.get(0).getLinha();
			listaComandos.remove(0);
			while (listaComandos.get(0).getLinha() == linha) {
				if (listaComandos.get(0).getTipo() != TipoToken.FechaPar) {
					listaTermoRel.add(listaComandos.get(0));
					listaComandos.remove(0);
				} else {
					fechaPar = true;
					listaComandos.remove(0);
					break;
				}//else
			}//while
			if (!fechaPar) {
				return 1;
			}//if
			if (ExpRel(listaTermoRel) == 0) {
				return 0;
			}//if
		}//if
		return 1;
	}//termoRel
    
    //Expressão Relacional recursiva a esquerda - Valida a expressão relacional recursiva a esquerda de acordo com a gramatica GYH
	public int expRelLeft(ArrayList<Token> listaComandos) {
		if (listaComandos.isEmpty()) {
			return 0;
		}//if
		if (OpBool(listaComandos) == 0) {
			if (termoRel(listaComandos) == 0) {
				if (expRelLeft(listaComandos) == 0) {
					return 0;
				}//if
			}//if
		} else if(alterna == 0) {
			//System.out.println("Palavra Vazia!\n");
			return 2; 
		}//else
		return 1;
	}//expRelLeft

    //Termo Relacional recursivo a esquerda-  Valida o termo relacional recursivo a esquerda de acordo com a gramatica GYH
	public int termoRelLeft(ArrayList<Token> listaComandos) {
		if (listaComandos.isEmpty()) {
			return 0;
		}//if
		if (OpRel(listaComandos) == 0) {
			if (ExpArit(listaComandos) == 0) {
				if (termoRelLeft(listaComandos) == 0) {
					//System.out.println("Termo Relacional Validado!\n");
					return 0;
				}//if
			}//if
		} else {
			//System.out.println("Palavra Vazia!\n");
			return 1;
		}//else
		return 1;
	}//termoRelLeft

    //Operadores Relacionais - função que valida operadores relacionais 
	public int OpRel(ArrayList<Token> listaComandos) {
		if(alterna == 1){ // condição que verifica se é um  operador relacional ou operador booleano, pois os dois se alternam dentro da linguagem GYH 
			return 1;
		}//if
		if (listaComandos.get(0).getTipo() == TipoToken.OpRelMaior) {
			listaComandos.remove(0);
			alterna = 1; //Relacional
			return 0;
		} else if (listaComandos.get(0).getTipo() == TipoToken.OpRelMenorIgual) {
			listaComandos.remove(0);
			alterna = 1; //Relacional
			return 0;
		} else if (listaComandos.get(0).getTipo() == TipoToken.OpRelMenor) {
			listaComandos.remove(0);
			alterna = 1; //Relacional
			return 0;
		} else if (listaComandos.get(0).getTipo() == TipoToken.OpRelMaiorIgual) {
			listaComandos.remove(0);
			alterna = 1; //Relacional
			return 0;
		} else if (listaComandos.get(0).getTipo() == TipoToken.OpRelIgual) {
			listaComandos.remove(0);
			alterna = 1; //Relacional
			return 0;
		} else if (listaComandos.get(0).getTipo() == TipoToken.OpRelDif) {
			listaComandos.remove(0);
			alterna = 1; //Relacional
			return 0;
		} //if
		return 1;
	}//OpRel

     //função que valida operadores booleanos
	public int OpBool(ArrayList<Token> listaComandos) {
		if(alterna == 0){ // condição que verifica se é um  operador relacional ou operador booleano, pois os dois se alternam dentro da linguagem GYH
			return 1;
		}//if
		if (listaComandos.get(0).getTipo() == TipoToken.OpBoolE) {
			listaComandos.remove(0);
			alterna = 0; //Bool
			return 0;
		} else if (listaComandos.get(0).getTipo() == TipoToken.OpBoolOu) {
			listaComandos.remove(0);
			alterna = 0; //Bool 
			return 0;
		} else {
			System.out.println("ERROR: OPBOOL na linha " + Integer.toString(listaComandos.get(0).getLinha()));
			System.exit(1);
		}//else 

		return 1;
	}//OpBool
	
	public ArrayList<String> getVarUsada(){
		return this.VarUsada;
	}
	
	public ArrayList<String> getVarDec(){
		return this.VarDec;
	}
	
	public ArrayList<Token> getTipoDec(){
		return this.TipoDec;
	}
	
	public ArrayList<Token> getTipoCheck(){
		return this.TipoCheck;
	}
	

}