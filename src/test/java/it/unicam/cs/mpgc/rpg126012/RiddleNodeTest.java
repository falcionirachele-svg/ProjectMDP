package it.unicam.cs.mpgc.rpg126012;

import it.unicam.cs.mpgc.rpg126012.Model.Story.CombatNode;
import it.unicam.cs.mpgc.rpg126012.Model.Story.RiddleNode;
import it.unicam.cs.mpgc.rpg126012.Model.Story.StoryNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RiddleNodeTest {
    private RiddleNode riddleNode;
    private StoryNode nextNode;
    @BeforeEach
    public void setUp(){
        riddleNode = new RiddleNode();
        riddleNode.setText("Indovinello");
        List<String> risposte = Arrays.asList("opzione1", "opzione2", "opzione3");
        riddleNode.setAnswers(risposte);
        riddleNode.setRightAnswer(1);
        nextNode = new CombatNode();
    }
    @Test
    @DisplayName("Test risposta corretta con gli indici giusti")
    void testIsRightAnswer() {
        assertTrue(riddleNode.isRightAnswer(1), "L'indice 1 deve risultare corretto");
        assertFalse(riddleNode.isRightAnswer(0), "L'indice 0 deve risultare errato");
        assertFalse(riddleNode.isRightAnswer(2), "L'indice 2 deve risultare errato");
        assertThrows(IllegalArgumentException.class, () -> riddleNode.isRightAnswer(-1));
        assertThrows(IllegalArgumentException.class, () -> riddleNode.isRightAnswer(3));
    }
    @Test
    @DisplayName("arrayAnswers deve gestire bene la lista")
    void testArrayAnswers() {
        String[] array = riddleNode.arrayAnswers();
        assertEquals(3, array.length, "L'array deve contenere 3 elementi");
        assertEquals("opzione2", array[1], "Il secondo elemento deve essere opzione2");
        RiddleNode nodoVuoto = new RiddleNode();
        assertThrows(IllegalArgumentException.class, nodoVuoto::arrayAnswers);
    }
    @Test
    @DisplayName("La navigazione e la fine della storia devono gestire bene i nodi")
    void testNavigazioneENodi() {
        assertTrue(riddleNode.isLastNode(), "Senza nodoA, deve essere l'ultimo nodo");
        riddleNode.setNodoA(nextNode);
        assertFalse(riddleNode.isLastNode(), "Ora che ha il nodoA, non è più l'ultimo");
        assertEquals(nextNode, riddleNode.getNextNode(0), "L'indice 0 deve portare al nodoA");
        assertNull(riddleNode.getNextNode(1), "L'indice 1 per il nodo indovinello deve restituire null");
        assertThrows(IllegalArgumentException.class, () -> riddleNode.getNextNode(99), "Un indice diverso da 0 o 1 deve far scattare l'IllegalArgumentException");
    }

}
