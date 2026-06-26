package it.unicam.cs.mpgc.rpg126012;
import it.unicam.cs.mpgc.rpg126012.Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class StoryLoaderTest {
    private StoryLoader storyLoader;
    @TempDir
    Path tempDir;
    @BeforeEach
    void setUp() {
        storyLoader = new StoryLoader();
    }
    @Test
    @DisplayName("Test per vedere se il loader carica correttamente una storia collegando i nodi")
    void testLoadStory() throws Exception {
        String json = """
                {
                  "start": "n1",
                  "enemy": [ {"id": "orco", "name": "Orco", "healt": 50, "damage": 10} ],
                  "nodes": [
                    { "id": "n1", "type": "combat", "nemico": "orco", "nodoA": "n2", "nodoB": null },
                    { "id": "n2", "type": "dialogue", "text": "Vittoria!", "nodoA": null, "nodoB": null }
                  ]
                }""";
        Path fileJson = tempDir.resolve("storia_test.json");
        Files.writeString(fileJson, json);
        StoryData data = storyLoader.load(fileJson.toString());

        assertNotNull(data, "StoryData non deve essere null");

        StoryNode startNode = data.getNodoIniziale();
        assertNotNull(startNode, "Il nodo iniziale deve esistere");
        assertEquals("n1", startNode.getId(), "Il nodo iniziale deve essere n1");
        assertInstanceOf(CombatNode.class, startNode, "Il nodo n1 deve essere di tipo combat");

        StoryNode nextNode = startNode.getNextNode(0);
        assertNotNull(nextNode, "Il loader deve aver collegato il nodo n1 al nodo n2");
        assertEquals("n2", nextNode.getId(), "Il nodoA di n1 deve essere n2");
    }

    @Test
    @DisplayName("Test se manca la chiave 'start' nel JSON")
    void testLoadMissingStart() throws Exception {
        String jsonSenzaStart = """
                {
                  "enemy": [], "player": [],
                  "nodes": [ { "id": "n1", "type": "dialogue", "text": "Ciao" } ]
                }""";

        Path fileJson = tempDir.resolve("storia_no_start.json");
        Files.writeString(fileJson, jsonSenzaStart);
        assertThrows(Exception.class, () -> storyLoader.load(fileJson.toString()));
    }

    @Test
    @DisplayName("Test se un nodo punta a un nodo inesistente (collegamento rotto)")
    void testLoadBrokenLink() throws Exception {
        String jsonRotto = """
                {
                  "start": "n1",
                  "enemy": [], "player": [],
                  "nodes": [
                    { "id": "n1", "type": "dialogue", "text": "Ciao", "nodoA": "n_inesistente", "nodoB": null }
                  ]
                }""";
        Path fileJson = tempDir.resolve("storia_rotta.json");
        Files.writeString(fileJson, jsonRotto);
        assertThrows(Exception.class, () -> storyLoader.load(fileJson.toString()));
    }

}
