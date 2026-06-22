package it.unicam.cs.mpgc.rpg126012.Controller;

import it.unicam.cs.mpgc.rpg126012.Model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class GameControllerFX {
    @FXML
    private TextArea testoStoria;
    @FXML
    private Button bottoneScelta1;
    @FXML
    private Button bottoneScelta2;
    @FXML
    private Button bottoneScelta3;
    @FXML
    private Button bottoneScelta4;
    private Player player;
    private StoryNode nodoCorrente;
    public void startGame(StoryNode nodoiniziale, Player player){
        this.player=player;
        testoStoria.setText("Inizio del Gioco\n\n");
        aggiornaSchermata(nodoiniziale);

    }
    private void aggiornaSchermata(StoryNode nodo){
        this.nodoCorrente=nodo;
        testoStoria.setText(nodo.getDescription());
        //se è un nodo finale
        if(nodoCorrente.isLastNode()){
            impostaBottoni(new String[] {"Fine"});
        }
        //se è un nodo combattimento
        else if(nodoCorrente instanceof CombatNode){
            CombatNode combatNode = (CombatNode) nodoCorrente;
            impostaBottoni(new String[] {"Attacca", "Fuggi"});
        }
        //se è un nodo indovinello
        else if(nodoCorrente instanceof RiddleNode){
            RiddleNode riddleNode = (RiddleNode) nodoCorrente;
            impostaBottoni(riddleNode.arrayAnswers());
        }
        //nodo dialogo
        else{
            impostaBottoni(new String[] {"Si", "No"});
        }
    }
    private void impostaBottoni(String[] scelte){
        Button[] bottoni={bottoneScelta1, bottoneScelta2, bottoneScelta3, bottoneScelta4};
        for(int i=0; i<bottoni.length; i++){
            if(i<scelte.length) {
                //se per quel bottone ho una scelta, setto il bottone
                bottoni[i].setText(scelte[i]);
                bottoni[i].setManaged(true);
                bottoni[i].setVisible(true);
            }
            else{
                //se non ho una scelta, levo il bottone
                bottoni[i].setManaged(false);
                bottoni[i].setVisible(false);
            }
        }
    }
    @FXML
    public void scelta1Click(){
        String comando=bottoneScelta1.getText();
        //vedo se è il nodo finale
        if(comando.equals("Fine")){
            System.exit(0);
            return;
        }  else if (comando.equals("Avanti"))  {
            aggiornaSchermata(nodoCorrente.getNextNode(0));
        }
        else if(nodoCorrente instanceof CombatNode){
            CombatNode combatNode = (CombatNode) nodoCorrente;
            GestoreCombattimenti gestoreCombattimenti=new GestoreCombattimenti(player, combatNode.getEnemy());
            testoStoria.setText(gestoreCombattimenti.getCombat());
        }
        else if(nodoCorrente instanceof RiddleNode){
            gestisciIndovinello(0);
        }
        else{
            aggiornaSchermata(nodoCorrente.getNextNode(0));
        }

    }
    @FXML
    public void scelta2Click(){
        if(nodoCorrente instanceof RiddleNode) gestisciIndovinello(1);
        else{
            aggiornaSchermata(nodoCorrente.getNextNode(1));
        }
    }
    @FXML
    public void scelta3Click(){
        if(nodoCorrente instanceof RiddleNode) gestisciIndovinello(2);
        else{
            aggiornaSchermata(nodoCorrente.getNextNode(2));
        }
    }
    @FXML
    public void scelta4Click(){
        if(nodoCorrente instanceof RiddleNode) gestisciIndovinello(3);
        else{
            aggiornaSchermata(nodoCorrente.getNextNode(3));
        }
    }
}
