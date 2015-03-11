/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameLogic;

import table.allPlayers;

/**
 *
 * @author Sara ja Laur
 */
public class Game {
    
    private int roundNumber;
    private gameSettings gameSettings;
    
    public Game() {
        
        this.roundNumber = 1;
        gameSettings = new gameSettings(4, 100);
        gameSettings.initialize();
        
    }
    
    
    public void start() {
        System.out.println("Started!");
        System.out.println(allPlayers.getPlayers().size());
    }
    
    
}
