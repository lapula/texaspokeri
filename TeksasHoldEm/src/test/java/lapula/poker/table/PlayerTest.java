/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapula.poker.table;

import lapula.poker.table.Player;
import lapula.poker.cards.Card;
import lapula.poker.cards.Suit;
import java.util.ArrayList;
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
        player = new Player(100, true);
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
    public void testBid() {
        assertEquals(0, player.bid());
    }

    
    @Test
    public void testAddBid() {
        player.addBid(10);
        assertEquals(10, player.bid());
    }
    
    @Test
    public void testAddBidWithNegative() {
        player.addBid(-10);
        assertEquals(0, player.bid());
    }

    
    @Test
    public void testResetBid() {
        
        player.addBid(10);
        player.resetBid();
        assertEquals(0, player.bid());
        
    }
    
}
