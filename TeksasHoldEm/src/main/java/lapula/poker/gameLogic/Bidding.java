/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapula.poker.gameLogic;

import java.util.ArrayList;
import java.util.HashMap;
import lapula.poker.table.AllPlayers;
import lapula.poker.table.Player;
import lapula.poker.table.Table;
import lapula.poker.util.TextReader;

/**
 *
 * @author Sara ja Laur
 */
public class Bidding {
    
    private boolean bid;
    private ArrayList<Player> players;
    private TextReader textReader;
    private static boolean buttonsBid = true;
    
    public Bidding(ArrayList<Player> players) {
        this.players = players;
        this.textReader = new TextReader();
        
    }
    
    public ArrayList<Player> startBidding() {
        
        int end = players.size();
        int highest = 0;
        Player lastRaised = null;
        
        for (int i = 0; i < end; i++) {
            
            Player player = players.get(i % players.size());
            String order = "call";
                
                if (player.isHuman()) {
                    System.out.println("PLAYER: " + player.getId());
                    System.out.println("Current cost to call is: " + (highest - player.getBid()));
                    System.out.println("Your money: " + player.getBalance());
                    System.out.println("Give order:");
                    order = textReader.read();
                }    
                
                if (order.equals("call")) {
                    
                    boolean succeeded = call(player, (highest - player.getBid()));
                    
                    if (!succeeded) {
                        System.out.println("You do not have enough money");
                        players.remove(player);
                    } else {
                        player.addBid(highest - player.getBid());
                    }
                    
                } else if (order.equals("bid")) {
                    
                    System.out.print("Amount to bid: ");
                    int amount = Integer.parseInt(textReader.read());
                    
                    boolean succeeded = bid(player, amount);
                    
                    if (!succeeded) {
                        System.out.println("You did not have enough money");
                    } else {
                        highest += amount;
                        player.addBid(amount);
                        end += players.size();
                        lastRaised = player;
                    }
                        
                } else if (order.equals("raise")) {
                    
                    System.out.print("Raise by: ");
                    int amount = Integer.parseInt(textReader.read());
                    
                    boolean succeeded = raise(player, highest, amount);
                    
                    if (!succeeded) {
                        System.out.println("You did not have enough money");
                    } else {
                        player.addBid(amount + (highest - player.getBid()) );
                        highest += amount;
                        end += players.size();
                        lastRaised = player;
                    }
                      
                } else if (order.equals("fold")) { 
                    
                    players.remove(player);
                    
                } else {
                    if (lastRaised != null) {
                        if (player.getId() == lastRaised.getId()) {
                            break;
                        }
                    }
                    //pass
                }
            
            
        }
        AllPlayers.resetBids();
        return players;
        
    }
    
    public static boolean buttonsBid() {
        return buttonsBid;
    }
    
    
    
    private boolean call(Player player, int highest) {
        
        boolean succeeded = player.alterBalance(-highest);
        
        if (!succeeded) {
            return false;
        }
        
        Table.addToPot(highest);
        return true;
    }
    
    private boolean bid(Player player, int sum) {
        
        boolean succeeded = player.alterBalance(-sum);
        
        if (!succeeded) {
            return false;
        }
        
        Table.addToPot(sum);
        return true;
        
    }
    
    private boolean raise(Player player, int highest, int sum) {
        
        boolean succeeded = player.alterBalance(-(sum + highest));
        
        if (!succeeded) {
            return false;
        }
        
        Table.addToPot(sum + highest);
        return true;
        
    }
    

}
