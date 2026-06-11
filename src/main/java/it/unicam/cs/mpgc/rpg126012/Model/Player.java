package it.unicam.cs.mpgc.rpg126012.Model;

public class Player {
    public String name;
    public int maxHealt;
    public int currentHealth;
    public int damage;
    public Player(){}
    public Player(String name, int maxHealt, int damage){
        this.name=name;
        this.maxHealt=maxHealt;
        this.damage=damage;
    }
    public int getCurrentHealth() {
        return currentHealth;
    }
    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }
    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
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
