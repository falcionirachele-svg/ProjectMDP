package it.unicam.cs.mpgc.rpg126012.Model;

public class GestoreCombattimenti {
    private Player player;
    private Enemy enemy;
    private TurnBasedCombat turno;
    private boolean endBattle=false;
    public GestoreCombattimenti(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        //genro un turno di combattimento
        this.turno=new TurnBasedCombat(enemy, player);
    }
    public void eseguiTurno() {
        turno.eseguiTurno();
        //controllo se il combattimento finisce
        if(turno.fineCombattimento()){
            this.endBattle=true;
        }
    }
    public String testoBattle() {
        //creo il testo del combattimento
        StringBuilder log=new StringBuilder();
        log.append("\nHai attaccato! Infliggi ").append(turno.getDannoInflitto()).append(" danni.\n");
        if(player.getColpoCritico()) log.append("Il colpo è stato critico!\n");
        log.append("Il nemico ti attacca, ricevi ").append(turno.getDannoRicevuto()).append(" danni.\n");
        //salute attuale
        log.append("La tua salute attuale è ").append(player.getCurrentHealth());
        log.append("\nLa salute del nemico è ").append(enemy.getCurrentHealth());
        //se il combattimento finisce
        if(endBattle){
            //controllo chi ha vinto
            if(turno.isPlayerWin()){
                log.append("\n\n Hai Vinto!");
            }
            else{
                log.append("\n\n Hai Perso!");
            }
        }
        return log.toString();
    }
    public boolean endBattle() {
        return this.endBattle;
    }


}
