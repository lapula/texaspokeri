/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import poker.cards.Card;
import poker.cards.Suit;

/**
 *
 * @author Sara ja Laur
 */
public class CodeToTextTest {
    
    CodeToText ctt;
    
    public CodeToTextTest() {
        
        ctt = new CodeToText();
        
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testCardText() {
        assertEquals("Two of Spades", ctt.cardText(new Card(Suit.SPADES, 0), -1));
        assertEquals("Three of Hearts", ctt.cardText(new Card(Suit.HEARTS, 1), -1));
        assertEquals("Four of Diamonds", ctt.cardText(new Card(Suit.DIAMONDS, 2), -1));
        assertEquals("Five of Clubs", ctt.cardText(new Card(Suit.CLUBS, 3), -1));
        assertEquals("Six of Clubs", ctt.cardText(new Card(Suit.CLUBS, 4), -1));
        assertEquals("Seven of Clubs", ctt.cardText(new Card(Suit.CLUBS, 5), -1));
        assertEquals("Eight of Clubs", ctt.cardText(new Card(Suit.CLUBS, 6), -1));
        assertEquals("Nine of Clubs", ctt.cardText(new Card(Suit.CLUBS, 7), -1));
        assertEquals("Ten of Clubs", ctt.cardText(new Card(Suit.CLUBS, 8), -1));
        assertEquals("Jack of Clubs", ctt.cardText(new Card(Suit.CLUBS, 9), -1));
        assertEquals("Queen of Clubs", ctt.cardText(new Card(Suit.CLUBS, 10), -1));
        assertEquals("King of Clubs", ctt.cardText(new Card(Suit.CLUBS, 11), -1));
        assertEquals("Ace of Clubs", ctt.cardText(new Card(Suit.CLUBS, 12), -1));
    }
    
    @Test
    public void testCardTextOnlyValue() {
        assertEquals("King", ctt.cardText(null, 11));
    }

    @Test
    public void testRatingToText() {
        assertEquals("High Card ", ctt.ratingToText(112.0));
        assertEquals("One Pair ", ctt.ratingToText(212.0));
        assertEquals("Two Pairs ", ctt.ratingToText(312.0));
        assertEquals("Three of a Kind ", ctt.ratingToText(412.0));
        assertEquals("Straight ", ctt.ratingToText(512.0));
        assertEquals("Flush ", ctt.ratingToText(612.0));
        assertEquals("Full House ", ctt.ratingToText(712.0));
        assertEquals("Four of a Kind ", ctt.ratingToText(812.0));
        assertEquals("Straight Flush", ctt.ratingToText(912.0));
        
    }
    
}
