package com.bm.membership;

public class FuzzyGrade implements IGraphic {

    private double lowerValue;
    private double upperValue;
    private double spacing = 0.1;
    
    public FuzzyGrade(double lowerValue, double upperValue) {
        this.lowerValue = lowerValue;
        this.upperValue = upperValue;
    }

    public FuzzyGrade(double lowerValue, double upperValue,  double spacing) {
        this.lowerValue = lowerValue;
        this.upperValue = upperValue;
        this.spacing = spacing;
    }

    public double degree(double x){
        if (x <= lowerValue)
        {
            return 0;
        }
        else if (x < upperValue)
        {
            return (x - lowerValue) / (upperValue - lowerValue);
        }
        else
        {
            return 1;
        }
    }
    
    public double value(double upper,double lower,double x){
    	if(x<1) {
    		for (double i = lowerValue; i < upper+spacing; i+=spacing) {
				if(x <= degree(i)) {
					return (i+upper)/2;
				}
			}
    		return 0;
    	}else {
    		return (upperValue+upper)/2;
    	}
    }
    

}
