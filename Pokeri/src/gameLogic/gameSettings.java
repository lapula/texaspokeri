/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameLogic;

import java.util.ArrayList;
import table.Player;
import table.allPlayers;

/**
 *
 * @author Sara ja Laur
 */
public class gameSettings {
    
    int playerCount;
    int startingBalance;
    
    
    public gameSettings(int playerCount, int startingBalance) {
        
        
        this.playerCount = playerCount;
        this.startingBalance = startingBalance;
        
        
    }
    
    public void initialize() {
        allPlayers.addPlayer(new Player(startingBalance, true));
        for (int i = 0; i < playerCount - 1; i++) {
            allPlayers.addPlayer(new Player(startingBalance, false));
        }
    }
    
}
