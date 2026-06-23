package it.unicam.cs.mpgc.rpg126012.Model;

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
    public void setCurrentHealth(int currentHealth) {this.currentHealth = currentHealth;}
    public boolean isAlive() {return currentHealth > 0;}
    public void setDamage(int damage) {this.damage = damage;}
    public void setName(String name) {this.name = name;}
    public void setMaxHealth(int maxHealth) {this.health = health;}
    //in base al tipo di personaggio l'attacco cambia
    public abstract int getDamage();
    //in base al tipo di personaggio posso avere o no l'attacco critico
    public abstract boolean getColpoCritico();
    //metodo per capire chi è il giocatore
    public abstract boolean isPlayer();
}
