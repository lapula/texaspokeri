/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.gameLogic;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JTextArea;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import poker.table.AllPlayers;
import poker.table.Player;
import poker.table.Table;

/**
 *
 * @author Sara ja Laur
 */
public class GameTest {

    Game game;
    Table table;
    Player playerOne;
    Player playerTwo;
    Player playerThree;
    Resolve resolve;
    ArrayList<Player> players;

    public GameTest() {
        game = new Game(new JTextArea());
        /*playerOne = new Player(100, true, 1);
        playerTwo = new Player(200, true, 2);
        playerThree = new Player(300, true, 3);

        players = new ArrayList<Player>();
        players.add(playerOne);
        players.add(playerTwo);
        players.add(playerThree);*/
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testStartRound() {
        game.startRound(0);
        
        assertTrue(game.getGameTable().getCards().isEmpty());
    }

    @Test
    public void testFinishGame() {
        game.startRound(0);
        game.startRound(1);
        game.startRound(2);
        game.startRound(3);
        game.finishGame();
        
        assertFalse(game.isRunning());
        assertNotNull(game.getWinner());
        assertEquals(0, game.getGameTable().getPot());
        assertNotNull(game.getFeed().getText());
    }

    

}
