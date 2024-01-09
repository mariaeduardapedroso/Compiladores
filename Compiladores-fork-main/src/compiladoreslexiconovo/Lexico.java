package compiladoreslexiconovo;

public class Lexico {

    LeitorArquivo dataRead;
    char charRead;
    int charIntRead;
    int state;
    String tempString;

    public Lexico(String file_programa1gyh) {
        try {
            this.dataRead = new LeitorArquivo(file_programa1gyh);
        } catch (Exception e) {
            System.out.print("File not found;\n");
            throw e;
        }

//        this.charRead = null;
//        this.charIntRead = null;
        this.state = 1;
        this.tempString = "";
    }

    public void verificadorLexico() {

        while ((this.charIntRead = this.dataRead.nextIntCaracter()) != -1) {
            // charintread -> charread
            // verificaçao de  state
            // switch
            // TODAS
            // printa lexico <bagulhere, aeasd>
            //  state = 1

            this.charRead = (char) this.charIntRead;

            switch (state) {
                case 1:
                    switch (charRead) {
                        case ' ':
                        case '\n':
                            state = 1;
                            break;
                        case ':':
                            state = 43;
                            break;
                        case '#':
                            while ((this.charRead = (char) this.dataRead.nextIntCaracter()) != '\n') {
                                int a = 0;
                            }
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
                        default:
                            //letra primeiro
                            if (this.charIntRead >= 97 && this.charIntRead <= 122) {
                                System.out.print("<Var, \"");
                                System.out.print(this.charRead);
                                while ((this.charRead = (char) this.dataRead.nextIntCaracter()) != ' ') {
                                    if (this.charRead == '\n') {
                                        break;
                                    }
                                    System.out.print(this.charRead);
                                }
                                System.out.print("\">    ");
                            } //numero primeiro
                            else if (this.charIntRead >= 48 && this.charIntRead <= 57) {
                                int isReal = 0;
                                this.tempString = "";
                                while ((this.charRead = (char) this.dataRead.nextIntCaracter()) != ' ') {
                                    if (this.charRead == '\n') {
                                        break;
                                    }
                                    if (this.charRead == '.') {
                                        isReal = 1;
                                    }
                                    this.tempString = this.tempString + this.charRead;
                                }

                                if (isReal == 0) {
                                    System.out.print("<NumInt, \"" + this.tempString + "\">    ");
                                } else {
                                    System.out.print("<NumReal, \"" + this.tempString + "\">    ");
                                }
                            }
                            break;
                    }
                    break;

                case 2:
                    switch (charRead) {
                        case '=':
                            state = 3;
                        default:
                            state = 4;
                    }
                    break;

                case 3:
                    //<=
                    System.out.print("<OpRelMenorigual, \"<=\">    ");
                    state = 1;
                    break;

                case 4:
                    //<
                    System.out.print("<OpRelMenor, \"<\">    ");
                    state = 1;
                    break;

                case 5:
                    if (charRead == '=') {
                        state = 6;
                    } else {
                        System.out.print("<Error, \"=>\"");
                    }
                    break;

                case 6:
                    //==
                    System.out.print("<OpRellgual, \"==>\"");
                    state = 1;
                    break;

                case 7:
                    switch (charRead) {
                        case '=':
                            state = 8;
                        default:
                            state = 9;
                    }
                    break;

                case 8:
                    System.out.print("<OpRelMaiorigual, \">=\">    ");
                    state = 1;
                    break;

                case 9:
                    System.out.print("<OpRelMaior, \">>\"");
                    state = 1;
                    break;

                case 10:
                    if (charRead == '=') {
                        state = 11;
                    } else {
                        System.out.print("<Error, \"!\">    ");
                    }
                    break;

                case 11:
                    System.out.print("<OpRelDif, \"!=\">    ");
                    state = 1;
                    break;

                case 12:
                    if (charRead == 'E') {
                        state = 13;
                    } else {
                        System.out.print("<Error, \"D\">    ");
                    }
                    break;

                case 13:
                    if (charRead == 'C') {
                        state = 14;
                    } else {
                        System.out.print("<Error, \"DE\">    ");
                    }
                    break;

                case 14:
                    System.out.print("<PCDec, \"DEC\">    ");
                    state = 1;
                    break;

                case 15:
                    if (charRead == 'R') {
                        state = 16;
                    } else {
                        System.out.print("<Error, \"P\">    ");
                    }
                    break;

                case 16:
                    if (charRead == 'O') {
                        state = 17;
                    } else {
                        System.out.print("<Error, \"PR\">    ");
                    }
                    break;

                case 17:
                    if (charRead == 'G') {
                        state = 18;
                    } else {
                        System.out.print("<Error, \"PRO\">    ");
                    }
                    break;

                case 18:
                    System.out.print("<PCProg, \"PROG\">    ");
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
                            System.out.print("<Error, \"I>\"");
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
                            System.out.print("<Error, \"IN\">    ");
                    }
                    break;

                case 21:
                    System.out.print("<PCIni, \"INI\">    ");
                    state = 1;
                    break;

                case 22:
                    System.out.print("<PCint, \"INT\">    ");
                    state = 1;
                    break;

                case 23:
                    if (charRead == 'P') {
                        state = 24;
                    } else {
                        System.out.print("<Error, \"IM\">    ");
                    }
                    break;

                case 24:
                    if (charRead == 'R') {
                        state = 25;
                    } else {
                        System.out.print("<Error, \"IMP\">    ");
                    }
                    break;

                case 25:
                    if (charRead == 'I') {
                        state = 26;
                    } else {
                        System.out.print("<Error, \"IMPR\">    ");
                    }
                    break;

                case 26:
                    if (charRead == 'M') {
                        state = 27;
                    } else {
                        System.out.print("<Error, \"IMPRI\">    ");
                    }
                    break;

                case 27:
                    if (charRead == 'I') {
                        state = 28;
                    } else {
                        System.out.print("<Error, \"IMPRIM\">    ");
                    }
                    break;

                case 28:
                    if (charRead == 'R') {
                        state = 29;
                    } else {
                        System.out.print("<Error, \"IMPRIMI\">    ");
                    }
                    break;

                case 29:
                    System.out.print("<PCImprimir, \"IMPRIMIR\">    ");
                    state = 1;
                    break;

                case 30:
                    if (charRead == 'E') {
                        state = 31;
                    } else {
                        System.out.print("<Error, \"L\">    ");
                    }
                    break;

                case 31:
                    if (charRead == 'R') {
                        state = 32;
                    } else {
                        System.out.print("<Error, \"LE\">    ");
                    }
                    break;

                case 32:
                    System.out.print("<PCLer, \"LER\">    ");
                    state = 1;
                    break;

                case 33:
                    if (charRead == 'E') {
                        state = 34;
                    } else {
                        System.out.print("<Error, \"S\">    ");
                    }
                    break;

                case 34:
                    System.out.print("<PCSe, \"SE\">    ");
                    state = 1;
                    break;

                case 35:
                    if (charRead == 'N') {
                        state = 36;
                    } else {
                        System.out.print("<Error, \"E\">    ");
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
                            System.out.print("<Error, \"EN\">    ");
                    }
                    break;

                case 37:
                    if (charRead == 'T') {
                        state = 38;
                    } else {
                        System.out.print("<Error, \"ENQ\">    ");
                    }
                    break;

                case 38:
                    if (charRead == 'O') {
                        state = 39;
                    } else {
                        System.out.print("<Error, \"ENQT\">    ");
                    }
                    break;

                case 39:
                    System.out.print("<PCEnqto, \"ENQTO\">    ");
                    state = 1;
                    break;

                case 40:
                    if (charRead == 'A') {
                        state = 41;
                    } else {
                        System.out.print("<Error, \"ENT\">    ");
                    }
                    break;

                case 41:
                    if (charRead == 'O') {
                        state = 42;
                    } else {
                        System.out.print("<Error, \"ENTA\">    ");
                    }
                    break;

                case 42:
                    System.out.print("<PCEntao, \"ENTAO\">    ");
                    state = 1;
                    break;

                case 43:
                    LeitorArquivo aux = this.dataRead; //rollback on pointer

                    while ((this.charRead = (char) dataRead.nextIntCaracter()) != '\n') {
                        //:
                        if (charRead != '=') {
                            System.out.print("<Delim, \":\">    ");
                            this.dataRead = aux;
                            state = 1;
                            break;
                            
                        } //:=
                        else if (charRead == '=') {
                            System.out.print("<Atrib, \":=\">    ");
                            state = 1;
                            break;
                        } else {
                            System.out.print("<Error, \":\">    ");
                            this.dataRead = aux;
                            state = 1;
                            break;
                        }
                    }
                    break;

                case 44:
                    if (charRead == 'I') {
                        state = 45;
                        break;
                    } else {
                        System.out.print("<Error, \"F\">    ");
                    }
                    break;
                case 45:
                    if (charRead == 'M') {
                        System.out.print("<PCFim, \"FIM\">    ");
                        state = 1;
                        break;
                    } else {
                        System.out.print("<Error, \"FI\">    ");
                    }
                    break;

                default:
                    break;
            }
        }
    }
}
