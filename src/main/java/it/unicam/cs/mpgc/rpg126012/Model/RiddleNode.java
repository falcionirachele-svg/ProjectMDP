package it.unicam.cs.mpgc.rpg126012.Model;

import java.util.List;

public class RiddleNode implements StoryNode{
    public String id;//id del nodo
    private String text;//testo della storia
    private StoryNode nextNodeA;
    private List<String> answers;
    private int rightAnswer;
    public RiddleNode(){
    }
    @Override
    public String getId(){
        return id;
    }
    @Override
    public String getDescription(){return "\n"+text+"\n";}
    @Override
    public String getOptions(){
        return "1.\n"+answers.get(0)
                +"  2."+answers.get(1)
                +"  3."+answers.get(2)
                +"  4."+answers.get(3);
    }
    @Override
    public boolean isLastNode(){
        return nextNodeA==null;
    }
    @Override
    public StoryNode getNextNode(int choiceIndex) {
        if(choiceIndex==0) return nextNodeA;
        return null;
    }

    public String[] arrayAnswers(){
        if(answers==null) return  new String[0];
        return answers.toArray(new String[0]);
    }
    public boolean isRightAnswer(int answer){
        return answer==rightAnswer;
    }
    public void setRightAnswer(int rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
    public void setText(String text) {
        this.text = text;
    }
    public int getRightAnswer() {
        return rightAnswer;
    }
    @Override
    public void setNodoA(StoryNode nodo){
        this.nextNodeA= nodo;
    }
    //non ha un riferimento al nodoB
    @Override
    public void setNodoB(StoryNode nextNodeB){

    }

}
