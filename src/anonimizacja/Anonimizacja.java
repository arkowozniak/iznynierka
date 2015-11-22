package anonimizacja;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Anonimizacja {
    public static void main(String[] args) throws FileNotFoundException {

        StreamTokenizer textIn = new StreamTokenizer(new FileReader("testImiona"));

        ArrayList<Token> list;

        list=toArrayList(textIn); // DONE

        list=reverseList(list); //DONE

        list=tokenize(list); //TO DO

       // list = anonimize(list); //To Do

        saveToFile(list); //DONE



    }

    static Scanner scanFile(String fileNemae) throws FileNotFoundException {
        return new Scanner(new File(fileNemae));

    }

    static PrintWriter fileToPrint(String fileName) throws FileNotFoundException {
        return new PrintWriter(fileName);
    }

    static ArrayList toArrayList(StreamTokenizer in) {
        ArrayList<Token> list = new ArrayList<Token>();
        int wartosc;
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

    static ArrayList reverseList(ArrayList<Token> in){
        ArrayList<Token> out= new ArrayList<Token>();
        for(int i=in.size()-1;i>=0;i--){
            out.add(in.get(i));
        }
        return out;
    }

    static void saveToFile(ArrayList<Token> in) throws FileNotFoundException{
        PrintWriter textOut = fileToPrint("textOut");


        for(Token element: in){
        //    textOut.print(element.getWord()+" ");
        System.out.println(element.getWord());
        }

        textOut.close();
    }

    static ArrayList tokenize(ArrayList<Token> in){
        ArrayList<Token> out = new ArrayList<>();
        for(int i=0;i<in.size();i++){
            out.add(new Token(in.get(i).getWord(),in.get(i).getToAnonimize(),in.get(i).getType()));
        }

        return out;
    }


}
