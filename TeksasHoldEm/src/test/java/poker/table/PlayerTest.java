/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.table;

import poker.table.Player;
import poker.cards.Card;
import poker.cards.Suit;
import java.util.ArrayList;
import poker.table.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sara ja Laur
 */
public class PlayerTest {
    
    Player player;
    
    public PlayerTest() {
        player = new Player(100, true, 1);
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void testAddCardWorks() {
        Card card = new Card(Suit.CLUBS, 2);
        player.addCard(card);
        assertEquals(1, player.getCards().size());
    }

    
    @Test
    public void testGetCards() {
        Card card = new Card(Suit.CLUBS, 2);
        player.addCard(card);
        assertEquals(card, player.getCards().get(0));
    }

    
    @Test
    public void testAlterBalance() {
        player.alterBalance(10);
        assertEquals(110, player.getBalance());
    }
    
    @Test
    public void testAlterBalanceToUnderZero() {
        player.alterBalance(-200);
        assertEquals(100, player.getBalance());
    }

    
    @Test
    public void testGetBalance() {
        assertEquals(100, player.getBalance());
    }

    
    @Test
    public void testIsHuman() {
        assertTrue(player.isHuman());
    }
    
    @Test
    public void testGetId() {
        
        assertEquals(1, player.getId());
        
    }
    
}
