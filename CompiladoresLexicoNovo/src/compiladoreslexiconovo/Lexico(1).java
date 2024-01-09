package compiladoreslexiconovo;

public class Lexico {

	LeitorArquivo dataRead;
	char charRead;
	int charIntRead;
	int state;
	String tempString;
	int isReal;
	LeitorArquivo aux;

	public Lexico(String file_programa1gyh) {
		try {
			this.dataRead = new LeitorArquivo(file_programa1gyh);
		} catch (Exception e) {
			System.out.println("File not found;\n");
			throw e;
		}

//        this.charRead = null;
//        this.charIntRead = null;
		this.isReal = 0;
		this.state = 1;
		this.tempString = "";
	}

	public void verificadorLexico() {
		int termino = 0;
		while (termino != 1) {
			this.charIntRead = this.dataRead.nextIntCaracter();

			this.charRead = (char) this.charIntRead;
			
			//System.out.println("estado = " + state + ", caracterint = " + charIntRead);
			
			switch (state) {
				case 1:
					switch (charRead) {
						case ' ':
						case '\n':
							break;
						case ':':
							state = 70;
							aux = this.dataRead; //rollback on pointer
							break;
						case '#':
							state = 100;
							break;
						case 'D':
							state = 12;
							break;
						case 'P':
							state = 15;
							break;
						case 'I':
							state = 19;
							break;
						case 'L':
							state = 30;
							break;
						case 'S':
							state = 33;
							break;
						case 'E':
							state = 35;
							break;
						case '<':
							state = 2;
							break;
						case '=':
							state = 5;
							break;
						case '>':
							state = 7;
							break;
						case '!':
							state = 10;
							break;
						case 'F':
							state = 44;
							break;
						case '*':
							System.out.println("<OpAritMult, \"*\">");
							break;
						case '"':
							this.tempString = "";
							state = 4;
							break;
						case '/':
							System.out.println("<OpAritDiv, \"/\">");
							break;
						case 'R':
							state = 48;
							break;
						case '+':
							System.out.println("<OpAritSoma, \"+\">");
							break;
						case '-':
							System.out.println("<OpAritSub, \"-\">");
							break;
						case 'O':
							state = 11;
							break;
						case '(':
							System.out.println("<AbrePar, \"(\">");
							break;
						case ')':
							System.out.println("<FechaPar, \")\">");
							break;

						default:
							//letra primeiro
							if (this.charIntRead >= 97 && this.charIntRead <= 122) {
								this.tempString = "";
								this.tempString += this.charRead;
								state = 46;
							} //numero primeiro
							else if (this.charIntRead >= 48 && this.charIntRead <= 57) {
								isReal = 0;
								this.tempString = "";
								this.tempString += this.charRead;
								state = 47;
							} else {
								System.out.println("<Error, \"" + charRead + "\">    ");
							}
							break;
					}
					break;

				case 2:
					switch (charRead) {
						case '=':
							state = 3;
							break;
						default:
							System.out.println("<OpRelMenor, \"<\">");
							state = 1;
							break;
					}
					break;

				case 3:
					//<=
					System.out.println("<OpRelMenorigual, \"<=\">");
					state = 1;
					break;

				case 4:
					if (this.charRead == '"') {
						System.out.println("<Cadeia, \"" + this.tempString + "\">");
						state = 1;
						break;
					}

					this.tempString += this.charRead;
					break;

				case 5:
					if (charRead == '=') {
						System.out.println("<OpRelIgual, \"==\">");
					} else {
						System.out.println("<Error, \"=\">");
					}
					state = 1;
					break;

				case 6:
					break;

				case 7:
					switch (charRead) {
						case '=':
							state = 8;
							break;
						case '>':
							state = 9;
							break;
						default:
							System.out.println("<OpRelMaior, \">\">");
							state = 1;
							break;
					}
					break;

				case 8:
					System.out.println("<OpRelMaiorigual, \">=\">");
					state = 1;
					break;

				case 9:
					System.out.println("<OpRelMaior, \">>\">");
					state = 1;
					break;

				case 10:
					if (charRead == '=') {
						System.out.println("<OpRelDif, \"!=\">    ");
					} else {
						System.out.println("<Error, \"!\">    ");
					}
					state = 1;
					break;

				case 11:
					if (charRead == 'U') {
						System.out.println("<OPBoolOu, \"OU\">    ");
					} else {
						System.out.println("<Error, \"O\">    ");
					}
					state = 1;
					break;

				case 12:
					if (charRead == 'E') {
						state = 13;
					} else {
						System.out.println("<Error, \"D\">    ");
						state = 1;
					}
					break;

				case 13:
					if (charRead == 'C')
						state = 14;
					else {
						System.out.println("<Error, \"DE\">    ");
						state = 1;
					}
					break;

				case 14:
					System.out.println("<PCDec, \"DEC\">");
					state = 1;
					break;

				case 15:
					switch (charRead) {
						case 'R':
							state = 16;
							break;
						default:
							System.out.println("<Error, \"P\">    ");
							state = 1;
							break;
					}
					break;

				case 16:
					if (charRead == 'O') {
						state = 17;
					} else {
						System.out.println("<Error, \"PR\">    ");
					}
					break;

				case 17:
					if (charRead == 'G') {
						state = 18;
					} else {
						System.out.println("<Error, \"PRO\">    ");
					}
					break;

				case 18:
					System.out.println("<PCProg, \"PROG\">    ");
					state = 1;
					break;

				case 19:
					switch (charRead) {
						case 'N':
							state = 20;
							break;
						case 'M':
							state = 23;
							break;
						default:
							System.out.println("<Error, \"I\">");
					}
					break;

				case 20:
					switch (charRead) {
						case 'I':
							state = 21;
							break;
						case 'T':
							state = 22;
							break;
						default:
							System.out.println("<Error, \"IN\">    ");
							state = 1;
					}
					break;

				case 21:
					System.out.println("<PCIni, \"INI\">    ");
					state = 1;
					break;

				case 22:
					System.out.println("<PCint, \"INT\">    ");
					state = 1;
					break;

				case 23:
					if (charRead == 'P') {
						state = 24;
					} else {
						System.out.println("<Error, \"IM\">    ");
						state = 1;
					}
					break;

				case 24:
					if (charRead == 'R') {
						state = 25;
					} else {
						System.out.println("<Error, \"IMP\">    ");
						state = 1;
					}
					break;

				case 25:
					if (charRead == 'I') {
						state = 26;
					} else {
						System.out.println("<Error, \"IMPR\">    ");
						state = 1;
					}
					break;

				case 26:
					if (charRead == 'M') {
						state = 27;
					} else {
						System.out.println("<Error, \"IMPRI\">    ");
					}
					break;

				case 27:
					if (charRead == 'I') {
						state = 28;
					} else {
						System.out.println("<Error, \"IMPRIM\">    ");
					}
					break;

				case 28:
					if (charRead == 'R') {
						state = 29;
					} else {
						System.out.println("<Error, \"IMPRIMI\">    ");
					}
					break;

				case 29:
					System.out.println("<PCImprimir, \"IMPRIMIR\">    ");
					state = 1;
					break;

				case 30:
					if (charRead == 'E') {
						state = 31;
					} else {
						System.out.println("<Error, \"L\">    ");
					}
					break;

				case 31:
					if (charRead == 'R') {
						state = 32;
					} else {
						System.out.println("<Error, \"LE\">    ");
					}
					break;

				case 32:
					System.out.println("<PCLer, \"LER\">    ");
					state = 1;
					break;

				case 33:
					if (charRead == 'E') {
						state = 34;
					} else {
						System.out.println("<Error, \"S\">    ");
					}
					break;

				case 34:
					System.out.println("<PCSe, \"SE\">    ");
					state = 1;
					break;

				case 35:
					switch (charRead) {
						case 'N':
							state = 36;
							break;
						default:
							System.out.println("<OPBoolE, \"E\">    ");
							state = 1;
							break;
					}
					break;

				case 36:
					switch (charRead) {
						case 'Q':
							state = 37;
							break;
						case 'T':
							state = 40;
							break;
						default:
							System.out.println("<Error, \"EN\">    ");
							state = 1;
							break;
					}
					break;

				case 37:
					if (charRead == 'T') {
						state = 38;
					} else {
						System.out.println("<Error, \"ENQ\">    ");
						state = 1;
					}
					break;

				case 38:
					if (charRead == 'O') {
						state = 39;
					} else {
						System.out.println("<Error, \"ENQT\">    ");
						state = 1;
					}
					break;

				case 39:
					System.out.println("<PCEnqto, \"ENQTO\">    ");
					state = 1;
					break;

				case 40:
					if (charRead == 'A') {
						state = 41;
					} else {
						System.out.println("<Error, \"ENT\">    ");
						state = 1;
					}
					break;

				case 41:
					if (charRead == 'O') {
						state = 42;
					} else {
						System.out.println("<Error, \"ENTA\">    ");
					}
					break;

				case 42:
					System.out.println("<PCEntao, \"ENTAO\">    ");
					state = 1;
					break;

				case 70:
					//:
					if (charRead == '=') 
						System.out.println("<Atrib, \":=\">    ");
					else if (charRead != ' ')
						System.out.println("<Delim, \":\">    ");
					else
						System.out.println("<Error, \":\">    ");
					this.dataRead = aux;
					state = 1;
					break;

				case 44:
					if (charRead == 'I') {
						state = 45;
					} else {
						System.out.println("<Error, \"F\">    ");
						state = 1;
					}
					break;

				case 45:
					if (charRead == 'M') {
						System.out.println("<PCFim, \"FIM\">");
					} else {
						System.out.println("<Error, \"FI\">    ");
					}
					state = 1;
					break;

				case 46: //letras primeiro
					if (this.charRead == ' ' || this.charRead == '\n' || this.charRead == ':') {
						System.out.println("<Var, \"" + this.tempString + "\"> ");
						state = 1;
						if (this.charRead == ':')
							
						break;
					}

					this.tempString += this.charRead;
					break;

				case 47: //numeros primeiro
					if (this.charRead == ' ' || this.charRead == '\n' || this.charIntRead >= 58 || this.charIntRead <= 47) {
						if (isReal == 0) {
							System.out.println("<NumInt, \"" + this.tempString + "\">");
						} else {
							System.out.println("<NumReal, \"" + this.tempString + "\">");
						}
						state = 1;
						break;
					} // 1)

					if (this.charRead == '.') {
						isReal = 1;
					}
					this.tempString += this.charRead;
					break;

				case 48: //Real
					if (charRead == 'E') {
						state = 49;
					} else {
						System.out.println("<Error, \"R\">    ");
						state = 1;
					}
					break;

				case 49: //REal
					if (charRead == 'A') {
						state = 50;
					} else {
						System.out.println("<Error, \"RE\">    ");
						state = 1;
					}
					break;

				case 50:
					if (charRead == 'L') {
						System.out.println("<PCReal, \"REAL\">");
					} else {
						System.out.println("<Error, \"REA\">    ");
					}
					state = 1;
					break;
					
				case 100:
					if (charRead == '\n')
						state = 1;
					break;

				default:
					break;
			}
			
			if (charIntRead == -1)
				termino = 1;
		}
	}
}
