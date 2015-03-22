/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.gameLogic;

import java.util.ArrayList;
import java.util.HashMap;
import poker.cards.Card;
import poker.cards.Suit;
import poker.table.Player;
import poker.table.Table;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sara ja Laur
 */
public class ResolveTest {
    
    Table table;
    Player playerOne;
    Player playerTwo;
    Resolve resolve;
    
    public ResolveTest() {
        table = new Table(0);
        table.addCard(new Card(Suit.CLUBS, 1));
        table.addCard(new Card(Suit.SPADES, 3));
        table.addCard(new Card(Suit.CLUBS, 5));
        table.addCard(new Card(Suit.CLUBS, 7));
        table.addCard(new Card(Suit.SPADES, 10));
        playerOne = new Player(100, true, 1);
        playerTwo = new Player(100, true, 2);
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testResolveClearWinner() {
        
        ArrayList<Player> players = new ArrayList<>();
        
        players.add(playerOne);
        players.add(playerTwo);
        
        playerOne.addCard(new Card(Suit.CLUBS, 7));
        playerOne.addCard(new Card(Suit.SPADES, 7));
        playerTwo.addCard(new Card(Suit.CLUBS, 12));
        playerTwo.addCard(new Card(Suit.SPADES, 12));
        
        resolve = new Resolve(players, table);
        Player winner = null;
        
        for (Player player : resolve.giveWinner().keySet()) {
            winner = player;
        }
        
        assertEquals(playerOne ,winner);
        assertEquals(1, resolve.giveWinner().keySet().size());
        
    }
    
    @Test
    public void testResolveOnePlayer() {
        
        ArrayList<Player> players = new ArrayList<>();
        
        players.add(playerOne);
        
        playerOne.addCard(new Card(Suit.CLUBS, 7));
        playerOne.addCard(new Card(Suit.SPADES, 7));
        
        resolve = new Resolve(players, table);
        Player winner = null;
        
        for (Player player : resolve.giveWinner().keySet()) {
            winner = player;
        }
        
        assertEquals(playerOne ,winner);
        assertEquals(1, resolve.giveWinner().keySet().size());
        
    }
    
    @Test
    public void testResolveWinnerByHighCard() {
        
        ArrayList<Player> players = new ArrayList<>();
        
        players.add(playerOne);
        players.add(playerTwo);
        
        playerOne.addCard(new Card(Suit.CLUBS, 11));
        playerOne.addCard(new Card(Suit.HEARTS, 10));
        playerTwo.addCard(new Card(Suit.CLUBS, 12));
        playerTwo.addCard(new Card(Suit.DIAMONDS, 10));
        
        resolve = new Resolve(players, table);
        Player winner = null;
        
        for (Player player : resolve.giveWinner().keySet()) {
            winner = player;
        }
        
        assertEquals(playerTwo ,winner);
        assertEquals(1, resolve.giveWinner().keySet().size());
        
    }
    
    @Test
    public void testResolveWinnerByLowCard() {
        
        ArrayList<Player> players = new ArrayList<>();
        
        players.add(playerOne);
        players.add(playerTwo);
        
        playerOne.addCard(new Card(Suit.CLUBS, 0));
        playerOne.addCard(new Card(Suit.HEARTS, 10));
        playerTwo.addCard(new Card(Suit.CLUBS, 3));
        playerTwo.addCard(new Card(Suit.DIAMONDS, 10));
        
        resolve = new Resolve(players, table);
        Player winner = null;
        
        for (Player player : resolve.giveWinner().keySet()) {
            winner = player;
        }
        
        assertEquals(playerTwo ,winner);
        assertEquals(1, resolve.giveWinner().keySet().size());
        
    }
    
    @Test
    public void testResolveWinnerByLowCard2() {
        
        table = new Table(0);
        table.addCard(new Card(Suit.CLUBS, 9));
        table.addCard(new Card(Suit.DIAMONDS, 12));
        table.addCard(new Card(Suit.HEARTS, 7));
        table.addCard(new Card(Suit.HEARTS, 10));
        table.addCard(new Card(Suit.DIAMONDS, 7));
        
        ArrayList<Player> players = new ArrayList<>();
        
        players.add(playerOne);
        players.add(playerTwo);
        
        playerOne.addCard(new Card(Suit.DIAMONDS, 8));
        playerOne.addCard(new Card(Suit.SPADES, 9));
        playerTwo.addCard(new Card(Suit.CLUBS, 3));
        playerTwo.addCard(new Card(Suit.DIAMONDS, 9));
        
        resolve = new Resolve(players, table);
        Player winner = null;
        
        for (Player player : resolve.giveWinner().keySet()) {
            winner = player;
        }
        
        assertEquals(playerOne ,winner);
        assertEquals(1, resolve.giveWinner().keySet().size());
        
    }
}
