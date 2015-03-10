/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table;

import java.util.ArrayList;
import cards.Card;

/**
 *
 * @author Sara ja Laur
 */
public class Player {
    
    private ArrayList<Card> hand = new ArrayList<>();
    
    public Player() {
        
    }
    
    
    public void addCard(Card card) {
        hand.add(card);
    }
    
    public ArrayList getCard() {
        return this.hand;
    }
    
}
