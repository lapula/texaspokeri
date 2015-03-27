/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.gameLogic;

import poker.cards.Deck;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JTextArea;
import poker.gui.SoundPlayer;
import poker.table.Player;
import poker.table.Table;
import poker.table.AllPlayers;
import poker.util.CodeToText;
import poker.util.GameFeed;

/**
 *
 * @author Sara ja Laur
 */
public class Game {

    private JTextArea feed;
    private int roundNumber;
    private GameSettings gameSettings;
    private ArrayList<Player> currentPlayers;
    private Table table;
    private Deck deck;
    private Bidding bidding;
    private Resolve resolve;
    private CodeToText codeToText;
    private boolean isRunning = false;
    private AllPlayers allPlayers;
    private Player winner;
    private boolean isSetupping = true;
    private SoundPlayer soundPlayer;

    public Game(JTextArea feed) {
        
        this.table = new Table(0);
        this.feed = feed;
        this.roundNumber = 0;
        this.gameSettings = new GameSettings();
        this.gameSettings.initialize();
        this.allPlayers = gameSettings.getAllPlayers();
        this.currentPlayers = new ArrayList<>(allPlayers.getPlayers());
        this.codeToText = new CodeToText();
        this.soundPlayer = new SoundPlayer();

    }

    public void startRound(int round) {
        
        isRunning = true;
        this.roundNumber = round;
        
        
        if (roundNumber == 0) {
            isSetupping = true;
            setUp();
            isSetupping = false;
        }
        
        

        if (round == 0) {
            bidding = new Bidding(true, this);
        } else if (round == 1) {
            table.addCard(deck.drawCard());
            table.addCard(deck.drawCard());
            table.addCard(deck.drawCard());
            soundPlayer.playSound("dealThree");
            bidding = new Bidding(false, this);
        } else {
            table.addCard(deck.drawCard());
            soundPlayer.playSound("deal");
            bidding = new Bidding(false, this);
        }
        
        GameFeed.addText(feed, "-----------------------------------------------");
        GameFeed.addText(feed, "");
        GameFeed.addText(feed, "ROUND!!!");
        GameFeed.addText(feed, "");
        GameFeed.addText(feed, "PLAYER ORDER");

        for (int a = 0; a < currentPlayers.size(); a++) {
            GameFeed.addText(feed, "" + currentPlayers.get(a).getId());
        }
        GameFeed.addText(feed, "");
        GameFeed.addText(feed, "----------------------------------------------");
        GameFeed.addText(feed, "");
        
    }

    public void finishGame() {

        /*GameFeed.addText(feed, "TABLE CARDS: ");
        for (int i = 0; i < 5; i++) {
            GameFeed.addText(feed, codeToText.cardText(table.getCards().get(i), -1));
        }
        GameFeed.addText(feed, "");
        for (int i = 0; i < currentPlayers.size(); i++) {
            GameFeed.addText(feed, "Player " + currentPlayers.get(i).getId() + " cards:");
            GameFeed.addText(feed, codeToText.cardText(currentPlayers.get(i).getCards().get(0), -1));
            GameFeed.addText(feed, codeToText.cardText(currentPlayers.get(i).getCards().get(1), -1));
            GameFeed.addText(feed, "");
        }*/


        resolve = new Resolve(currentPlayers, table);

        HashMap<Player, Double> result = resolve.giveWinner();
        

        GameFeed.addText(feed, "Winner is:");
        for (Player player : result.keySet()) {
            GameFeed.addText(feed, "Player " + player.getId() + " with a " + codeToText.ratingToText(result.get(player)));
            winner = player;
        }
        GameFeed.addText(feed, "");

        
        /*for (Player player : result.keySet()) {
            player.alterBalance(table.getPot() / result.keySet().size());
        }*/
        dividePot(result);

        GameFeed.addText(feed, "SIZE ALL: " + allPlayers.getPlayers().size());
        endOfRound();
        isRunning = false;
        
        if (allPlayers.getPlayers().size() == 1) {
            Player endWinner =  (Player) allPlayers.getPlayers().get(0);
            GameFeed.addText(feed, "PLAYER " + endWinner.getId() + " HAS CLEANED THE TABLE!");
            GameFeed.addText(feed, "GAME HAS ENDED!");
        }

    }

    private void setUp() {
        
        soundPlayer.playSound("start");
        
        for (int i = 0; i < allPlayers.getPlayers().size(); i++) {

            Player player = (Player) allPlayers.getPlayers().get(i);
            
            player.refreshMaxWin();
            player.resetAllIn();
            /*
            if (player.getBalance() < 10) {
                GameFeed.addText(feed, "Remove player: " + player.getId());
                allPlayers.removePlayer(player);
                i--;
            }*/

        }
        
        table.getCards().removeAll(table.getCards());
        table.resetPot();
        
        for (int i = 0; i < allPlayers.getPlayers().size(); i++) {
            Player player = (Player) allPlayers.getPlayers().get(i);
            player.getCards().removeAll(player.getCards());
        }
        
        deck = new Deck();

        for (int i = 0; i < currentPlayers.size(); i++) {
            
            currentPlayers.get(i).addCard(deck.drawCard());
            currentPlayers.get(i).addCard(deck.drawCard());
        }
    }

    private void endOfRound() {
        
        for (int i = 0; i < allPlayers.getPlayers().size(); i++) {

            Player player = (Player) allPlayers.getPlayers().get(i);
            
            player.refreshMaxWin();
            player.resetAllIn();
            GameFeed.addText(feed, "END OF ROUND BALANCE: " + player.getId() + ": " + player.getBalance());
            if (player.getBalance() < 10) {
                GameFeed.addText(feed, "Remove player: " + player.getId());
                allPlayers.removePlayer(player);
                i--;
            }

        }
        GameFeed.addText(feed, "");

        currentPlayers = new ArrayList<>(allPlayers.getPlayers());
        Player player = currentPlayers.get(0);
        currentPlayers.remove(0);
        currentPlayers.add(player);

    }

    public void dividePot(HashMap<Player, Double> result) {
        
        ArrayList<Player> copy = new ArrayList<>(currentPlayers);
        int pot = table.getPot();

        while (true) {

            int win = 0;

            for (Player player : result.keySet()) {
                win = player.getMaxWin() * allPlayers.getPlayers().size();
                
                if (win < pot) {
                    player.alterBalance(win);
                } else {
                    player.alterBalance(pot);
                    
                }
                pot = pot - win;
                copy.remove(player);
            }

            if (pot <= 0 || copy.size() < 1) {
                if (pot > 0) {
                    System.out.println("POT WAS NOT DIVIDED FULLY, " + pot + " REMAINED");
                }
                break;
            } else {
                resolve = new Resolve(copy, table);
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
    
    public Table getGameTable() {
        return this.table;
    }
    
    public Player getWinner() {
        return this.winner;
    }
    
    public boolean isSetupping() {
        return this.isSetupping;
    }

}
