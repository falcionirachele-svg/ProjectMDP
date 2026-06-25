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
    /*Mi restituisce il nodo successivo
    * @param choiceIndex l'indice della scelta (0 o 1)
    * @return il nodo successivo
    * @throws IllegalArgumentException se choiceIndex non è 0 o 1*/
    @Override
    public StoryNode getNextNode(int choiceIndex) {
        if(choiceIndex==0) return nextNodeA;
        if(choiceIndex==1) return nextNodeB;
        else throw new IllegalArgumentException("choiceIndex deve essere 0 o 1");
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
    public void setText(String text) {
        this.text = text;
    }

}
