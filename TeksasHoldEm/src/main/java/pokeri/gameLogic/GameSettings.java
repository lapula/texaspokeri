/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokeri.gameLogic;

import java.util.ArrayList;
import pokeri.table.Player;
import pokeri.table.AllPlayers;

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
        AllPlayers.addPlayer(new Player(startingBalance, true));
        for (int i = 0; i < playerCount - 1; i++) {
            AllPlayers.addPlayer(new Player(startingBalance, false));
        }
    }
    
}
