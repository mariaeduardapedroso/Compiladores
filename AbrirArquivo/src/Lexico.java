
public class Lexico {
	LeitorArquivo ldat;

	public Lexico(String fileName) {
		ldat = new LeitorArquivo(fileName);
	}

	public Token proximoToken() {
		int c;

		while ((c = ldat.lerProxCaractere()) != -1) {
			char caracter = (char) c;
			
			//op aritmetico
			switch (caracter) {
			case '-':
				return (new Token(TipoToken.OpAritSub,"-"));
				
			case '+':
				return (new Token(TipoToken.OpAritSoma,"+"));
				
			case '*':
				return (new Token(TipoToken.OpAritMult,"*"));
				
			case '/':
				return (new Token(TipoToken.OpAritDiv,"-"));
				
				
			case '<':
				/*
				 * int estado =0; switch (estado) { case 0: if (ldat.lerProxCaractere() == '=')
				 * { }
				 */
			
			case '>':
				return (new Token(TipoToken.OpAritSub,"-"));
				
			case ':':
				return (new Token(TipoToken.OpAritSub,"-"));
				
			default:
				int estado =1;
				switch(estado) {
				case 1:
					switch (caracter) {
					case 'D':
						estado = 12;
					case 'P':
						estado = 15;
					case 'I':
						estado = 19;
					case 'L':
						estado = 30;
					case 'S':
						estado = 33;
					case 'E':
						estado = 35;
					case '<':
						estado = 2;
					case '=':
						estado = 5;
					case '>':
						estado = 7;
					case '!':
						estado = 10;
						
					default:
						//variavel
					}
				case 2:
					switch (caracter) {
					case '=':
						estado =3;
					default:
						estado =4;
					}
				case 7:
					switch (caracter) {
					case '=':
						estado =8;
					default:
						estado =9;
					}
					
				}
			}
			
		}

		return null;
	}
}
