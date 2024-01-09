package compiladoreslexiconovo;

//INTEGRANTES:
// Vitor Luis de Queiroz Batista - 2104679
// Maria Eduarda Pedroso - 2150336

public class Main {
    public static void main(String[] args) {
        Lexico lexico = new Lexico("arquivo.gyh");
        lexico.verificadorLexico();
    }
}
