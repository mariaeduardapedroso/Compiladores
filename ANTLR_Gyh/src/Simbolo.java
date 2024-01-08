
public class Simbolo {
	
	public String nome;
	public int tipo;
	public String valor;
	
	public static final int REAL =0;
	public static final int INT =1;
	
	public Simbolo(String nome, String tipo, String valor) {
		this.nome = nome;
		this.valor = valor;
		if(tipo.equals("INT")) this.tipo = INT;
		else this.tipo = REAL;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return"[nome="+this.nome+"\t tipo="+this.tipo+"\t valor= "+this.valor+"]";
	}
	
	public String generateCode() {
		String str;
		if(this.tipo == REAL) {
			str="\n\tdouble "+this.nome+";\n";
		}
		else {
			str="\n\tint "+this.nome+";\n";
		}
		return str;
	}
	
	
}
