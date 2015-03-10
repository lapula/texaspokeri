/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table;

import cards.Card;
import java.util.ArrayList;

/**
 *
 * @author Laur
 */
public class Poyta {
    
    private ArrayList<Card> tableCards = new ArrayList<Card>();
    
    public Poyta() {
        
    }
    
    public void addCard(Card card) {
        tableCards.add(card);
    }
    
    public ArrayList getCard() {
        return this.tableCards;
    }
    
    
    
    
}
