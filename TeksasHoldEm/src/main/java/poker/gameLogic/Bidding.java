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
 *
 * @author Sara ja Laur
 */
public class Bidding {

    private boolean bid;
    private ArrayList<Player> players;
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
    private SoundPlayer soundPlayer;

    public Bidding(boolean isFirstRound, Game game) {

        this.isFirstRound = isFirstRound;
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

    public void bidding() {

    }

    public void startBidding() {
        
        int size = players.size();
        player = players.get(turn % size);
        
        if (turn >= end || players.size() < 2) {
            game.getGameAllPlayers().resetBids();

            if (game.getRoundNumber() + 1 == 4) {
                
                game.finishRound();
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

        if (player.isAllIn()) {
            takeBiddingAction("pass");
            return;
        }

        if (player.isHuman()) {

            GameFeed.addText(game.getFeed(), "PLAYER: " + player.getId());
            GameFeed.addText(game.getFeed(), "Current cost to call is: " + (highest - player.getBid()));
            GameFeed.addText(game.getFeed(), "Balance: " + player.getBalance());
            GameFeed.addText(game.getFeed(), "Select order:");

            if (isFirstRound) {
                GameFeed.addText(game.getFeed(), "YOU PLACED BIG BLIND (NON-OPTIONAL)");
                
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
            soundPlayer.playSound("addChips");
            GameFeed.addText(game.getFeed(), "CALL");
            orderCall(player);
            turn++;

        } else if (order.equals("bid")) {
            
            if (!isFirstRound) {
                soundPlayer.playSound("addChips");
                
            } else {
                this.isFirstRound = false;
            }
            
            GameFeed.addText(game.getFeed(), "BID");
            orderBid(player);
            turn++;

        } else if (order.equals("raise")) {
            soundPlayer.playSound("addChips");
            GameFeed.addText(game.getFeed(), "RAISE");
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
            orderAllIn(player);
            turn++;

        } else {
            soundPlayer.playSound("pass");
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
                        
                        this.turn = 0;
                        this.end = end;
                        GameFeed.addText(game.getFeed(), "");
                        game.finishRound();
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
            turn++;
        }
        GameFeed.addText(game.getFeed(), "");

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
        if (player.getBalance() - (highest - player.getBid()) > 0) {
            highest += player.getBalance() - (highest - player.getBid());
        }
        action.bid(player, player.getBalance());
        end += players.size() - 1;
        lastRaised = player;
    }

}
