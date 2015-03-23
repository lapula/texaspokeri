/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.gameLogic;

import poker.cards.Card;
import poker.cards.Deck;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import poker.gui.PokerGUI;
import poker.table.Player;
import poker.table.Table;
import poker.table.AllPlayers;
import poker.util.CodeToText;
import poker.util.GameFeed;
import poker.util.TextReader;

/**
 *
 * @author Sara ja Laur
 */
public class Game {

    private JTextArea feed;
    private int roundNumber;
    private final int PHASE = 4;
    private GameSettings gameSettings;
    private ArrayList<Player> currentPlayers;
    private Table table = new Table(0);
    private Deck deck;
    private Bidding bidding;
    private Resolve resolve;
    private TextReader textReader;
    private CodeToText codeToText;
    private boolean isRunning = false;
    private AllPlayers allPlayers;

    public Game(JTextArea feed) {

        this.feed = feed;
        this.roundNumber = 0;
        this.gameSettings = new GameSettings(4, 100);
        this.gameSettings.initialize();
        allPlayers = gameSettings.getAllPlayers();
        this.currentPlayers = new ArrayList<>(allPlayers.getPlayers());
        this.textReader = new TextReader();
        this.codeToText = new CodeToText();
        

    }

    public void startRound(int round) {
        isRunning = true;
        System.out.println("round: " + round);
        this.roundNumber = round;
        
        if (roundNumber == 0) {
            setUp();
        }
        

                //System.out.println("");
        //System.out.println("PLAYER ORDER");
        GameFeed.addText(feed, "PLAYER ORDER");

        for (int a = 0; a < currentPlayers.size(); a++) {
            GameFeed.addText(feed, "" + currentPlayers.get(a).getId());
        }
        GameFeed.addText(feed, "");

        if (round == 0) {
            bidding = new Bidding(true, this);
        } else if (round == 1) {
            table.addCard(deck.drawCard());
            table.addCard(deck.drawCard());
            table.addCard(deck.drawCard());
            bidding = new Bidding(false, this);
        } else {
            table.addCard(deck.drawCard());
            bidding = new Bidding(false, this);
        }
        
        
        
       //bidding.startBidding();
        
        GameFeed.addText(feed, "");
        GameFeed.addText(feed, "ROUND!!!");
        GameFeed.addText(feed, "table money: " + table.getPot());
        GameFeed.addText(feed, "size:" + currentPlayers.size());
        GameFeed.addText(feed, "");

    }

    public void finishRound() {

        GameFeed.addText(feed, "Table Cards: ");
        for (int i = 0; i < 5; i++) {
            GameFeed.addText(feed, codeToText.cardText(table.getCards().get(i), -1));
        }
        GameFeed.addText(feed, "");
        for (int i = 0; i < currentPlayers.size(); i++) {
            GameFeed.addText(feed, "Player " + currentPlayers.get(i).getId() + " cards:");
            GameFeed.addText(feed, codeToText.cardText(currentPlayers.get(i).getCards().get(0), -1));
            GameFeed.addText(feed, codeToText.cardText(currentPlayers.get(i).getCards().get(1), -1));
        }
        
        System.out.println(table.getCards().size());

        resolve = new Resolve(currentPlayers, table);

        HashMap<Player, Double> result = resolve.giveWinner();

        GameFeed.addText(feed, "Winners are:");
        for (Player player : result.keySet()) {
            GameFeed.addText(feed, "Player " + player.getId() + " with a rating of: " + codeToText.ratingToText(result.get(player)));
        }

        
        for (Player player : result.keySet()) {
            player.alterBalance(table.getPot() / result.keySet().size());
        }

        GameFeed.addText(feed, "SIZE ALL: " + allPlayers.getPlayers().size());
        endOfRound();
        isRunning = false;

    }

    private void setUp() {

        deck = new Deck();

        for (int i = 0; i < currentPlayers.size(); i++) {
            System.out.println("!!");
            currentPlayers.get(i).addCard(deck.drawCard());
            currentPlayers.get(i).addCard(deck.drawCard());
        }
    }

    private void endOfRound() {

        table.resetPot();
        table.getCards().removeAll(table.getCards());

        for (int i = 0; i < allPlayers.getPlayers().size(); i++) {

            Player player = (Player) allPlayers.getPlayers().get(i);
            player.getCards().removeAll(player.getCards());
            player.refreshMaxWin();
            player.resetAllIn();
            GameFeed.addText(feed, "END OF ROUND BALANCE: " + player.getId() + ": " + player.getBalance());
            if (player.getBalance() < 10) {
                GameFeed.addText(feed, "Remove player: " + player.getId());
                allPlayers.removePlayer(player);
                i--;
            }

        }

        currentPlayers = new ArrayList<>(allPlayers.getPlayers());
        Player player = currentPlayers.get(0);
        currentPlayers.remove(0);
        currentPlayers.add(player);

    }

    private void dividePot(HashMap<Player, Double> result) {

        int pot = table.getPot();

        while (true) {

            int win = 0;

            for (Player player : result.keySet()) {
                win = player.getMaxWin() * currentPlayers.size();
                player.alterBalance(pot);
                pot = pot - win;
                currentPlayers.remove(player);
            }

            if (pot <= 0 || currentPlayers.size() < 1) {
                break;
            } else {
                resolve = new Resolve(currentPlayers, table);
                result = new HashMap<>(resolve.giveWinner());
            }
        }
    }
    
    public int getRoundNumber() {
        return this.roundNumber;
    }
    
    public ArrayList<Player> getCurrentPlayers() {
        return this.currentPlayers;
    }
    
    public void setCurrentPlayers(ArrayList<Player> newPlayers) {
        this.currentPlayers = newPlayers;
    }
    
    public JTextArea getFeed() {
        return this.feed;
    }

    public Bidding getCurrentBidding() {
        return this.bidding;
    }
    
    public boolean isRunning() {
        return this.isRunning;
    }
    
    public AllPlayers getGameAllPlayers() {
        return this.allPlayers;
    }

}
