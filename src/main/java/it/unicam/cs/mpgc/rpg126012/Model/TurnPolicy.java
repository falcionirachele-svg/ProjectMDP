package it.unicam.cs.mpgc.rpg126012.Model;
/*interfaccia che contiene le regole del turno di combattimento*/
public interface TurnPolicy {
    public void resetLifePlayer();
    public boolean endBattle();
    public boolean isPlayerWin();
}
