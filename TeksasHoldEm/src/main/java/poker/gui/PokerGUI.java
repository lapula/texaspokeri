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
import javax.swing.*;
import javax.swing.plaf.metal.MetalSliderUI;

/**
 *
 * @author Sara ja Laur
 */
public class PokerGUI implements Runnable {

    private final int HEIGHT = 840;
    private final int WIDTH = 1200;
    private Timer timer;
    private JFrame frame;

    @Override
    public void run() {

        frame = new JFrame("Ikkuna");
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());
        frame.pack();
        frame.setResizable(false);
        frame.setTitle("Teksas Hold 'Em");

        frame.setVisible(true);

    }

    public JFrame getFrame() {
        return frame;
    }

    private void createComponents(Container container) {

        container.setLayout(new BorderLayout());

        ImageLoader imageLoader = new ImageLoader();

        GameFeedRenderer right = new GameFeedRenderer(new BorderLayout());
        JPanel middle = new JPanel(new BorderLayout());

        JTextArea feed = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(feed);
        JButton start = new JButton("Start");
        start.setPreferredSize(new Dimension(700, 100));
        
        JSlider slider = new JSlider();
        slider.setMajorTickSpacing(5);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(true);
        slider.setBackground(new Color(0, 107, 61));
        slider.setForeground(Color.white);
        slider.setValue(10);
        slider.setPreferredSize(new Dimension(500,40));
        setSliderUI(slider);

        feed.setForeground(Color.white);
        feed.setBackground(new Color(0, 107, 61));
        
        right.add(scrollPane, BorderLayout.CENTER);
        right.add(start, BorderLayout.SOUTH);

        feed.setEditable(false);
        right.setPreferredSize(new Dimension(300, HEIGHT));

        JButton fold = new JButton("Fold");
        JButton call = new JButton("Call");
        JButton raise = new JButton("Call & Raise by 10");
        JButton allIn = new JButton("All In");
        JButton bid = new JButton("Bid (10)");
        JButton pass = new JButton("Pass");
        PokerActionListener listener = new PokerActionListener(feed, start, fold, call, raise, allIn, bid, pass);
        ButtonRenderer buttons = new ButtonRenderer(new FlowLayout(), start, fold, call, raise, allIn, bid, pass, listener, slider);
        MainRenderer window = new MainRenderer(listener, imageLoader);
        middle.add(window, BorderLayout.CENTER);

        middle.add(buttons, BorderLayout.SOUTH);
        buttons.setPreferredSize(new Dimension(700, 100));
        bid.setPreferredSize(new Dimension(215, 90));
        fold.setPreferredSize(new Dimension(215, 90));
        call.setPreferredSize(new Dimension(215, 90));
        raise.setPreferredSize(new Dimension(215, 90));
        allIn.setPreferredSize(new Dimension(215, 90));
        pass.setPreferredSize(new Dimension(215, 90));

        buttons.setBackground(new Color(0, 107, 61));
        
        buttons.add(fold);
        buttons.add(call);
        buttons.add(raise);

        buttons.add(bid);
        buttons.add(pass);
        bid.setVisible(false);
        pass.setVisible(false);

        buttons.add(allIn);

        pass.setEnabled(false);
        fold.setEnabled(false);
        call.setEnabled(false);
        raise.setEnabled(false);
        allIn.setEnabled(false);
        bid.setEnabled(false);
        start.setEnabled(true);
        
        setButtonGraphics(pass, "Pass", imageLoader);
        setButtonGraphics(fold, "Fold", imageLoader);
        setButtonGraphics(call, "Call", imageLoader);
        setButtonGraphics(raise, "Raise", imageLoader);
        setButtonGraphics(allIn, "All In", imageLoader);
        setButtonGraphics(bid, "Bid", imageLoader);
        start.addActionListener(listener);
        fold.addActionListener(listener);
        call.addActionListener(listener);
        raise.addActionListener(listener);
        allIn.addActionListener(listener);
        bid.addActionListener(listener);
        pass.addActionListener(listener);
        
        
        
        
        


        container.add(middle, BorderLayout.CENTER);
        container.add(right, BorderLayout.EAST);
        container.add(slider, BorderLayout.SOUTH);

    }
    
    private void setButtonGraphics(JButton button, String text, ImageLoader imageLoader) {
        button.setIcon(new javax.swing.ImageIcon(imageLoader.loadButtonStyle1()));
        button.setRolloverIcon(new javax.swing.ImageIcon(imageLoader.loadButtonStyle2()));
        button.setPressedIcon(new javax.swing.ImageIcon(imageLoader.loadButtonStyle3()));
        button.setDisabledIcon(new javax.swing.ImageIcon(imageLoader.loadButtonStyle4()));
        button.setText("<html><font color = white>" + text + "</font></html>");
        button.setForeground(Color.white);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER);
    }
    
    private void setSliderUI(JSlider slider) {
        slider.setUI(new MetalSliderUI() {
            @Override
            protected void scrollDueToClickInTrack(int direction) {
                int value = slider.getValue();
                if (slider.getOrientation() == JSlider.HORIZONTAL) {
                    value = this.valueForXPosition(slider.getMousePosition().x);
                } else if (slider.getOrientation() == JSlider.VERTICAL) {
                    value = this.valueForYPosition(slider.getMousePosition().y);
                }
                slider.setValue(value);
            }
        }
        );
    }

}
