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
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *
 * @author Sara ja Laur
 */
public class PokerGUI implements Runnable {

    private final int HEIGHT = 800;
    private final int WIDTH = 1200;
    private Timer timer;
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

        GameFeedRenderer right = new GameFeedRenderer(new BorderLayout());
        //JPanel middle = new JPanel(new BorderLayout());
        JPanel middle = new JPanel(new BorderLayout());

        JTextArea middleTop = new JTextArea();

        JTextArea rightTop = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(rightTop);
        JButton start = new JButton("Start");
        start.setPreferredSize(new Dimension(700, 100));

        rightTop.setBackground(Color.cyan);
        right.add(scrollPane, BorderLayout.CENTER);
        right.add(start, BorderLayout.SOUTH);
        middleTop.setEditable(false);
        middle.add(middleTop, BorderLayout.CENTER);

        rightTop.setEditable(false);
        right.setPreferredSize(new Dimension(300, HEIGHT));

        JButton fold = new JButton("Fold");
        JButton call = new JButton("Call");
        JButton raise = new JButton("Raise");
        JButton allIn = new JButton("All In");
        JButton bid = new JButton("Bid");
        JButton pass = new JButton("Pass");
        PokerActionListener listener = new PokerActionListener(rightTop, start, fold, call, raise, allIn, bid, pass);
        ButtonRenderer middleButtons = new ButtonRenderer(new FlowLayout(), start, fold, call, raise, allIn, bid, pass, listener);

        middle.add(middleButtons, BorderLayout.SOUTH);
        middleButtons.setPreferredSize(new Dimension(700, 100));
        bid.setPreferredSize(new Dimension(215, 90));
        fold.setPreferredSize(new Dimension(215, 90));
        call.setPreferredSize(new Dimension(215, 90));
        raise.setPreferredSize(new Dimension(215, 90));
        allIn.setPreferredSize(new Dimension(215, 90));
        pass.setPreferredSize(new Dimension(215, 90));

        middleButtons.setBackground(Color.GREEN);

        middleButtons.add(fold);
        middleButtons.add(call);
        middleButtons.add(raise);

        middleButtons.add(bid);
        middleButtons.add(pass);
        bid.setVisible(false);
        pass.setVisible(false);

        middleButtons.add(allIn);

        pass.setEnabled(false);
        fold.setEnabled(false);
        call.setEnabled(false);
        raise.setEnabled(false);
        allIn.setEnabled(false);
        bid.setEnabled(false);
        start.setEnabled(true);

        start.addActionListener(listener);
        fold.addActionListener(listener);
        call.addActionListener(listener);
        raise.addActionListener(listener);
        allIn.addActionListener(listener);
        bid.addActionListener(listener);
        pass.addActionListener(listener);

        container.add(middle, BorderLayout.CENTER);
        container.add(right, BorderLayout.EAST);

    }

}
