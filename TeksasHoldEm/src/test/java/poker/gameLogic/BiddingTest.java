/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.gameLogic;

import javax.swing.JTextArea;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import poker.table.Player;

/**
 *
 * @author Sara ja Laur
 */
public class BiddingTest {
    
    private Game game;
    private Bidding bidding;
    
    public BiddingTest() {
        
        game = new Game(new JTextArea());
    }
    
    @Before
    public void setUp() {
        game.startRound(0);
        bidding = game.getCurrentBidding();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testBidding() {
        assertEquals(0, bidding.highest());
    }

    @Test
    public void testStartBidding() {
        bidding.startBidding();
        assertEquals(10, bidding.highest());
    }

    @Test
    public void testTakeBiddingActionBid() {
        bidding.startBidding();
        bidding.takeBiddingAction("bid");
        
        assertEquals(1, bidding.getCurrentBidder().getId());
        assertEquals(5, bidding.getCurrentBidder().getBid());
    }
    
    @Test
    public void testTakeBiddingActionFold() {
        bidding.startBidding();
        assertEquals(0, bidding.getCurrentBidder().getBid());
        bidding.takeBiddingAction("fold");
        
        assertEquals(1, bidding.getCurrentBidder().getId());
        assertEquals(5, bidding.getCurrentBidder().getBid());
    }
    
    @Test
    public void testGameRound() {
        bidding.startBidding();
        bidding.takeBiddingAction("call");
        bidding.takeBiddingAction("call");
        bidding.takeBiddingAction("pass");
        bidding.takeBiddingAction("pass");
        
       
        assertEquals(2, game.getRoundNumber());
    }
    
    @Test
    public void testAllOthersAllIn() {
        bidding.startBidding();
        bidding.takeBiddingAction("allIn");
        
        assertFalse(bidding.isAllOthersAllIn());
        
        bidding.takeBiddingAction("allIn");
        assertTrue(bidding.isAllOthersAllIn());
        
    }

    
    
}
