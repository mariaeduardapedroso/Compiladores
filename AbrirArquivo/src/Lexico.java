package projeto_1;

public class Lexico {
    LeitorArquivo ldat;
    LeitorArquivo buffer;

    public Lexico(String fileName) {
        ldat = new LeitorArquivo(fileName);
    }

    public Token proximoToken(){
        int c;
        int estado = 1;
        String aux = "";
        
        while ((c = ldat.proxCaractere()) != -1) {
            char caracter = (char) c;

            switch(caracter){
                case ' ': case '\n': case '\t':
                    estado = 43;
                    break;
                case '#':
//                    char caracterAux = (char) c;
//                    while(caracterAux != '\n'){
//                        c = ldat.proxCaractere
//                        
//                        
//                        
//                        caracterAux = (char) c;
//                    }
                    
                default:
                    if (estado != 45){
                        if (c >= 97 && c <= 122)
                            estado = 44; //letra
                    }
                    if (estado != 44){
                        if (c >= 48 && c <= 47)
                            estado = 45; //numero
                    }
                    
//                    // se for LETRA e receber NUMERO
//                    if (estado == 44 && (c >= 48 && c <= 47))
//                        
            }
            

            switch (estado) {
                case 1:
                    switch (caracter) {
                        case 'D':
                            estado = 12;
                            break;
                        case 'P':
                            estado = 15;
                            break;
                        case 'I':
                            estado = 19;
                            break;
                        case 'L':
                            estado = 30;
                            break;
                        case 'S':
                            estado = 33;
                            break;
                        case 'E':
                            estado = 35;
                            break;
                        case '<':
                            estado = 2;
                            break;
                        case '=':
                            estado = 5;
                            break;
                        case '>':
                            estado = 7;
                            break;
                        case '!':
                            estado = 10;
                            break;

                        default:
                        //variavel
                            break;
                    }
                    break;

                case 2:
                    switch (caracter) {
                        case '=':
                            estado = 3;
                        default:
                            estado = 4;
                    }
                    break;

                case 3:
                    //<=
                    return (new Token(TipoToken.OpRelMenorigual, "<="));

                case 4:
                    //<
                    return (new Token(TipoToken.OpRelMenor, "<"));

                case 5:
                    if (caracter == '=')
                        estado = 6;
                    else
                        return (new Token(TipoToken.Error, "="));
                    break;

                case 6:
                    //==
                    return (new Token(TipoToken.OpRellgual, "=="));

                case 7:
                    switch (caracter) {
                        case '=':
                            estado = 8;
                        default:
                            estado = 9;
                    }
                    break;

                case 8:
                    return (new Token(TipoToken.OpRelMaiorigual, ">="));

                case 9:
                    return (new Token(TipoToken.OpRelMaior, ">"));

                case 10:
                    if (caracter == '=')
                        estado = 11;
                    else
                        return (new Token(TipoToken.Error, "!"));

                case 11:
                    return (new Token(TipoToken.OpRelDif, "!="));

                case 12:
                    if (caracter == 'E')
                        estado = 13;
                    else 
                        return (new Token(TipoToken.Error, "D"));
                    break;

                case 13:
                    if (caracter == 'C')
                        estado = 14;
                    else 
                        return (new Token(TipoToken.Error, "DE"));
                    break;

                case 14:
                    return (new Token(TipoToken.PCDec, "DEC"));

                case 15:
                    if (caracter == 'R')
                        estado = 16;
                    else 
                        return (new Token(TipoToken.Error, "P"));
                    break;

                case 16:
                    if (caracter == 'O')
                        estado = 17;
                    else 
                        return (new Token(TipoToken.Error, "PR"));
                    break;

                case 17:
                    if (caracter == 'G')
                        estado = 18;
                    else 
                        return (new Token(TipoToken.Error, "PRO"));
                    break;

                case 18:
                    return (new Token(TipoToken.PCProg, "PROG"));

                case 19:
                    switch (caracter){
                        case 'N':
                            estado = 20;
                            break;
                        case 'M':
                            estado = 23;
                            break;
                        default:
                            return (new Token(TipoToken.Error, "I"));
                    }
                    break;

                case 20:
                    switch (caracter){
                        case 'I':
                            estado = 21;
                            break;
                        case 'T':
                            estado = 22;
                            break;
                        default:
                            return (new Token(TipoToken.Error, "IN"));
                    }
                    break;

                case 21:
                    return (new Token(TipoToken.PCIni, "INI"));

                case 22:
                    return (new Token(TipoToken.PCint, "INT"));

                case 23:
                    if (caracter == 'P')
                        estado = 24;
                    else 
                        return (new Token(TipoToken.Error, "IM"));
                    break;

                case 24:
                    if (caracter == 'R')
                        estado = 25;
                    else 
                        return (new Token(TipoToken.Error, "IMP"));
                    break;

                case 25:
                    if (caracter == 'I')
                        estado = 26;
                    else 
                        return (new Token(TipoToken.Error, "IMPR"));
                    break;

                case 26:
                    if (caracter == 'M')
                        estado = 27;
                    else 
                        return (new Token(TipoToken.Error, "IMPRI"));
                    break;

                case 27:
                    if (caracter == 'I')
                        estado = 28;
                    else 
                        return (new Token(TipoToken.Error, "IMPRIM"));
                    break;

                case 28:
                    if (caracter == 'R')
                        estado = 29;
                    else 
                        return (new Token(TipoToken.Error, "IMPRIMI"));
                    break;

                case 29:
                    return (new Token(TipoToken.PCImprimir, "IMPRIMIR"));

                case 30:
                    if (caracter == 'E')
                        estado = 31;
                    else 
                        return (new Token(TipoToken.Error, "L"));
                    break;

                case 31:
                    if (caracter == 'R')
                        estado = 32;
                    else 
                        return (new Token(TipoToken.Error, "LE"));
                    break;

                case 32:
                    return (new Token(TipoToken.PCLer, "LER"));

                case 33:
                    if (caracter == 'E')
                        estado = 34;
                    else 
                        return (new Token(TipoToken.Error, "S"));
                    break;

                case 34:
                    return (new Token(TipoToken.PCSe, "SE"));

                case 35:
                    if (caracter == 'N')
                        estado = 36;
                    else 
                        return (new Token(TipoToken.Error, "E"));
                    break;

                case 36:
                    switch (caracter){
                        case 'Q':
                            estado = 37;
                            break;
                        case 'T':
                            estado = 40;
                            break;
                        default:
                            return (new Token(TipoToken.Error, "EN"));
                    }
                    break;

                case 37:
                    if (caracter == 'T')
                        estado = 38;
                    else 
                        return (new Token(TipoToken.Error, "ENQ"));
                    break;

                case 38:
                    if (caracter == 'O')
                        estado = 39;
                    else 
                        return (new Token(TipoToken.Error, "ENQT"));
                    break;

                case 39:
                    return (new Token(TipoToken.PCEnqto, "ENQTO"));

                case 40:
                    if (caracter == 'A')
                        estado = 41;
                    else 
                        return (new Token(TipoToken.Error, "ENT"));
                    break;

                case 41:
                    if (caracter == 'O')
                        estado = 42;
                    else 
                        return (new Token(TipoToken.Error, "ENTA"));
                    break;

                case 42: 
                    return (new Token(TipoToken.PCEntao, "ENTAO"));
            
                case 44: //leitura nome variavel
                    aux = aux + caracter;
                    break;
                    
                case 45: //leitura numero int
                    aux = aux = caracter;
                    
                case 46: //leitura numero float
                    
                    
                default:
                    
            }
        }
        return null;
    }
}
