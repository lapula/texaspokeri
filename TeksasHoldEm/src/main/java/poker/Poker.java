/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;


import javax.swing.SwingUtilities;
import poker.gameLogic.Game;
import poker.gui.PokerGUI;
import poker.gui.SettingsGUI;

/**
 *
 * @author Laur
*/

public class Poker {

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new SettingsGUI());
        //SwingUtilities.invokeLater(new PokerGUI());
    }
    
}
