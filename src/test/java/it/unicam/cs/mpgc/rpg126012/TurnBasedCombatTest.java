package it.unicam.cs.mpgc.rpg126012;

import it.unicam.cs.mpgc.rpg126012.Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TurnBasedCombatTest {
    private Player player;
    private Enemy enemy;
    private TurnBasedCombat combat;

    @BeforeEach
    void setUp() {
        player = new Player("p1", "Giocatore", 100, 20);
        enemy = new Enemy("e1", "Nemico", 50, 10);
        combat = new TurnBasedCombat(enemy, player);
    }

    @Test
    @DisplayName("Test calcolo vita in base al danno")
    void testEseguiTurno() {
        int vitaInizialePlayer = player.getCurrentHealth();
        int vitaInizialeEnemy = enemy.getCurrentHealth();
        Risultato risultato = combat.eseguiTurno();
        assertNotNull(risultato, "Il metodo eseguiTurno deve restituire un Risultato valido");
        int dannoSubito = combat.getDannoRicevuto();
        int dannoInflitto = combat.getDannoInflitto();
        assertEquals(vitaInizialePlayer - dannoSubito, player.getCurrentHealth(),
                "La vita del giocatore non è stata scalata correttamente!");

        assertEquals(vitaInizialeEnemy - dannoInflitto, enemy.getCurrentHealth(),
                "La vita del nemico non è stata scalata correttamente!");
    }

    @Test
    @DisplayName("Test fine combattimento")
    void testFineCombattimento() {
        assertFalse(combat.fineCombattimento(), "La battaglia non deve essere finita all'inizio");
        enemy.setCurrentHealth(0);
        combat.eseguiTurno();
        assertTrue(combat.fineCombattimento(), "Se il nemico va a 0, la battaglia deve finire");
        assertTrue(combat.isPlayerWin(), "Se il nemico va a 0 e il player è vivo, il player vince");
    }
}
