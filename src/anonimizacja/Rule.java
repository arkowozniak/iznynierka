package anonimizacja;


import anonimizacja.enums.TokenType;
import anonimizacja.enums.functionToAnonimize;

import java.util.ArrayList;

class Rule {

    private ArrayList<String> in;
    private TokenType ruleType;
    private functionToAnonimize func;

    Rule(ArrayList in, TokenType ruleType, functionToAnonimize func) {
        this.in = in;
        this.ruleType = ruleType;
        this.func = func;
    }

    public ArrayList<String> getInArgs(){
        return this.in;
    }

    public TokenType getRuleType() {
        return this.ruleType;
    }

    public functionToAnonimize getFunc(){
        return this.func;
    }

}
