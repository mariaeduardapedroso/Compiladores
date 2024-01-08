public class ComandoAtribuicao extends Comando{
	private String id;
	private String atribuido;
	
	public ComandoAtribuicao(String newId, String newAtribuido){
		this.id = newId;
		this.atribuido = newAtribuido;
	}
	
	@Override
	public String generateCode(){
		return "\n\t" + this.id + " = " + this.atribuido + ";\n";
	}
	
	//getter setter
	
	public String getId(){
		return this.id;
	}
	
	public void setId(String newId){
		this.id = newId;
	}
}
