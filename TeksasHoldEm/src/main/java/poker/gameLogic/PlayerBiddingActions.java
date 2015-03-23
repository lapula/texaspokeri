/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.gameLogic;

import java.util.ArrayList;
import poker.table.Player;
import poker.table.Table;

/**
 *
 * @author Sara ja Laur
 */
public class PlayerBiddingActions {
    
    private static String order;
    private Table table;
    
    public PlayerBiddingActions(Table table) {
        this.order = "";
        this.table = table;
    }
    
    
    public boolean call(Player player, int amount) {

        boolean succeeded = player.alterBalance(-(amount));

        if (!succeeded) {
            return false;
        }

        table.addToPot(amount);
        return true;
    }

    public boolean bid(Player player, int sum) {

        boolean succeeded = player.alterBalance(-sum);

        if (!succeeded) {
            return false;
        }

        table.addToPot(sum);
        return true;

    }

    public boolean raise(Player player, int highest, int sum) {

        boolean succeeded = player.alterBalance(-(sum + (highest - player.getBid())));

        if (!succeeded) {
            return false;
        }

        table.addToPot(sum + (highest - player.getBid()));
        return true;

    }
    
    
    
}
