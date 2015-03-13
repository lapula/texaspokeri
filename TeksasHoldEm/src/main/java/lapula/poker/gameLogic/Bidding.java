/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapula.poker.gameLogic;

import java.util.ArrayList;
import lapula.poker.table.AllPlayers;
import lapula.poker.table.Player;

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
    
    public ArrayList<Player> startBidding() {
        
        int highest = 0;
        
        while (true) {
            
            for (int i = 0; i < players.size(); i++) {
                
                if (players.get(i).isHuman()) {
                    System.out.println("Choose action: pass");
                    
                    players.get(i).addBid(0);
                    
                } else {
                    players.get(i).addBid(highest);
                }
                
            }
            
            if (biddingDone()) {
                return players;
            }
            
        }
        
        
        
    }
    
    private boolean biddingDone() {
        
        if (players.size() == 1) {
            return true;
        }
        
        for (int i = 0; i < players.size() - 1; i++) {
            if (players.get(i).bid() == players.get(i + 1).bid()) {
                continue;
            } else {
                return false;
            }
        }
        
        return true;
        
    }
    
}
