/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.table;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sara ja Laur
 */
public class AllPlayersTest {
    
    AllPlayers allPlayers;
    
    public AllPlayersTest() {
        allPlayers = new AllPlayers();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAddPlayer() {
        
        Player player = new Player(100, true, 1); 
        allPlayers.addPlayer(player);
        
        assertEquals(player, allPlayers.getPlayers().get(0));
        
    }

    @Test
    public void testGetPlayers() {
        
        Player player = new Player(100, true, 1); 
        allPlayers.addPlayer(player);
        
        assertEquals(1, allPlayers.getPlayers().size());
        
    }
    
    @Test
    public void testRemovePlayer() {
        
        Player remove = new Player(100, true, 2); 
        Player player = new Player(100, true, 1); 
        allPlayers.addPlayer(player);
        
        allPlayers.removePlayer(remove);
        
        assertEquals(player, allPlayers.getPlayers().get(0));
        
    }
    
    @Test
    public void testResetBids() {
        
        Player player = new Player(100, true, 1);
        player.addBid(10);
        allPlayers.addPlayer(player);
        allPlayers.resetBids();
        Player compare = (Player) allPlayers.getPlayers().get(0);
        
        assertEquals(0, compare.getBid());
        
    }
    
}
