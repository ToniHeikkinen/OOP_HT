package com.example.bootlegmon;

import java.io.Serializable;

public class Lutemon implements Serializable {

    protected String lutemonName;
    protected String lutemonColor;
    protected int lutemonAttack;
    protected int lutemonDefence;
    protected int lutemonExperience;
    protected int lutemonHealth;
    protected int lutemonMaxHealth;

    public Lutemon(String lutemonName, String lutemonColor,int lutemonAttack,int lutemonDefence,int lutemonExperience,int lutemonHealth,int lutemonMaxHealth) {
        this.lutemonName = lutemonName;
        this.lutemonColor= lutemonColor;
        this.lutemonAttack=lutemonAttack;
        this.lutemonDefence=lutemonDefence;
        this.lutemonExperience=lutemonExperience;
        this.lutemonHealth=lutemonHealth;
        this.lutemonMaxHealth=lutemonMaxHealth;
    }

    public String getLutemonName() {
        return lutemonName;
    }
    public String getLutemonColor() {
        return lutemonColor;
    }
    public int getLutemonAttack() {
        return lutemonAttack;
    }
    public void setLutemonAttack(int lutemonAttack) {
        this.lutemonAttack = lutemonAttack;
    }
    public int getLutemonDefence() {
        return lutemonDefence;
    }
    public int getLutemonExperience() {
        return lutemonExperience;
    }
    public void setLutemonExperience(int lutemonExperience) {
        this.lutemonExperience = lutemonExperience;
    }
    public int getLutemonHealth() {
        return lutemonHealth;
    }
    public int getLutemonMaxHealth() {
        return lutemonMaxHealth;
    }

}