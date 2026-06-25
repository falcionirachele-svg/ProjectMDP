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
    /*Avvia un turno di battaglia*/
    public void eseguiTurno() {
        turno.eseguiTurno();
        //controllo se il combattimento finisce
        if(turno.fineCombattimento()){
            this.endBattle=true;
        }
    }
    /*Metodo per creare il testo del combattimento
    * @return Stringa con il testo del combattimento*/
    public String testoBattle() {
        StringBuilder log=new StringBuilder();
        log.append("\nHai attaccato! Infliggi ").append(turno.getDannoInflitto()).append(" danni.\n");
        if(player.getColpoCritico()) log.append("Il colpo è stato critico!\n");
        log.append("Il nemico ti attacca, ricevi ").append(turno.getDannoRicevuto()).append(" danni.\n");
        log.append("La tua salute attuale è ").append(player.getCurrentHealth());
        log.append("\nLa salute del nemico è ").append(enemy.getCurrentHealth());
        if(endBattle){
            if(turno.isPlayerWin()){
                log.append("\n\n Hai Vinto!");
            }
            else{
                log.append("\n\n Hai Perso!");
            }
        }
        return log.toString();
    }
    /*Metodo per verificare se il combattimento è finito
    * @return booleano che indica se il combattimento è finito*/
    public boolean endBattle() {
        return this.endBattle;
    }


}
