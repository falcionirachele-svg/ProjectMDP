package it.unicam.cs.mpgc.rpg126012.Model.Combatt;

/*Classe per ottenere i risultati ottenuti dal turno di combattimento svolto*/
public class RisultatoTurno implements Risultato {
    private int playerHealth;
    private int enemyHealth;
    private int playerDamage;
    private int enemyDamage;
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
