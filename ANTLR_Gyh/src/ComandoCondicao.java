import java.util.ArrayList;

public class ComandoCondicao extends Comando{
	public String condicao;
	public ArrayList<Comando> listTrue;
	public ArrayList<Comando> listFalse;
	public ComandoCondicao(String condicao, ArrayList<Comando> listTrue, ArrayList<Comando> listFalse) {
		this.condicao = condicao;
		this.listTrue = listTrue;
		this.listFalse = listFalse;
	}
	
	@Override
	public String generateCode(){
		String str="\n\t if( "+this.condicao+" ){\n";
		for(Comando cmd: listTrue) {
			str+=cmd.generateCode();
		}
		str+="\n\t }\n";
		
		if(listFalse.isEmpty()==false) {
			str+="\t else {\n";
			for(Comando cmd: listFalse) {
				str+=cmd.generateCode();
			}
			str+="\t }\n";
		}
		return str;
	}
}
