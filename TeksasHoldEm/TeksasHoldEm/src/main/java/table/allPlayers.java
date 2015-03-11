/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table;

import java.util.ArrayList;

/**
 *
 * @author Sara ja Laur
 */
public class allPlayers {
    
    private static ArrayList<Player> players = new ArrayList<Player>();
    
    public allPlayers() {
        
    }
    
    public static void addPlayer(Player player) {
        players.add(player);
    }
    
    public static ArrayList getPlayers() {
        return players;
    }
    
}
