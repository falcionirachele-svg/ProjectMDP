package it.unicam.cs.mpgc.rpg126012.Model;

public class GestoreTurno implements TurnPolicy{
    private int vitaGiocatoreMax;
    private int vitaGiocatore;
    private int vitaNemico;
    public GestoreTurno(int vitaGiocatoreMax, int vitaGiocatore, int vitaNemico){
        this.vitaGiocatoreMax=vitaGiocatoreMax;
        this.vitaGiocatore=vitaGiocatore;
        this.vitaNemico=vitaNemico;
    }
    public void setVitaGiocatore(int vitaGiocatore){
        this.vitaGiocatore=vitaGiocatore;
    }
    public void setVitaNemico(int vitaNemico){
        this.vitaNemico=vitaNemico;
    }
    public void resetLifePlayer(){
        this.vitaGiocatore=vitaGiocatoreMax;
    }
    public boolean endBattle(){
        return vitaGiocatore<=0 || vitaNemico<=0;
    }
    public Risultato getRisultato(){
        return new RisultatoTurno(vitaGiocatore, vitaNemico);
    }
    public boolean isPlayerWin(){
        return vitaGiocatore>0 && vitaNemico<=0;
    }
}
