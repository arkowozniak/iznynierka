package anonimizacja;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Anonimizacja {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner rules = scanFile("rules");
        StreamTokenizer textIn = new StreamTokenizer(new FileReader("textIn"));


        ArrayList<Token> list;

        list=toArrayList(textIn);
        saveToFile(list);



    }

    static Scanner scanFile(String fileNemae) throws FileNotFoundException {
        return new Scanner(new File(fileNemae));

    }

    static PrintWriter fileToPrint(String fileName) throws FileNotFoundException {
        return new PrintWriter(fileName);
    }

    static ArrayList toArrayList(StreamTokenizer in) {
        ArrayList<Token> list = new ArrayList<Token>();
        int wartosc = 0;
        int i = 0;
        try {
            while( (wartosc = in.nextToken()) != StreamTokenizer.TT_EOF ){
                if(wartosc == StreamTokenizer.TT_WORD)
                    list.add(i, new Token(in.sval,false,TokenType.slowo));
                else if(wartosc == StreamTokenizer.TT_NUMBER)
                    list.add(i, new Token(String.valueOf(in.nval),true,TokenType.liczba));
            }
        } catch (IOException e) {
            System.out.println("BŁĄD ODCZYTU Z PLIKU!");
            System.exit(2);
        }


        return list;
    }
    static void saveToFile(ArrayList<Token> in) throws FileNotFoundException{
        PrintWriter textOut = fileToPrint("textOut");

        for(Token element: in){
            System.out.print(in.get(1));
        }

        textOut.close();
    }
}
