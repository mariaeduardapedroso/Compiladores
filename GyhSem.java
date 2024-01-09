import java.util.ArrayList;

class GyhSem {

    public GyhSem() {

    }

    // inicia a função do analisador semantico
    public void Semantico(ArrayList<String> VarUsada, ArrayList<String> VarDec, ArrayList<Token> TipoDec,
            ArrayList<Token> TipoCheck) {

        System.out.println("========== Analise Semântica ==========\n");

        ArrayList<String> VarDecCheck = new ArrayList<String>(VarDec); // lista para variaveis declaradas
        ArrayList<Token> TipoC = new ArrayList<Token>(TipoCheck); // lista para verificar tipos
        ArrayList<String> VarUsadaCheck = new ArrayList<String>(VarUsada);// lista de variaveis usadas
        ArrayList<Token> Linha = new ArrayList<Token>();// lista guarda a linha
        boolean key; // variavel booleano chave
        int aux = 0; // variavel auxiliar

      // Realiza as primeiras comparações
      // Se a variavel usada tiver sido declarada ela é removida da lista
        for (int i = 0; i < VarDec.size(); i++) {
            key = false;
            while (aux < VarUsadaCheck.size()) {
                if (VarUsadaCheck.get(aux).equals(VarDecCheck.get(0))) {
                    key = true;
                    VarUsadaCheck.remove(VarUsadaCheck.get(aux));
                } else {
                    aux++;
                }
            }
            aux = 0;
          // Se key for verdade, remove da lista de variaveis declaradas
            if (key) {
                VarDecCheck.remove(VarDecCheck.get(0));
            }
        }
        // Verifica se a variavel foi declarada e nao utilizada, retornando um warning no console
        if (!VarDecCheck.isEmpty()) {
            for (String i : VarDecCheck) {
                System.out.println("ERRO: variavel " + i + " declarada e nao utilizada!");
            }
        }
        // Verifica se a variavel foi usada sem ser declarada, retornando erro e encerrando o programa
        if (!VarUsadaCheck.isEmpty()) {
            for (String i : VarUsadaCheck) {
                System.out.println("ERRO: variavel " + i + " nao declarada!");
                System.exit(1);
            }
        }

        int x = 0, cont = 0;
        int lin = TipoC.get(0).getLinha();
        // Verifica o tipo de cada variavel com a funcao auxiliar QualTipo()
        while (cont < TipoC.get(0).getLinha()) {
            while (TipoC.get(x).getLinha() == lin && cont < TipoC.size()) {
                Linha.add(TipoC.get(x));
                if ((x + 1) < TipoC.size()) {
                    x++;
                    cont++;
                } else {
                    cont++;
                }
            }

            if (QualTipo(VarDec, Linha, TipoDec)) {
                lin = TipoC.get(x).getLinha();
                int tam = Linha.size();
                for (int i = 0; i < tam; i++) {
                    Linha.remove(0);
                }

            } else {
                System.out.println("ERRO: Tipos diferentes para as variaveis da linha " + lin);
                System.exit(1);
            }

        }
        // Verifica o tamanho maximo para cada variavel com a funcao auxiliar MaxTam()
        if (MaxTam(TipoCheck)) {
            System.out.println("\u001B[35m");
            System.out.println("Semantica Validada!!!");// Mostra na tela que o programa foi validado
            System.out.println("\u001B[0m");
        }

    }

    public boolean QualTipo(ArrayList<String> VarDec, ArrayList<Token> Linha, ArrayList<Token> TipoDec) { // função que verifica qual tipo(int ou Real)
                                                                                                
        int cont = 0; // contador
        boolean ret = true; // variavel booleana
        ArrayList<String> t = new ArrayList<String>(); // lista t armazena o tipo
        for (int i = 0; i < Linha.size(); i++) {
            cont = 0;
            if (Linha.get(i).getTipo() == TipoToken.Var) {
                while (!(Linha.get(i).getNome().equals(VarDec.get(cont))) && cont < VarDec.size()) { //percorre a lista VarDec
                    cont++; //incremento do contador
                }
                t.add(TipoDec.get(cont).getNome());
            } else if (Linha.get(i).getTipo() == TipoToken.NumInt) {
                t.add("INT"); //condição para adicionar INT na lista
            } else if (Linha.get(i).getTipo() == TipoToken.NumReal) {
                t.add("REAL"); //condição para adicionar REAL na lista
            }
        }
        String aux = t.get(0); //variavel auxiliar recebe a primeira posição da lista
        t.remove(0); //remove a posição
        for (int i = 0; i < t.size(); i++) { //percorre a lista 
            if (!aux.equals(t.get(i))) {// condição que verifica posição por posição do auxilar com a lista t
                ret = false; //return se achar algo de errado
                break;
            }
        }
        return ret; //return da função
    }

    public boolean MaxTam(ArrayList<Token> TipoCheck) { 
        for (int i = 0; i < TipoCheck.size(); i++) {
            if (TipoCheck.get(i).getTipo() == TipoToken.NumInt) { //condição para inteiro
                double c = Double.parseDouble(TipoCheck.get(i).getNome());
                if (c < -2147483648 || c > 2147483647) { //intervalo para numero inteiro
                    System.out.println("ERRO: overflow na linha " + TipoCheck.get(i).getLinha());
                    System.exit(1);
                }
            } else if (TipoCheck.get(i).getTipo() == TipoToken.NumReal) {
                double c = Double.parseDouble(TipoCheck.get(i).getNome());
                if (c < Math.pow(10, -18) || c > Math.pow(10, 18)) { //intervalo para numero real
                    System.out.println("ERRO: overflow na linha " + TipoCheck.get(i).getLinha());
                    System.exit(1);
                }
            }
            if (TipoCheck.get(i).getNome().contains(".")) { //condição que define o numero de casas decimais
                String aux = TipoCheck.get(i).getNome();
                String auxx = aux;
                if (aux.length() > aux.indexOf('.') + 8) {
                    auxx = "";
                    for (int j = 0; j < aux.indexOf('.') + 9; j++) {
                        auxx = auxx.concat(Character.toString(aux.charAt(j)));
                    }
                }
                TipoCheck.get(i).setNome(auxx);

            }
            return true;
        }
        return false;
    }

}
