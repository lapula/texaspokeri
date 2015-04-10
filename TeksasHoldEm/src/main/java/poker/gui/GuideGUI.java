/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.plaf.metal.MetalSliderUI;

/**
 *
 * @author Sara ja Laur
 */
public class GuideGUI implements Runnable, ActionListener {

    private JFrame frame;
    private final int HEIGHT = 700;
    private final int WIDTH = 500;
    private JButton button;
    private JTextArea text;

    @Override
    public void run() {

        frame = new JFrame("Ikkuna");
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        createComponents(frame.getContentPane());
        frame.pack();
        frame.setResizable(false);

        frame.setTitle("Settings");

        frame.setVisible(true);

    }

    public JFrame getFrame() {
        return frame;
    }

    private void createComponents(Container container) {

        container.setLayout(new BorderLayout());

        
        button = new JButton("Close");
        text = new JTextArea();
        text.setEditable(false);
        text.setBackground(new Color(0, 107, 60));
        text.setForeground(Color.white);
        button.addActionListener(this);
        
        File file = new File("teksts/guide.txt");
        try {
            Scanner scanner = new Scanner(file);
            
            while (scanner.hasNextLine()) {
                text.append(" " + scanner.nextLine() + "\n");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("GUIDE TEXT NOT FOUND");
            Logger.getLogger(GuideGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        container.add(text);
        container.add(button, BorderLayout.SOUTH);
        
        
        

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        frame.dispose();
    }
}
