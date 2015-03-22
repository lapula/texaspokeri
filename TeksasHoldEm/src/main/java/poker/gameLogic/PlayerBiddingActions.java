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
    
    public PlayerBiddingActions() {
        this.order = "";
    }
    
    public ArrayList<String> getActions(Player player, boolean isBidding, boolean isAllIn) {
        
        ArrayList<String> result = new ArrayList<>();
        result.add("fold");
        
        if (isBidding) {
            result.add("pass");
            result.add("bid");
        } else {
            result.add("call");
            result.add("raise");
        }
        
        result.add("allIn");
        
        return result;
    }
    
    public boolean call(Player player, int highest) {

        boolean succeeded = player.alterBalance(-highest);

        if (!succeeded) {
            return false;
        }

        Table.addToPot(highest);
        return true;
    }

    public boolean bid(Player player, int sum) {

        boolean succeeded = player.alterBalance(-sum);

        if (!succeeded) {
            return false;
        }

        Table.addToPot(sum);
        return true;

    }

    public boolean raise(Player player, int highest, int sum) {

        boolean succeeded = player.alterBalance(-(sum + highest));

        if (!succeeded) {
            return false;
        }

        Table.addToPot(sum + highest);
        return true;

    }
    
    public static void setOrder(String string) {
        PlayerBiddingActions.order = string;
    }
    
    public static String getOrder() {
        return PlayerBiddingActions.order;
    }
    
}
