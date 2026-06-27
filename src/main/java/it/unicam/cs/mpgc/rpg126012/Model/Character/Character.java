package it.unicam.cs.mpgc.rpg126012.Model.Character;
/*Classe astratta per i personaggi*/
public abstract class Character {
    protected String id;
    protected String name;
    protected int health;
    protected int currentHealth;
    protected int damage;
    public Character(String id, String name, int health, int damage) {
        this.id = id;
        this.name = name;
        this.health = health;
        this.currentHealth = health;
        this.damage = damage;
    }
    public String getId() {return id;}
    public String getName() {return name;}
    public int getMaxHealth() {return health;}
    public int getCurrentHealth() {return currentHealth;}
    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = Math.min(currentHealth, health);
    }
    public boolean isAlive() {return currentHealth > 0;}
    public void setDamage(int damage) {this.damage = damage;}
    public void setName(String name) {this.name = name;}
    public void setMaxHealth(int health) {this.health = health;}
    public abstract int getDamage();
    public abstract boolean getColpoCritico();
    public abstract boolean isPlayer();
}
