package jag3498.github.com.socialconnect;

import java.io.Serializable;

public class Interaction implements Serializable {

    private Friend friend;
    private int time;
    private int numPeople;
    private int comfort;
    private int know;

    public Interaction(Friend friend, int time, int numPeople, int comfort, int know) {
        this.friend = friend;
        this.time = time;
        this.numPeople = numPeople;
        this.comfort = comfort;
        this.know = know;
    }

    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getNumPeople() {
        return numPeople;
    }

    public void setNumPeople(int numPeople) {
        this.numPeople = numPeople;
    }

    public int getComfort() {
        return comfort;
    }

    public void setComfort(int comfort) {
        this.comfort = comfort;
    }

    public int getKnow() {
        return know;
    }

    public void setKnow(int know) {
        this.know = know;
    }
}
