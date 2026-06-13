package it.unicam.cs.mpgc.rpg126012.Model;

public class TurnBasedCombat {
    public Enemy enemy;
    public Player player;
    public boolean win;
    public int dannoRicevuto;
    public int dannoInflitto;
    public TurnBasedCombat(){}
    public TurnBasedCombat(Enemy enemy, Player player) {
        this.enemy = enemy;
        this.player = player;
    }

    public RisultatoTurno eseguiTurno() {
        //verifico che nessuno dei due sia senza vita
        if(!enemy.isAlive() || !player.isAlive()){
            fineCombattimento();
            //se il combattimento finisce restituisco null
            return null;
        }
        //il nemico attacca il giocatore
        player.setCurrentHealth(player.getCurrentHealth() - enemy.getDamage());
        dannoRicevuto=enemy.getDamage();
        //il giocatore attacca il nemico
        enemy.setHealth(enemy.getHealth() - player.getDamage());
        dannoInflitto=player.getDamage();
        RisultatoTurno risultatoTurno= new RisultatoTurno(player.getCurrentHealth(), enemy.getHealth());
        risultatoTurno.setEnemyDamage(getDannoRicevuto());
        risultatoTurno.setPlayerDamage(getDannoInflitto());
        return risultatoTurno;
    }
    public void fineCombattimento(){
        if(player.isAlive()){
            win=true;
        }else{
            win=false;
        }
        //una volta terminato il combattimento il giocatore torna ad avere la massima salute
        //per future implementazioni si possono introdurre cure durante la storia e non resettare la salute
        player.setCurrentHealth(player.getMaxHealt());
    }
    public boolean endBattle() {
        if(!player.isAlive() || !enemy.isAlive()) return true;
        return false;
    }
    public int getDannoInflitto() {
        return dannoInflitto;
    }
    public int getDannoRicevuto() {
        return dannoRicevuto;
    }

}
