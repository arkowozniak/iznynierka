package anonimizacja;

import anonimizacja.enums.FirstTokenType;

public class FirstToken {
    private String word;
    private String wersjaSlownikowa;
    private FirstTokenType firstTokenType;

    FirstToken(String word, String wersjaSlownikowa, FirstTokenType firstTokenType) {
        this.word = word;
        this.wersjaSlownikowa = wersjaSlownikowa;
        this.firstTokenType = firstTokenType;

    }

    public String getWord() {
        return this.word;
    }

    public String getWersjaSlownikowa(){
        return this.wersjaSlownikowa;
    }

    public FirstTokenType getFirstTokenType() {
        return this.firstTokenType;
    }
}
