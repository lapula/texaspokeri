/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.gameLogic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import poker.table.Player;
import poker.table.AllPlayers;

/**
 *
 * @author Sara ja Laur
 */
public class GameSettings {
    
    private AllPlayers allPlayers;
    private Scanner scanner;
    
    
    public GameSettings() {
        
        this.allPlayers = new AllPlayers();
        
        
    }
    
    public void initialize() {
        
        File file = new File("teksts/settings.txt");
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException ex) {
            System.out.println("SETTINGS NOT LOADED");
        }
        
        int startingBalance = Integer.parseInt(scanner.nextLine());
        int humanPlayers = Integer.parseInt(scanner.nextLine());
        int aiPlayers = Integer.parseInt(scanner.nextLine());
        int a = 0;
        
        for (int i = 1; i < humanPlayers + 1; i++) {
            allPlayers.addPlayer(new Player(startingBalance, true, i));
            a++;
        }
        for (int i = a; i < aiPlayers + a; i++) {
            allPlayers.addPlayer(new Player(startingBalance, false, i));
        }
        
    }
    
    public AllPlayers getAllPlayers() {
        return this.allPlayers;
    }
    
}
