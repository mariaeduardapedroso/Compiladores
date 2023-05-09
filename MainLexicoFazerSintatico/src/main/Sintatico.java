package main;

import java.util.ArrayList;
import java.util.List;

public class Sintatico {

    LeitorArquivo dataRead;
    List<Token> listaTokens;
    int index;
    int erroEncontrado;

    public Sintatico(String arquivoUtilizado, List inputListaTokens) {
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
    public void verificadorSintatico() {
        System.out.print("\n\nQuantidade de tokens: " + listaTokens.size()
                + "\nUltimo token: " + listaTokens.get(listaTokens.size() - 1).lexema
                + "\nPrimeiro token: " + listaTokens.get(index).lexema + "\n");

        for (Token i : listaTokens) {
            if (i.tipo == tipoToken.Error) {
                System.out.println("\n======\nExiste um Token Erro na lista! Sintatico nao sera executado.\n======\n");
                return;
            }
        }

        this.programa();

        switch (erroEncontrado) {
            case 1:
                System.out.println("\nErro sintático: ");
                break;
        }
    }

    //Programa → ':' 'DEC' ListaDeclaracoes ':' 'PROG' ListaComandos;
    void programa() {
        if (listaTokens.get(index).tipo == tipoToken.Delim)
            index++;
        else
            System.exit(2);
        
        if (listaTokens.get(index).tipo == tipoToken.PCDec)
            index++;
        else
            System.exit(2);
        
        listaDeclaracoes();
        
        if (listaTokens.get(index).tipo == tipoToken.Delim)
            index++;
        else
            System.exit(2);
            
        if (listaTokens.get(index).tipo == tipoToken.PCProg)
            index++;
        else
            System.exit(2);
        
        System.out.println("\nSintatico verificado completamente!!\n");
    }

    //ListaDeclaracoes → Declaracao ListaDeclaracoes2;
    void listaDeclaracoes() {
        declaracao();
        listaDeclaracoes2();
    }
    
    //ListaDeclaracoes2 → ListaDeclaracoes | e;
    void listaDeclaracoes2() {
        if (listaTokens.get(index).tipo == tipoToken.Var)
            listaDeclaracoes();
    }

    //Declaracao → VARIAVEL ':' TipoVar;
    void declaracao() {
        if (listaTokens.get(index).tipo == tipoToken.Var)
            index++;
        else 
            System.exit(2);
        
        if (listaTokens.get(index).tipo == tipoToken.Delim)
            index++;
        else 
            System.exit(2);
        
        tipoVar();
    }

    //TipoVar → 'INT' | 'REAL';
    void tipoVar() {
        if (listaTokens.get(index).tipo == tipoToken.NumInt)
            index++;
        else if (listaTokens.get(index).tipo == tipoToken.NumReal)
            index++;
        else 
            System.exit(2);
    }

    //ExpressaoAritmetica → TermoAritmetico ExpressaoAritmetica2;
    void expressaoAritmetica() {
        termoAritmetico();
        expressaoAritmetica2();
    }
    //ExpressaoAritmetica2 → '+' ExpressaoAritmetica | '-' ExpressaoAritmetica | e;

    void expressaoAritmetica2() {
        switch(listaTokens.get(index).tipo){
            case OpAritSoma:
            case OpAritSub:
                expressaoAritmetica();
                index++;
                break;
            default:
                break;
        }
    }

    //TermoAritmetico → FatorAritmetico TermoAritmetico2;
    void termoAritmetico() {
        fatorAritmetico();
        termoAritmetico2();
    }
    //TermoAritmetico2 → '*' FatorAritmetico TermoAritmetico2 | '/' FatorAritmetico TermoAritmetico2 | e;

    void termoAritmetico2() {
        switch(listaTokens.get(index).tipo){
            case OpAritMult:
            case OpAritDiv:
                fatorAritmetico();
                termoAritmetico2();
                index++;
                break;
            default:
                break;
        }
    }

    //FatorAritmetico → NUMINT | NUMREAL | VARIAVEL | '(' ExpressaoAritmetica ')'
    void fatorAritmetico() {
        switch(listaTokens.get(index).tipo){
            case NumInt:
            case NumReal:
            case Var:
                index++;
                break;
            case AbrePar:
                index++;
                expressaoAritmetica();
                
                if (listaTokens.get(index).tipo == tipoToken.FechaPar)
                    index++;
                else 
                    System.exit(2);
   
                break;
            default:
                System.exit(2);
        }
    }

    //ExpressaoRelacional → TermoRelacional ExpressaoRelacional2;
    void expressaoRelacional() {
        termoRelacional();
        expressaoRelacional2();
    }
    
    //ExpressaoRelacional2 → OperadorBooleano TermoRelacional ExpressaoRelacional2 | e;
    void expressaoRelacional2() {
        if (listaTokens.get(index).tipo == tipoToken.OPBoolE
         || listaTokens.get(index).tipo == tipoToken.OPBoolOu){
            operadorBooleano();
            termoRelacional();
            expressaoRelacional2();
        }
    }

    //TermoRelacional → ExpressaoAritmetica OP_REL ExpressaoAritmetica | '(' ExpressaoRelacional ')';
    void termoRelacional() {
        
        
        switch(listaTokens.get(index).tipo){
            case NumInt:
            case NumReal:
            case Var:
            case AbrePar:
				index++;
				switch(listaTokens.get(index).tipo){
					expressaoAritmetica(){
						switch (op  relacionais);
						expressaoAritmetica();
					}
					//ou
					expressaoRelacional(){
						tipoToken.FechaPar;
					}
				}
                break;
			default:
				System.exit(2);
        }
    }
        
    //OperadorBooleano → 'E' | 'OU';
    void operadorBooleano(){
        switch(listaTokens.get(index).tipo){
            case OPBoolE:
            case OPBoolOu:
                index++;
                break;
            default:
                System.exit(2);
        }
    }

    //ListaComandos → Comando ListaComandos2;
    void listaComandos(){
        comando();
        listaComandos2();
    }
    //ListaComandos2 → ListaComandos | e;
    void listaComandos2(){
        switch(listaTokens.get(index).tipo){
            case Var:
            case PCLer:
            case PCSe:
            case PCImprimir:
            case PCEnqto:
            case PCIni:
                listaComandos();
            default:
                break;
        }
    }



    //########################################
    //Comando → ComandoAtribuicao | ComandoEntrada | ComandoSaida | ComandoCondicao | ComandoRepeticao | SubAlgoritmo;
    void comando(){
        tipoToken aux = listaTokens.get(index).tipo;
        
        if (aux == tipoToken.Var)
            comandoAtribuicao();
        else if (aux == tipoToken.PCLer)
            comandoEntrada();
        else if (aux == tipoToken.PCImprimir)
            comandoSaida();
        else if (aux == tipoToken.PCSe)
            comandoCondicao();
        else if (aux == tipoToken.PCEnqto)
            comandoRepeticao();
        else if (aux == tipoToken.PCIni)
            subAlgoritmo();
        else
            System.exit(2);
    }

    //ComandoAtribuicao → VARIAVEL ':=' ExpressaoAritmetica;
    void comandoAtribuicao(){
        if (listaTokens.get(index).tipo == tipoToken.Var)
            index++;
        else 
            System.exit(2);
        
        if (listaTokens.get(index).tipo == tipoToken.Atrib)
            index++;
        else 
            System.exit(2);
        
        expressaoAritmetica();
    }

    //ComandoEntrada → 'LER' VARIAVEL;
    void comandoEntrada(){
        if (listaTokens.get(index).tipo == tipoToken.PCLer)
            index++;
        else 
            System.exit(2);
        
        if (listaTokens.get(index).tipo == tipoToken.Var)
            index++;
        else 
            System.exit(2);
    }

    //ComandoSaida → 'IMPRIMIR' ComandoSaida2;
    void comandoSaida(){
        if (listaTokens.get(index).tipo == tipoToken.PCImprimir)
            index++;
        else 
            System.exit(2);
        
        comandoSaida2();
    }
    //ComandoSaida2 → VARIAVEL | CADEIA;
    void comandoSaida2(){
        switch (listaTokens.get(index).tipo){
            case Var:
            case Cadeia:
                index++;
                break;
            default:
                System.exit(2);
        }
    }

    //ComandoCondicao → 'SE' ExpressaoRelacional 'ENTAO' Comando ComandoCondicao2;
    void comandoCondicao(){
        if (listaTokens.get(index).tipo == tipoToken.PCSe)
            index++;
        else 
            System.exit(2);
        
        expressaoRelacional();
        
        if (listaTokens.get(index).tipo == tipoToken.PCEntao)
            index++;
        else 
            System.exit(2);
        
        comando();
        comandoCondicao2();
    }
    //ComandoCondicao2 → 'SENAO' Comando | e;
    void comandoCondicao2(){
        switch(listaTokens.get(index).tipo){
            case PCSenao:
                index++;
                comando();
            default:
                break;
        }
    }

    //ComandoRepeticao → 'ENQTO' ExpressaoRelacional Comando;
    void comandoRepeticao(){
        if (listaTokens.get(index).tipo == tipoToken.PCEnqto)
            index++;
        else 
            System.exit(2);
        
        expressaoRelacional();
        comando();
    }

    //SubAlgoritmo → 'INI' ListaComandos 'FIM';
    void subAlgoritmo(){
        if (listaTokens.get(index).tipo == tipoToken.PCIni)
            index++;
        else 
            System.exit(2);
        
        listaComandos();
        
        if (listaTokens.get(index).tipo == tipoToken.PCFim)
            index++;
        else 
            System.exit(2);
    }
}