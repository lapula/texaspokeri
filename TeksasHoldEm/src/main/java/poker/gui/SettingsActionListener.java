/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Sara ja Laur
 */
public class SettingsActionListener implements ActionListener {
    
    private JSlider balance;
    private JSlider playersHuman;
    private JSlider playersAI;
    private JButton start;
    private JFrame frame;
    
    public SettingsActionListener(JSlider balance, JSlider playersHuman, JSlider playersAI, JButton start, JFrame frame) {
        this.balance = balance;
        this.playersHuman = playersHuman;
        this.playersAI = playersAI;
        this.start = start;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if (ae.getSource() == start) {
            
            SoundPlayer sp = new SoundPlayer();
            sp.playSound("pass");

            try {
                FileWriter writer = new FileWriter("teksts/settings.txt");
                writer.write("" + balance.getValue() + "\n");
                writer.write("" + playersHuman.getValue() + "\n");
                writer.write("" + playersAI.getValue() + "\n");
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(SettingsActionListener.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            frame.dispose();
            SwingUtilities.invokeLater(new PokerGUI());
        }
        
    }

    
    
}
