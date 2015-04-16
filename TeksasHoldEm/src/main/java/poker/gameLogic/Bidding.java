/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.gameLogic;

import java.util.ArrayList;
import poker.gui.SoundPlayer;
import poker.table.Player;
import poker.util.GameFeed;

/**
 * Tarjousvaihetta käsittelevä luokka. Hoitaa tarjousvuoron kieroa ja siihen
 * liittyviä toimintoja.
 */
public class Bidding {

    private boolean bid;
    private ArrayList<Player> players;
    private boolean buttonSetPassBid;
    private boolean isFirstRound;
    private int blinds;
    private int end;
    private int highest;
    private Player lastRaised;
    private PlayerBiddingActions action;
    private String order;
    private int turn;
    private Player player;
    private Game game;
    private int sliderValue;
    private SoundPlayer soundPlayer;

    public Bidding(boolean isFirstRound, Game game) {

        this.isFirstRound = isFirstRound;
        this.blinds = 0;

        if (isFirstRound) {
            this.blinds = 2;
        }

        this.highest = 0;
        this.lastRaised = null;
        this.game = game;
        this.action = new PlayerBiddingActions(game.getGameTable());
        this.order = null;
        this.players = game.getCurrentPlayers();
        this.end = players.size();
        this.turn = 0;
        this.player = null;
        this.soundPlayer = new SoundPlayer();
    }

    public void startBidding() {

        int size = players.size();
        player = players.get(turn % size);

        if (turn >= end || players.size() < 2) {
            game.getGameAllPlayers().resetBids();

            if (game.getRoundNumber() + 1 == 4) {

                game.finishGame();
                this.turn = 0;
                this.end = end;
                return;
            } else {

                this.turn = 0;
                this.end = end;
                game.startRound(game.getRoundNumber() + 1);
                game.getCurrentBidding().startBidding();
                return;

            }

        }

        this.buttonSetPassBid = true;

        if (lastRaised != null) {
            if (lastRaised.getId() != player.getId()) {
                buttonSetPassBid = false;
            }

        }
        if (highest - player.getBid() > 0) {
            buttonSetPassBid = false;
        }

        if (player.isAllIn()) {
            takeBiddingAction("allInPass");
            return;
        }

        GameFeed.addText(game.getFeed(), "PLAYER: " + player.getId());
        GameFeed.addText(game.getFeed(), "Balance: " + player.getBalance());
        GameFeed.addText(game.getFeed(), "Select order:");

        if (blinds == 2) {
            GameFeed.addText(game.getFeed(), "YOU PLACED SMALL BLIND (NON-OPTIONAL)");
            takeBiddingAction("bid");
        } else if (blinds == 1) {
            GameFeed.addText(game.getFeed(), "YOU PLACED BIG BLIND (NON-OPTIONAL)");
            takeBiddingAction("raise");
        }
        
        if (!player.isHuman()) {
            if (buttonSetPassBid) {
                takeBiddingAction("pass");
            } else {
                takeBiddingAction("call");
            }
            //startBidding();
        }

    }

    public void takeBiddingAction(String order) {
        if (order.equals("call")) {
            soundPlayer.playSound("addChips");
            GameFeed.addText(game.getFeed(), "CALL");
            orderCall(player);
            turn++;

        } else if (order.equals("bid")) {

            if (!isFirstRound) {
                soundPlayer.playSound("addChips");

            }

            if (!isFirstRound) {
                GameFeed.addText(game.getFeed(), "BID");
            }

            orderBid(player);
            turn++;

        } else if (order.equals("raise")) {
            soundPlayer.playSound("addChips");
            if (!isFirstRound) {
                GameFeed.addText(game.getFeed(), "RAISE");
            }

            orderRaise(player);
            turn++;

        } else if (order.equals("fold")) {

            if (turn % players.size() == 0) {
                turn--;
            }
            soundPlayer.playSound("fold");
            GameFeed.addText(game.getFeed(), "FOLD");
            players.remove(player);

        } else if (order.equals("allIn")) {
            soundPlayer.playSound("allIn");
            GameFeed.addText(game.getFeed(), "ALL IN");
            GameFeed.addText(game.getFeed(), "");
            orderAllIn(player);
            turn++;

        } else {

            if (order.equals("allInPass")) {

            } else {
                soundPlayer.playSound("pass");
                GameFeed.addText(game.getFeed(), "PASS");

                if (lastRaised != null) {
                    if (lastRaised.getId() == player.getId() && (!player.isAllIn())) {
                        game.getGameAllPlayers().resetBids();
                        if (game.getRoundNumber() + 1 == 4) {

                            this.turn = 0;
                            this.end = end;
                            GameFeed.addText(game.getFeed(), "");
                            game.finishGame();
                            return;
                        } else {

                            this.turn = 0;
                            this.end = end;
                            GameFeed.addText(game.getFeed(), "");
                            game.startRound(game.getRoundNumber() + 1);
                            game.getCurrentBidding().startBidding();
                            return;

                        }
                    }
                }
            }

            turn++;
        }
        if (!player.isAllIn()) {
            GameFeed.addText(game.getFeed(), "");
        }

        startBidding();

    }

    public boolean buttonsSetPassBid() {
        return buttonSetPassBid;
    }

    public Player getCurrentBidder() {
        return this.player;
    }

    public int costToCall(Player player) {
        return highest - player.getBid();
    }

    public int highest() {
        return this.highest;
    }

    public void setSliderValue(int value) {
        this.sliderValue = value;
    }

    public boolean isAllOthersAllIn() {

        int count = 0;
        for (Player player : players) {
            if (player.isAllIn()) {
                count++;
            }
        }
        if (players.size() < 2 || count != players.size() - 1) {
            return false;
        }
        return true;

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
            System.out.println(blinds);

            amount = 5;
            blinds = 1;

        } else {
            amount = sliderValue;
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

        int amount;

        if (isFirstRound) {
            amount = 5;
            blinds = 0;
            isFirstRound = false;
        } else {
            amount = sliderValue;
        }

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
        if (player.getBalance() - (highest - player.getBid()) > 0) {
            highest += player.getBalance() - (highest - player.getBid());
        }
        action.bid(player, player.getBalance());
        end += players.size() - 1;
        //lastRaised = player;
    }

}
