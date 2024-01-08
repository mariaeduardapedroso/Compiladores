import java.util.ArrayList;

public class ComandoRepeticao extends Comando{
	private String id;
	private String repet;
	private ArrayList<Comando> listRepet;
	
	public ComandoRepeticao(String newRepet, ArrayList<Comando> newListRepet) {
		this.repet = newRepet;
		this.listRepet = newListRepet;
	}
	
	@Override
	public String generateCode(){
		//while(motivo){
		String str = "\n\twhile(" + this.repet + "){\n";
		
		//   [dentro do while]
		for (Comando m : listRepet){
			str += m.generateCode();
		}
		
		//}
		str += "\n\t}\n";
		
		return str;	
	}
}