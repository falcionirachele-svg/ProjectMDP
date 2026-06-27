package it.unicam.cs.mpgc.rpg126012;

import it.unicam.cs.mpgc.rpg126012.Model.Combatt.GestoreTurno;
import it.unicam.cs.mpgc.rpg126012.Model.Combatt.Risultato;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GestoreTurnoTest {
    private GestoreTurno gestore;
    @Test
    @DisplayName("Test reset life player")
    public void testResetLifePlayer(){
        gestore = new GestoreTurno(100, 100, 100);
        gestore.setVitaGiocatore(50);
        gestore.resetLifePlayer();
        assertEquals(100, gestore.getVitaGiocatore());
    }
    @Test
    @DisplayName("Test endBattle")
    public void testEndBattle(){
        gestore = new GestoreTurno(100, 100, 100);
        assertFalse(gestore.endBattle());
        gestore.setVitaGiocatore(0);
        assertTrue(gestore.endBattle());
        assertFalse(gestore.isPlayerWin());
    }
    @Test
    @DisplayName("Test getRisultato")
    public void testGetRisultato(){
        gestore = new GestoreTurno(100, 80, 65);
        Risultato risultato= gestore.getRisultato();
        assertEquals(80, risultato.getPlayerHealth());
        assertEquals(65, risultato.getEnemyHealth());
    }
}
