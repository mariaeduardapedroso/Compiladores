import java.util.ArrayList;
import java.util.HashMap;

public class TabelaSimbolo {
		private HashMap<String, Simbolo> tabela;
		
		
		public TabelaSimbolo() {
			
			this.tabela = new HashMap<String, Simbolo>();
		}


		public HashMap<String, Simbolo> getTabela() {
			return tabela;
		}


		public void setTabela(HashMap<String, Simbolo> tabela) {
			this.tabela = tabela;
		}
		
		public void add(Simbolo simbolo) {
			this.tabela.put(simbolo.getNome(), simbolo);
		}
		
		public boolean exists(String nome) {
			return this.tabela.get(nome) != null;
		}
		
		public ArrayList<Simbolo> getAll(){
			ArrayList<Simbolo> list= new ArrayList<Simbolo>();
			for(Simbolo symbol: tabela.values()) {
				list.add(symbol);
			}
			return list;
		}
}
