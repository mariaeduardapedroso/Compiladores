import java.util.ArrayList;

public class ComandoCondicao extends Comando{
    private String id;
	private String cond;
	private ArrayList<Comando> listTrue;
	private ArrayList<Comando> listFalse;

	public ComandoCondicao(String newCondicao,
						   ArrayList<Comando> newListTrue,
						   ArrayList<Comando> newListFalse){
		this.cond = newCondicao;
		this.listTrue = newListTrue;
		this.listFalse = newListFalse;
	}
	
    @Override
    public String generateCode(){
		String str = "";
		
		//if (algo){
		str += "\n\tif (" + this.cond + "){\n";
		
		//    [dentro do if]
		for (Comando m : listTrue){
			str += m.generateCode();
		}
		
		//}
		str += "\n\t}\n";
		
		if (listFalse.isEmpty() == false){
			//else {
			str += "\telse {\n";
			
			//    [dentro do else]
			for (Comando m : listFalse){
				str += m.generateCode();
			}
			
			//}
			str += "\n\t}\n";
		}
		
        return str;
    }
    
    public String getId(){
        return id;
    }
    
    public void setId(String entra){
        this.id = entra;
    }
}