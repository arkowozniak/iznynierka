package anonimizacja;

import anonimizacja.enums.TokenType;

class Token {
    private String word;
    private Boolean toAnonimize;
    private TokenType type;

    Token(String word, Boolean toAnonimize, TokenType type) {
        this.word = word;
        this.toAnonimize = toAnonimize;
        this.type = type;
    }

    public String getWord() {
        return this.word;
    }

    public Boolean getToAnonimize() {
        return this.toAnonimize;
    }

    public TokenType getType() {
        return this.type;
    }
}
