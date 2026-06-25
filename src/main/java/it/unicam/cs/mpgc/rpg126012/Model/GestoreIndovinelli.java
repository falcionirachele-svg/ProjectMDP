package it.unicam.cs.mpgc.rpg126012.Model;

public class GestoreIndovinelli {
    private boolean indovinato;
    private RiddleNode riddleNode;
    public GestoreIndovinelli(RiddleNode riddleNode, int answer) {
        this.riddleNode=riddleNode;
        this.indovinato=riddleNode.isRightAnswer(answer);
    }
    public boolean isIndovinato() {
        return indovinato;
    }
    public String testoIndovinello(){
        if(indovinato) return "Hai indovinato!\n";
        return "Riprova! Non hai indoovinato\n";
    }
}
