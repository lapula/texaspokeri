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
    public void testTakeBiddingAction() {
        
    }

    @Test
    public void testButtonsSetPassBid() {
        
    }

    @Test
    public void testGetCurrentBidder() {
        
    }

    @Test
    public void testCostToCall() {
        
    }
    
}
