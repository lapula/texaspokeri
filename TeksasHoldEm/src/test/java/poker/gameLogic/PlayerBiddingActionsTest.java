/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.gameLogic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import poker.table.Player;
import poker.table.Table;

/**
 *
 * @author Sara ja Laur
 */
public class PlayerBiddingActionsTest {
    
    private PlayerBiddingActions pba;
    private Player player;
    private Table table;
    
    public PlayerBiddingActionsTest() {
        
        player = new Player(100, true, 1);
        table = new Table(0);
        pba = new PlayerBiddingActions(table);
        
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testCall() {
        assertTrue(pba.call(player, 10));
        assertEquals(10, table.getPot());
    }

    @Test
    public void testBid() {
        assertTrue(pba.bid(player, 10));
        assertEquals(10, table.getPot());
    }

    @Test
    public void testRaise() {
        assertTrue(pba.raise(player, 10, 10));
        assertEquals(20, table.getPot());
    }
    
    @Test
    public void testCallFail() {
        assertFalse(pba.call(player, 200));
        assertEquals(0, table.getPot());
    }

    @Test
    public void testBidFail() {
        assertFalse(pba.bid(player, 200));
        assertEquals(0, table.getPot());
    }

    @Test
    public void testRaiseFail() {
        assertFalse(pba.raise(player,10, 200));
        assertEquals(0, table.getPot());
    }
    
}
