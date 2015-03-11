/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameLogic;

import cards.Card;
import cards.Deck;
import java.util.ArrayList;
import table.Player;
import table.Table;
import table.allPlayers;

/**
 *
 * @author Sara ja Laur
 */
public class Game {
    
    private int roundNumber;
    private final int PHASE = 4;
    private gameSettings gameSettings;
    private ArrayList<Player> currentPlayers;
    private Table table = new Table(0);
    private Deck deck;
    private Bidding bidding;
    private Resolve resolve;
    
    
    public Game() {
        
        this.roundNumber = 1;
        gameSettings = new gameSettings(4, 100);
        gameSettings.initialize();
        currentPlayers = allPlayers.getPlayers();
        
    }
    
    
    public void start() {
        System.out.println("Started!");
        
        while (true) {
            
            setUp();
                     
            for (int i = 0; i < PHASE; i++) {

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
            }
            
            resolve = new Resolve(currentPlayers, table);
            
            System.out.println(resolve.giveWinner());
            
            
            
            break;
        }
        
    }
    
    private void setUp() {
        
        deck = new Deck();
        table.resetPot();
        
        for (int i = 0; i < currentPlayers.size(); i++) {
            currentPlayers.get(i).addCard(deck.drawCard());
            currentPlayers.get(i).addCard(deck.drawCard());
        }
    }
    
    
}
