/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapula.poker.cards;

import lapula.poker.cards.Card;
import lapula.poker.cards.Suit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
public class CardTest {
    
    Card card;
    
    public CardTest() {
        card = new Card(Suit.CLUBS, 4);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void testGetSuitID() {
        assertEquals(2, card.getSuitID());
    }

    
    @Test
    public void testGetSuit() {
        assertEquals(Suit.CLUBS, card.getSuit());
    }

    
    @Test
    public void testGetValue() {
        assertEquals(4, card.getValue());
    }
    
    @Test
    public void testCompare() {
        Card card2 = new Card(Suit.HEARTS, 12);
        Card card3 = new Card(Suit.HEARTS, 11);
        
        ArrayList<Card> list = new ArrayList<Card>();
        
        list.add(card);
        list.add(card2);
        list.add(card3);
        
        Collections.sort(list);
        
        assertEquals(card2, list.get(2));
    }
    
}
