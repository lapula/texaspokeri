/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.gui;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import poker.gameLogic.Bidding;
import poker.gameLogic.Game;

/**
 *
 * @author Sara ja Laur
 */
public class PokerActionListener implements ActionListener {

    private Game game;
    private JTextArea feed;
    private JButton start;
    private JButton fold;
    private JButton call;
    private JButton raise;
    private JButton allIn;
    private JButton bid;
    private JButton pass;
    private boolean started;
    private ButtonRenderer buttonRenderer;
    
    

    public PokerActionListener(JTextArea feed, JButton start, JButton fold,
            JButton call, JButton raise, JButton allIn,
            JButton bid, JButton pass) {
        this.start = start;
        this.fold = fold;
        this.call = call;
        this.raise = raise;
        this.allIn = allIn;
        this.bid = bid;
        this.pass = pass;
        this.game = new Game(feed);
        this.feed = feed;
        
        

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == start) {
            
            game.startRound(0);
            game.getCurrentBidding().startBidding();
        } else if (ae.getSource() == call) {
            
            game.getCurrentBidding().takeBiddingAction("call");
        } else if (ae.getSource() == fold) {
            
            game.getCurrentBidding().takeBiddingAction("fold");
        } else if (ae.getSource() == allIn) {
            
            game.getCurrentBidding().takeBiddingAction("allIn");
        } else if (ae.getSource() == pass) {
            
            game.getCurrentBidding().takeBiddingAction("pass");
        } else if (ae.getSource() == raise) {
            
            game.getCurrentBidding().takeBiddingAction("raise");
        } else if (ae.getSource() == bid) {
            
            game.getCurrentBidding().takeBiddingAction("bid");
        }

    }

    public Game getGame() {
        return this.game;
    }

}
