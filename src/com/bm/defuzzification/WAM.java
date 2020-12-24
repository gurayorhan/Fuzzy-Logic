package com.bm.defuzzification;

import java.util.ArrayList;
import java.util.List;

import com.bm.fuzzification.Mamdani;
import com.bm.fuzzification.RuleResult;
import com.bm.membership.FuzzySet;

public class WAM {
	
	private double r;
	List<Result> list = new ArrayList<Result>();
	
	public double start(Mamdani engine,FuzzySet fuzzySet) {
		List<RuleResult> ruleResults = engine.getResults();
		
		System.out.println("---------------------------");
		for (RuleResult ruleResult : ruleResults) {
			double x = ruleResult.getRule().getClause().getFuzzySet().getValue(ruleResult.getRule().getClause().getString(),ruleResult.getMin());
			System.out.println("Min   : "+ruleResult.getMin());
			System.out.println("Aralýk: "+ruleResult.getRule().getClause().getString());
			System.out.println("Sonuc : "+x);
			System.out.println("Kural : "+ruleResult.getRule().getName());
			System.out.println("---------------------------");
            list.add(new Result(x,ruleResult.getMin()));
		}
		double lower = 0,top = 0;
		
		for (Result result : list) {
			top+=(result.getY()*result.getX());
			lower+=result.getY();
		}
		
		r = top/lower;
		
		System.out.println("WAM Sonucu: "+r);
		
		return r;
	}
	

}
