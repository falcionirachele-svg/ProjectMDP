package it.unicam.cs.mpgc.rpg126012.Model.Story;

/*Nodo di tipo dialogo*/
public class DialogueNode implements StoryNode {
    private String id;
    private String text;
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
    public void setDescription(String text) {
        this.text = text;
    }
    public boolean isLastNode(){
        return nextNodeA==null && nextNodeB==null;
    }
    @Override
    public StoryNode getNextNode(int choiceIndex) {
        if(choiceIndex==0) return nextNodeA;
        if(choiceIndex==1) return nextNodeB;
        else throw new IllegalArgumentException("choiceIndex deve essere 0 o 1");
    }
    public void setNodoA(StoryNode nodo){
        this.nextNodeA= nodo;
    }

    public void setNodoB(StoryNode nodo) {
         this.nextNodeB=nodo;
    }
}
