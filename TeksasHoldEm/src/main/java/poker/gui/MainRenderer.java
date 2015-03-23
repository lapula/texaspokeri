/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.gui;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import poker.cards.Card;
import poker.cards.Suit;
import poker.gameLogic.Game;
import poker.table.Player;

/**
 *
 * @author Sara ja Laur
 */
public class MainRenderer extends JPanel {

    private PokerActionListener listener;
    private ImageLoader imageLoader;
    private Game game;

    public MainRenderer(PokerActionListener listener, ImageLoader imageLoader) {

        this.listener = listener;
        this.imageLoader = imageLoader;
        this.game = listener.getGame();
        StartTimer();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.drawImage(imageLoader.loadCardImage(new Card(Suit.SPADES, 0)), 10, 10, null);
        if (game.isRunning()) {
            //System.out.println("#######");
            paintPlayerCards(g);
        }
        if (game.getWinner() != null && !game.isRunning()) {
            paintWinnerCards(g);
        }
        
        if (game.getGameTable().getCards() != null) {
            paintTableCards(g);
        }

    }

    private void StartTimer() {
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                repaint();
            }
        };
        new Timer(10, taskPerformer).start();
    }

    private void paintPlayerCards(Graphics g) {

        Player player = game.getCurrentBidding().getCurrentBidder();

        if (player.isHuman()) {
            g.drawImage(imageLoader.loadCardImage(player.getCards().get(0)), 10, 10, null);
            g.drawImage(imageLoader.loadCardImage(player.getCards().get(1)), 30, 10, null);
        }

    }

    private void paintWinnerCards(Graphics g) {
        Player player = game.getWinner();

        g.drawImage(imageLoader.loadCardImage(player.getCards().get(0)), 700, 10, null);
        g.drawImage(imageLoader.loadCardImage(player.getCards().get(1)), 780, 10, null);
    }
    
    private void paintTableCards(Graphics g) {
        
        int x = 250;
        int y = 300;
        
        for (Card card : game.getGameTable().getCards()) {
            g.drawImage(imageLoader.loadCardImage(card), x, y, null);
            x += 80;
        }
    }

}
