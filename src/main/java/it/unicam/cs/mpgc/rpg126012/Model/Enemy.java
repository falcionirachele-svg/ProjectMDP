package it.unicam.cs.mpgc.rpg126012.Model;
public class Enemy {
    public String id;
    public String name;
    public int health;
    public int damage;

    public Enemy(){}
    public Enemy(String id, String name, int health, int damage) {
        this.id=id;
        this.name = name;
        this.health = health;
        this.damage = damage;
    }
    public String getId(){return id;}
    public String getName() {return name;}
    public void setName(String name) {
        this.name = name;
    }
    public int getHealth() {
        return health;
    }
    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public boolean isAlive() {
        return health > 0;
    }
}
