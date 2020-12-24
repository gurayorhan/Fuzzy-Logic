package com.bm.membership;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class FuzzySet {

    private String name;

	private double lowerBound;
    private double upperBound;
    private Map<String,IGraphic> graphics = new HashMap<String, IGraphic>();
    private List<String> values = new ArrayList<String>();
    private double value;

    public void setValue(double value){
        this.value = value;
    }
    
    public double getValue(){
      	return value;
    }
    
    public String getName(){
    	return name;
    }
    
    public FuzzySet(String name, double lowerBound, double upperBound) {
        this.name = name;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public double getDegree(String graphicName){
        IGraphic graphic = graphics.get(graphicName);
        return graphic.degree(value);
    }

    public void addGraphic(String string, IGraphic graphic){
        graphics.put(string,graphic);
        values.add(string);
    }
    
    public double getValue(String graphicName,double x){
        IGraphic graphic = graphics.get(graphicName);
        return graphic.value(upperBound,lowerBound ,x);
    }

}
