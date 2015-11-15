package anonimizacja;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Anonimizacja {
    public static void main(String[] args) throws FileNotFoundException{
        Scanner textIn = scanFile("textIn");
        StreamTokenizer rules = new StreamTokenizer(new FileReader("textIn"));
        PrintWriter textOut = fileToPrint("textOut");
//        String text = new String();
//        while (textIn.hasNext()){
//            text=text+textIn.nextLine();
//        }
//        StringTokenizer st = new StringTokenizer(text);
//
//        while (st.hasMoreTokens()){
//            textOut.print(st.nextToken());
//        }
//        //textOut.println(text);
//        textOut.close();
        int wartosc = 0;
        try {
            while( (wartosc = rules.nextToken()) != StreamTokenizer.TT_EOF ){
                if(wartosc == StreamTokenizer.TT_WORD)
                    System.out.println("Wczytano słowo: "+ rules.sval);
                else if(wartosc == StreamTokenizer.TT_NUMBER)
                    System.out.println("Wczytano liczbę: "+ rules.nval);
            }
        } catch (IOException e) {
            System.out.println("BŁĄD ODCZYTU Z PLIKU!");
            System.exit(2);
        }


    }

    static Scanner scanFile(String fileNemae) throws FileNotFoundException{
        return new Scanner(new File(fileNemae));
    }

    static PrintWriter fileToPrint(String fileName) throws FileNotFoundException{
        return new PrintWriter(fileName);
    }

}
