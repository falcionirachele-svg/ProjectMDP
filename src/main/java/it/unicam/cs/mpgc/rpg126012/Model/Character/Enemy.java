package it.unicam.cs.mpgc.rpg126012.Model.Character;

public class Enemy extends Character {

    public Enemy(String id, String name, int health, int damage) {
        super(id, name, health, damage);
    }
    @Override
    public int getDamage() {
        return damage;
    }
    @Override
    public boolean isPlayer() {
        return false;
    }

    @Override
    public boolean getColpoCritico() {
        return false;
    }
}
