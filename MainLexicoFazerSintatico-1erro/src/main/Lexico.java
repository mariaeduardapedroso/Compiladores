package main;

import java.util.ArrayList;
import java.util.List;

public class Lexico {
    LeitorArquivo dataRead;
    char charRead;
    int charIntRead;
	
    int state;
	int isReal;
	int lerProximo;
	
    String tempString;
	
	List<Token> listaTokens;

    public Lexico(String arquivoUtilizado) {
        try {
            this.dataRead = new LeitorArquivo(arquivoUtilizado);
		} catch (Exception e) {
            System.out.print("File not found;\n");
            throw e;
        }
		
		lerProximo = 0;
        this.state = 1;
    }

    public void verificadorLexico() {
		this.listaTokens = new ArrayList<>();
		
        int sair = 0;

        this.charIntRead = this.dataRead.nextIntCaracter();

        while (sair == 0) {
            if (lerProximo == 1)
                this.charIntRead = this.dataRead.nextIntCaracter();

			this.charRead = (char) this.charIntRead;

			this.maquinaDeEstados();
            
			if (this.charIntRead == -1) 
                sair = 1;
        }
    }
	
	public void printaListaTokens(){
		for (Token i : this.listaTokens){
			System.out.print("<" + i.tipo + ", \"" + i.lexema + "\">    ");
		}
	}
	
	public void maquinaDeEstados(){
		switch (state) {
			case 0: //comentários
				if (charRead == '\n')
					state = 1;
				lerProximo = 1;
				break;

			case 1: //ESTADO 1 inicial
				switch (charRead) {
					case ' ': case '\n':
						this.state = 1;
						lerProximo = 1;
						break;

					case ':':
						this.charIntRead = this.dataRead.nextIntCaracter();
						this.state = 43;
						lerProximo = 0;
						break;

					case '#':
						this.state = 0;
						lerProximo = 1;
						break;

					case 'D':
						this.state = 12;
						lerProximo = 1;
						break;

				case 'P':
						this.state = 15;
						lerProximo = 1;
						break;

					case 'I':
						this.state = 19;
						lerProximo = 1;
						break;

					case 'L':
						this.state = 30;
						lerProximo = 1;
						break;

					case 'S':
						this.state = 33;
						lerProximo = 1;
						break;

					case 'E':
						this.state = 35;
						lerProximo = 1;
						break;

					case '<':
						this.state = 2;
						lerProximo = 1;
						break;

					case '=':
						this.state = 5;
						lerProximo = 1;
						break;

					case '>':
						this.state = 7;
						lerProximo = 1;
						break;

					case '!':
						this.state = 10;
						lerProximo = 1;
						break;

					case 'F':
						this.state = 44;
						lerProximo = 1;
						break;

					case '*':
						this.listaTokens.add(new Token(tipoToken.OpAritMult, "*"));
						//System.out.print("<" + tipoToken.OpAritMult + ", \"*\"> ");
						lerProximo = 1;
						this.state = 1;
						break;

					case '"':
						this.tempString = "";
						this.state = 4;
						lerProximo = 1;
						break;

					case '/':
						this.listaTokens.add(new Token(tipoToken.OpAritDiv, "/"));
						// System.out.print("<" + tipoToken.OpAritDiv + ", \"/\"> ");
						this.state = 1;
						lerProximo = 1;
						break;

					case 'R':
						this.state = 48;
						lerProximo = 1;
						break;

					case '+':
						this.listaTokens.add(new Token(tipoToken.OpAritSoma, "+"));
						// System.out.print("<" + tipoToken.OpAritSoma +", \"+\"> ");
						lerProximo = 1;
						this.state = 1;
						break;

					case '-':
						this.listaTokens.add(new Token(tipoToken.OpAritSub, "-"));
						// System.out.print("<" + tipoToken.OpAritSub +", \"-\"> ");
						this.state = 1;
						lerProximo = 1;
						break;

					case 'O':
						this.state = 11;
						lerProximo = 1;
						break;

					case '(':
						this.listaTokens.add(new Token(tipoToken.AbrePar, "("));
						// System.out.print("<" + tipoToken.AbrePar +", \"(\"> ");
						lerProximo = 1;
						this.state = 1;
						break;

					case ')':
						this.listaTokens.add(new Token(tipoToken.FechaPar, ")"));
						// System.out.print("<" + tipoToken.FechaPar + ", \")\"> ");
						this.state = 1;
						lerProximo = 1;
						break;

					default:
						lerProximo = 1;
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
						}
						else if (charIntRead != 13)
							this.listaTokens.add(new Token(tipoToken.Error, "" + charRead));
							// System.out.print("<Error, \"" + charRead + "\"> ");
						break;
				}
				break;

			case 2:
				switch (charRead) {
					case '=':
						state = 3;
						break;

					default:
						this.listaTokens.add(new Token(tipoToken.OpRelMenor, "<"));
						// System.out.print("<" + tipoToken.OpRelMenor + ", \"<\"> ");
						state = 1;
						lerProximo = 1;
						break;
				}
				break;

			case 3:
				this.listaTokens.add(new Token(tipoToken.OpRelMenorigual, "<="));
				// System.out.print("<" + tipoToken.OpRelMenorigual + ", \"<=\"> ");
				lerProximo = 1;
				state = 1;
				break;

			case 4:
				if (this.charRead == '"') {
					this.listaTokens.add(new Token(tipoToken.Cadeia, this.tempString));
					// System.out.print("<" + tipoToken.Cadeia + ", \"" + this.tempString + "\"> ");
					lerProximo = 1;
				} else
					this.tempString = this.tempString + this.charRead;
				break;

			case 5:
				if (charRead == '='){
					this.listaTokens.add(new Token(tipoToken.OpRelIgual, "=="));
					// System.out.print("<" + tipoToken.OpRelIgual + ", \"==\"> ");
				}
				else {
					this.listaTokens.add(new Token(tipoToken.Error, "="));
					// System.out.print("<" + tipoToken.Error + ", \"=\"> ");
				}
				state = 1;
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
						this.listaTokens.add(new Token(tipoToken.OpRelMaior, ">"));
						// System.out.print("<" + tipoToken.OpRelMaior + ", \">\"> ");
						state = 1;
						break;
				}
				break;

