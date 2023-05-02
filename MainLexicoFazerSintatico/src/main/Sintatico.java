package main;

import java.util.ArrayList;
import java.util.List;

public class Sintatico {
    LeitorArquivo dataRead;
	List<Token> listaTokens;
	int index;
	int erroEncontrado;
	
	public Sintatico(String arquivoUtilizado, List inputListaTokens){
		try {
            this.dataRead = new LeitorArquivo(arquivoUtilizado);
		} catch (Exception e) {
            System.out.print("File not found;\n");
            throw e;
        }
		
		this.listaTokens = inputListaTokens;
		
		index = 0;
		erroEncontrado = 0;
	}
	
	/*
			Programa → ':' 'DEC' ListaDeclaracoes ':' 'PROG' ListaComandos;
		ListaDeclaracoes → Declaracao ListaDeclaracoes2;
		ListaDeclaracoes2 → ListaDeclaracoes | e;
	Declaracao → VARIAVEL ':' TipoVar;
	TipoVar → 'INT' | 'REAL';

	ExpressaoAritmetica → TermoAritmetico ExpressaoAritmetica2;
	ExpressaoAritmetica2 → '+' ExpressaoAritmetica | '-' ExpressaoAritmetica | e;
	TermoAritmetico → FatorAritmetico TermoAritmetico2;
	TermoAritmetico2 → '*' FatorAritmetico TermoAritmetico2 | '/' FatorAritmetico TermoAritmetico2 | e;
	FatorAritmetico → NUMINT | NUMREAL | VARIAVEL | '(' ExpressaoAritmetica ')'
	ExpressaoRelacional → TermoRelacional ExpressaoRelacional2;
	ExpressaoRelacional2 → OperadorBooleano TermoRelacional ExpressaoRelacional2 | e;
	TermoRelacional → ExpressaoAritmetica OP_REL ExpressaoAritmetica | '(' ExpressaoRelacional ')';
	OperadorBooleano → 'E' | 'OU';

	ListaComandos → Comando ListaComandos2;
	ListaComandos2 → ListaComandos | e;

		Comando → ComandoAtribuicao | ComandoEntrada | ComandoSaida | ComandoCondicao | ComandoRepeticao | SubAlgoritmo;
	ComandoAtribuicao → VARIAVEL ':=' ExpressaoAritmetica;
	ComandoEntrada → 'LER' VARIAVEL;
	ComandoSaida → 'IMPRIMIR' ComandoSaida2;
	ComandoSaida2 → VARIAVEL | CADEIA;
	ComandoCondicao → 'SE' ExpressaoRelacional 'ENTAO' Comando ComandoCondicao2;
	ComandoCondicao2 → 'SENAO' Comando | e;
	ComandoRepeticao → 'ENQTO' ExpressaoRelacional Comando;
	SubAlgoritmo → 'INI' ListaComandos 'FIM';
	*/
	
	// ver notepadd++ /\
	
	public void verificadorSintatico(){
		System.out.print("\n\nQuantidade de tokens: " + listaTokens.size() +
				"\nUltimo token: " + listaTokens.get(listaTokens.size() - 1).lexema +
				"\nPrimeiro token: " + listaTokens.get(index).lexema + "\n");
		
		for (Token i: listaTokens){
			if (i.tipo == tipoToken.Error){
				System.out.println("\n======\nExiste um Token Erro na lista! Sintatico nao sera executado.\n======\n");
				return;
			}
		}
		
		this.programa();
		
		switch (erroEncontrado){
			case 1:
				System.out.println("\nErro sintático: ");
				break;
		}
	}
	
	//Programa → ':' 'DEC' ListaDeclaracoes ':' 'PROG' ListaComandos;
	void programa(){
		tipoToken.Delim;
		tipoToken.PCDec;
		listaDeclaracoes();
		tipoToken.Delim;
		tipoToken.PCProg;
	}
	
