/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextArea;
import javax.swing.*;
/**
 *
 * @author Sara ja Laur
 */
public class PokerGUI implements Runnable {
    
    
    private final int HEIGHT = 800;
    private final int WIDTH = 1200;
    private JFrame frame;

    @Override
    public void run() {
        // Luo sovelluksesi tänne
        frame = new JFrame("Ikkuna");
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();

        frame.setVisible(true);
    }
    
    public JFrame getFrame() {
        return frame;
    }
    
    private void createComponents(Container container) {
        
        container.setLayout(new BorderLayout());
        
        JPanel right = new JPanel(new BorderLayout());
        JPanel middle = new JPanel(new BorderLayout());
        JPanel middleButtons = new JPanel(new GridLayout(1, 4));
        JTextArea middleTop = new JTextArea();
        middleButtons.setPreferredSize(new Dimension(700, 100));
        JTextField rightTop = new JTextField();
        JButton rightBottom = new JButton("Start");
        rightBottom.setPreferredSize(new Dimension(700, 100));
        
        right.add(rightTop, BorderLayout.CENTER);
        right.add(rightBottom, BorderLayout.SOUTH);
        middleTop.setEditable(false);
        middle.add(middleTop, BorderLayout.CENTER);
        middle.add(middleButtons, BorderLayout.SOUTH);
        
        rightTop.setEditable(false);
        right.setPreferredSize(new Dimension(300, HEIGHT));
        
        JButton fold = new JButton("Fold");
        JButton call = new JButton("Call");
        JButton raise = new JButton("Raise");
        JButton allIn = new JButton("All In");
        
        middleButtons.add(fold);
        middleButtons.add(call);
        middleButtons.add(raise);
        middleButtons.add(allIn);
        
        container.add(middle, BorderLayout.CENTER);
        container.add(right, BorderLayout.EAST);
        
    }
    
}
