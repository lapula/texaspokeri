/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.table;

import poker.table.Player;
import poker.table.AllPlayers;
import java.util.ArrayList;
import poker.table.AllPlayers;
import poker.table.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sara ja Laur
 */
public class AllPlayersTest {
    
    
    
    public AllPlayersTest() {
        
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
        AllPlayers.addPlayer(player);
        
        assertEquals(player, AllPlayers.getPlayers().get(0));
        
    }

    @Test
    public void testGetPlayers() {
        
        
        assertEquals(0, AllPlayers.getPlayers().size());
        
    }
    
}
