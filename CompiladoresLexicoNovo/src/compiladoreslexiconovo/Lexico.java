package compiladoreslexiconovo;

public class Lexico {
    LeitorArquivo dataRead;
    char charRead;
    int charIntRead;
    int  state;
    String tempString;
    
    public Lexico(String file_programa1gyh) {
        try{
            this.dataRead = new LeitorArquivo(file_programa1gyh);
        }
        catch(Exception e){
            System.out.println("File not found;\n");
            throw e;
        }
        
//        this.charRead = null;
//        this.charIntRead = null;
        this. state = 1;
        this.tempString = "";
    }
    
    public void verificadorLexico(){

        while((this.charIntRead = this.dataRead.nextIntCaracter()) != -1){
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
                        case '#':
                            while ((this.charRead = (char) this.dataRead.nextIntCaracter())!= '\n'){
                                
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
                        default:
                            //variavel
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
                    System.out.println("<OpRelMenorigual, \"<=\">");
                case 4:
                    //<
                    System.out.println("<OpRelMenor, \"<\">");
                case 5:
                    if (charRead == '=') {
                        state = 6;
                    } else {
                        System.out.println("<Error, \"=>\"");
                    }
                    break;
                case 6:
                    //==
                    System.out.println("<OpRellgual, \"==>\"");
                case 7:
                    switch (charRead) {
                        case '=':
                            state = 8;
                        default:
                            state = 9;
                    }
                    break;
                case 8:
                    System.out.println("<OpRelMaiorigual, \">=\">");
                case 9:
                    System.out.println("<OpRelMaior, \">>\"");
                case 10:
                    if (charRead == '=') {
                        state = 11;
                    } else {
                        System.out.println("<Error, \"!\">");
                    }
                case 11:
                    System.out.println("<OpRelDif, \"!=\">");
                case 12:
                    if (charRead == 'E') {
                        state = 13;
                    } else {
                        System.out.println("<Error, \"D\">");
                    }
                    break;
                case 13:
                    if (charRead == 'C') {
                        state = 14;
                    } else {
                        System.out.println("<Error, \"DE\">");
                    }
                    break;
                case 14:
                    System.out.println("<PCDec, \"DEC\">");

                case 15:
                    if (charRead == 'R') {
                        state = 16;
                    } else {
                        System.out.println("<Error, \"P\">");
                    }
                    break;

                case 16:
                    if (charRead == 'O') {
                        state = 17;
                    } else {
                        System.out.println("<Error, \"PR\">");
                    }
                    break;

                case 17:
                    if (charRead == 'G') {
                        state = 18;
                    } else {
                        System.out.println("<Error, \"PRO\">");
                    }
                    break;

                case 18:
                    System.out.println("<PCProg, \"PROG\">");

                case 19:
                    switch (charRead) {
                        case 'N':
                            state = 20;
                            break;
                        case 'M':
                            state = 23;
                            break;
                        default:
                            System.out.println("<Error, \"I>\"");
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
                            System.out.println("<Error, \"IN\">");
                    }
                    break;

                case 21:
                    System.out.println("<PCIni, \"INI\">");

                case 22:
                    System.out.println("<PCint, \"INT\">");

                case 23:
                    if (charRead == 'P') {
                        state = 24;
                    } else {
                        System.out.println("<Error, \"IM\">");
                    }
                    break;

                case 24:
                    if (charRead == 'R') {
                        state = 25;
                    } else {
                        System.out.println("<Error, \"IMP\">");
                    }
                    break;

                case 25:
                    if (charRead == 'I') {
                        state = 26;
                    } else {
                        System.out.println("<Error, \"IMPR\">");
                    }
                    break;

                case 26:
                    if (charRead == 'M') {
                        state = 27;
                    } else {
                        System.out.println("<Error, \"IMPRI\">");
                    }
                    break;

                case 27:
                    if (charRead == 'I') {
                        state = 28;
                    } else {
                        System.out.println("<Error, \"IMPRIM\">");
                    }
                    break;

                case 28:
                    if (charRead == 'R') {
                        state = 29;
                    } else {
                        System.out.println("<Error, \"IMPRIMI\">");
                    }
                    break;

                case 29:
                    System.out.println("<PCImprimir, \"IMPRIMIR\">");

                case 30:
                    if (charRead == 'E') {
                        state = 31;
                    } else {
                        System.out.println("<Error, \"L\">");
                    }
                    break;

                case 31:
                    if (charRead == 'R') {
                        state = 32;
                    } else {
                        System.out.println("<Error, \"LE\">");
                    }
                    break;

                case 32:
                    System.out.println("<PCLer, \"LER\">");

                case 33:
                    if (charRead == 'E') {
                        state = 34;
                    } else {
                        System.out.println("<Error, \"S\">");
                    }
                    break;

                case 34:
                    System.out.println("<PCSe, \"SE\">");

                case 35:
                    if (charRead == 'N') {
                        state = 36;
                    } else {
                        System.out.println("<Error, \"E\">");
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
                            System.out.println("<Error, \"EN\">");
                    }
                    break;

                case 37:
                    if (charRead == 'T') {
                        state = 38;
                    } else {
                        System.out.println("<Error, \"ENQ\">");
                    }
                    break;

                case 38:
                    if (charRead == 'O') {
                        state = 39;
                    } else {
                        System.out.println("<Error, \"ENQT\">");
                    }
                    break;

                case 39:
                    System.out.println("<PCEnqto, \"ENQTO\">");

                case 40:
                    if (charRead == 'A') {
                        state = 41;
                    } else {
                        System.out.println("<Error, \"ENT\">");
                    }
                    break;

                case 41:
                    if (charRead == 'O') {
                        state = 42;
                    } else {
                        System.out.println("<Error, \"ENTA\">");
                    }
                    break;

                case 42:
                    System.out.println("<PCEntao, \"ENTAO\">");
                    
                default:

            }
        }
    }
}
