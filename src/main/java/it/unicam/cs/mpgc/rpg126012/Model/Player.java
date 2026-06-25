package it.unicam.cs.mpgc.rpg126012.Model;

import java.util.Random;

public class Player extends Character {
    public transient Random random;//per generare numeri casuali per rendere danni variabili
    public boolean colpoCritico=false;//se i danni raddoppiano
    public Player(String id, String name, int healt, int damage){
        super(id, name, healt, damage);
        this.random=new Random();
    }

    @Override
    public int getDamage() {
        int dannoMin= damage-10;
        int dannoMax= damage+10;
        int dannoCasuale=random.nextInt((dannoMax-dannoMin)+1)+dannoMin;
        //15% di possibilità che il danno raddoppi
        if((random.nextInt(100)+1)<=15){
            colpoCritico=true;
            return dannoCasuale*2;
        }
        colpoCritico=false;
        return dannoCasuale;
    }
    @Override
    public boolean getColpoCritico() {
        return colpoCritico;
    }
    @Override
    public boolean isPlayer() {
        return true;
    }

}
