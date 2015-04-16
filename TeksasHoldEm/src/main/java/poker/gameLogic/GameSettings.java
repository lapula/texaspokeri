
package poker.gameLogic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import poker.table.Player;
import poker.table.AllPlayers;

/**
 * Lukee pelin asetukset tekstitiedostosta joka muokataan settings - paneelissa.
 */
public class GameSettings {
    
    private AllPlayers allPlayers;
    private Scanner scanner;
    
    
    public GameSettings() {
        
        this.allPlayers = new AllPlayers();
        
        
    }
    
    
    
    /**
     * Luo pelaajat ja asettaa rahamäärän (yms.) tekstitiedoston pohjalta.
     */
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
        int a = 1;
        
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
