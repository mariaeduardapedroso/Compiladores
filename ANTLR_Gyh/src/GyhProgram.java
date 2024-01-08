import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GyhProgram {
	private TabelaSimbolo varTabela;
	private ArrayList<Comando> comando;

	public ArrayList<Comando> getComando() {
		return comando;
	}

	public void setComando(ArrayList<Comando> comando) {
		this.comando = comando;
	}

	public TabelaSimbolo getVarTabela() {
		return varTabela;
	}

	public void setVarTabela(TabelaSimbolo varTabela) {
		this.varTabela = varTabela;
	}
	
	public void generateTarget() {
		StringBuilder str= new StringBuilder();
		
		str.append("#include <stdio.h>\n");    //#include<stdio.h>
		str.append("#include <stdlib.h>\n");
		str.append("#include <stdbool.h>\n");
		str.append("#include <string.h>\n");
		str.append("\n\nint main(void){\n");  //int main(void){
		
		for(Simbolo symbol: varTabela.getAll()) {
			str.append(symbol.generateCode());
		}
		
		for(Comando cmd: comando) {
			str.append(cmd.generateCode());
		}
		
		
		str.append("\n\treturn 0;\n");           //return 0;}
		str.append("}\n");
		
		try {
			FileWriter file= new  FileWriter(new File("codigo.c"));
			file.write(str.toString());
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
