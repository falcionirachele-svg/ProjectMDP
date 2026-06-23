package it.unicam.cs.mpgc.rpg126012.Model;
/*Singolo Turno di combattimento*/
public class TurnBasedCombat {
    private Character character1;
    private Character character2;
    private int dannoRicevuto;
    private int dannoInflitto;
    private GestoreTurno gestoreTurno;
    public TurnBasedCombat(){}
    public TurnBasedCombat(Character enemy, Character player) {
        this.character1 = enemy;
        this.character2 = player;
        GestoreTurno gestoreTurno = new GestoreTurno(character2.getMaxHealth(), character2.getCurrentHealth(), character1.getCurrentHealth());
        }

    public Risultato eseguiTurno() {
        //il nemico attacca il giocatore
        dannoRicevuto=character1.getDamage();
        character2.setCurrentHealth(character2.getCurrentHealth() - dannoRicevuto);
        //il giocatore attacca il nemico
        dannoInflitto=character2.getDamage();
        character1.setCurrentHealth(character1.getCurrentHealth() - dannoInflitto);
        //cambio vite correnti post attacco
        gestoreTurno.setVitaGiocatore(character2.getCurrentHealth());
        gestoreTurno.setVitaNemico(character1.getCurrentHealth());
        //aggiorno variabile locale con risultato ottenuto
        return gestoreTurno.getRisultato();
    }
    public boolean fineCombattimento(){
        return gestoreTurno.endBattle();
    }

    public int getDannoInflitto() {
        return dannoInflitto;
    }
    public int getDannoRicevuto() {
        return dannoRicevuto;
    }
    public boolean isPlayerWin() {return gestoreTurno.isPlayerWin();}

}
