/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.gameLogic;

import java.util.ArrayList;
import java.util.HashMap;
import poker.table.AllPlayers;
import poker.table.Player;
import poker.table.Table;
import poker.util.TextReader;

/**
 *
 * @author Sara ja Laur
 */
public class Bidding {

    private boolean bid;
    private ArrayList<Player> players;
    private TextReader textReader;
    private boolean buttonsBid = true;
    private boolean isFirstRound;
    private int end;
    private int highest;
    private Player lastRaised;

    public Bidding(ArrayList<Player> players, boolean isFirstRound) {
        this.players = players;
        this.textReader = new TextReader();
        this.isFirstRound = isFirstRound;
        this.end = players.size();
        this.highest = 0;
        this.lastRaised = null;
    }

    public ArrayList<Player> startBidding() {
        
        for (int i = 0; i < end; i++) {
            
            int size = players.size();
            Player player = players.get(i % size);
            String order = "call";
            
            if (player.isAllIn()) {
                continue;
            }
            
            this.buttonsBid = true;
            
            if (lastRaised != null) {
                if (lastRaised.getId() != player.getId()) {
                    this.buttonsBid = false;
                }
            }
            
            
            if (player.isHuman()) {
                System.out.println("");
                System.out.println("PLAYER: " + player.getId());
                System.out.println("Current cost to call is: " + (highest - player.getBid()));
                System.out.println("Your money: " + player.getBalance());
                System.out.println("Give order:");

                if (isFirstRound) {
                    System.out.println("BLIND");
                    order = "bid";
                } else {
                    order = textReader.read();
                }
            }

            if (order.equals("call")) {

                orderCall(player);

            } else if (order.equals("bid")) {

                orderBid(player);

            } else if (order.equals("raise")) {

                orderRaise(player);

            } else if (order.equals("fold")) {

                players.remove(player);
                i--;

            } else if (order.equals("allIn")) {

                orderAllIn(player);

            } else {
                if (lastRaised != null) {
                    if (player.getId() == lastRaised.getId()) {
                        break;
                    }
                }
                //pass
            }

            if (this.isFirstRound) {
                this.isFirstRound = false;
            }

        }
        AllPlayers.resetBids();
        return players;

    }

    public boolean buttonsBid() {
        return buttonsBid;
    }

    private void orderCall(Player player) {
        boolean succeeded = call(player, (highest - player.getBid()));

        if (!succeeded) {
            System.out.println("You do not have enough money");
            players.remove(player);
        } else {
            player.addBid(highest - player.getBid());
        }
    }

    private void orderBid(Player player) {

        int amount = 0;

        if (isFirstRound) {
            amount = 10;
        } else {
            System.out.print("Amount to bid: ");
            amount = Integer.parseInt(textReader.read());
        }

        boolean succeeded = bid(player, amount);

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
        int amount = Integer.parseInt(textReader.read());

        boolean succeeded = raise(player, highest, amount);

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
        bid(player, player.getBalance());
        end += players.size() - 1;
    }



    private boolean call(Player player, int highest) {

        boolean succeeded = player.alterBalance(-highest);

        if (!succeeded) {
            return false;
        }

        Table.addToPot(highest);
        return true;
    }

    private boolean bid(Player player, int sum) {

        boolean succeeded = player.alterBalance(-sum);

        if (!succeeded) {
            return false;
        }

        Table.addToPot(sum);
        return true;

    }

    private boolean raise(Player player, int highest, int sum) {

        boolean succeeded = player.alterBalance(-(sum + highest));

        if (!succeeded) {
            return false;
        }

        Table.addToPot(sum + highest);
        return true;

    }

}
