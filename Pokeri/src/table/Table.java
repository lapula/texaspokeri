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
public class Table {
    
    private ArrayList<Card> tableCards = new ArrayList<Card>();
    private int pot;
    
    
    public Table(int pot) {
        this.pot = pot;
    }
    
    public void addCard(Card card) {
        tableCards.add(card);
    }
    
    public ArrayList getCard() {
        return this.tableCards;
    }
    
    public void addToPot(int sum) {
        this.pot += sum;
    }
    
    public int getPot(int sum) {
        return this.pot;
    }
    
    public void resetPot() {
        this.pot = 0;
    }
    
    
    
}
