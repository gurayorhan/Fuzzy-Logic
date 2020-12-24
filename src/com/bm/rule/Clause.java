package com.bm.rule;

import com.bm.membership.FuzzySet;

public class Clause {

    private FuzzySet fuzzySet;
    private String string;

    public Clause(FuzzySet fuzzySet, String string) {
        this.fuzzySet = fuzzySet;
        this.string = string;
    }

    public FuzzySet getFuzzySet(){
        return fuzzySet;
    }

    public String getString() {
        return string;
    }
}
