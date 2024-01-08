
public class ComandoLeitura extends Comando{
	private String id;
	
	
	
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	@Override 
	public String generateCode() {
		//LER parametro
		String str;
		str="\n\tscanf(\"%d\", &"+this.id+");";   //scanf("%d", &nome);
		return str;
	}
}
