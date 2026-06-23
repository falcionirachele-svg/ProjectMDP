package it.unicam.cs.mpgc.rpg126012.Model;
/*Contratto per gestire i risultati dei combattimenti*/
public interface Risultato {
    public boolean endBattle();
    public boolean isPlayerWin();
    public int getEnemyHealth();
    public int getPlayerHealth();
    public int getEnemyDamage();
    public int getPlayerDamage();
    public void setEnemyHealth(int enemyHealth);
    public void setPlayerHealth(int playerHealth);
    public void setEnemyDamage(int enemyDamage);
    public void setPlayerDamage(int playerDamage);
}
