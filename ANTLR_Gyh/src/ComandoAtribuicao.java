
public class ComandoAtribuicao extends Comando{	
	public String id;
	public String exp;
	
	
	public ComandoAtribuicao(String id, String exp) {
		this.id = id;
		this.exp = exp;
	}

	
	
	@Override
	public String generateCode() {
		return "\n\t"+id+" = "+exp+";";  //id := exp ; 
	}

}
