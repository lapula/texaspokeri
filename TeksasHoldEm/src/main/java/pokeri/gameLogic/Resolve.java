/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokeri.gameLogic;

import pokeri.cards.Card;
import java.util.ArrayList;
import java.util.Arrays;
import pokeri.table.Player;
import pokeri.table.Table;

/**
 *
 * @author Sara ja Laur
 */
public class Resolve {
    
    private ArrayList<Player> players;
    private Table table;
    private HandRating handRating;
    
    public Resolve(ArrayList<Player> players, Table table) {
        this.players = players;
        this.table = table;
        this.handRating = new HandRating();
    }
    
    public int giveWinner() {
        
        int[] result = new int[players.size()];
        
        for (int i = 0; i < players.size(); i++) {
            ArrayList<Card> sevenCards = unify(players.get(i).getCards(), table.getCards());
            int rating = handRating.giveRating(sevenCards);
            result[i] = rating;
        }
        
        Arrays.sort(result);
        
        return result[result.length - 1];
    }
    
    private ArrayList<Card> unify(ArrayList<Card> player, ArrayList<Card> table) {
        
        ArrayList<Card> unified = new ArrayList<Card>();
        
        for (int i = 0; i < player.size(); i++) {
            unified.add(player.get(i));
        }
        for (int i = 0; i < table.size(); i++) {
            unified.add(table.get(i));
        }
        
        return unified;
    }
    
    
}
