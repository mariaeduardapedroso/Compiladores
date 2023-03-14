import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class LeitorArquivo {
	public InputStream is;
	
	public LeitorArquivo(String name) {
		try {
			is = new FileInputStream(name);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int lerProxCaractere() {
		int c=-1;
		try {
			c=is.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (c);
	}
}