			case 8:
				this.listaTokens.add(new Token(tipoToken.OpRelMaiorigual, ">="));
				// System.out.print("<" + tipoToken.OpRelMaiorigual + ", \">=\"> ");
				state = 1;
				break;

			case 9:
				//>> nao existe;
				this.listaTokens.add(new Token(tipoToken.OpRelMaior, ">>"));
				// System.out.print("<" + tipoToken.OpRelMaior + ", \">>\"> ");
				state = 1;
				break;

			case 10:
				if (charRead == '='){
					this.listaTokens.add(new Token(tipoToken.OpRelDif, "!="));
					// System.out.print("<" + tipoToken.OpRelDif + ", \"!=\"> ");
				}
				else {
					this.listaTokens.add(new Token(tipoToken.Error, "!"));
					// System.out.print("<" + tipoToken.Error + ", \"!\"> ");
				}
				state = 1;
				break;

			case 11:
				if (charRead == 'U'){
					this.listaTokens.add(new Token(tipoToken.OPBoolOu, "OU"));
					// System.out.print("<" + tipoToken.OPBoolOu + ", \"OU\"> ");
				}
				else {
					this.listaTokens.add(new Token(tipoToken.Error, "O"));
					// System.out.print("<" + tipoToken.Error + ", \"O\"> ");
				}
				state = 1;
				break;

			case 12:
				if (charRead == 'E')
					state = 13;
				else {
					this.listaTokens.add(new Token(tipoToken.Error, "D"));
					// System.out.print("<" + tipoToken.Error + ", \"D\"> ");
					state = 1;
				}
				break;

			case 13:
				if (charRead == 'C')
					state = 14;
				else {
					this.listaTokens.add(new Token(tipoToken.Error, "DE"));
					// System.out.print("<" + tipoToken.Error + ", \"DE\"> ");
					state = 1;
				}
				break;

			case 14:
				this.listaTokens.add(new Token(tipoToken.PCDec, "DEC"));
				// System.out.print("<" + tipoToken.PCDec + ", \"DEC\"> ");
				state = 1;
				break;

			case 15:
				switch (charRead) {
					case 'R':
						state = 16;
						break;
					default:
						this.listaTokens.add(new Token(tipoToken.Error, "P"));
						// System.out.print("<" + tipoToken.Error + ", \"P\"> ");
						state = 1;
						break;
				}
				break;

			case 16:
				if (charRead == 'O')
					state = 17;
				else {
					this.listaTokens.add(new Token(tipoToken.Error, "PR"));
					// System.out.print("<" + tipoToken.Error + ", \"PR\"> ");
					state = 1;
				}
				break;

			case 17:
				if (charRead == 'G')
					state = 18;
				else {
					this.listaTokens.add(new Token(tipoToken.Error, "PRO"));
					// System.out.print("<" + tipoToken.Error + ", \"PRO\"> ");
					state = 1;
				}
				break;

