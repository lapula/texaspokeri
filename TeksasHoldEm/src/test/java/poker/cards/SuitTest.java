/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.cards;

import poker.cards.Suit;
import poker.cards.Suit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sara ja Laur
 */
public class SuitTest {
        
    public SuitTest() {
        
    }

    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void testSuitID() {
        assertEquals(1, Suit.HEARTS.id);
    }


    
    @Test
    public void testGetByID() {
        assertEquals(Suit.SPADES, Suit.getByID(0));
    }
    
}
