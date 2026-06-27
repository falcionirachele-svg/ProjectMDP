package it.unicam.cs.mpgc.rpg126012;

import it.unicam.cs.mpgc.rpg126012.Model.Story.GestoreIndovinelli;
import it.unicam.cs.mpgc.rpg126012.Model.Story.RiddleNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GestoreIndovinelliTest {
    private RiddleNode riddleNode;
    @BeforeEach
    public void setUp() {
        riddleNode= new RiddleNode();
        List<String> answers= List.of("risposta1", "risposta2", "risposta3");
        riddleNode.setAnswers(answers);
        riddleNode.setRightAnswer(1);
    }
    @Test
    @DisplayName("Test di isIndovinato")
    public void testIsIndovinato() {
        GestoreIndovinelli gestore = new GestoreIndovinelli(riddleNode, 1);
        assertTrue(gestore.isIndovinato());
        gestore = new GestoreIndovinelli(riddleNode, 0);
        assertFalse(gestore.isIndovinato());
    }
}
