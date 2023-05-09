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
        if (listaTokens.get(index).tipo == tipoToken.Delim){
            System.out.println("[programa] lido: " + listaTokens.get(index).tipo + ", index: " + index);
            index++;
        }
        else{
            ERROSINTATICO();
        }
        
        if (listaTokens.get(index).tipo == tipoToken.PCDec){
            System.out.println("[programa] lido: " + listaTokens.get(index).tipo + ", index: " + index);
            index++;
        }
        else{
            ERROSINTATICO();
        }
        
        listaDeclaracoes();
        
        if (listaTokens.get(index).tipo == tipoToken.Delim){
            System.out.println("[programa] lido: " + listaTokens.get(index).tipo + ", index: " + index);
            index++;
        }
        else{
            ERROSINTATICO();
        }
        
            
        if (listaTokens.get(index).tipo == tipoToken.PCProg){
            System.out.println("[programa] lido: " + listaTokens.get(index).tipo + ", index: " + index);
            index++;
        }
        else{
            ERROSINTATICO();
        }
        
        listaComandos();
    }

    //ListaDeclaracoes → Declaracao ListaDeclaracoes2;
    void listaDeclaracoes() {
        declaracao();
        listaDeclaracoes2();
    }
    
    //ListaDeclaracoes2 → ListaDeclaracoes | e;
    void listaDeclaracoes2() {
        if (listaTokens.get(index).tipo == tipoToken.Var){
            listaDeclaracoes();
        }
    }

    //Declaracao → VARIAVEL ':' TipoVar;
    void declaracao() {
        if (listaTokens.get(index).tipo == tipoToken.Var){
            System.out.println("[declaracao] lido: " + listaTokens.get(index).tipo + ", index: " + index);
            index++;
        }
        else {
            ERROSINTATICO();
        }
        
        if (listaTokens.get(index).tipo == tipoToken.Delim){
            System.out.println("[declaracao] lido: " + listaTokens.get(index).tipo + ", index: " + index);
            index++;
        }
        else {
            ERROSINTATICO();
        }
        
        tipoVar();
    }

    //TipoVar → 'INT' | 'REAL';
    void tipoVar() {
        if (listaTokens.get(index).tipo == tipoToken.PCint){
            System.out.println("[tipoVar] lido: " + listaTokens.get(index).tipo + ", index: " + index);
            index++;
        }
        else{
            if (listaTokens.get(index).tipo == tipoToken.PCReal){
                System.out.println("[tipoVar] lido: " + listaTokens.get(index).tipo + ", index: " + index);
                index++;
            }
            else {
                ERROSINTATICO();
            }
        }
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
                System.out.println("[expressaoAritmetica2] lido: " + listaTokens.get(index).tipo + ", index: " + index);
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
                System.out.println("[termoAritmetico2] lido: " + listaTokens.get(index).tipo + ", index: " + index);
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
                System.out.println("[fatorAritmetico] lido: " + listaTokens.get(index).tipo + ", index: " + index);
                index++;
                break;
            case AbrePar:
                System.out.println("[fatorAritmetico] lido: " + listaTokens.get(index).tipo + ", index: " + index);
                index++;
                expressaoAritmetica();
                
                if (listaTokens.get(index).tipo == tipoToken.FechaPar){
                    System.out.println("[fatorAritmetico] lido: " + listaTokens.get(index).tipo + ", index: " + index);
                    index++;
                }
                else {
                    ERROSINTATICO();
                }
                
   
                break;
            default:
                ERROSINTATICO();
                break;
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

    //TermoRelacional → ExpressaoAritmetica OP_REL ExpressaoAritmetica;
    //TermoRelacional → '(' ExpressaoRelacional ')';
    void termoRelacional() {
        switch(listaTokens.get(index).tipo){
            case NumInt:
            case NumReal:
            case Var:
                System.out.println("[termoRelacional] lido: " + listaTokens.get(index).tipo + ", index: " + index);
                index++;
                switch(listaTokens.get(index).tipo){
                    case OpRelMenorigual:
                    case OpRelIgual:
                    case OpRelMaior:
                    case OpRelMaiorigual:
                    case OpRelDif:
                    case OpRelMenor:
                        System.out.println("[termoRelacional] lido: " + listaTokens.get(index).tipo + ", index: " + index);
                        index++;
                        expressaoAritmetica();
                        break;
                        
                    default:
                        ERROSINTATICO();
                        break;
                }
                break;
                
            case AbrePar:
                System.out.println("[termoRelacional] lido: " + listaTokens.get(index).tipo + ", index: " + index);
                index++;
                switch(listaTokens.get(index).tipo){
                    case OpRelMenorigual:
                    case OpRelIgual:
                    case OpRelMaior:
                    case OpRelMaiorigual:
                    case OpRelDif:
                    case OpRelMenor:
                        System.out.println("[termoRelacional] lido: " + listaTokens.get(index).tipo + ", index: " + index);
                        index++;
                        expressaoAritmetica();
                        break;
                    default:
                        expressaoRelacional();
                        System.out.println("[termoRelacional] lido: " + listaTokens.get(index).tipo + ", index: " + index);
                        index++;
                        
                        if (listaTokens.get(index).tipo == tipoToken.FechaPar){
                            System.out.println("[termoRelacional] lido: " + listaTokens.get(index).tipo + ", index: " + index);
                            index++;
                        }
                        else{
                            ERROSINTATICO();
                        }
                        
                        break;
                }
        }
    }
        
    //OperadorBooleano → 'E' | 'OU';
    void operadorBooleano(){
        switch(listaTokens.get(index).tipo){
            case OPBoolE:
            case OPBoolOu:
                System.out.println("[operadorBooleano] lido: " + listaTokens.get(index).tipo + ", index: " + index);
                index++;
                break;
            default:
                ERROSINTATICO();
                break;
                
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
                break;
            default:
                break;
        }
    }



    //########################################
    //Comando → ComandoAtribuicao | ComandoEntrada | ComandoSaida | ComandoCondicao | ComandoRepeticao | SubAlgoritmo;
    void comando(){
        tipoToken aux = listaTokens.get(index).tipo;
        
        switch(aux){
            case Var:
                comandoAtribuicao();
                break;
            case PCLer:
                comandoEntrada();
                break;
            case PCImprimir:
                comandoSaida();
                break;
            case PCSe:
                comandoCondicao();
                break;
            case PCEnqto:
                comandoRepeticao();
                break;
            case PCIni:
                subAlgoritmo();
                break;
            default:
                ERROSINTATICO();
                break;
        }
    }

    //ComandoAtribuicao → VARIAVEL ':=' ExpressaoAritmetica;
    void comandoAtribuicao(){
        if (listaTokens.get(index).tipo == tipoToken.Var){
            System.out.println("[comandoAtribuicao] lido: " + listaTokens.get(index).tipo + ", index: " + index);
            index++;
        }
        else {
            ERROSINTATICO();
        }
        
        
        if (listaTokens.get(index).tipo == tipoToken.Atrib){
            System.out.println("[comandoAtribuicao] lido: " + listaTokens.get(index).tipo + ", index: " + index);
            index++;
        }
        else {
            ERROSINTATICO();
        }
        
        
        expressaoAritmetica();
    }

    //ComandoEntrada → 'LER' VARIAVEL;
    void comandoEntrada(){
        if (listaTokens.get(index).tipo == tipoToken.PCLer){
            System.out.println("[comandoEntrada] lido: " + listaTokens.get(index).tipo + ", index: " + index);
            index++;
        }
        else {
            ERROSINTATICO();
        }
        
        
        if (listaTokens.get(index).tipo == tipoToken.Var){
            System.out.println("[comandoEntrada] lido: " + listaTokens.get(index).tipo + ", index: " + index);
            index++;
        }
        else {
            ERROSINTATICO();
        }
        
    }

    //ComandoSaida → 'IMPRIMIR' ComandoSaida2;
    void comandoSaida(){
        if (listaTokens.get(index).tipo == tipoToken.PCImprimir){
            System.out.println("[comandoSaida] lido: " + listaTokens.get(index).tipo + ", index: " + index);
            index++;
        }
        else {
            ERROSINTATICO();
        }
        
        
        comandoSaida2();
    }
    //ComandoSaida2 → VARIAVEL | CADEIA;
    void comandoSaida2(){
        switch (listaTokens.get(index).tipo){
            case Var:
            case Cadeia:
                System.out.println("[comandoSaida2] lido: " + listaTokens.get(index).tipo + ", index: " + index);
                index++;
                break;
            default:
                ERROSINTATICO();
                break;
        }
    }

    //ComandoCondicao → 'SE' ExpressaoRelacional 'ENTAO' Comando ComandoCondicao2;
    void comandoCondicao(){
        if (listaTokens.get(index).tipo == tipoToken.PCSe){
            System.out.println("[comandoCondicao] lido: " + listaTokens.get(index).tipo + ", index: " + index);
            index++;
        }
        else {
            ERROSINTATICO();
        }
        
        
        expressaoRelacional();
        
        if (listaTokens.get(index).tipo == tipoToken.PCEntao){
            System.out.println("[comandoCondicao] lido: " + listaTokens.get(index).tipo + ", index: " + index);
            index++;
        }
        else {
            ERROSINTATICO();
        }
        
        
        comando();
        comandoCondicao2();
    }
    //ComandoCondicao2 → 'SENAO' Comando | e;
    void comandoCondicao2(){
        switch(listaTokens.get(index).tipo){
            case PCSenao:
                System.out.println("[comandoCondicao2] lido: " + listaTokens.get(index).tipo + ", index: " + index);
                index++;
                comando();
            default:
                break;
        }
    }

    //ComandoRepeticao → 'ENQTO' ExpressaoRelacional Comando;
    void comandoRepeticao(){
        if (listaTokens.get(index).tipo == tipoToken.PCEnqto){
            System.out.println("[comandoRepeticao] lido: " + listaTokens.get(index).tipo + ", index: " + index);
            index++;
        }
        else {
            ERROSINTATICO();
        }
        
        
        expressaoRelacional();
        comando();
    }

    //SubAlgoritmo → 'INI' ListaComandos 'FIM';
    void subAlgoritmo(){
        if (listaTokens.get(index).tipo == tipoToken.PCIni){
            System.out.println("[subAlgoritmo] lido: " + listaTokens.get(index).tipo + ", index: " + index);
            index++;
        }
        else {
            ERROSINTATICO();
        }
        
        
        listaComandos();
        
        if (listaTokens.get(index).tipo == tipoToken.PCFim){
            System.out.println("[subAlgoritmo] lido: " + listaTokens.get(index).tipo + ", index: " + index);
            index++;
        }
        else {
            ERROSINTATICO();
        }
        
    }
    
    
    
    
    
    
    void ERROSINTATICO(){
        System.out.println("\n\nErro sintático: " + listaTokens.get(index).tipo + " inesperado no index " + index + "!\n\n");
        System.exit(2);
    }
}