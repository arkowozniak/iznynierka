package anonimizacja;


import anonimizacja.enums.TokenType;
import anonimizacja.enums.functionToAnonimize;

import java.util.ArrayList;

class Rule {

    private ArrayList<String> in;
    //private TokenType ruleType;
    private functionToAnonimize func;
    private Integer priority;

    Rule(ArrayList in, functionToAnonimize func, Integer priority) {
        this.in = in;
      //  this.ruleType = ruleType;
        this.func = func;
        this.priority = priority;
    }

    public ArrayList<String> getInArgs(){
        return this.in;
    }

   // public TokenType getRuleType() {
   //     return this.ruleType;
// }
    public functionToAnonimize getfunctionToAnonimize (){
            return this.func;
    }
    public Integer getPriority(){
            return this.priority;
    }

}
