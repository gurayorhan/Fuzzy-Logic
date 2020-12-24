package com.bm.fuzzification;

import com.bm.rule.Rule;

public class RuleResult {

    private double min;
    private Rule rule;

    public RuleResult(double min, Rule rule) {
        this.min = min;
        this.rule = rule;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }
}
