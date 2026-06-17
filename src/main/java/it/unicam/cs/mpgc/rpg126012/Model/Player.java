package it.unicam.cs.mpgc.rpg126012.Model;

import java.util.Random;

public class Player {
    public String name;
    public int maxHealt;
    public int currentHealth;
    public int baseDamage;
    public Random random;//per generare numeri casuali per rendere danni variabili
    public boolean colpoCritico=false;//se i danni raddoppiano
    public Player(String name, int maxHealt, int baseDamage){
        this.name=name;
        this.maxHealt=maxHealt;
        this.baseDamage=baseDamage;
        this.currentHealth=maxHealt;
        this.random=new Random();
    }
    public int getCurrentHealth() {
        return currentHealth;
    }
    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }
    //il danno del giocatore oscilla di 10 punti
    //c'è poi un 15% di possibilità che il danno raddoppi
    public int getDamage() {
        int dannoMin= baseDamage-10;
        int dannoMax= baseDamage+10;
        int dannoCasuale=random.nextInt((dannoMax-dannoMin)+1)+dannoMin;
        //15% di possibilità che il danno raddoppi
        if((random.nextInt(100)+1)<=15){
            colpoCritico=true;
            return dannoCasuale*2;
        }
        colpoCritico=false;
        return dannoCasuale;
    }
    public boolean getColpoCritico() {
        return colpoCritico;
    }
    public void setDamage(int damage) {
        this.baseDamage = damage;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getMaxHealt() {
        return maxHealt;
    }
    public void setMaxHealt(int maxHealt) {
        this.maxHealt = maxHealt;
    }
    public boolean isAlive() {
        return currentHealth > 0;
    }
}
