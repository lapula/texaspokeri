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
        assertEquals("King of Clubs", ctt.cardText(new Card(Suit.CLUBS, 11), -1));
    }
    
    @Test
    public void testCardTextOnlyValue() {
        assertEquals("King", ctt.cardText(null, 11));
    }

    @Test
    public void testRatingToText() {
        assertEquals("Three of a Kind ", ctt.ratingToText(412.0));
    }
    
}
