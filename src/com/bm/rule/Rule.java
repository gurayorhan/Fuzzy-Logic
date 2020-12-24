package com.bm.rule;

import java.util.ArrayList;
import java.util.List;

public class Rule {

    private String name;
    List<Clause> clauseList = new ArrayList<Clause>();
    private Clause clause;

    public String getName() {
        return name;
    }

    public Rule(String name) {
        this.name = name;
    }

    public List<Clause> getClauses(){
        return clauseList;
    }

    public Clause getClause(){
        return clause;
    }

    public void clauseAdd(Clause clause){
        clauseList.add(clause);
    }

    public void setClause(Clause clause){
        this.clause = clause;
    }

}
