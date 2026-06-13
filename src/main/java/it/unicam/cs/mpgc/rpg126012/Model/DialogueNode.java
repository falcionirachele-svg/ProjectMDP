package it.unicam.cs.mpgc.rpg126012.Model;

public class DialogueNode implements StoryNode {
    public String id;//id del nodo
    private String text;//testo della storia
    private StoryNode nextNodeA;
    private StoryNode nextNodeB;
    public DialogueNode() {
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    @Override
    public String getDescription() {
        return "\n"+text+"\n";
    }
    public String getOptions(){
        return "1. Si\n"
                +"  2. No";
    }
    public boolean isLastNode(){
        return nextNodeA==null && nextNodeB==null;
    }
    @Override
    public StoryNode getNextNode(int choiceIndex) {
        if(choiceIndex==0) return nextNodeA;
        if(choiceIndex==1) return nextNodeB;
        return null;
    }
    public void setNodoA(StoryNode nodo){
        this.nextNodeA= nodo;
    }

    public void setNodoB(StoryNode nodo) {
         this.nextNodeB=nodo;
    }
}
