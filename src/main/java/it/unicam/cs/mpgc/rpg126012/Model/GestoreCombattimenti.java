package it.unicam.cs.mpgc.rpg126012.Model;

public class GestoreCombattimenti {
    private Player player;
    private Enemy enemy;
    private boolean end=false;
    private boolean win=false;
    public GestoreCombattimenti(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
    }
    public String getCombat() {
        TurnBasedCombat turno=new TurnBasedCombat(enemy, player);
        Risultato risultato=turno.eseguiTurno();
        if(risultato==null){
            end=true;
            if(turno.isPlayerWin())
                win=true;
        }
        StringBuilder log=new StringBuilder();
        log.append("Hai attaccato! Infliggi ").append(turno.getDannoInflitto()).append(" danni.\n");
        if(player.getColpoCritico()) log.append("Il colpo è stato critico!\n");
        log.append("Il nemico ti attacca, ricevi ").append(turno.getDannoRicevuto()).append(" danni.\n");
        //salute attuale
        log.append("La tua salute attuale è ").append(player.getCurrentHealth());
        log.append("\nLa salute del nemico è ").append(enemy.getCurrentHealth());
        return log.toString();
    }
    public boolean isEnd() {
        return end;
    }
    public boolean isWin() {
        return win;
    }

}
