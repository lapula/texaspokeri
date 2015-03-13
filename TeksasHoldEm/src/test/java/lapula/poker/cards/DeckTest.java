/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapula.poker.cards;

import lapula.poker.cards.Card;
import lapula.poker.cards.Deck;
import lapula.poker.cards.Suit;
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
public class DeckTest {
    
    Deck deck;
    
    public DeckTest() {
        deck = new Deck();
    }
    
    
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void testDeckIsShuffled() {
        
        Card card = deck.drawCard();
        
        assertNotEquals(new Card(Suit.SPADES, 0), card);
        
    }
    
    @Test
    public void testDrawCard() {
        
        Card card = deck.drawCard();
        
        assertNotNull(card);
        
    }
    
    @Test
    public void testDrawAllCards() {
        
        for (int i = 0; i < 51; i++) {
            deck.drawCard();
        }
        
        assertNotNull(deck.drawCard());
        
    }
    
}
