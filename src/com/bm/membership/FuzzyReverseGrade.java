package com.bm.membership;

public class FuzzyReverseGrade implements IGraphic {

    private double lowerValue;
    private double upperValue;
    private double spacing;
    
    public FuzzyReverseGrade(double lowerValue, double upperValue) {
        this.lowerValue = lowerValue;
        this.upperValue = upperValue;
    }

    public FuzzyReverseGrade(double lowerValue, double upperValue,double spacing) {
        this.lowerValue = lowerValue;
        this.upperValue = upperValue;
        this.spacing = spacing;
    }

    public double degree(double x) {
        if (x <= lowerValue)
        {
            return 1;
        }
        else if (x < upperValue)
        {
            return (upperValue - x) / (upperValue - lowerValue);
        }
        else
        {
            return 0;
        }
    }
    
    public double value(double upper,double lower,double x){
    	if(x<1) {
    		for (double i = upper; i > lower-spacing; i-=spacing) {
				if(x <= degree(i)) {
					return (i+lower)/2;
				}
			}
    		return 0;
    	}else {
    		return (lower+upperValue)/2;
    	}
    }
    
}
