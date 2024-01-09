/* Classe no qual ocorre a criação dos Tokens para impressão
*/

public class Token {
	
	public String nome; //nome da variavel
	public TipoToken tok; //tipo do token
	private int l; //linha
	
  public Token() {
	}

  public Token(TipoToken tokenLido, String n, int lin) {
	  super();
	  this.tok = tokenLido;
	  this.nome = n;
	  this.l = lin;
  } //construtor 

  public String getNome(){
        return this.nome;
    }
  
  public void setNome(String n) {
	  this.nome = n;
  }
  
  public TipoToken getTipo(){
    return this.tok;
  }
  
  public int getLinha(){
    return this.l;
  }
  
  @Override
  public String toString() {
	  return "<" + nome + ", " + tok + ">";
  } //metodo toString para imprimir os tokens

}