	//ListaDeclaracoes → Declaracao ListaDeclaracoes2;
	void listaDeclaracoes(){
		declaracao();
		listaDeclaracoes2();
	}
	//ListaDeclaracoes2 → ListaDeclaracoes | e;
	void listaDeclaracoes2(){
		listaDeclaracoes();
		//ou
		empty;
	}
	
	//Declaracao → VARIAVEL ':' TipoVar;
	void declaracao(){
		tipoToken.Var;
		tipoToken.Delim;
		tipoVar();
	}
	
	//TipoVar → 'INT' | 'REAL';
	void tipoVar(){
		tipoToken.NumInt;
		//ou
		tipoToken.NumReal;
	}

	//ExpressaoAritmetica → TermoAritmetico ExpressaoAritmetica2;
	void expressaoAritmetica(){
		termoAritmetico();
		expressaoAritmetica2();
	}
	//ExpressaoAritmetica2 → '+' ExpressaoAritmetica | '-' ExpressaoAritmetica | e;
	void expressaoAritmetica2(){
		"+"{
			expressaoAritmetica();
		}
		//ou
		"-"{
			expressaoAritmetica();
		}
		//ou
		empty;
	}
	
	//TermoAritmetico → FatorAritmetico TermoAritmetico2;
	void TermoAritmetico(){
		fatorAritmetico();
		termoAritmetico2();
	}
	//TermoAritmetico2 → '*' FatorAritmetico TermoAritmetico2 | '/' FatorAritmetico TermoAritmetico2 | e;
	void termoAritmetico2(){
		tipoToken.OpAritMult{
			fatorAritmetico();
			termoAritmetico2();
		}
		//ou
		tipoToken.OpAritDiv{
			fatorAritmetico();
			termoAritmetico2();
		}
		//ou
		empty;
	}
	
	FatorAritmetico → NUMINT | NUMREAL | VARIAVEL | '(' ExpressaoAritmetica ')'
	void fatorAritmetico(){
		
	}
	
	ExpressaoRelacional → TermoRelacional ExpressaoRelacional2;
	void ExpressaoRelacional(){
		
	}
	ExpressaoRelacional2 → OperadorBooleano TermoRelacional ExpressaoRelacional2 | e;
	void ExpressaoRelacional2(){
		
	}
	
	TermoRelacional → ExpressaoAritmetica OP_REL ExpressaoAritmetica | '(' ExpressaoRelacional ')';
	void TermoRelacional(){
		
	}
	
	OperadorBooleano → 'E' | 'OU';
	void OperadorBooleano(){
		
	}

	ListaComandos → Comando ListaComandos2;
	void ListaComandos(){
		
	}
	ListaComandos2 → ListaComandos | e;
	void ListaComandos2(){
		
	}

	
	
	//########################################
		Comando → ComandoAtribuicao | ComandoEntrada | ComandoSaida | ComandoCondicao | ComandoRepeticao | SubAlgoritmo;
		void Comando(){

		}
		
	ComandoAtribuicao → VARIAVEL ':=' ExpressaoAritmetica;
	void ComandoAtribuicao(){
		
	}
	
	ComandoEntrada → 'LER' VARIAVEL;
	void ComandoEntrada(){
		
	}
	
	ComandoSaida → 'IMPRIMIR' ComandoSaida2;
	void ComandoSaida(){
		
	}
	ComandoSaida2 → VARIAVEL | CADEIA;
	void ComandoSaida2(){
		
	}
	
	ComandoCondicao → 'SE' ExpressaoRelacional 'ENTAO' Comando ComandoCondicao2;
	void ComandoCondicao(){
		
	}
	ComandoCondicao2 → 'SENAO' Comando | e;
	void ComandoCondicao2(){
		
	}
	
	ComandoRepeticao → 'ENQTO' ExpressaoRelacional Comando;
	void ComandoRepeticao(){
		
	}
	
	SubAlgoritmo → 'INI' ListaComandos 'FIM';
	void SubAlgoritmo(){
		
	}
}
