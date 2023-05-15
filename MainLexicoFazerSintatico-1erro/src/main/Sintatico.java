package main;

import java.util.ArrayList;
import java.util.List;

public class Sintatico {

    LeitorArquivo dataRead;
    List<Token> listaTokens;
    int index;

    public Sintatico(String arquivoUtilizado, List inputListaTokens) {
        try {
            this.dataRead = new LeitorArquivo(arquivoUtilizado);
        } catch (Exception e) {
            System.out.print("File not found;\n");
            throw e;
        }

        this.listaTokens = inputListaTokens;
        index = 0;
    }

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
        System.out.println("\nSEM Erro sintatico: ");
    }

    //Programa → ':' 'DEC' ListaDeclaracoes ':' 'PROG' ListaComandos;
    void programa() {
        System.out.println("inicio do programa");
        if (listaTokens.get(index).tipo == tipoToken.Delim) {
            System.out.println("[programa] lido: " + listaTokens.get(index).tipo + ", index: " + index);
            acrescentarIndex();

            if (listaTokens.get(index).tipo == tipoToken.PCDec) {
                System.out.println("[programa] lido: " + listaTokens.get(index).tipo + ", index: " + index);
                acrescentarIndex();
                listaDeclaracoes();
                if (listaTokens.get(index).tipo == tipoToken.Delim) {
                    System.out.println("[programa] lido: " + listaTokens.get(index).tipo + ", index: " + index);
                    acrescentarIndex();
                    if (listaTokens.get(index).tipo == tipoToken.PCProg) {
                        System.out.println("[programa] lido: " + listaTokens.get(index).tipo + ", index: " + index);
                        acrescentarIndex();
                        listaComandos();
                    } else {
                        ERROSINTATICO("PcProg");
                    }
                } else {
                    ERROSINTATICO("Delim");
                }
            } else {
                ERROSINTATICO("PcDec");
            }
        } else {
            ERROSINTATICO("Delim");
        }
    }

    //ListaDeclaracoes → Declaracao ListaDeclaracoes2;
    void listaDeclaracoes() {
        System.out.println("listaDeclaracoes ");
        declaracao();
        listaDeclaracoes2();
    }

    //ListaDeclaracoes2 → ListaDeclaracoes | e;
    void listaDeclaracoes2() {
        System.out.println("Lista de Declaracoes");
        if (listaTokens.get(index).tipo == tipoToken.Var) {
            listaDeclaracoes();
        }
    }

    //Declaracao → VARIAVEL ':' TipoVar;
    void declaracao() {
        if (listaTokens.get(index).tipo == tipoToken.Var) {
            System.out.println("[declaracao] lido: " + listaTokens.get(index).tipo + ", index: " + index);
            acrescentarIndex();
            if (listaTokens.get(index).tipo == tipoToken.Delim) {
                System.out.println("[declaracao] lido: " + listaTokens.get(index).tipo + ", index: " + index);
                acrescentarIndex();
                tipoVar();
            } else {
                ERROSINTATICO("Delim");
            }
        } else {
            ERROSINTATICO("Var");
        }
    }

    //TipoVar → 'INT' | 'REAL';
    void tipoVar() {
        if (listaTokens.get(index).tipo == tipoToken.PCint) {
            System.out.println("[tipoVar] lido: " + listaTokens.get(index).tipo + ", index: " + index);
            acrescentarIndex();
        } else {
            if (listaTokens.get(index).tipo == tipoToken.PCReal) {
                System.out.println("[tipoVar] lido: " + listaTokens.get(index).tipo + ", index: " + index);
                acrescentarIndex();
            } else {
                ERROSINTATICO("TipoVar");
            }
        }
    }

    //ExpressaoAritmetica → TermoAritmetico ExpressaoAritmetica2;
    void expressaoAritmetica() {
        System.out.println("Expressao aritmetica");
        termoAritmetico();
        expressaoAritmetica2();
    }

    //ExpressaoAritmetica2 → '+' ExpressaoAritmetica | '-' ExpressaoAritmetica | e;
    void expressaoAritmetica2() {
        switch (listaTokens.get(index).tipo) {
            case OpAritSoma:
            case OpAritSub:
                acrescentarIndex();
                expressaoAritmetica();
                System.out.println("[expressaoAritmetica2] lido: " + listaTokens.get(index).tipo + ", index: " + index);
                break;
            default:
                break;
        }
    }

    //TermoAritmetico → FatorAritmetico TermoAritmetico2;
    void termoAritmetico() {
        System.out.println("[termoAritmetico] ");
        fatorAritmetico();
        termoAritmetico2();
    }

    //TermoAritmetico2 → '*' TermoAritmetico  | '/' TermoAritmetico  | ε;
    void termoAritmetico2() {
        switch (listaTokens.get(index).tipo) {
            case OpAritMult:
            case OpAritDiv:
                acrescentarIndex();
                termoAritmetico();
                System.out.println("[termoAritmetico2] lido: " + listaTokens.get(index).tipo + ", index: " + index);
                break;
            default:
                break;
        }
    }

    //FatorAritmetico → NUMINT | NUMREAL | VARIAVEL | '(' ExpressaoAritmetica ')'
    void fatorAritmetico() {
        switch (listaTokens.get(index).tipo) {
            case NumInt:
            case NumReal:
            case Var:
                System.out.println("[fatorAritmetico] lido: " + listaTokens.get(index).tipo + ", index: " + index);
                acrescentarIndex();
                break;
            case AbrePar:
                System.out.println("[fatorAritmetico] lido: " + listaTokens.get(index).tipo + ", index: " + index);
                acrescentarIndex();
                expressaoAritmetica();
                if (listaTokens.get(index).tipo == tipoToken.FechaPar) {
                    System.out.println("[fatorAritmetico] lido: " + listaTokens.get(index).tipo + ", index: " + index);
                    acrescentarIndex();
                } else {
                    ERROSINTATICO("FechaPar");
                }
                break;
            default:
                ERROSINTATICO("Num");
                break;
        }
    }

    //ExpressaoRelacional → TermoRelacional ExpressaoRelacional2;
    void expressaoRelacional() {
        System.out.println("[expressaoRelacional] ");
        termoRelacional();
        expressaoRelacional2();
    }

    //ExpressaoRelacional2 → OperadorBooleano TermoRelacional ExpressaoRelacional2 | e;
    void expressaoRelacional2() {
        if (listaTokens.get(index).tipo == tipoToken.OPBoolE
                || listaTokens.get(index).tipo == tipoToken.OPBoolOu) {
            operadorBooleano();
            termoRelacional();
            expressaoRelacional2();
        }
    }

    //TermoRelacional → ExpressaoAritmetica OP_REL ExpressaoAritmetica;TermoRelacional → ExpressaoAritmetica OP_REL ExpressaoAritmetica | 
    //TermoRelacional → '(' ExpressaoRelacional ')';
    void termoRelacional() {
        switch (listaTokens.get(index).tipo) {
            case NumInt:
            case NumReal:
            case Var:
                System.out.println("[termoRelacional] lido: " + listaTokens.get(index).tipo + ", index: " + index);
                acrescentarIndex();
                switch (listaTokens.get(index).tipo) {
                    case OpRelMenorigual:
                    case OpRelIgual:
                    case OpRelMaior:
                    case OpRelMaiorigual:
                    case OpRelDif:
                    case OpRelMenor:
                        System.out.println("[termoRelacional] lido: " + listaTokens.get(index).tipo + ", index: " + index);
                        acrescentarIndex();
                        expressaoAritmetica();
                        break;
                    default:
                        ERROSINTATICO("Num int var ou real");
                        break;
                }
                break;
            case AbrePar:
                System.out.println("[termoRelacional] lido: " + listaTokens.get(index).tipo + ", index: " + index);
                acrescentarIndex();
                switch (listaTokens.get(index).tipo) {
                    case OpRelMenorigual:
                    case OpRelIgual:
                    case OpRelMaior:
                    case OpRelMaiorigual:
                    case OpRelDif:
                    case OpRelMenor:
                        System.out.println("[termoRelacional] lido: " + listaTokens.get(index).tipo + ", index: " + index);
                        acrescentarIndex();
                        expressaoAritmetica();
                        break;
                    default:
                        expressaoRelacional();
                        System.out.println("[termoRelacional] lido: " + listaTokens.get(index).tipo + ", index: " + index);
                        acrescentarIndex();

                        if (listaTokens.get(index).tipo == tipoToken.FechaPar) {
                            System.out.println("[termoRelacional] lido: " + listaTokens.get(index).tipo + ", index: " + index);
                            acrescentarIndex();
                        } else {
                            ERROSINTATICO("FechaPar");
                        }
                        break;
                }
        }
    }

    //OperadorBooleano → 'E' | 'OU';
    void operadorBooleano() {
        switch (listaTokens.get(index).tipo) {
            case OPBoolE:
            case OPBoolOu:
                System.out.println("[operadorBooleano] lido: " + listaTokens.get(index).tipo + ", index: " + index);
                acrescentarIndex();
                break;
            default:
                ERROSINTATICO("OpBool");
                break;

        }
    }

    //ListaComandos → Comando ListaComandos2;
    void listaComandos() {
        System.out.println("[listaComandos] lido: " + listaTokens.get(index).tipo + ", index: " + index);
        comando();
        listaComandos2();
    }

    //ListaComandos2 → ListaComandos | e;
    void listaComandos2() {
        switch (listaTokens.get(index).tipo) {
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

    //Comando → ComandoAtribuicao | ComandoEntrada | ComandoSaida | ComandoCondicao | ComandoRepeticao | SubAlgoritmo;
    void comando() {
        tipoToken aux = listaTokens.get(index).tipo;
        switch (aux) {
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
                ERROSINTATICO("Comando");
                break;
        }
    }

    //ComandoAtribuicao → VARIAVEL ':=' ExpressaoAritmetica;
    void comandoAtribuicao() {
        if (listaTokens.get(index).tipo == tipoToken.Var) {
            System.out.println("[comandoAtribuicao] lido: " + listaTokens.get(index).tipo + ", index: " + index);
            acrescentarIndex();
            if (listaTokens.get(index).tipo == tipoToken.Atrib) {
                System.out.println("[comandoAtribuicao] lido: " + listaTokens.get(index).tipo + ", index: " + index);
                acrescentarIndex();
                expressaoAritmetica();
            } else {
                ERROSINTATICO("Atrib");
            }
        } else {
            ERROSINTATICO("Var");
        }
    }

    //ComandoEntrada → 'LER' VARIAVEL;
    void comandoEntrada() {
        if (listaTokens.get(index).tipo == tipoToken.PCLer) {
            System.out.println("[comandoEntrada] lido: " + listaTokens.get(index).tipo + ", index: " + index);
            acrescentarIndex();
            if (listaTokens.get(index).tipo == tipoToken.Var) {
                System.out.println("[comandoEntrada] lido: " + listaTokens.get(index).tipo + ", index: " + index);
                acrescentarIndex();
            } else {
                ERROSINTATICO("Var");
            }
        } else {
            ERROSINTATICO("PcLer");
        }
    }

    //ComandoSaida → 'IMPRIMIR' ComandoSaida2;
    void comandoSaida() {
        if (listaTokens.get(index).tipo == tipoToken.PCImprimir) {
            System.out.println("[comandoSaida] lido: " + listaTokens.get(index).tipo + ", index: " + index);
            acrescentarIndex();
            comandoSaida2();
        } else {
            ERROSINTATICO("PcImprimir");
        }
    }

    //ComandoSaida2 → VARIAVEL | CADEIA;
    void comandoSaida2() {
        switch (listaTokens.get(index).tipo) {
            case Var:
            case Cadeia:
                System.out.println("[comandoSaida2] lido: " + listaTokens.get(index).tipo + ", index: " + index);
                acrescentarIndex();
                break;
            default:
                ERROSINTATICO("Var ou cadeia");
                break;
        }
    }

    //ComandoCondicao → 'SE' ExpressaoRelacional 'ENTAO' Comando ComandoCondicao2;
    void comandoCondicao() {
        if (listaTokens.get(index).tipo == tipoToken.PCSe) {
            System.out.println("[comandoCondicao] lido: " + listaTokens.get(index).tipo + ", index: " + index);
            acrescentarIndex();
            expressaoRelacional();
            if (listaTokens.get(index).tipo == tipoToken.PCEntao) {
                System.out.println("[comandoCondicao] lido: " + listaTokens.get(index).tipo + ", index: " + index);
                acrescentarIndex();
                comando();
                comandoCondicao2();
            } else {
                ERROSINTATICO("PcEntao");
            }
        } else {
            ERROSINTATICO("PcSe");
        }
    }

    //ComandoCondicao2 → 'SENAO' Comando | e;
    void comandoCondicao2() {
        switch (listaTokens.get(index).tipo) {
            case PCSenao:
                System.out.println("[comandoCondicao2] lido: " + listaTokens.get(index).tipo + ", index: " + index);
                acrescentarIndex();
                comando();
            default:
                break;
        }
    }

    //ComandoRepeticao → 'ENQTO' ExpressaoRelacional Comando;
    void comandoRepeticao() {
        if (listaTokens.get(index).tipo == tipoToken.PCEnqto) {
            System.out.println("[comandoRepeticao] lido: " + listaTokens.get(index).tipo + ", index: " + index);
            acrescentarIndex();
            expressaoRelacional();
            comando();
        } else {
            ERROSINTATICO("PcEnqto");
        }
    }

    //SubAlgoritmo → 'INI' ListaComandos 'FIM';
    void subAlgoritmo() {
        if (listaTokens.get(index).tipo == tipoToken.PCIni) {
            System.out.println("[subAlgoritmo] lido: " + listaTokens.get(index).tipo + ", index: " + index);
            acrescentarIndex();
            listaComandos();
            if (listaTokens.get(index).tipo == tipoToken.PCFim) {
                System.out.println("[subAlgoritmo] lido: " + listaTokens.get(index).tipo + ", index: " + index);
                acrescentarIndex();
            } else {
                ERROSINTATICO("PcFim");
            }
        } else {
            ERROSINTATICO("PcIni");
        }
    }

    void ERROSINTATICO(String tokenEsperado) {
        System.out.println("\n\nErro sintatico - Inesperado Token: " + listaTokens.get(index).tipo + "| Esperado token: " + tokenEsperado + "\n\n");
        System.exit(2);
    }

    void acrescentarIndex() {
        if (index < (listaTokens.size() - 1)) {
            index++;
        }
    }
}
