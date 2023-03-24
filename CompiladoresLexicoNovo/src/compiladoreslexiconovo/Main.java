package compiladoreslexiconovo;

public class Main {
    public static void main(String[] args) {
        Lexico lexico = new Lexico("arquivo.gyh");
        lexico.verificadorLexico();
    }
    
}
