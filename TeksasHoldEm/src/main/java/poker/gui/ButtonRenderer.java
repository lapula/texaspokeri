/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.gui;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.UIManager;
import poker.gameLogic.Bidding;
import poker.gameLogic.Game;
import poker.table.Player;

/**
 *
 * @author Sara ja Laur
 */
public class ButtonRenderer extends JPanel {

    private JButton start;
    private JButton fold;
    private JButton call;
    private JButton raise;
    private JButton allIn;
    private JButton bid;
    private JButton pass;
    private PokerActionListener listener;
    private JSlider slider;
    private Player oldPlayer;

    public ButtonRenderer(LayoutManager lm, JButton start, JButton fold,
            JButton call, JButton raise, JButton allIn,
            JButton bid, JButton pass, PokerActionListener listener, JSlider slider) {
        super(lm);
        this.start = start;
        this.fold = fold;
        this.call = call;
        this.raise = raise;
        this.allIn = allIn;
        this.bid = bid;
        this.pass = pass;
        this.listener = listener;
        this.slider = slider;
        StartTimer();

    }

    private void StartTimer() {
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //System.out.println("asd");
                if (listener.getGame().isRunning()) {
                    setGame();
                    setButtons();
                    setSlider();
                } else {
                    setEnd();
                }

                repaint();
            }
        };
        new Timer(10, taskPerformer).start();
    }

    private void setButtons() {

        Game game = listener.getGame();
        Bidding bidding = game.getCurrentBidding();
        Player player = bidding.getCurrentBidder();

        pass.setVisible(false);
        bid.setVisible(false);
        call.setVisible(true);
        raise.setVisible(true);
        

        if (bidding.buttonsSetPassBid()) {

            pass.setVisible(true);
            bid.setVisible(true);
            call.setVisible(false);
            raise.setVisible(false);
            fold.setEnabled(false);

            if (bidding.isAllOthersAllIn()) {
                bid.setEnabled(false);
            }

        }

        if (player.getBalance() < 5) {
            call.setEnabled(false);
            raise.setEnabled(false);
            bid.setEnabled(false);
        }
        if (bidding.costToCall(player) >= player.getBalance()) {
            call.setEnabled(false);
            raise.setEnabled(false);
            bid.setEnabled(false);
        }
        if (bidding.costToCall(player) > player.getBalance()) {
            call.setEnabled(false);
            raise.setEnabled(false);
        }

    }
    
    private void setSlider() {
        
        Game game = listener.getGame();
        Bidding bidding = game.getCurrentBidding();
        Player player = bidding.getCurrentBidder();
        
        slider.setEnabled(true);
        slider.setMajorTickSpacing(5);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setLabelTable(slider.createStandardLabels(5));
        
        
        if (player.getBalance() >= 700) {
            slider.setMajorTickSpacing(50);
            slider.setMinorTickSpacing(5);
            slider.setPaintTicks(true);
            slider.setLabelTable(slider.createStandardLabels(50));
        } else if (player.getBalance() >= 200) {
            slider.setMajorTickSpacing(25);
            slider.setMinorTickSpacing(5);
            slider.setPaintTicks(true);
            slider.setLabelTable(slider.createStandardLabels(25));
        }
        
        bidding.setSliderValue(slider.getValue());
        
        if (oldPlayer != null) {
            if (oldPlayer.getId() != player.getId()) {
                slider.setValue(10);
            }
        }
        
        oldPlayer = player;
        
        slider.setMaximum(player.getBalance());
        slider.setMinimum(0);
        
        if (slider.getValue() == 0) {
            slider.setValue(5);
        }
        
        
        if (player.getBalance() < 5) {
            slider.setEnabled(false);
        }
        
        if (raise.isEnabled()) {
            slider.setMaximum(player.getBalance() - (bidding.highest() - player.getBid()));
        }
        
        if (!bid.isEnabled() && !raise.isEnabled()) {
            slider.setEnabled(false);
        }
        
    }

    private void setEnd() {
        pass.setEnabled(false);
        fold.setEnabled(false);
        call.setEnabled(false);
        raise.setEnabled(false);
        allIn.setEnabled(false);
        bid.setEnabled(false);
        if (listener.getGame().getGameAllPlayers().getPlayers().size() == 1) {
            start.setEnabled(false);
        } else {
            start.setEnabled(true);
        }
        
    }

    private void setGame() {
        pass.setEnabled(true);
        fold.setEnabled(true);
        call.setEnabled(true);
        raise.setEnabled(true);
        allIn.setEnabled(true);
        bid.setEnabled(true);
        start.setEnabled(false);
    }

}
