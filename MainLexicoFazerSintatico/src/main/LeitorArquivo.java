package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

class LeitorArquivo {
    public InputStream inputStreamRead;

    public LeitorArquivo(String name) {
        try {
            inputStreamRead = new FileInputStream(name);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public int nextIntCaracter() {
        int c=-1;
        try {
            c = inputStreamRead.read();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return (c);
    }
    
        public char nextCharCarater() {
        int c=-1;
        try {
            c = inputStreamRead.read();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return (char) c;
    }
}
