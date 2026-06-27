package it.unicam.cs.mpgc.rpg126012.Model.Story;

import java.util.List;
/*Nodo di tipo indovinello*/
public class RiddleNode implements StoryNode {
    private String id;
    private String text;
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
    public boolean isLastNode(){
        return nextNodeA==null;
    }
    @Override
    public StoryNode getNextNode(int choiceIndex) {
        if(choiceIndex==0) return nextNodeA;
        if(choiceIndex==1) return null;
        else throw new IllegalArgumentException("choiceIndex deve essere 0 o 1");
    }

    public String[] arrayAnswers(){
        if(answers==null) throw new IllegalArgumentException("La lista di risposte non può essere null");
        return answers.toArray(new String[0]);
    }
    public boolean isRightAnswer(int answer){
        if(answer<0 || answer>=answers.size())
            throw new IllegalArgumentException("answer deve essere compreso tra 0 e "+(answers.size()-1));
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
    public int getRightAnswer() {return rightAnswer;}
    @Override
    public void setNodoA(StoryNode nodo){
        this.nextNodeA= nodo;
    }
    //non ha un riferimento al nodoB
    @Override
    public void setNodoB(StoryNode nextNodeB){

    }

}
