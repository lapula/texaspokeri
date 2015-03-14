/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapula.poker.gameLogic;

import java.util.ArrayList;
import lapula.poker.cards.Card;
import lapula.poker.cards.Suit;
import lapula.poker.table.Player;
import lapula.poker.table.Table;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sara ja Laur
 */
public class HandRatingTest {
    
    Table table;
    Player player;
    HandRating handRating;
    private static final double DELTA = 1e-15;
    
    public HandRatingTest() {
        table = new Table(0);
        player = new Player(100, true, 1);
        handRating = new HandRating();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGiveRatingHighCard() {
        
        table.addCard(new Card(Suit.CLUBS, 1));
        table.addCard(new Card(Suit.SPADES, 3));
        table.addCard(new Card(Suit.CLUBS, 5));
        table.addCard(new Card(Suit.CLUBS, 7));
        table.addCard(new Card(Suit.SPADES, 10));
        table.addCard(new Card(Suit.HEARTS, 11));
        table.addCard(new Card(Suit.HEARTS, 12));
        
        assertEquals(112.0, handRating.giveRating(table.getCards()), DELTA);
        
    }
    
    @Test
    public void testGiveRatingOnePair() {
        
        table.addCard(new Card(Suit.CLUBS, 1));
        table.addCard(new Card(Suit.SPADES, 3));
        table.addCard(new Card(Suit.CLUBS, 5));
        table.addCard(new Card(Suit.SPADES, 7));
        table.addCard(new Card(Suit.CLUBS, 10));
        table.addCard(new Card(Suit.HEARTS, 11));
        table.addCard(new Card(Suit.HEARTS, 11));
        
        assertEquals(211.0, handRating.giveRating(table.getCards()), DELTA);
    }
    
    @Test
    public void testGiveRatingTwoPairs() {
        
        table.addCard(new Card(Suit.CLUBS, 1));
        table.addCard(new Card(Suit.SPADES, 3));
        table.addCard(new Card(Suit.CLUBS, 5));
        table.addCard(new Card(Suit.SPADES, 9));
        table.addCard(new Card(Suit.CLUBS, 9));
        table.addCard(new Card(Suit.HEARTS, 11));
        table.addCard(new Card(Suit.HEARTS, 11));
        
        assertEquals(311.09, handRating.giveRating(table.getCards()), DELTA);
    }
    
    @Test
    public void testGiveRatingThreeOfAKind() {
        
        table.addCard(new Card(Suit.CLUBS, 1));
        table.addCard(new Card(Suit.SPADES, 3));
        table.addCard(new Card(Suit.CLUBS, 3));
        table.addCard(new Card(Suit.SPADES, 3));
        table.addCard(new Card(Suit.CLUBS, 10));
        table.addCard(new Card(Suit.HEARTS, 11));
        table.addCard(new Card(Suit.HEARTS, 11));
        
        assertEquals(403.0, handRating.giveRating(table.getCards()), DELTA);
    }
    
    @Test
    public void testGiveRatingStraight() {
        
        table.addCard(new Card(Suit.CLUBS, 1));
        table.addCard(new Card(Suit.CLUBS, 2));
        table.addCard(new Card(Suit.SPADES, 2));
        table.addCard(new Card(Suit.CLUBS, 3));
        table.addCard(new Card(Suit.CLUBS, 4));
        table.addCard(new Card(Suit.HEARTS, 5));
        table.addCard(new Card(Suit.HEARTS, 6));
        
        assertEquals(506.0, handRating.giveRating(table.getCards()), DELTA);
    }
    
    @Test
    public void testGiveRatingFlush() {
        
        table.addCard(new Card(Suit.CLUBS, 1));
        table.addCard(new Card(Suit.HEARTS, 2));
        table.addCard(new Card(Suit.HEARTS, 3));
        table.addCard(new Card(Suit.HEARTS, 4));
        table.addCard(new Card(Suit.HEARTS, 5));
        table.addCard(new Card(Suit.HEARTS, 7));
        table.addCard(new Card(Suit.HEARTS, 8));
        
        assertEquals(608.0, handRating.giveRating(table.getCards()), DELTA);
    }
    
    @Test
    public void testGiveRatingFullHouse() {
        
        table.addCard(new Card(Suit.CLUBS, 2));
        table.addCard(new Card(Suit.HEARTS, 2));
        table.addCard(new Card(Suit.HEARTS, 3));
        table.addCard(new Card(Suit.SPADES, 4));
        table.addCard(new Card(Suit.HEARTS, 5));
        table.addCard(new Card(Suit.CLUBS, 5));
        table.addCard(new Card(Suit.SPADES, 5));
        
        assertEquals(705.02, handRating.giveRating(table.getCards()), DELTA);
    }
    
    @Test
    public void testGiveRatingFourOfAKind() {
        
        table.addCard(new Card(Suit.CLUBS, 9));
        table.addCard(new Card(Suit.HEARTS, 9));
        table.addCard(new Card(Suit.DIAMONDS, 9));
        table.addCard(new Card(Suit.SPADES, 9));
        table.addCard(new Card(Suit.DIAMONDS, 5));
        table.addCard(new Card(Suit.CLUBS, 5));
        table.addCard(new Card(Suit.SPADES, 5));
        
        assertEquals(809.0, handRating.giveRating(table.getCards()), DELTA);
    }
    
    @Test
    public void testGiveRatingStraightFlush() {
        
        table.addCard(new Card(Suit.CLUBS, 2));
        table.addCard(new Card(Suit.HEARTS, 2));
        table.addCard(new Card(Suit.HEARTS, 7));
        table.addCard(new Card(Suit.HEARTS, 8));
        table.addCard(new Card(Suit.HEARTS, 10));
        table.addCard(new Card(Suit.HEARTS, 11));
        table.addCard(new Card(Suit.HEARTS, 9));
        
        assertEquals(911.0, handRating.giveRating(table.getCards()), DELTA);
    }
    
    
}
