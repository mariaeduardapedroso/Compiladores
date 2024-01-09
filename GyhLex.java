import java.util.ArrayList;

public class GyhLex {
	
	public GyhLex() {
		
	}

  private int linhas = 0; //contador das linhas
	
	ArrayList<Token> listaToken = new ArrayList<Token>(); //lista de tokens
	
	ArrayList<String> listaLinha = new ArrayList<String>(); //linhas presentes no arquivo

  ArrayList<Token> Erro = new ArrayList<Token>();
  
	public ArrayList<Token> analisarLexico(String nomePrograma) {

    System.out.println("========== Analise Lexica ==========\n");
    
		String palavra = ""; //String que receberá a palavra
		String lerLinha = ""; //String que receberá a linha a ser lida
		
		LeitorArquivo ler = new LeitorArquivo(); //objeto da classe leitor arquivo
		
		this.listaLinha = ler.ler(nomePrograma); //le o programa .gyh e armazena na lista de linhas
		
		for(int z = 0; z<this.listaLinha.size(); z++) {
			this.linhas ++; // incrementa a linha
			lerLinha = this.listaLinha.get(z); // pega a linha do indice z
			for(int i = 0; i<lerLinha.length(); i++) { 
				if(lerLinha.charAt(i) >= 65 && lerLinha.charAt(i) <= 90) {
					while(i < lerLinha.length() && lerLinha.charAt(i) >= 65 && lerLinha.charAt(i) <= 90) { // verifica se os caracteres do programa estão dentro do intervalo de letras maiusculas da tabela ASCII
						palavra = palavra.concat(Character.toString(lerLinha.charAt(i))); //concatena os caracteres até formar a palavra reservada completa
            i++;
					}
					i--;
          //Confere as palavras reservadas que pertencentes a lingaguem e em caso afirmativo as adiciona na lista de tokens
					switch(palavra) {
					case "E":
						listaToken.add(new Token(TipoToken.OpBoolE, palavra, this.linhas));
						break; // palavra reservada E
					case "OU":
						listaToken.add(new Token(TipoToken.OpBoolOu, palavra, this.linhas));
						break; // palavra reservada OU
					case "DEC":
						listaToken.add(new Token(TipoToken.PCDec, palavra, this.linhas));
						break; // palavra reservada DEC
					case "PROG":
						listaToken.add(new Token(TipoToken.PCProg, palavra, this.linhas));
						break; // palavra reservada PROG
					case "INT":
						listaToken.add(new Token(TipoToken.PCInt, palavra, this.linhas));
						break; // palavra reservada INT
					case "REAL":
						listaToken.add(new Token(TipoToken.PCReal, palavra, this.linhas));
						break; // palavra reservada REAL
					case "LER":
						listaToken.add(new Token(TipoToken.PCLer, palavra, this.linhas));
						break; // palavra reservada LER
					case "IMPRIMIR":
						listaToken.add(new Token(TipoToken.PCImprimir, palavra, this.linhas));
						break; // palavra reservada IMPRIMIR
					case "SE":
						listaToken.add(new Token(TipoToken.PCSe, palavra, this.linhas));
						break; // palavra reservada SE
					case "ENTAO":
						listaToken.add(new Token(TipoToken.PCEntao, palavra, this.linhas));
						break; // palavra reservada ENTAO
					case "ENQTO":
						listaToken.add(new Token(TipoToken.PCEnqto, palavra, this.linhas));
						break; // palavra reservada ENQTO
					case "INI":
						listaToken.add(new Token(TipoToken.PCIni, palavra, this.linhas));
						break; // palavra reservada INI
					case "FIM":
						listaToken.add(new Token(TipoToken.PCFim, palavra, this.linhas));
						break; // palavra reservada FIM
					default:
            palavra = palavra.concat(Character.toString(lerLinha.charAt(i+1)));//concatena a palavra com o proximo indice, que é o incorreto e adiciona o erro na lista
						listaToken.add(new Token(TipoToken.ERROR, palavra+" não é palavra chave definida da linguagem", this.linhas));
						System.out.println(listaToken.get(listaToken.size()-1));
						return this.Erro; //Caso o programa não leia umas das palavras reservadas significa que não pertence a nossa linguagem
					}
					palavra = ""; //esvazia o conteudo de palavra para proxima leitura
				}else if(lerLinha.charAt(i) >= 48 && lerLinha.charAt(i) <= 57) {
					while(i < lerLinha.length() && ((lerLinha.charAt(i) >= 48 && lerLinha.charAt(i) <= 57 || lerLinha.charAt(i) == '.'))) { // verifica se os numeros estao dentro do intervalo definido pela tabela ASCII e se o numero é inteiro ou real
						palavra = palavra.concat(Character.toString(lerLinha.charAt(i)));//concatena todos o scaracteres numericos da linha lida e armazena em palavra
						i++;
					}
					i--;
					if(!palavra.contains(".")) {
						listaToken.add(new Token(TipoToken.NumInt, palavra, this.linhas));
						palavra = ""; //esvazia o conteudo de palavra para proxima leitura
					} else if(palavra.indexOf('.') == palavra.lastIndexOf('.')){
            listaToken.add(new Token(TipoToken.NumReal,palavra, this.linhas));
            palavra = "";//esvazia o conteudo de palavra para proxima leitura          
          }else{
						listaToken.add(new Token(TipoToken.ERROR, palavra+" não é número definido da linguagem", this.linhas)); //caso nao seja numero real ou inteiro retorna o token de erro
						return this.Erro;
					}
				}else if(lerLinha.charAt(i) >= 97 && lerLinha.charAt(i) <= 122) {
					while(i < lerLinha.length() && ((lerLinha.charAt(i) >= 97 && lerLinha.charAt(i) <= 122) || (lerLinha.charAt(i) >= 65 && lerLinha.charAt(i) <= 90) || (lerLinha.charAt(i) >= 48 && lerLinha.charAt(i) <= 57))) { //verifica se o caracetere pertence ao intervalo de simbolos da tabela ASCII
						palavra = palavra.concat(Character.toString(lerLinha.charAt(i)));
						i++;
					}
					i--;
					listaToken.add(new Token(TipoToken.Var, palavra, this.linhas)); //*
					palavra = "";
				}else {
          //Confere os caracteres reservados que pertencentes a lingaguem e em caso afirmativo adiciona na lista de tokens
					switch(lerLinha.charAt(i)) {
					case '+':
						listaToken.add(new Token(TipoToken.OpAritSoma, "+", this.linhas));
						break; //Caractere reservado +
					case '-':
						listaToken.add(new Token(TipoToken.OpAritSub, "-", this.linhas));
						break; //Caractere reservado -
					case '/':
						listaToken.add(new Token(TipoToken.OpAritDiv, "/", this.linhas));
						break; //Caractere reservado /
					case '*':
						listaToken.add(new Token(TipoToken.OpAritMult, "*", this.linhas));
						break; //Caractere reservado *
					case ':':
						if(i + 1 != lerLinha.length() && lerLinha.charAt(i+1) == '=') {
							i++;
							listaToken.add(new Token(TipoToken.Atrib, ":=", this.linhas));
						}else {
							listaToken.add(new Token(TipoToken.Delim, ":", this.linhas));
						}
						break; //Caractere reservado : e seus derivados
					case '>':
						if(lerLinha.charAt(i+1) == '=') {
							i++;
							listaToken.add(new Token(TipoToken.OpRelMaiorIgual, ">=", this.linhas));
						}else {
							listaToken.add(new Token(TipoToken.OpRelMaior, ">", this.linhas));
						}
						break; //Caractere reservado < e seus derivados
					case '<':
						if(lerLinha.charAt(i+1) == '=') {
							i++;
							listaToken.add(new Token(TipoToken.OpRelMenorIgual, "<=", this.linhas));
						}else {
							listaToken.add(new Token(TipoToken.OpRelMenorIgual, "<", this.linhas));
						}
						break; ///Caractere reservado = e seus derivados
					case '=':
						if(lerLinha.charAt(i+1) == '=') {
							i++;
							listaToken.add(new Token(TipoToken.OpRelIgual, "==", this.linhas));
						}else {
							listaToken.add(new Token(TipoToken.ERROR, "Operação Desconhecida: =", this.linhas));
              System.out.println(listaToken.get(listaToken.size()-1));
							return this.Erro;
						}
						break; //Caractere reservado ! e seus derivados
					case '!':
						if(lerLinha.charAt(i+1) == '=') {
							i++;
							listaToken.add(new Token(TipoToken.OpRelDif, "!=", this.linhas));
						}else {
							listaToken.add(new Token(TipoToken.ERROR, "Operação Desconhecida: !", this.linhas));
							System.out.println(listaToken.get(listaToken.size()-1));
							return this.Erro;
						}
						break; //Caractere reservado (
					case '(':
						listaToken.add(new Token(TipoToken.AbrePar, "(", this.linhas));
						break;
					case ')':
						listaToken.add(new Token(TipoToken.FechaPar, ")", this.linhas));
						break; //Caractere reservado )
          case '"':
              palavra = palavra.concat("\"");
              i++;
              while(i<lerLinha.length() && lerLinha.charAt(i) != '"'){
        palavra = palavra.concat(Character.toString(lerLinha.charAt(i)));
						i++;
					}
					palavra = palavra.concat("\"");
					listaToken.add(new Token(TipoToken.Cadeia, palavra, this.linhas));        
          break; // Barra de espaço
					case '\n':
						this.linhas++;
						break; // \n quebra de linha
					case ' ':
						break; // tab
          case '#':
            i++; 
            while(i < lerLinha.length()){
              palavra = palavra.concat(Character.toString(lerLinha.charAt(i)));
              i++;
            }
            //listaToken.add(new Token(TipoToken.Comentario, palavra, this.linhas)); 
            palavra = "";
            break; //Comentario
					default:
						listaToken.add(new Token(TipoToken.ERROR, " Caractere "+ Character.toString(lerLinha.charAt(i))+ " não reconhecido", this.linhas));	//Caso o programa não leia um dos caracteres reservados significa que não pertence a nossa linguagem
System.out.println(listaToken.get(listaToken.size()-1));
						return this.Erro;
					} //end Switch simbolos
				} //end else
			} //end For comprimento linha
		}//end For linhas
    //listaToken.add(new Token(TipoToken.EOF, "Final do arquivo", this.linhas)); //adiciona o token de final do arquivo
		System.out.println(this.listaToken + "\n\n"); // retorna a lista de tokens no terminal

		return this.listaToken;
	}
  public ArrayList<Token> getLista(){
    return listaToken;
  }
   
}