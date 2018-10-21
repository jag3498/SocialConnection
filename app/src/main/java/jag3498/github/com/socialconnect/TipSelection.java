package jag3498.github.com.socialconnect;

import java.io.Serializable;

public class TipSelection implements Serializable {

 private int tipValue;
 private Tip tip;

    public TipSelection(int tipValue, Tip tip) {
        this.tipValue = tipValue;
        this.tip = tip;
    }

    public int getTipValue() {
        return tipValue;
    }

    public void setTipValue(int tipValue) {
        this.tipValue = tipValue;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }
}
