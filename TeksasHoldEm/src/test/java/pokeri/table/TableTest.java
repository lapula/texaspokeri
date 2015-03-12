/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokeri.table;

import pokeri.table.Table;
import pokeri.cards.Card;
import pokeri.cards.Suit;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sara ja Laur
 */
public class TableTest {
    
    Table table;
    
    public TableTest() {
        table = new Table(0);
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAddCard() {
        Card card = new Card(Suit.CLUBS, 4);
        table.addCard(card);
        assertEquals(card, table.getCards().get(0));
        
    }

    @Test
    public void testGetCards() {
        Card card = new Card(Suit.CLUBS, 4);
        table.addCard(card);
        assertEquals(1, table.getCards().size());
    }

    @Test
    public void testAddToPot() {
        table.addToPot(10);
        assertEquals(10, table.getPot());
    }
    
    @Test
    public void testAddToPotWithNegativeSum() {
        table.addToPot(-10);
        assertEquals(0, table.getPot());
    }

    @Test
    public void testGetPot() {
        assertEquals(0, table.getPot());
    }

    @Test
    public void testResetPot() {
        table.addToPot(100);
        table.resetPot();
        assertEquals(0, table.getPot());
    }
    
}
