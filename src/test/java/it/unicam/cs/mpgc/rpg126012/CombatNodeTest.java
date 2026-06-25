package it.unicam.cs.mpgc.rpg126012;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import it.unicam.cs.mpgc.rpg126012.Model.*;
public class CombatNodeTest {
    private CombatNode combatNode;
    private Enemy enemy;
    private CombatNode nextNodeA;
    private CombatNode nextNodeB;

    @BeforeEach
    public void setUp() {
        enemy=new Enemy("orco", "Orco", 50, 10);
        combatNode=new CombatNode(enemy);
        combatNode.setId("nodoCombat1");
        nextNodeA=new CombatNode();
        nextNodeA.setId("nodoA");
        nextNodeB=new CombatNode();
        nextNodeB.setId("nodoB");
    }
    @Test
    @DisplayName("Test nemico del CombatNode corretto")
    public void testGetEnemy() {
        assertNotNull(combatNode.getEnemy(), "enemy non può essere null");
        assertEquals("orco",combatNode.getEnemy().getId(), "nemico non corretto");
    }
    @Test
    @DisplayName("Test dei getter dei nodi successivi")
    public void testGetNextNodeA() {
        combatNode.setNodoA(nextNodeA);
        combatNode.setNodoB(nextNodeB);
        assertEquals(nextNodeA, combatNode.getNextNodeA(), "nodo A non corretto");
        assertEquals(nextNodeB, combatNode.getNextNodeB(), "nodo B non corretto");
        assertThrows(IllegalArgumentException.class, () -> combatNode.getNextNode(99));
    }
    @Test
    @DisplayName("Test di isLastNode")
    void testIsLastNode() {
        assertTrue(combatNode.isLastNode(), "Se non ci sono nodi sucessivi deve essere l'ultimo nodo");
        combatNode.setNodoA(nextNodeA);
        assertFalse(combatNode.isLastNode(), "Se ci sono nodi sucessivi non deve essere l'ultimo nodo");
    }

}
