/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.gameLogic;

import java.util.ArrayList;
import poker.table.Player;
import poker.table.AllPlayers;

/**
 *
 * @author Sara ja Laur
 */
public class GameSettings {
    
    int playerCount;
    int startingBalance;
    
    
    public GameSettings(int playerCount, int startingBalance) {
        
        
        this.playerCount = playerCount;
        this.startingBalance = startingBalance;
        
        
    }
    
    public void initialize() {
        AllPlayers.addPlayer(new Player(startingBalance, true, 1));
        for (int i = 2; i < playerCount + 1; i++) {
            AllPlayers.addPlayer(new Player(startingBalance, false, i));
        }
        AllPlayers.addPlayer(new Player(startingBalance, true, 5));
    }
    
}
