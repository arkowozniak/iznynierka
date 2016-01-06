package anonimizacja;

import anonimizacja.enums.FirstTokenType;
import anonimizacja.enums.TokenType;
import anonimizacja.enums.functionToAnonimize;
import org.languagetool.JLanguageTool;
import org.languagetool.language.Polish;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Anonimizacja {
    public static void main(String[] args) throws IOException {
        //JLanguageTool languageTool = new JLanguageTool(new Polish());
        String everything;
        ArrayList<FirstToken> firstTokens;

        Scanner imionaTxt = scanFile("textFiles/slowniki/imiona.txt");
        Scanner miejscowosciTxt = scanFile("textFiles/slowniki/miejscowosc.txt");
        Scanner nazwiskaTxt = scanFile("textFiles/slowniki/nazwiska.txt");

        HashMap<Integer, String> imiona = new HashMap<Integer, String>();
        HashMap<Integer, String> miejscowosci = new HashMap<Integer, String>();
        HashMap<Integer, String> nazwiska = new HashMap<Integer, String>();

        System.out.println(imionaTxt.toString());
        int i =0;
        while(imionaTxt.hasNext()){
            imiona.put(i, imionaTxt.next());
            i++;
        }
         i =0;
        while(miejscowosciTxt.hasNext()){
            miejscowosci.put(i, miejscowosciTxt.next());
            i++;
        }
        i =0;
        while(nazwiskaTxt.hasNext()){
            nazwiska.put(i, nazwiskaTxt.next());
            i++;
        }



        System.out.println(nazwiska.get(24));


        try (BufferedReader br = new BufferedReader(new FileReader("textFiles/textIn"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            everything = sb.toString();
        }


        char[] letters = everything.toCharArray();
        firstTokens = readFirstTokens(letters);
//        for (FirstToken element : firstTokens) {
//            System.out.print(element.getWord());
//        }


    }

    static ArrayList<FirstToken> readFirstTokens(char[] letters) {
        ArrayList<FirstToken> out = new ArrayList<>();

        Boolean changedWord = false;
        String word = "";

        for (int i = 0; i < letters.length; i++) {
            // System.out.println((int)letters[i]);
            if ((int) letters[i] >= 'A' && (int) letters[i] <= 'Ż' || (int) letters[i] >= 'a' && (int) letters[i] <= 'ż' || (int) letters[i] >= '0' && (int) letters[i] <= '9') {
                word = word + letters[i];
            } else {
                changedWord = true;
            }
            if (changedWord) {
                out.add(new FirstToken(word, "null", FirstTokenType.SLOWO_ZWYKLE));
                changedWord = false;
                word = "";
            }


            if ((int) letters[i] == '.') {
                out.add(new FirstToken(".", "null", FirstTokenType.KROPKA));
            }
            if ((int) letters[i] == ' ') {
                out.add(new FirstToken(" ", "null", FirstTokenType.SPACE));
            }
            if ((int) letters[i] == ',') {
                out.add(new FirstToken(",", "null", FirstTokenType.PRZECINEK));
            }
            if ((int) letters[i] == '-') {
                out.add(new FirstToken("-", "null", FirstTokenType.MYSLNIK));
            }
            if ((int) letters[i] == '?') {
                out.add(new FirstToken("?", "null", FirstTokenType.PYTAJNIK));
            }
            if ((int) letters[i] == '!') {
                out.add(new FirstToken("!", "null", FirstTokenType.WYKRZYKNIK));
            }
            if ((int) letters[i] == '\n') {
                out.add(new FirstToken("\n", "null", FirstTokenType.NASTEPNA_LINIA));
            }
            if ((int) letters[i] == '\\') {
                out.add(new FirstToken("\\", "null", FirstTokenType.L_UKOSNIK));
            }
            if ((int) letters[i] == '/') {
                out.add(new FirstToken("/", "null", FirstTokenType.P_UKOSNIK));
            }
            if ((int) letters[i] == ':') {
                out.add(new FirstToken(":", "null", FirstTokenType.DWUKROPEK));
            }
            if ((int) letters[i] == '(') {
                out.add(new FirstToken("(", "null", FirstTokenType.L_NAWIAS));
            }
            if ((int) letters[i] == ')') {
                out.add(new FirstToken(")", "null", FirstTokenType.P_NAWIAS));
            }


        }
        return out;
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
            while ((wartosc = in.nextToken()) != StreamTokenizer.TT_EOF) {
                if (wartosc == StreamTokenizer.TT_WORD)
                    list.add(i, new Token(in.sval, false, TokenType.slowo));
                else if (wartosc == StreamTokenizer.TT_NUMBER)
                    list.add(i, new Token(String.valueOf(in.nval), true, TokenType.slowo));
            }
        } catch (IOException e) {
            System.out.println("BŁĄD ODCZYTU Z PLIKU!");
            System.exit(2);
        }

        return list;
    }

    static ArrayList reverseList(ArrayList<Token> in) {
        ArrayList<Token> out = new ArrayList<Token>();
        for (int i = in.size() - 1; i >= 0; i--) {
            out.add(in.get(i));
        }
        return out;
    }

    static void saveToFile(ArrayList<Token> in) throws FileNotFoundException {
        PrintWriter textOut = fileToPrint("textFiles/textOut");


        for (Token element : in) {
            textOut.print(element.getWord() + " ");
            // System.out.print(element.getWord() + " ");
        }

        textOut.close();
    }

    static ArrayList tokenize(ArrayList<Token> in) {
        ArrayList<Token> out = new ArrayList<>();
        for (int i = 0; i < in.size(); i++) {
            out.add(new Token(in.get(i).getWord(), in.get(i).getToAnonimize(), in.get(i).getType()));
        }

        return out;
    }

    static ArrayList scannRules(BufferedReader in) throws IOException {
        ArrayList<Rule> rules = new ArrayList<>();
        //ZastapInicjalami(Imie Nazwisko),1)
        String currentLine;
        functionToAnonimize fnc = functionToAnonimize.ZastapInicjalami;
        Integer priority;

        while ((currentLine = in.readLine()) != null) {
            ArrayList<String> lside = new ArrayList<>();
            String[] split1 = currentLine.split("->");
            split1[0] = split1[0].substring(1);
            // argumenty wejsciowe
            String[] split1L = split1[0].split(" ");

            for (int i = 0; i < split1L.length; i++) {
                lside.add(split1L[i]);
            }
            // funkcja wykonywana
            String[] split1R = split1[1].split("\\(");
            //  System.out.println(split1R[0]);
            if (split1R[0].equals("ZastapInicjalami")) fnc = functionToAnonimize.ZastapInicjalami;
            else if (split1R.equals("ZastapKropkami")) fnc = functionToAnonimize.ZastapInicjalami.ZastapKropkami;
            // priorytet
            //  System.out.print(split1R[1]);
            int liczba = Integer.parseInt(split1R[1].split(",")[1].split("\\)")[0]);
            //  System.out.println(liczba);


            rules.add(new Rule(lside, fnc, liczba));

        }
        return rules;
    }

    static ArrayList anonimize(ArrayList<Token> in, ArrayList<Rule> rules) {
        ArrayList<Token> out = new ArrayList<>();
        ArrayList<Token> txt = new ArrayList<>();
        Boolean isAnonimizing = false;
        int i = 0;
        while (i < in.size()) {
            if (in.get(i).getToAnonimize() == false) {
                if (isAnonimizing == true)
                    out = compare(txt, rules, out);
                out.add(in.get(i));
                i++;
                isAnonimizing = false;
            } else if (in.get(i).getToAnonimize() == true) {
                isAnonimizing = true;
                txt.add(in.get(i));
                if (out.size() > 1)
                    out = compare(txt, rules, out);

                i++;
            }
        }


        return out;
    }

    static ArrayList<Token> compare(ArrayList<Token> txt, ArrayList<Rule> rules, ArrayList<Token> out) {
        ArrayList<Token> retur = new ArrayList<>();
        int i = 0;
        int numberofFitting = 0;
        int fitNumber = 999;
        while (i < txt.size()) {
            if (i == 0)
                if (txt.get(i).getWord() == rules.get(i).getInArgs().get(i).toString()) {
                    numberofFitting++;
                    fitNumber = 0;
                }


        }

        if (numberofFitting == 1) {
            System.out.println("anonimizuje: " + fitNumber);
        }

        return out;
    }

    static void doTheAnonimize() {

    }


}
