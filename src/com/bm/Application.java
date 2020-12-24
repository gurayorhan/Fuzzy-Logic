package com.bm;

import java.util.HashMap;
import java.util.Map;

import com.bm.fuzzification.Mamdani;
import com.bm.membership.*;
import com.bm.rule.Clause;
import com.bm.rule.Rule;

public class Application {

	@SuppressWarnings("serial")
	public final static Map<Integer, String> ALL = new HashMap<Integer, String>() {
		{
			put(0, "zayif");
			put(1, "orta");
			put(2, "iyi");
		}
	};

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// https://github.com/cschen1205/java-fuzzy-logic kaynaktan yararlanýlmýþtýr.
		MainFrame frame = new MainFrame(new Application());

	}

	public FuzzySet getSonuc() {
		FuzzySet sonuc = new FuzzySet("sonuc", 0, 100);
		sonuc.addGraphic("zayif", new FuzzyReverseGrade(20, 40, 0.01));
		sonuc.addGraphic("orta", new FuzzyTrapezoid(20, 40, 60, 80));
		sonuc.addGraphic("iyi", new FuzzyGrade(60, 80, 0.01));
		return sonuc;
	}

	public Mamdani getMamdani(double iletkenlikValue, double yagValue, double oksijenValue, double azotValue) {

		// Üyelik fonksiyonlarý
		FuzzySet sonuc = new FuzzySet("sonuc", 0, 100);
		sonuc.addGraphic("zayif", new FuzzyReverseGrade(20, 40, 0.01));
		sonuc.addGraphic("orta", new FuzzyTrapezoid(20, 40, 60, 80));
		sonuc.addGraphic("iyi", new FuzzyGrade(60, 80, 0.01));

		FuzzySet iletkenlik = new FuzzySet("iletkenlik", 0, 3500);
		iletkenlik.addGraphic("iyi", new FuzzyReverseGrade(1000, 2000));
		iletkenlik.addGraphic("orta", new FuzzyTrapezoid(1000, 2000, 2000, 3000));
		iletkenlik.addGraphic("zayif", new FuzzyGrade(2000, 3000));

		FuzzySet yag = new FuzzySet("yag", 0, 0.6);
		yag.addGraphic("iyi", new FuzzyReverseGrade(0.2, 0.35));
		yag.addGraphic("orta", new FuzzyTrapezoid(0.2, 0.35, 0.35, 0.5));
		yag.addGraphic("zayif", new FuzzyGrade(0.35, 0.5));

		FuzzySet oksijen = new FuzzySet("oksijen", 0, 10);
		oksijen.addGraphic("zayif", new FuzzyReverseGrade(3, 6));
		oksijen.addGraphic("orta", new FuzzyTrapezoid(3, 6, 6, 8));
		oksijen.addGraphic("iyi", new FuzzyGrade(6, 8));

		FuzzySet azot = new FuzzySet("azot", 0, 25);
		azot.addGraphic("iyi", new FuzzyReverseGrade(0, 12.5));
		azot.addGraphic("orta", new FuzzyTrapezoid(0, 12.5, 12.5, 25));
		azot.addGraphic("zayif", new FuzzyGrade(12.5, 25));

		Mamdani mamdani = new Mamdani();

		iletkenlik.setValue(iletkenlikValue);
		yag.setValue(yagValue);
		oksijen.setValue(oksijenValue);
		azot.setValue(azotValue);
		
		int ruleNumber = 0;

		Rule rule = new Rule("Kural "+ ++ruleNumber); // 1
		rule.clauseAdd(new Clause(oksijen, ALL.get(0)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(0)));
		rule.clauseAdd(new Clause(yag, ALL.get(0)));
		rule.clauseAdd(new Clause(azot, ALL.get(0)));
		rule.setClause(new Clause(sonuc, ALL.get(0)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 2
		rule.clauseAdd(new Clause(oksijen, ALL.get(0)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(0)));
		rule.clauseAdd(new Clause(yag, ALL.get(0)));
		rule.clauseAdd(new Clause(azot, ALL.get(1)));
		rule.setClause(new Clause(sonuc, ALL.get(0)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 3
		rule.clauseAdd(new Clause(oksijen, ALL.get(0)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(0)));
		rule.clauseAdd(new Clause(yag, ALL.get(0)));
		rule.clauseAdd(new Clause(azot, ALL.get(2)));
		rule.setClause(new Clause(sonuc, ALL.get(0)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 4
		rule.clauseAdd(new Clause(oksijen, ALL.get(0)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(0)));
		rule.clauseAdd(new Clause(yag, ALL.get(1)));
		rule.clauseAdd(new Clause(azot, ALL.get(0)));
		rule.setClause(new Clause(sonuc, ALL.get(0)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 5
		rule.clauseAdd(new Clause(oksijen, ALL.get(0)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(0)));
		rule.clauseAdd(new Clause(yag, ALL.get(1)));
		rule.clauseAdd(new Clause(azot, ALL.get(1)));
		rule.setClause(new Clause(sonuc, ALL.get(0)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 6
		rule.clauseAdd(new Clause(oksijen, ALL.get(0)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(0)));
		rule.clauseAdd(new Clause(yag, ALL.get(1)));
		rule.clauseAdd(new Clause(azot, ALL.get(2)));
		rule.setClause(new Clause(sonuc, ALL.get(0)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 7
		rule.clauseAdd(new Clause(oksijen, ALL.get(0)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(0)));
		rule.clauseAdd(new Clause(yag, ALL.get(2)));
		rule.clauseAdd(new Clause(azot, ALL.get(0)));
		rule.setClause(new Clause(sonuc, ALL.get(0)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 8
		rule.clauseAdd(new Clause(oksijen, ALL.get(0)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(0)));
		rule.clauseAdd(new Clause(yag, ALL.get(2)));
		rule.clauseAdd(new Clause(azot, ALL.get(1)));
		rule.setClause(new Clause(sonuc, ALL.get(0)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 9
		rule.clauseAdd(new Clause(oksijen, ALL.get(0)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(0)));
		rule.clauseAdd(new Clause(yag, ALL.get(2)));
		rule.clauseAdd(new Clause(azot, ALL.get(2)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 10
		rule.clauseAdd(new Clause(oksijen, ALL.get(0)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(1)));
		rule.clauseAdd(new Clause(yag, ALL.get(0)));
		rule.clauseAdd(new Clause(azot, ALL.get(0)));
		rule.setClause(new Clause(sonuc, ALL.get(0)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 11
		rule.clauseAdd(new Clause(oksijen, ALL.get(0)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(1)));
		rule.clauseAdd(new Clause(yag, ALL.get(0)));
		rule.clauseAdd(new Clause(azot, ALL.get(1)));
		rule.setClause(new Clause(sonuc, ALL.get(0)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 12
		rule.clauseAdd(new Clause(oksijen, ALL.get(0)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(1)));
		rule.clauseAdd(new Clause(yag, ALL.get(0)));
		rule.clauseAdd(new Clause(azot, ALL.get(2)));
		rule.setClause(new Clause(sonuc, ALL.get(0)));
		mamdani.addRule(rule);
	

		rule = new Rule("Kural "+ ++ruleNumber); // 13
		rule.clauseAdd(new Clause(oksijen, ALL.get(0)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(1)));
		rule.clauseAdd(new Clause(yag, ALL.get(1)));
		rule.clauseAdd(new Clause(azot, ALL.get(0)));
		rule.setClause(new Clause(sonuc, ALL.get(0)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 14
		rule.clauseAdd(new Clause(oksijen, ALL.get(0)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(1)));
		rule.clauseAdd(new Clause(yag, ALL.get(1)));
		rule.clauseAdd(new Clause(azot, ALL.get(1)));
		rule.setClause(new Clause(sonuc, ALL.get(0)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 15
		rule.clauseAdd(new Clause(oksijen, ALL.get(0)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(1)));
		rule.clauseAdd(new Clause(yag, ALL.get(1)));
		rule.clauseAdd(new Clause(azot, ALL.get(2)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 16
		rule.clauseAdd(new Clause(oksijen, ALL.get(0)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(1)));
		rule.clauseAdd(new Clause(yag, ALL.get(2)));
		rule.clauseAdd(new Clause(azot, ALL.get(0)));
		rule.setClause(new Clause(sonuc, ALL.get(0)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 17
		rule.clauseAdd(new Clause(oksijen, ALL.get(0)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(1)));
		rule.clauseAdd(new Clause(yag, ALL.get(2)));
		rule.clauseAdd(new Clause(azot, ALL.get(1)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 18
		rule.clauseAdd(new Clause(oksijen, ALL.get(0)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(1)));
		rule.clauseAdd(new Clause(yag, ALL.get(2)));
		rule.clauseAdd(new Clause(azot, ALL.get(2)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 19
		rule.clauseAdd(new Clause(oksijen, ALL.get(0)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(2)));
		rule.clauseAdd(new Clause(yag, ALL.get(0)));
		rule.clauseAdd(new Clause(azot, ALL.get(0)));
		rule.setClause(new Clause(sonuc, ALL.get(0)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 20
		rule.clauseAdd(new Clause(oksijen, ALL.get(0)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(2)));
		rule.clauseAdd(new Clause(yag, ALL.get(0)));
		rule.clauseAdd(new Clause(azot, ALL.get(1)));
		rule.setClause(new Clause(sonuc, ALL.get(0)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 21
		rule.clauseAdd(new Clause(oksijen, ALL.get(0)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(2)));
		rule.clauseAdd(new Clause(yag, ALL.get(0)));
		rule.clauseAdd(new Clause(azot, ALL.get(2)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 22
		rule.clauseAdd(new Clause(oksijen, ALL.get(0)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(2)));
		rule.clauseAdd(new Clause(yag, ALL.get(1)));
		rule.clauseAdd(new Clause(azot, ALL.get(0)));
		rule.setClause(new Clause(sonuc, ALL.get(0)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 23
		rule.clauseAdd(new Clause(oksijen, ALL.get(0)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(2)));
		rule.clauseAdd(new Clause(yag, ALL.get(1)));
		rule.clauseAdd(new Clause(azot, ALL.get(1)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 24
		rule.clauseAdd(new Clause(oksijen, ALL.get(0)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(2)));
		rule.clauseAdd(new Clause(yag, ALL.get(1)));
		rule.clauseAdd(new Clause(azot, ALL.get(2)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 25
		rule.clauseAdd(new Clause(oksijen, ALL.get(0)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(2)));
		rule.clauseAdd(new Clause(yag, ALL.get(2)));
		rule.clauseAdd(new Clause(azot, ALL.get(0)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 26
		rule.clauseAdd(new Clause(oksijen, ALL.get(0)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(2)));
		rule.clauseAdd(new Clause(yag, ALL.get(2)));
		rule.clauseAdd(new Clause(azot, ALL.get(1)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 27
		rule.clauseAdd(new Clause(oksijen, ALL.get(0)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(2)));
		rule.clauseAdd(new Clause(yag, ALL.get(2)));
		rule.clauseAdd(new Clause(azot, ALL.get(2)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 28
		rule.clauseAdd(new Clause(oksijen, ALL.get(1)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(0)));
		rule.clauseAdd(new Clause(yag, ALL.get(0)));
		rule.clauseAdd(new Clause(azot, ALL.get(0)));
		rule.setClause(new Clause(sonuc, ALL.get(0)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 29
		rule.clauseAdd(new Clause(oksijen, ALL.get(1)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(0)));
		rule.clauseAdd(new Clause(yag, ALL.get(0)));
		rule.clauseAdd(new Clause(azot, ALL.get(1)));
		rule.setClause(new Clause(sonuc, ALL.get(0)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 30
		rule.clauseAdd(new Clause(oksijen, ALL.get(1)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(0)));
		rule.clauseAdd(new Clause(yag, ALL.get(0)));
		rule.clauseAdd(new Clause(azot, ALL.get(2)));
		rule.setClause(new Clause(sonuc, ALL.get(0)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 31
		rule.clauseAdd(new Clause(oksijen, ALL.get(1)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(0)));
		rule.clauseAdd(new Clause(yag, ALL.get(1)));
		rule.clauseAdd(new Clause(azot, ALL.get(0)));
		rule.setClause(new Clause(sonuc, ALL.get(0)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 32
		rule.clauseAdd(new Clause(oksijen, ALL.get(1)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(0)));
		rule.clauseAdd(new Clause(yag, ALL.get(1)));
		rule.clauseAdd(new Clause(azot, ALL.get(1)));
		rule.setClause(new Clause(sonuc, ALL.get(0)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 33
		rule.clauseAdd(new Clause(oksijen, ALL.get(1)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(0)));
		rule.clauseAdd(new Clause(yag, ALL.get(1)));
		rule.clauseAdd(new Clause(azot, ALL.get(2)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 34
		rule.clauseAdd(new Clause(oksijen, ALL.get(1)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(0)));
		rule.clauseAdd(new Clause(yag, ALL.get(2)));
		rule.clauseAdd(new Clause(azot, ALL.get(0)));
		rule.setClause(new Clause(sonuc, ALL.get(0)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 35
		rule.clauseAdd(new Clause(oksijen, ALL.get(1)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(0)));
		rule.clauseAdd(new Clause(yag, ALL.get(2)));
		rule.clauseAdd(new Clause(azot, ALL.get(1)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 36
		rule.clauseAdd(new Clause(oksijen, ALL.get(1)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(0)));
		rule.clauseAdd(new Clause(yag, ALL.get(2)));
		rule.clauseAdd(new Clause(azot, ALL.get(2)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 37
		rule.clauseAdd(new Clause(oksijen, ALL.get(1)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(1)));
		rule.clauseAdd(new Clause(yag, ALL.get(0)));
		rule.clauseAdd(new Clause(azot, ALL.get(0)));
		rule.setClause(new Clause(sonuc, ALL.get(0)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 38
		rule.clauseAdd(new Clause(oksijen, ALL.get(1)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(1)));
		rule.clauseAdd(new Clause(yag, ALL.get(0)));
		rule.clauseAdd(new Clause(azot, ALL.get(1)));
		rule.setClause(new Clause(sonuc, ALL.get(0)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 39
		rule.clauseAdd(new Clause(oksijen, ALL.get(1)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(1)));
		rule.clauseAdd(new Clause(yag, ALL.get(0)));
		rule.clauseAdd(new Clause(azot, ALL.get(2)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 40
		rule.clauseAdd(new Clause(oksijen, ALL.get(1)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(1)));
		rule.clauseAdd(new Clause(yag, ALL.get(1)));
		rule.clauseAdd(new Clause(azot, ALL.get(0)));
		rule.setClause(new Clause(sonuc, ALL.get(0)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 41
		rule.clauseAdd(new Clause(oksijen, ALL.get(1)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(1)));
		rule.clauseAdd(new Clause(yag, ALL.get(1)));
		rule.clauseAdd(new Clause(azot, ALL.get(1)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 42
		rule.clauseAdd(new Clause(oksijen, ALL.get(1)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(1)));
		rule.clauseAdd(new Clause(yag, ALL.get(1)));
		rule.clauseAdd(new Clause(azot, ALL.get(2)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 43
		rule.clauseAdd(new Clause(oksijen, ALL.get(1)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(1)));
		rule.clauseAdd(new Clause(yag, ALL.get(2)));
		rule.clauseAdd(new Clause(azot, ALL.get(0)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 44
		rule.clauseAdd(new Clause(oksijen, ALL.get(1)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(1)));
		rule.clauseAdd(new Clause(yag, ALL.get(2)));
		rule.clauseAdd(new Clause(azot, ALL.get(1)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 45
		rule.clauseAdd(new Clause(oksijen, ALL.get(1)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(1)));
		rule.clauseAdd(new Clause(yag, ALL.get(2)));
		rule.clauseAdd(new Clause(azot, ALL.get(2)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 46
		rule.clauseAdd(new Clause(oksijen, ALL.get(1)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(2)));
		rule.clauseAdd(new Clause(yag, ALL.get(0)));
		rule.clauseAdd(new Clause(azot, ALL.get(0)));
		rule.setClause(new Clause(sonuc, ALL.get(0)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 47
		rule.clauseAdd(new Clause(oksijen, ALL.get(1)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(2)));
		rule.clauseAdd(new Clause(yag, ALL.get(0)));
		rule.clauseAdd(new Clause(azot, ALL.get(1)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 48
		rule.clauseAdd(new Clause(oksijen, ALL.get(1)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(2)));
		rule.clauseAdd(new Clause(yag, ALL.get(0)));
		rule.clauseAdd(new Clause(azot, ALL.get(2)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 49
		rule.clauseAdd(new Clause(oksijen, ALL.get(1)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(2)));
		rule.clauseAdd(new Clause(yag, ALL.get(1)));
		rule.clauseAdd(new Clause(azot, ALL.get(0)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 50
		rule.clauseAdd(new Clause(oksijen, ALL.get(1)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(2)));
		rule.clauseAdd(new Clause(yag, ALL.get(1)));
		rule.clauseAdd(new Clause(azot, ALL.get(1)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 51
		rule.clauseAdd(new Clause(oksijen, ALL.get(1)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(2)));
		rule.clauseAdd(new Clause(yag, ALL.get(1)));
		rule.clauseAdd(new Clause(azot, ALL.get(2)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 52
		rule.clauseAdd(new Clause(oksijen, ALL.get(1)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(2)));
		rule.clauseAdd(new Clause(yag, ALL.get(2)));
		rule.clauseAdd(new Clause(azot, ALL.get(0)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 53
		rule.clauseAdd(new Clause(oksijen, ALL.get(1)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(2)));
		rule.clauseAdd(new Clause(yag, ALL.get(2)));
		rule.clauseAdd(new Clause(azot, ALL.get(1)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 54
		rule.clauseAdd(new Clause(oksijen, ALL.get(1)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(2)));
		rule.clauseAdd(new Clause(yag, ALL.get(2)));
		rule.clauseAdd(new Clause(azot, ALL.get(2)));
		rule.setClause(new Clause(sonuc, ALL.get(2)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 55
		rule.clauseAdd(new Clause(oksijen, ALL.get(2)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(0)));
		rule.clauseAdd(new Clause(yag, ALL.get(0)));
		rule.clauseAdd(new Clause(azot, ALL.get(0)));
		rule.setClause(new Clause(sonuc, ALL.get(0)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 56
		rule.clauseAdd(new Clause(oksijen, ALL.get(2)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(0)));
		rule.clauseAdd(new Clause(yag, ALL.get(0)));
		rule.clauseAdd(new Clause(azot, ALL.get(1)));
		rule.setClause(new Clause(sonuc, ALL.get(0)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 57
		rule.clauseAdd(new Clause(oksijen, ALL.get(2)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(0)));
		rule.clauseAdd(new Clause(yag, ALL.get(0)));
		rule.clauseAdd(new Clause(azot, ALL.get(2)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 58
		rule.clauseAdd(new Clause(oksijen, ALL.get(2)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(0)));
		rule.clauseAdd(new Clause(yag, ALL.get(1)));
		rule.clauseAdd(new Clause(azot, ALL.get(0)));
		rule.setClause(new Clause(sonuc, ALL.get(0)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 59
		rule.clauseAdd(new Clause(oksijen, ALL.get(2)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(0)));
		rule.clauseAdd(new Clause(yag, ALL.get(1)));
		rule.clauseAdd(new Clause(azot, ALL.get(1)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 60
		rule.clauseAdd(new Clause(oksijen, ALL.get(2)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(0)));
		rule.clauseAdd(new Clause(yag, ALL.get(1)));
		rule.clauseAdd(new Clause(azot, ALL.get(2)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 61
		rule.clauseAdd(new Clause(oksijen, ALL.get(2)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(0)));
		rule.clauseAdd(new Clause(yag, ALL.get(2)));
		rule.clauseAdd(new Clause(azot, ALL.get(0)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 62
		rule.clauseAdd(new Clause(oksijen, ALL.get(2)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(0)));
		rule.clauseAdd(new Clause(yag, ALL.get(2)));
		rule.clauseAdd(new Clause(azot, ALL.get(1)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 63
		rule.clauseAdd(new Clause(oksijen, ALL.get(2)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(0)));
		rule.clauseAdd(new Clause(yag, ALL.get(2)));
		rule.clauseAdd(new Clause(azot, ALL.get(2)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 64
		rule.clauseAdd(new Clause(oksijen, ALL.get(2)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(1)));
		rule.clauseAdd(new Clause(yag, ALL.get(0)));
		rule.clauseAdd(new Clause(azot, ALL.get(0)));
		rule.setClause(new Clause(sonuc, ALL.get(0)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 65
		rule.clauseAdd(new Clause(oksijen, ALL.get(2)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(1)));
		rule.clauseAdd(new Clause(yag, ALL.get(0)));
		rule.clauseAdd(new Clause(azot, ALL.get(1)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 66
		rule.clauseAdd(new Clause(oksijen, ALL.get(2)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(1)));
		rule.clauseAdd(new Clause(yag, ALL.get(0)));
		rule.clauseAdd(new Clause(azot, ALL.get(2)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 67
		rule.clauseAdd(new Clause(oksijen, ALL.get(2)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(1)));
		rule.clauseAdd(new Clause(yag, ALL.get(1)));
		rule.clauseAdd(new Clause(azot, ALL.get(0)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 68
		rule.clauseAdd(new Clause(oksijen, ALL.get(2)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(1)));
		rule.clauseAdd(new Clause(yag, ALL.get(1)));
		rule.clauseAdd(new Clause(azot, ALL.get(1)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 69
		rule.clauseAdd(new Clause(oksijen, ALL.get(2)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(1)));
		rule.clauseAdd(new Clause(yag, ALL.get(1)));
		rule.clauseAdd(new Clause(azot, ALL.get(2)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 70
		rule.clauseAdd(new Clause(oksijen, ALL.get(2)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(1)));
		rule.clauseAdd(new Clause(yag, ALL.get(2)));
		rule.clauseAdd(new Clause(azot, ALL.get(0)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 71
		rule.clauseAdd(new Clause(oksijen, ALL.get(2)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(1)));
		rule.clauseAdd(new Clause(yag, ALL.get(2)));
		rule.clauseAdd(new Clause(azot, ALL.get(1)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 72
		rule.clauseAdd(new Clause(oksijen, ALL.get(2)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(1)));
		rule.clauseAdd(new Clause(yag, ALL.get(2)));
		rule.clauseAdd(new Clause(azot, ALL.get(2)));
		rule.setClause(new Clause(sonuc, ALL.get(2)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 73
		rule.clauseAdd(new Clause(oksijen, ALL.get(2)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(2)));
		rule.clauseAdd(new Clause(yag, ALL.get(0)));
		rule.clauseAdd(new Clause(azot, ALL.get(0)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 74
		rule.clauseAdd(new Clause(oksijen, ALL.get(2)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(2)));
		rule.clauseAdd(new Clause(yag, ALL.get(0)));
		rule.clauseAdd(new Clause(azot, ALL.get(1)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 75
		rule.clauseAdd(new Clause(oksijen, ALL.get(2)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(2)));
		rule.clauseAdd(new Clause(yag, ALL.get(0)));
		rule.clauseAdd(new Clause(azot, ALL.get(2)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 76
		rule.clauseAdd(new Clause(oksijen, ALL.get(2)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(2)));
		rule.clauseAdd(new Clause(yag, ALL.get(1)));
		rule.clauseAdd(new Clause(azot, ALL.get(0)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 77
		rule.clauseAdd(new Clause(oksijen, ALL.get(2)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(2)));
		rule.clauseAdd(new Clause(yag, ALL.get(1)));
		rule.clauseAdd(new Clause(azot, ALL.get(1)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 78
		rule.clauseAdd(new Clause(oksijen, ALL.get(2)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(2)));
		rule.clauseAdd(new Clause(yag, ALL.get(1)));
		rule.clauseAdd(new Clause(azot, ALL.get(2)));
		rule.setClause(new Clause(sonuc, ALL.get(2)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 79
		rule.clauseAdd(new Clause(oksijen, ALL.get(2)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(2)));
		rule.clauseAdd(new Clause(yag, ALL.get(2)));
		rule.clauseAdd(new Clause(azot, ALL.get(0)));
		rule.setClause(new Clause(sonuc, ALL.get(1)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 80
		rule.clauseAdd(new Clause(oksijen, ALL.get(2)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(2)));
		rule.clauseAdd(new Clause(yag, ALL.get(2)));
		rule.clauseAdd(new Clause(azot, ALL.get(1)));
		rule.setClause(new Clause(sonuc, ALL.get(2)));
		mamdani.addRule(rule);

		rule = new Rule("Kural "+ ++ruleNumber); // 81
		rule.clauseAdd(new Clause(oksijen, ALL.get(2)));
		rule.clauseAdd(new Clause(iletkenlik, ALL.get(2)));
		rule.clauseAdd(new Clause(yag, ALL.get(2)));
		rule.clauseAdd(new Clause(azot, ALL.get(2)));
		rule.setClause(new Clause(sonuc, ALL.get(2)));
		mamdani.addRule(rule);



		return mamdani;
	}

	public static Integer sonuc(Integer deger) {
		if (deger < 4) {
			return 0;
		} else if (3 < deger && deger < 7) {
			return 1;
		} else {
			return 2;
		}
	}

}
