package com.bm.fuzzification;

import java.util.ArrayList;
import java.util.List;

import com.bm.rule.Clause;
import com.bm.rule.Rule;

public class Mamdani {

    private List<RuleResult> ruleResults = new ArrayList<RuleResult>();
    private List<Rule> rules = new ArrayList<Rule>();
    
    public List<RuleResult> getResults(){
    	return ruleResults;
    }
    
    public List<Rule> getRules(){
    	return rules;
    }
    
    public void addRule(Rule rule){
    	rules.add(rule);
    }

    public void run(){
        boolean ruleExit = false;
        for (Rule rule:rules) {
            List<Double> doubles = new ArrayList<Double>();
            for (Clause clause:rule.getClauses()) {
                double value = clause.getFuzzySet().getDegree(clause.getString());
                doubles.add(value);
                if(value <= 0){
                    ruleExit = true;
                    break;
                }
            }
            if(ruleExit){
                ruleExit = false;
            }else{
            	ruleResults.add(new RuleResult(min(doubles),rule));
            }
        }
    }

    private double min(List<Double> doubles){
        double min = Double.MAX_VALUE;
        for (Double aDouble:doubles) {
            if(min>aDouble){
                min = aDouble;
            }
        }
        return min;
    }

    @SuppressWarnings("unused")
	private void deleteSimilarities() {
    	List<RuleResult> copy = new ArrayList<RuleResult>();
    	for (int i = 0; i < ruleResults.size(); i++) {
    		boolean add = false;
    		for (int j = i; j < ruleResults.size(); j++) {
    			if(i != j && ruleResults.get(i).getMin() == ruleResults.get(j).getMin() &&  ruleResults.get(i).getRule().getClause().getString().equals(ruleResults.get(j).getRule().getClause().getString())) {
    				add = false;
    				break;
    			}else {
    				add = true;
    			}
    		}
    		if(add) {
    			copy.add(ruleResults.get(i));
    		}
		}
    	ruleResults = copy;
    }
}
