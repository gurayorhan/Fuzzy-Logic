package com.bm.membership;

public class FuzzyTrapezoid implements IGraphic {

    private double v1;
    private double v2;
    private double v3;
    private double v4;

    public FuzzyTrapezoid(double v1, double v2, double v3, double v4) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.v4 = v4;
    }

    public double degree(double x)
    {
        if (x <= v1 || x >= v4)
        {
            return 0;
        }
        else if (x >= v2 && x <= v3)
        {
            return 1;
        }
        else if ((x > v1) && (x < v2))
        {
            return (x - v1) / (v2 - v1);
        }
        else
        {
            return (v4 - x) / (v4 - v3);
        }
    }
    
    public double value(double upper,double lower,double x){
    	return (v2+v3)/2;
    }
    
}
