/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapula.poker.gameLogic;

import lapula.poker.cards.Card;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import lapula.poker.table.Player;
import lapula.poker.table.Table;

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
    
    public HashMap<Player, Double> giveWinner() {
        
        HashMap<Player, Double> result = new HashMap<Player, Double>();
        double max = 0;
        
        for (int i = 0; i < players.size(); i++) {
            ArrayList<Card> sevenCards = unify(players.get(i).getCards(), table.getCards());
            double rating = handRating.giveRating(sevenCards);
            
            if (rating > max) {
                max = rating;
            }
            result.put(players.get(i), rating);
        }
        
        if (result.size() == 1) {
            return result;
        }
        
        HashMap<Player, Double> copy = new HashMap<Player, Double>(result);
        
        for (Player player : result.keySet()) {
            
            if (result.get(player) != max) {
                copy.remove(player);
            }
        }
        
        result = new HashMap<>(copy);
        copy = new HashMap<>(result);
        
        if (result.size() == 1) {
            return result;
        }
        
        
        
        int biggestCard = 0;
        
        for (Player player : result.keySet()) {
            
            Card card = Collections.max(player.getCards());
            
            if (card.getValue() > biggestCard) {
                biggestCard = card.getValue();
            }
        }
        
        for (Player player : result.keySet()) {
            
            Card card = Collections.max(player.getCards());
            
            if (card.getValue() != biggestCard) {
                copy.remove(player);
            }
        }
        
        result = new HashMap<>(copy);
        copy = new HashMap<>(result);
        
        if (result.size() == 1) {
            return result;
        }
        
        for (Player player : result.keySet()) {
            
            Card card = Collections.min(player.getCards());
            
            if (card.getValue() > biggestCard) {
                biggestCard = card.getValue();
            }
        }
        
        for (Player player : result.keySet()) {
            
            Card card = Collections.min(player.getCards());
            
            if (card.getValue() != biggestCard) {
                copy.remove(player);
            }
        }
        
        result = new HashMap<>(copy);
        copy = new HashMap<>(result);
        
        return result;
        
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