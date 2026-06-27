package it.unicam.cs.mpgc.rpg126012.Model.Combatt;

import it.unicam.cs.mpgc.rpg126012.Model.Character.Character;

/*Singolo Turno di combattimento*/
public class TurnBasedCombat {
    private it.unicam.cs.mpgc.rpg126012.Model.Character.Character character1;
    private it.unicam.cs.mpgc.rpg126012.Model.Character.Character character2;
    private int dannoRicevuto;
    private int dannoInflitto;
    private GestoreTurno gestoreTurno;
    public TurnBasedCombat(){}
    public TurnBasedCombat(it.unicam.cs.mpgc.rpg126012.Model.Character.Character enemy, Character player) {
        this.character1 = enemy;
        this.character2 = player;
        this.gestoreTurno = new GestoreTurno(character2.getMaxHealth(), character2.getCurrentHealth(), character1.getCurrentHealth());
        }
    /*Metodo per eseguire un turno di combattimento
    * @return risultato del turno*/
    public Risultato eseguiTurno() {
        dannoRicevuto=character1.getDamage();
        character2.setCurrentHealth(character2.getCurrentHealth() - dannoRicevuto);
        dannoInflitto=character2.getDamage();
        character1.setCurrentHealth(character1.getCurrentHealth() - dannoInflitto);
        gestoreTurno.setVitaGiocatore(character2.getCurrentHealth());
        gestoreTurno.setVitaNemico(character1.getCurrentHealth());
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
