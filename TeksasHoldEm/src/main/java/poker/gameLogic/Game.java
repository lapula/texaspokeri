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
import poker.table.Player;
import poker.table.Table;
import poker.table.AllPlayers;
import poker.util.TextReader;

/**
 *
 * @author Sara ja Laur
 */
public class Game {
    
    private int roundNumber;
    private final int PHASE = 4;
    private GameSettings gameSettings;
    private ArrayList<Player> currentPlayers;
    private Table table = new Table(0);
    private Deck deck;
    private Bidding bidding;
    private Resolve resolve;
    private TextReader textReader;
    
    
    public Game() {
        
        this.roundNumber = 1;
        gameSettings = new GameSettings(4, 100);
        gameSettings.initialize();
        currentPlayers = new ArrayList<>(AllPlayers.getPlayers());
        textReader = new TextReader();
        
    }
    
    
    public void start() {
        System.out.println("Started!");
        
        while (true) {
            
            
            
            setUp();
                     
            for (int i = 0; i < PHASE; i++) {
                
                System.out.println("");
                System.out.println("PLAYER ORDER");
                for (int a = 0; a < currentPlayers.size(); a++) {
                    System.out.println(currentPlayers.get(a).getId());
                }
                System.out.println("");

                if (i == 0) {
                    //nothing;
                } else if (i == 1) {
                    table.addCard(deck.drawCard());
                    table.addCard(deck.drawCard());
                    table.addCard(deck.drawCard());
                } else {
                    table.addCard(deck.drawCard());
                }
                
                
                bidding = new Bidding(currentPlayers);
                currentPlayers = bidding.startBidding();
                System.out.println("");
                System.out.println("ROUND!!!");
                System.out.println("");
                System.out.println("table money: " + table.getPot());
                System.out.println("size: " + currentPlayers.size());
            }
            
            /**
             * VÄLIAIKAINEN TARKISTUSMETODI
             */
            
            System.out.println("Table Cards: ");
            for (int i = 0; i < 5; i++) {
                System.out.print(table.getCards().get(i).getValue() + ", ");
            }
            System.out.println("");
            for (int i = 0; i < currentPlayers.size(); i++) {
                System.out.println("Player " + currentPlayers.get(i).getId() + " cards:");
                System.out.println(currentPlayers.get(i).getCards().get(0).getValue());
                System.out.println(currentPlayers.get(i).getCards().get(1).getValue());
            }
            /**
             * loppuu tähän.
             */
            
            resolve = new Resolve(currentPlayers, table);
            
            HashMap<Player, Double> result = resolve.giveWinner();
            
            
            System.out.println("Winners are:");
            for (Player player : result.keySet()) {
                System.out.println("Player " + player.getId() + " with a rating of: " + result.get(player));
            }
            
            /**
             * PÄIVITÄ!
             */
            for (Player player : result.keySet()) {
                player.alterBalance(table.getPot() / result.keySet().size());
            }
            
            System.out.println("SIZE ALL: " + AllPlayers.getPlayers().size());
            endOfRound();
            
            
            System.out.println("Quit? (Y/N)");
            String answer = textReader.read();
            
            if (answer.equals("Y") || currentPlayers.size() < 2) {
                break;
            }
        }
        
    }
    
    private void setUp() {
        
        deck = new Deck();
        
        for (int i = 0; i < currentPlayers.size(); i++) {
            currentPlayers.get(i).addCard(deck.drawCard());
            currentPlayers.get(i).addCard(deck.drawCard());
        }
    }
    
    private void endOfRound() {
        
        table.resetPot();
        table.getCards().removeAll(table.getCards());
        
        
        for (int i = 0; i < AllPlayers.getPlayers().size(); i++) {
            
            Player player = (Player) AllPlayers.getPlayers().get(i);
            player.getCards().removeAll(player.getCards());
            player.refreshMaxWin();
            player.resetAllIn();
            System.out.println("END OF ROUND BALANCE: " + player.getId() + ": " + player.getBalance());
            if (player.getBalance() < 10) {
                System.out.println("Remove player: " + player.getId());
                AllPlayers.removePlayer(player);
                i--;
            }
            
        }
        
        currentPlayers = new ArrayList<>(AllPlayers.getPlayers());
        Player player = currentPlayers.get(0);
        currentPlayers.remove(0);
        currentPlayers.add(player);
        
    }
    
    private void dividePot(HashMap<Player, Double> result) {
        for (Player player : result.keySet()) {
            player.alterBalance(table.getPot() / result.keySet().size());
        }
    }
    
    
}
