package it.unicam.cs.mpgc.rpg126012.Model;

public class RisultatoTurno implements Risultato{
    public int playerHealth;
    public int enemyHealth;
    public int playerDamage;
    public int enemyDamage;
    public RisultatoTurno(int playerHealth, int enemyHealth){
        this.playerHealth = playerHealth;
        this.enemyHealth = enemyHealth;
    }

    public int getEnemyHealth() {
        return enemyHealth;
    }
    public int getPlayerHealth() {
        return playerHealth;
    }
    public void setPlayerHealth(int playerHealth) {
        this.playerHealth = playerHealth;
    }
    public void setEnemyHealth(int enemyHealth) {
        this.enemyHealth = enemyHealth;
    }
    public int getEnemyDamage() {
        return enemyDamage;
    }
    public int getPlayerDamage() {
        return playerDamage;
    }
    public void setEnemyDamage(int enemyDamage) {
        this.enemyDamage = enemyDamage;
    }
    public void setPlayerDamage(int playerDamage) {
        this.playerDamage = playerDamage;
    }
    public boolean endBattle() {
        return playerHealth <= 0 || enemyHealth <= 0;
    }
    public boolean isPlayerWin() {
        return playerHealth > 0;
    }
}