			case 18:
				this.listaTokens.add(new Token(tipoToken.PCProg, "PROG"));
				// System.out.print("<" + tipoToken.PCProg + ", \"PROG\"> ");
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
						this.listaTokens.add(new Token(tipoToken.Error, "I"));
						// System.out.print("<" + tipoToken.Error + ", \"I\"> ");
						state = 1;
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
						this.listaTokens.add(new Token(tipoToken.Error, "IN"));
						// System.out.print("<" + tipoToken.Error + ", \"IN\"> ");
						state = 1;
				}
				break;

			case 21:
				this.listaTokens.add(new Token(tipoToken.PCIni, "INI"));
				// System.out.print("<" + tipoToken.PCIni + ", \"INI\"> ");
				state = 1;
				break;

			case 22:
				this.listaTokens.add(new Token(tipoToken.PCint, "INT"));
				// System.out.print("<" + tipoToken.PCint + ", \"INT\"> ");
				state = 1;
				break;

			case 23:
				if (charRead == 'P')
					state = 24;
				else {
					this.listaTokens.add(new Token(tipoToken.Error, "IM"));
					// System.out.print("<" + tipoToken.Error + ", \"IM\"> ");
					state = 1;
				}
				break;

			case 24:
				if (charRead == 'R')
					state = 25;
				else {
					this.listaTokens.add(new Token(tipoToken.Error, "IMP"));
					//System.out.print("<" + tipoToken.Error + ", \"IMP\"> ");
					state = 1;
				}
				break;

			case 25:
				if (charRead == 'I')
					state = 26;
				else {
					this.listaTokens.add(new Token(tipoToken.Error, "IMPR"));
					// System.out.print("<" + tipoToken.Error + ", \"IMPR\"> ");
					state = 1;
				}
				break;

			case 26:
				if (charRead == 'M')
					state = 27;
				else {
					this.listaTokens.add(new Token(tipoToken.Error, "IMPRI"));
					// System.out.print("<" + tipoToken.Error + ", \"IMPRI\"> ");
					state = 1;
				}
				break;

			case 27:
				if (charRead == 'I')
					state = 28;
				else {
					this.listaTokens.add(new Token(tipoToken.Error, "IMPRIM"));
					// System.out.print("<" + tipoToken.Error + ", \"IMPRIM\"> ");
					state = 1;
				}
				break;

			case 28:
				if (charRead == 'R')
					state = 29;
				else {
					this.listaTokens.add(new Token(tipoToken.Error, "IMPRIMI"));
					// System.out.print("<" + tipoToken.Error + ", \"IMPRIMI\"> ");
					state = 1;
				}
				break;

			case 29:
				this.listaTokens.add(new Token(tipoToken.PCImprimir, "IMPRIMIR"));
				// System.out.print("<" + tipoToken.PCImprimir + ", \"IMPRIMIR\"> ");
				state = 1;
				break;

			case 30:
				if (charRead == 'E')
					state = 31;
				else {
					this.listaTokens.add(new Token(tipoToken.Error, "L"));
					// System.out.print("<" + tipoToken.Error + ", \"L\"> ");
					state = 1;
				}
				break;

			case 31:
				if (charRead == 'R')
					state = 32;
				else {
					this.listaTokens.add(new Token(tipoToken.Error, "LE"));
					// System.out.print("<" + tipoToken.Error + ", \"LE\"> ");
					state = 1;
				}
				break;

			case 32:
				this.listaTokens.add(new Token(tipoToken.PCLer, "LER"));
				// System.out.print("<" + tipoToken.PCLer + ", \"LER\"> ");
				state = 1;
				break;

			case 33:
				if (charRead == 'E')
					state = 34;
				else {
					this.listaTokens.add(new Token(tipoToken.Error, "S"));
					// System.out.print("<" + tipoToken.Error + ", \"S\"> ");
					state = 1;
				}
				break;

			case 34:
				this.listaTokens.add(new Token(tipoToken.PCSe, "SE"));
				// System.out.print("<" + tipoToken.PCSe + ", \"SE\"> ");
				state = 1;
				break;

			case 35:
				switch (charRead) {
					case 'N':
						state = 36;
						break;
					default:
						this.listaTokens.add(new Token(tipoToken.OPBoolE, "E"));
						// System.out.print("<" + tipoToken.OPBoolE + ", \"E\"> ");
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
						this.listaTokens.add(new Token(tipoToken.Error, "EN"));
						// System.out.print("<" + tipoToken.Error + ", \"EN\"> ");
						state = 1;
						break;
				}
				break;

			case 37:
				if (charRead == 'T')
					state = 38;
				else {
					this.listaTokens.add(new Token(tipoToken.Error, "ENQ"));
					// System.out.print("<" + tipoToken.Error + ", \"ENQ\"> ");
					state = 1;
				}
				break;

			case 38:
				if (charRead == 'O')
					state = 39;
				else {
					this.listaTokens.add(new Token(tipoToken.Error, "ENQT"));
					// System.out.print("<" + tipoToken.Error + ", \"ENQT\"> ");
					state = 1;
				}
				break;

			case 39:
				this.listaTokens.add(new Token(tipoToken.PCEnqto, "ENQTO"));
				// System.out.print("<" + tipoToken.PCEnqto + ", \"ENQTO\"> ");
				state = 1;
				break;

			case 40:
				if (charRead == 'A')
					state = 41;
				else {
					this.listaTokens.add(new Token(tipoToken.Error, "ENT"));
					// System.out.print("<" + tipoToken.Error + ", \"ENT\"> ");
					state = 1;
				}
				break;

			case 41:
				if (charRead == 'O')
					state = 42;
				else {
					this.listaTokens.add(new Token(tipoToken.Error, "ENTA"));
					// System.out.print("<" + tipoToken.Error + ", \"ENTA\"> ");
					state = 1;
				}
				break;

			case 42:
				this.listaTokens.add(new Token(tipoToken.PCEntao, "ENTAO"));
				// System.out.print("<" + tipoToken.PCEntao + ", \"ENTAO\"> ");
				state = 1;
				break;

			case 43:
				if (charRead == '=') {
					this.listaTokens.add(new Token(tipoToken.Atrib, ":="));
					// System.out.print("<" + tipoToken.Atrib + ", \":=\"> ");
					state = 1;
					lerProximo = 1;
				}
				else {
					state = 1;
					this.listaTokens.add(new Token(tipoToken.Delim, ":"));
					// System.out.print("<" + tipoToken.Delim + ", \":\"> ");
					lerProximo = 0;
				}
				break;

			case 44:
				if (charRead == 'I')
					state = 45;
				else {
					this.listaTokens.add(new Token(tipoToken.Error, "F"));
					// System.out.print("<" + tipoToken.Error + ", \"F\"> ");
					state = 1;
				}
				break;

			case 45:
				if (charRead == 'M'){
					this.listaTokens.add(new Token(tipoToken.PCFim, "FIM"));
					// System.out.print("<" + tipoToken.PCFim + ", \"FIM\"> ");
				}
				else {
					this.listaTokens.add(new Token(tipoToken.Error, "FI"));
					// System.out.print("<" + tipoToken.Error + ", \"FI\"> ");
				}
				state = 1;
				break;

			case 46: //letras primeiro
				if ((this.charIntRead >= 97 && this.charIntRead <= 122) || (this.charIntRead >= 48 && this.charIntRead <= 57)) { //letra Ok
					this.tempString = this.tempString + this.charRead;
					state = 46;
				} else {
					lerProximo = 0;
					this.listaTokens.add(new Token(tipoToken.Var, this.tempString));
					// System.out.print("<" + tipoToken.Var + ", \"" + this.tempString + " \"> ");
					state = 1;
				}
				break;

			case 47: //numeros primeiro
				if (this.charIntRead >= 48 && this.charIntRead <= 57) {
					this.tempString += this.charRead;
					state = 47;
				} else {
					if (this.charRead != '.') {
						lerProximo = 0;
						if (isReal == 0){
							this.listaTokens.add(new Token(tipoToken.NumInt, this.tempString));
							// System.out.print("<" + tipoToken.NumInt + ", \"" + this.tempString + "\"> ");
						}
						else {
							this.listaTokens.add(new Token(tipoToken.NumReal, this.tempString));
							// System.out.print("<" + tipoToken.NumReal + ", \"" + this.tempString + "\"> ");
						}
						state = 1;
					}
					else {
						isReal = 1;
						this.tempString += this.charRead;
					}
				}
				break;

			case 48: //Real
				if (charRead == 'E')
					state = 49;
				else {
					this.listaTokens.add(new Token(tipoToken.Error, "R"));
					// System.out.print("<" + tipoToken.Error + ", \"R\"> ");
					state = 1;
				}
				break;

			case 49: //REal
				if (charRead == 'A')
					state = 50;
				else {
					this.listaTokens.add(new Token(tipoToken.Error, "RE"));
					// System.out.print("<" + tipoToken.Error + ", \"RE\"> ");
					state = 1;
				}
				break;

			case 50:
				if (charRead == 'L'){
					this.listaTokens.add(new Token(tipoToken.PCReal, "REAL"));
					// System.out.print("<" + tipoToken.PCReal + ", \"REAL\"> ");
				}
				else {
					this.listaTokens.add(new Token(tipoToken.Error, "REA"));
					// System.out.print("<" + tipoToken.Error + ", \"REA\"> ");
				}
				state = 1;
				break;

			default:
				System.out.print("ERRO DE ESTADOS");
				break;
		}
	}
}