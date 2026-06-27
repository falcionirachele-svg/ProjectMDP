package it.unicam.cs.mpgc.rpg126012.Model.Combatt;
/*interfaccia che contiene le regole per la gestione del turno di combattimento*/
public interface TurnPolicy {
    public void resetLifePlayer();
    public boolean endBattle();
    public boolean isPlayerWin();
}
