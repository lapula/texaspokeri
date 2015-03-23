/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.gameLogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import poker.table.AllPlayers;
import poker.table.Player;
import poker.table.Table;
import poker.util.GameFeed;
import poker.util.TextReader;

/**
 *
 * @author Sara ja Laur
 */
public class Bidding {

    private boolean bid;
    private ArrayList<Player> players;
    private TextReader textReader;
    private boolean buttonSetPassBid;
    private boolean isFirstRound;
    private int end;
    private int highest;
    private Player lastRaised;
    private PlayerBiddingActions action;
    private String order;
    private int turn;
    private Player player;
    private Game game;

    public Bidding(boolean isFirstRound, Game game) {

        this.textReader = new TextReader();
        this.isFirstRound = isFirstRound;
        this.highest = 0;
        this.lastRaised = null;
        this.action = new PlayerBiddingActions();
        this.game = game;
        this.order = null;
        this.players = game.getCurrentPlayers();
        this.end = players.size();
        this.turn = 0;
        this.player = null;
    }

    public void bidding() {

    }

    public void startBidding() {

        if (turn >= end || players.size() < 2) {
            game.getGameAllPlayers().resetBids();

            if (game.getRoundNumber() + 1 == 4) {
                System.out.println("!");
                game.finishRound();
                this.turn = 0;
                this.end = end;
                return;
            } else {
                System.out.println("?");
                this.turn = 0;
                this.end = end;
                game.startRound(game.getRoundNumber() + 1);
                game.getCurrentBidding().startBidding();
                return;

            }

        }

        int size = players.size();
        player = players.get(turn % size);

        this.buttonSetPassBid = true;

        if (lastRaised != null) {

            if (lastRaised.getId() != player.getId()) {

                buttonSetPassBid = false;
            }
        }
        GameFeed.addText(game.getFeed(), "");
        System.out.println("turn: " + turn);
        GameFeed.addText(game.getFeed(), "turn: " + turn);
        System.out.println("end: " + end);
        GameFeed.addText(game.getFeed(), "end: " + end);
        GameFeed.addText(game.getFeed(), "");

        System.out.println(player.getId());
        System.out.println(player.getBalance());
        System.out.println(turn);

        if (player.isAllIn()) {
            takeBiddingAction("pass");
            return;
        }

        if (player.isHuman()) {

            GameFeed.addText(game.getFeed(), "");
            GameFeed.addText(game.getFeed(), "PLAYER: " + player.getId());
            GameFeed.addText(game.getFeed(), "Current cost to call is: " + (highest - player.getBid()));
            GameFeed.addText(game.getFeed(), "Your money: " + player.getBalance());
            GameFeed.addText(game.getFeed(), "Select order:");
            //GameFeed.addText(game.getFeed(), "");

            if (isFirstRound) {
                GameFeed.addText(game.getFeed(), "YOU PLACED BIG BLIND (NON-OPTIONAL)");
                this.isFirstRound = false;
                //end -= players.size();
                takeBiddingAction("bid");
            }

        } else {
            turn++;
            takeBiddingAction("call");
            startBidding();
        }

    }

    public void takeBiddingAction(String order) {
        if (order.equals("call")) {
            GameFeed.addText(game.getFeed(), "CALL");
            orderCall(player);
            turn++;

        } else if (order.equals("bid")) {
            GameFeed.addText(game.getFeed(), "BID");
            orderBid(player);
            turn++;

        } else if (order.equals("raise")) {
            GameFeed.addText(game.getFeed(), "RAISE");
            orderRaise(player);
            turn++;

        } else if (order.equals("fold")) {
            GameFeed.addText(game.getFeed(), "FOLD");
            players.remove(player);
            //turn++;
            //end--;

        } else if (order.equals("allIn")) {
            GameFeed.addText(game.getFeed(), "ALL IN");
            orderAllIn(player);
            turn++;

        } else {
            if (lastRaised != null) {
                if (player.getId() == lastRaised.getId()) {

                }
            }
            GameFeed.addText(game.getFeed(), "PASS");
            //pass
            if (lastRaised != null) {
                if (lastRaised.getId() == player.getId() && (!player.isAllIn())) {
                    game.getGameAllPlayers().resetBids();
                    if (game.getRoundNumber() + 1 == 4) {
                        System.out.println("!");
                        this.turn = 0;
                        this.end = end;
                        game.finishRound();
                        return;
                    } else {
                        System.out.println("?");
                        this.turn = 0;
                        this.end = end;
                        game.startRound(game.getRoundNumber() + 1);
                        game.getCurrentBidding().startBidding();
                        return;

                    }
                }
            }
            turn++;
        }

        startBidding();

    }

    public boolean buttonsSetPassBid() {
        return buttonSetPassBid;
    }
    
    public Player getCurrentBidder() {
        return this.player;
    }

    private void orderCall(Player player) {

        boolean succeeded = action.call(player, (highest - player.getBid()));

        if (!succeeded) {

            players.remove(player);
        } else {
            player.addBid(highest - player.getBid());
        }
    }

    private void orderBid(Player player) {

        int amount;

        if (isFirstRound) {
            amount = 10;
        } else {

            amount = 10;
        }

        boolean succeeded = action.bid(player, amount);

        if (!succeeded) {
            System.out.println("You did not have enough money");
        } else {
            highest += amount;
            player.addBid(amount);
            end += players.size();
            lastRaised = player;
        }

    }

    private void orderRaise(Player player) {

        System.out.print("Raise by: ");
        int amount = 10;

        boolean succeeded = action.raise(player, highest, amount);

        if (!succeeded) {
            System.out.println("You did not have enough money");
        } else {
            player.addBid(amount + (highest - player.getBid()));
            highest += amount;
            end += players.size();
            lastRaised = player;
        }
    }

    private void orderAllIn(Player player) {

        player.setAllInTrue();
        if (player.getBalance() - highest > 0) {
            highest += player.getBalance() - highest;
        }
        action.bid(player, player.getBalance());
        end += players.size() - 1;
        lastRaised = player;
    }

}
