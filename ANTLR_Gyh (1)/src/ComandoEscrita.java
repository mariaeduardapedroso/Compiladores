
public class ComandoEscrita extends Comando{
	private String id; //melhorar
	
	@Override
	public String generateCode() {		
		String str="\n\tprintf(\"%d\","+this.id+");"; 
		return str;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

}
