/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameLogic;

import java.util.ArrayList;
import table.allPlayers;
import table.Player;

/**
 *
 * @author Sara ja Laur
 */
public class Bidding {
    
    private boolean bid;
    private ArrayList<Player> players;
    
    public Bidding(ArrayList<Player> players) {
        this.players = players;
    }
    
    public void startBidding() {
        
        bid = false;
        
        while (true) {
            
            int bid = 0;
            
            for (int i = 0; i < players.size(); i++) {
                
                if (players.get(i).isHuman()) {
                    
                } else {
                    
                }
                
            }
            
        }
        
    }
    
}
