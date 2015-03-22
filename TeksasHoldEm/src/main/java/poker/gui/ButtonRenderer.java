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
import javax.swing.Timer;
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

    public ButtonRenderer(LayoutManager lm, JButton start, JButton fold,
            JButton call, JButton raise, JButton allIn,
            JButton bid, JButton pass, PokerActionListener listener) {
        super(lm);
        this.start = start;
        this.fold = fold;
        this.call = call;
        this.raise = raise;
        this.allIn = allIn;
        this.bid = bid;
        this.pass = pass;
        this.listener = listener;
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
            

        }

        if (player.getBalance() < 10) {
            call.setEnabled(false);
            raise.setEnabled(false);
            bid.setEnabled(false);
        }

    }

    private void setEnd() {
        pass.setEnabled(false);
        fold.setEnabled(false);
        call.setEnabled(false);
        raise.setEnabled(false);
        allIn.setEnabled(false);
        bid.setEnabled(false);
        start.setEnabled(true);
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
