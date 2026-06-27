package it.unicam.cs.mpgc.rpg126012.Model.Story;
/*Uso un interfaccia per gestire i nodi della mia storia.
* Per implementazioni future posso creare altri tipi di nodi*/
public interface StoryNode {
    String getDescription();
    StoryNode getNextNode(int choiceIndex);
    String getId();
    void setNodoA(StoryNode nodoA);
    void setNodoB(StoryNode nodoB);
    boolean isLastNode();
}
