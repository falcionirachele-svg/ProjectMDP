package it.unicam.cs.mpgc.rpg126012;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.unicam.cs.mpgc.rpg126012.Model.Character.Enemy;
import it.unicam.cs.mpgc.rpg126012.Model.Character.Player;
import it.unicam.cs.mpgc.rpg126012.Model.Story.CombatNode;
import it.unicam.cs.mpgc.rpg126012.Model.InputOtput.NodeFactory;
import it.unicam.cs.mpgc.rpg126012.Model.Story.DialogueNode;
import it.unicam.cs.mpgc.rpg126012.Model.Story.StoryNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class NodeFactoryTest {
    private NodeFactory nodeFactory;
    @BeforeEach
    public void setUp() {
        nodeFactory= new NodeFactory();
    }
    @Test
    @DisplayName("Test buildEnemyArray verifico che resituisca una mappa di nemici")
    public void testBuildEnemyArray() {
        String jsonNemici="[{\"id\": \"orco\", \"name\": \"Orco\", \"health\": 100, \"damage\": 10}]";
        JsonArray enemy= JsonParser.parseString(jsonNemici).getAsJsonArray();
        Map<String, Enemy> enemyMap= nodeFactory.buildEnemyArray(enemy);
        assertEquals(1, enemyMap.size(), "La mappa di nemici deve contienere 1 elemento");
        assertTrue(enemyMap.containsKey("orco"), "La mappa di nemici deve contenere il nemico orco");
    }
    @Test
    @DisplayName("Test per verificare eccezione se il nemico/player è nullo")
    public void testNullException(){
        String jsonNull="[null]";
        JsonArray enemy= JsonParser.parseString(jsonNull).getAsJsonArray();
        assertThrows(NullPointerException.class, () -> nodeFactory.buildEnemyArray(enemy));
        assertThrows(NullPointerException.class, () -> nodeFactory.buildPlayerArray(enemy));
    }
    @Test
    @DisplayName("Eccezione se i nemici hanno lo stesso id")
    void testDuplicateEnemy(){
        String jsonDuplicate="[{\"id\": \"orco\", \"name\":\"Orco\", \"health\": 100, \"damage\": 10}, {\"id\": \"orco\", \"name\":\"Orco\", \"health\": 100, \"damage\": 10}]";
        JsonArray enemy= JsonParser.parseString(jsonDuplicate).getAsJsonArray();
        assertThrows(IllegalArgumentException.class, () -> nodeFactory.buildEnemyArray(enemy));
    }
    @Test
    @DisplayName("Eccezione se i player hanno lo stesso id")
    void testDuplicatePlayer(){
        String jsonDuplicate="[{\"id\":\"eroe_01\", \"name\":\"Eroe\", \"healt\":100, \"damage\":20}, {\"id\":\"eroe_01\", \"name\":\"Eroe\", \"healt\":100, \"damage\":20}]";
        JsonArray player= JsonParser.parseString(jsonDuplicate).getAsJsonArray();
        assertThrows(IllegalArgumentException.class, () -> nodeFactory.buildPlayerArray(player));
    }
    @Test
    @DisplayName("Deve costruire correttamente la mappa dei giocatori")
    void testBuildPlayerArray() {
        String jsonGiocatori = "[{\"id\":\"eroe_01\", \"name\":\"Eroe\", \"healt\":100, \"damage\":20}]";
        JsonArray array = JsonParser.parseString(jsonGiocatori).getAsJsonArray();
        Map<String, Player> mappaGiocatori = nodeFactory.buildPlayerArray(array);
        assertEquals(1, mappaGiocatori.size());
        assertTrue(mappaGiocatori.containsKey("eroe_01"));
    }
    @Test
    @DisplayName("Deve costruire un nodo Dialogo in base al type")
    void testBuildNodeDialogue() throws Exception {
        String jsonStr = "{\"id\":\"nodo_d1\", \"type\":\"dialogue\", \"text\":\"Ciao viaggiatore!\"}";
        JsonObject jsonObj = JsonParser.parseString(jsonStr).getAsJsonObject();
        Map<String, Enemy> enemyMap = new HashMap<>();
        StoryNode nodo = nodeFactory.buildNode(jsonObj, enemyMap);
        assertInstanceOf(DialogueNode.class, nodo, "Il nodo deve essere di tipo DialogueNode");
    }
    @Test
    @DisplayName("Deve costruire un nodo Combattimento e assegnargli il nemico")
    void testBuildNodeCombatSuccess() throws Exception {
        Map<String, Enemy> enemyMap = new HashMap<>();
        Enemy orco = new Enemy("orco_01", "Orco", 50, 10);
        enemyMap.put("orco_01", orco);
        String jsonStr = "{\"id\":\"nodo_c1\", \"type\":\"combat\", \"nemico\":\"orco_01\"}";
        JsonObject jsonObj = JsonParser.parseString(jsonStr).getAsJsonObject();
        StoryNode nodo = nodeFactory.buildNode(jsonObj, enemyMap);
        assertInstanceOf(CombatNode.class, nodo);
        CombatNode combatNode = (CombatNode) nodo;
        assertNotNull(combatNode.getEnemy(), "Il nemico deve essere stato assegnato al nodo");
        assertEquals("orco_01", combatNode.getEnemy().getId(), "Il nemico assegnato deve essere l'orco_01");
    }
    @Test
    @DisplayName("Deve lanciare un'eccezione se il tipo di nodo è sconosciuto")
    void testBuildNodeInvalidType() {
        String jsonStr = "{\"id\":\"nodo_x\", \"type\":\"tipo_inventato\"}";
        JsonObject jsonObj = JsonParser.parseString(jsonStr).getAsJsonObject();
        Map<String, Enemy> enemyMap = new HashMap<>();
        assertThrows(Exception.class, () -> nodeFactory.buildNode(jsonObj, enemyMap));
    }
}
