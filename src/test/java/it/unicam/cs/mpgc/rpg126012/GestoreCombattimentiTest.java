package it.unicam.cs.mpgc.rpg126012;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import it.unicam.cs.mpgc.rpg126012.Model.*;

import static org.junit.jupiter.api.Assertions.*;

public class GestoreCombattimentiTest {
    private GestoreCombattimenti gestore;
    private Player player;
    private Enemy enemy;
    @BeforeEach
    public void setUp() {
        player=new Player("giocatore1","Giocatore", 100, 10);
        enemy=new Enemy("nemico1", "Orco", 120, 10);
        gestore=new GestoreCombattimenti(player, enemy);
    }
    @Test
    @DisplayName("Test per vedere se il combattimento risulta finito")
    public void testEndBattle() {
        assertFalse(gestore.endBattle(), "Appena inizializzato il combattimento non deve essere finito");
        while(player.isAlive() || enemy.isAlive()) {
            gestore.eseguiTurno();
        }
        assertTrue(gestore.endBattle(), "Se il giocatore o l'enemy non sono più in vita, il combattimento deve essere finito");
    }
    @Test
    @DisplayName("Test per vedere se il testo di fine battaglia viene stampato")
    public void testTestoBattle() {
        while(!gestore.endBattle()) {
            gestore.eseguiTurno();
        }
        String logTesto=gestore.testoBattle();
        if(player.isAlive()) assertTrue(logTesto.contains("Hai Vinto!"), "Se il giocatore è in vita, il testo deve contenere 'Hai Vinto!'");
        else assertTrue(logTesto.contains("Hai Perso!"), "Se l'enemy è in vita, il testo deve contenere 'Hai Perso!'");
    }
}
