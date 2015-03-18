/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.gameLogic;

import java.util.ArrayList;
import poker.table.Player;

/**
 *
 * @author Sara ja Laur
 */
public class PlayerBiddingActions {
    
    public PlayerBiddingActions() {
        
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
    
}
