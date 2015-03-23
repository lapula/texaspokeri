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
    
    @Test
    public void testAddBid() {
        player.addBid(10);
        assertEquals(10, player.getBid());
    }
    
    @Test
    public void testGetBid() {
        assertEquals(0, player.getBid());
    }
    
    @Test
    public void testResetBid() {
        player.addBid(10);
        player.resetBid();
        assertEquals(0, player.getBid());
    }
    
    @Test
    public void testIsAllIn() {
        assertFalse(player.isAllIn());
    }
    
    @Test
    public void testSetAllInTrue() {
        player.setAllInTrue();
        assertTrue(player.isAllIn());
    }
    
    @Test
    public void testResetAllIn() {
        player.setAllInTrue();
        player.resetAllIn();
        assertFalse(player.isAllIn());
    }
    
    @Test
    public void testGetMaxWin() {
        assertEquals(100, player.getMaxWin());
    }
    
    @Test
    public void testRefreshMaxWin() {
        player.alterBalance(-10);
        player.refreshMaxWin();
        assertEquals(90, player.getMaxWin());
    }
    
    
}
