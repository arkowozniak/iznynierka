package anonimizacja;

public class Token {
     String word;
     Boolean toAnonimize;
     TokenType type;

    Token(String word, Boolean toAnonimize, TokenType type) {
        this.word = word;
        this.toAnonimize = toAnonimize;
        this.type = type;
    }

    public String getWord() {
        return word;
    }

    public Boolean getToAnonimize() {
        return toAnonimize;
    }

    public TokenType getType() {
        return type;
    }
}
