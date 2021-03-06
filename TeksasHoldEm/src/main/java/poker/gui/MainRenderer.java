/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.gui;

import java.awt.Color;
import java.awt.Font;
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
 * Pääikkunan renderöintiä, päivittää mm. tekstit ja kortit.
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
        g.drawImage(imageLoader.loadBackground(), -110, -40, null);
        if (!game.isSetupping()) {
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

        int potXPos = 825;

        if (player == null) {
            return;
        }
        if (player.isHuman() || !player.isHuman()) {

            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g.setColor(Color.green);
            g.drawString("Your cards:", 10, 20);
            g.drawImage(imageLoader.loadCardImage(player.getCards().get(0)), 20, 40, null);
            g.drawImage(imageLoader.loadCardImage(player.getCards().get(1)), 33, 40, null);
            
            g.drawString("Your balance: ", 10, 610);
            g.drawString("Current pot: ", 770, 610);
            g.drawString("Cost to Call ", 405, 610);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
            g.setColor(Color.white);
            if (game.getGameTable().getPot() > 99) {
                potXPos = 805;
            }
            g.drawString("Player " + player.getId(), 400, 50);
            int costXPos = 430;

            if (game.getCurrentBidding().costToCall(player) < 10) {
                costXPos = 440;
            } else if (game.getCurrentBidding().costToCall(player) > 99) {
                costXPos = 420;
            }

            g.drawString("" + game.getGameTable().getPot(), potXPos, 650);
            g.drawString("" + player.getBalance(), 10, 650);
            g.drawString("" + game.getCurrentBidding().costToCall(player), costXPos, 650);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g.setColor(Color.green);
        }

    }

    private void paintWinnerCards(Graphics g) {
        Player player = game.getWinner();
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.setColor(Color.green);
        g.drawString("Winner Cards:", 715, 20);
        g.drawImage(imageLoader.loadCardImage(player.getCards().get(0)), 700, 40, null);
        g.drawImage(imageLoader.loadCardImage(player.getCards().get(1)), 780, 40, null);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        g.setColor(Color.white);
    }

    private void paintTableCards(Graphics g) {

        int x = 250;
        int y = 280;

        for (Card card : game.getGameTable().getCards()) {
            g.drawImage(imageLoader.loadCardImage(card), x, y, null);
            x += 80;
        }
    }

}
