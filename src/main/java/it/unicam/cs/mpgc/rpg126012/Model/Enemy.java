package it.unicam.cs.mpgc.rpg126012.Model;
public class Enemy extends Character{
    public String id;
    public String name;
    public int health;
    public int damage;

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
    //non ha mai colpi critici
    @Override
    public boolean getColpoCritico() {
        return false;
    }
}
