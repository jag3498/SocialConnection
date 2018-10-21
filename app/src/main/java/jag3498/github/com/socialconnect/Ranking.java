package jag3498.github.com.socialconnect;

import java.io.Serializable;

public class Ranking implements Serializable {

    private double numPeopleAvg;
    private double comfortAvg;
    private double knowAvg;
    private double IntervalAvg;
    /*
    These averages will be computed based on current interactions in the list.
     */
    private int tip1;
    private int tip2;
    private int tip3;
    private int tip4;
    /*
    Top 4 tips. The tips will be computed based on   (the number of the tip in the arraylist of tips)
    */


    public Ranking(double numPeopleAvg, double comfortAvg, double knowAvg, double IntervalAvg, int tip1, int tip2, int tip3, int tip4) {
        this.numPeopleAvg = numPeopleAvg;
        this.comfortAvg = comfortAvg;
        this.knowAvg = knowAvg;
        this.IntervalAvg = IntervalAvg;
        this.tip1 = tip1;
        this.tip2 = tip2;
        this.tip3 = tip3;
        this.tip4 = tip4;

    }

    public double getNumPeopleAvg() {
        return numPeopleAvg;
    }

    public void setNumPeopleAvg(double numPeopleAvg) {
        this.numPeopleAvg = numPeopleAvg;
    }

    public double getComfortAvg() {
        return comfortAvg;
    }

    public void setComfortAvg(double comfortAvg) {
        this.comfortAvg = comfortAvg;
    }

    public double getKnowAvg() {
        return knowAvg;
    }

    public void setKnowAvg(double knowAvg) {
        this.knowAvg = knowAvg;
    }

    public double getIntervalAvg() {
        return IntervalAvg;
    }

    public void setIntervalAvg(int intervalAvg) {
        IntervalAvg = intervalAvg;
    }

    public int getTip1() {
        return tip1;
    }

    public void setTip1(int tip1) {
        this.tip1 = tip1;
    }

    public int getTip2() {
        return tip2;
    }

    public void setTip2(int tip2) {
        this.tip2 = tip2;
    }

    public int getTip3() {
        return tip3;
    }

    public void setTip3(int tip3) {
        this.tip3 = tip3;
    }

    public int getTip4() {
        return tip4;
    }

    public void setTip4(int tip4) {
        this.tip4 = tip4;
    }


}

