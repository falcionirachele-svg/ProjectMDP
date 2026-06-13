package it.unicam.cs.mpgc.rpg126012.Model;
/*Uso un interfaccia per gestire i nodi della mia storia,
* in questo caso sono due, DialogueNode e CombatNode.
* Per implementazioni future posso creare altri tipi di nodi*/
public interface StoryNode {
    //metodo per ottenere testo delle storia e i testi delle due opzioni
    String getDescription();
    //metodo per ottenere le opzioni
    String getOptions();
    //metodo che restituisce l'ID del prossimo nodo in base alla scelta dell'utente
    StoryNode getNextNode(int choiceIndex);
    void setNodoA(StoryNode nodo);
    void setNodoB(StoryNode nodo);
    String getId();
    public boolean isLastNode();
}
