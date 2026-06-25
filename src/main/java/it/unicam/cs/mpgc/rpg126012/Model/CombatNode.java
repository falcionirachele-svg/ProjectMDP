package it.unicam.cs.mpgc.rpg126012.Model;
/*Nodo di tipo combattimento*/
public class CombatNode implements StoryNode {
    private String id;
    private String text;
    private StoryNode nextNodeA;
    private StoryNode nextNodeB;
    private Enemy enemy;
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
