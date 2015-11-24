package anonimizacja;

import anonimizacja.enums.TokenType;
import anonimizacja.enums.functionToAnonimize;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Anonimizacja {
    public static void main(String[] args) throws  IOException{

        BufferedReader  rules = new BufferedReader(new FileReader("textFiles/rules"));

        StreamTokenizer textIn = new StreamTokenizer(new FileReader("textFiles/testImiona"));

        ArrayList<Token> list = new ArrayList<>();

        ArrayList<Rule> rule;

    //    list=toArrayList(textIn); // DONE

    //    list=reverseList(list); //DONE

        rule=scannRules(rules); //TO DO 1


        for(int i =0;i<100;i++) {
            list.add(new Token("slowo", false, TokenType.slowo));
            if (i == 10) {
                list.add(new Token("Arkadiusz", true, TokenType.Imie));
                list.add(new Token("Wozniak", true, TokenType.Nazwisko));
            }
            if (i == 25) {
                list.add(new Token("Arkadiusz", true, TokenType.Imie));
                list.add(new Token("Arkadiusz", true, TokenType.Imie));
                list.add(new Token("Wozniak", true, TokenType.Nazwisko));
                //list.add(new Token("Wozniak", true, TokenType.Nazwisko));
            }
            if (i == 40){
                list.add(new Token("Arkadiusz", true, TokenType.Imie));
                list.add(new Token("Arkadiusz", true, TokenType.Imie));
                list.add(new Token("Wozniak-Koczwara", true, TokenType.Nazwisko_Nazwisko));
            }

            if(i==70) {
                list.add(new Token("Arkadiusz", true, TokenType.Imie));
                list.add(new Token("Wozniak-Koczwara", true, TokenType.Nazwisko_Nazwisko));
            }
        }

//        for(Rule element: rule){
//            String a = "";
//            for(int i=0;i<element.getInArgs().size();i++){
//                a=a+", "+element.getInArgs().get(i);
//            }
//            System.out.println("In args:"+ a+", Function to anonimize: "+element.getfunctionToAnonimize()+", Priority: "+element.getPriority());
//        }

        //list=tokenize(list); //TO DO 3

       list = anonimize(list, rule ); //To Do 2

    //    saveToFile(list); //DONE



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
                    list.add(i, new Token(in.sval,false, TokenType.slowo));
                else if(wartosc == StreamTokenizer.TT_NUMBER)
                    list.add(i, new Token(String.valueOf(in.nval),true,TokenType.slowo));
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
        PrintWriter textOut = fileToPrint("textFiles/textOut");


        for(Token element: in){
        //    textOut.print(element.getWord()+" ");
        System.out.print(element.getWord()+" ");
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

    static ArrayList scannRules(BufferedReader in) throws IOException{
        ArrayList<Rule> rules = new ArrayList<>();
        //ZastapInicjalami(Imie Nazwisko),1)
        String currentLine;
        functionToAnonimize fnc = functionToAnonimize.ZastapInicjalami;
        Integer priority;

        while((currentLine=in.readLine())!=null){
            ArrayList<String> lside = new ArrayList<>();
           String[] split1 = currentLine.split("->");
           split1[0]=split1[0].substring(1);
           // argumenty wejsciowe
            String[] split1L=split1[0].split(" ");

            for(int i=0;  i<split1L.length;i++){
                lside.add(split1L[i]);
            }
            // funkcja wykonywana
            String[] split1R = split1[1].split("\\(");
          //  System.out.println(split1R[0]);
            if(split1R[0].equals("ZastapInicjalami")) fnc=functionToAnonimize.ZastapInicjalami;
            else if(split1R.equals("ZastapKropkami")) fnc = functionToAnonimize.ZastapInicjalami.ZastapKropkami;
            // priorytet
         //  System.out.print(split1R[1]);
            int liczba = Integer.parseInt(split1R[1].split(",")[1].split("\\)")[0]);
      //  System.out.println(liczba);


        rules.add(new Rule(lside, fnc, liczba));

        }
        return rules;
    }

    static ArrayList anonimize(ArrayList<Token> in, ArrayList<Rule> rules){
        ArrayList<Token> out = new ArrayList<>();
        int i = 0;
        while(i<in.size()){
            if(in.get(i).getToAnonimize()==false)
                i++;
            else{
                System.out.println(in.get(i).getWord());
                i++;
            }


        }




        return out;
    }




}
