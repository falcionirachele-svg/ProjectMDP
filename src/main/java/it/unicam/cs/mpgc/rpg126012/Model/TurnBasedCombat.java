package it.unicam.cs.mpgc.rpg126012.Model;

public class TurnBasedCombat {
    public Character character1;
    public Character character2;
    public int dannoRicevuto;
    public int dannoInflitto;
    public Risultato risultato;
    public TurnBasedCombat(){}
    public TurnBasedCombat(Character enemy, Character player) {
        this.character1 = enemy;
        this.character2 = player;
        //istanzio localmente il risultato del turno
        risultato= new RisultatoTurno(character2.getMaxHealth(),character1.getMaxHealth());
    }

    public Risultato eseguiTurno() {
        //verifico che nessuno dei due sia senza vita
        if(risultato.endBattle()){
            fineCombattimento();
            //se il combattimento finisce restituisco null
            return null;
        }
        //il nemico attacca il giocatore
        dannoRicevuto=character1.getDamage();
        character2.setCurrentHealth(character2.getCurrentHealth() - dannoRicevuto);
        //il giocatore attacca il nemico
        dannoInflitto=character2.getDamage();
        character1.setCurrentHealth(character1.getCurrentHealth() - dannoInflitto);

        Risultato risultato= new RisultatoTurno(character2.getCurrentHealth(), character1.getCurrentHealth());
        risultato.setEnemyDamage(getDannoRicevuto());
        risultato.setPlayerDamage(getDannoInflitto());
        //aggiorno variabile locale con risultato ottenuto
        this.risultato=risultato;
        return risultato;
    }
    public void fineCombattimento(){
        //una volta terminato il combattimento il giocatore torna ad avere la massima salute
        //per future implementazioni si possono introdurre cure durante la storia e non resettare la salute
        character2.setCurrentHealth(character2.getMaxHealth());
    }

    public int getDannoInflitto() {
        return dannoInflitto;
    }
    public int getDannoRicevuto() {
        return dannoRicevuto;
    }
    public boolean isPlayerWin() {
        return risultato.isPlayerWin();
    }

}
