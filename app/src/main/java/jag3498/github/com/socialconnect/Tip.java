package jag3498.github.com.socialconnect;

import java.io.Serializable;

public class Tip implements Serializable {

    private String text;
    private int type;
    private int skill;
    /*
    Skill areas:
    1: Individual
    2: Group
    3: New friend
    4: Old friend
    5: Havent seen freinds in a while
    6: Uncomfortable
    7: Know people less

    Types:
    1: romantic
    2: work
    3: friends
     */

    public Tip(String text, int type, int skill) {
        this.text = text;
        this.type = type;
        this.skill = skill;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }


}
