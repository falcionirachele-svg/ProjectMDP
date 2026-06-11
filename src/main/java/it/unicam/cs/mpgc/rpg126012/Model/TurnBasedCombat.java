package it.unicam.cs.mpgc.rpg126012.Model;

public class TurnBasedCombat {
    public Enemy enemy;
    public Player player;
    public boolean win;
    public TurnBasedCombat(){}
    public TurnBasedCombat(Enemy enemy, Player player) {
        this.enemy = enemy;
        this.player = player;
    }
    public void doCombat() {
        while(enemy.isAlive() && player.isAlive()){
            //il nemico attacca il giocatore
            player.setCurrentHealth(player.getCurrentHealth() - enemy.getDamage());
            //il giocatore attacca il nemico
            enemy.setHealth(enemy.getHealth() - player.getDamage());
        }
        //salvo nella variabile win true se il giocatore vince, false altrimenti
        if(enemy.isAlive()){
            win=false;
        }else{
            win=true;
        }
        //una volta terminato il combattimento il giocatore torna ad avere la massima salute
        player.setCurrentHealth(player.getMaxHealt());
    }
    public boolean Win() {
        return win;
    }
}
