package it.unicam.cs.mpgc.rpg126012;
import it.unicam.cs.mpgc.rpg126012.Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Player player;
    private int dannoBase=20;
    @BeforeEach
    public void setUp(){
        player=new Player("player1", "Player", 100, dannoBase);
    }

    @Test
    @DisplayName("Test di isPlayer")
    public void isPlayer() {
        assertTrue(player.isPlayer());
    }
    @Test
    @DisplayName("Test che i danni rispettino i limiti imposti")
    public void testDamageLimits() {
        for(int i = 0; i < 1000; i++) {
            int dannoEffettivo = player.getDamage();
            if (player.getColpoCritico()) {
                assertTrue(dannoEffettivo >= (dannoBase - 10) * 2 && dannoEffettivo <= (dannoBase + 10) * 2,
                        "Errore critico: Danno " + dannoEffettivo + " fuori dai limiti!");
            } else {
                assertTrue(dannoEffettivo >= (dannoBase - 10) && dannoEffettivo <= (dannoBase + 10),
                        "Errore normale: Danno " + dannoEffettivo + " fuori dai limiti!");
            }
        }
    }
    @Test
    @DisplayName("Test che i danni critici compaiano con circa il 15% di probabilità")
    public void testCriticalDamageProbability() {
        int conteggioCritici = 0;
        int tentativi = 10000;
        for(int i = 0; i < tentativi; i++) {
            player.getDamage();
            if (player.getColpoCritico()) {
                conteggioCritici++;
            }
        }
        double percentuale = (double) conteggioCritici / tentativi * 100;
        assertTrue(percentuale >= 13.0 && percentuale <= 17.0,
                "Percentuale ottenuta: " + percentuale + "%, prevista 15%");
    }

}
