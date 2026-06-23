package it.unicam.cs.mpgc.rpg126012;
import it.unicam.cs.mpgc.rpg126012.Controller.GameControllerFX;
import it.unicam.cs.mpgc.rpg126012.Model.Player;
import it.unicam.cs.mpgc.rpg126012.Model.StoryData;
import it.unicam.cs.mpgc.rpg126012.Model.StoryLoader;
import it.unicam.cs.mpgc.rpg126012.Model.StoryNode;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            //carcio la storia del json
            StoryLoader storyLoader = new StoryLoader();
            //gli passo il file json e carico la storia
            StoryData storia = storyLoader.load("src/main/resources/NodeStory.json");
            //mi salvo il nodo di partenza per usarlo nel controller
            StoryNode nodoPartenza = storia.getNodoIniziale();
            //carico il file fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/SchermataGioco.fxml"));
            Parent root = loader.load();
            //prendo il controller dal file fxml
            GameControllerFX controller = loader.getController();
            //genero un Player preimpostato
            Player player= new Player("giocatore", "Giocatore", 180, 20);
            //inizio il gioco
            controller.startGame(nodoPartenza, player);
            //creo la scena e la setto
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            System.err.println("Errore durante il caricamento della storia");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Lancia l'applicazione JavaFX
        launch(args);
    }
}
