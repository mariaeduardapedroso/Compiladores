
public class Main {
	public static void main(String[] args) {
		LeitorArquivo ldat = new LeitorArquivo(args[0]);
		int c;
		
		System.out.println("teste: " + args[0]);
		
		while((c=ldat.lerProxCaractere())!=-1) {
			System.out.print((char) c);
		}
	}
}
