package it.unicam.cs.mpgc.rpg126012.Model;
public class CombatNode implements StoryNode {
    public String id;//id del nodo
    public String text;//testo della storia
    public StoryNode nextNodeA;
    public StoryNode nextNodeB;
    public Enemy enemy;//nemico con cui combatte
    public CombatNode() {
    }
    public CombatNode(Enemy enemy){
        this.enemy=enemy;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy){
        this.enemy=enemy;
    }
    @Override
    public String getDescription() {
        return "\n"+text+"\n";
    }
    public String getOptions(){
        return "1. Attacca\n"
                +"  2. Scappa";
    }
    @Override
    public StoryNode getNextNode(int choiceIndex) {
        if(choiceIndex==0) return nextNodeA;
        if(choiceIndex==1) return nextNodeB;
        return null;
    }
    public StoryNode getNextNodeA(){
        return nextNodeA;
    }
    public StoryNode getNextNodeB(){
        return nextNodeB;
    }
    public boolean isLastNode(){
        return nextNodeA==null && nextNodeB==null;
    }
    public void setNodoA(StoryNode nodo){
        this.nextNodeA= nodo;
    }
    public void setNodoB(StoryNode nodo){
        this.nextNodeB=nodo;
    }

}
