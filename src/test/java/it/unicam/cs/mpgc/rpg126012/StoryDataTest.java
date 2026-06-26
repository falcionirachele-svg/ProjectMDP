package it.unicam.cs.mpgc.rpg126012;

import it.unicam.cs.mpgc.rpg126012.Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class StoryDataTest {
    private StoryData storyData;
    private StoryNode startNode;
    private StoryNode nextNode;
    private Map<String, StoryNode> nodeMap;
    @BeforeEach
    void setUp(){
        startNode = new DialogueNode();
        nextNode = new DialogueNode();
        nodeMap = new HashMap<>();
        nodeMap.put("inizio", startNode);
        nodeMap.put("nodo_2", nextNode);
        storyData = new StoryData(nodeMap, startNode);
    }
    @Test
    @DisplayName("Test per il getter del nodo iniziale")
    void testGetStartNode() {
        assertEquals(startNode, storyData.getNodoIniziale());
    }
    @Test
    @DisplayName("Test per il getter del mappa dei nodi")
    void testGetNodeMap() {
        Map<String, StoryNode> mappa = storyData.getNodeMap();
        assertNotNull(mappa, "La mappa non può essere null");
        assertEquals(2, mappa.size(), "La mappa deve contenere 2 elementi");
        assertTrue(mappa.containsKey("inizio"), "La mappa deve contenere il nodo iniziale");
        assertTrue(mappa.containsKey("nodo_2"), "La mappa deve contenere il secondo nodo");
    }
}
