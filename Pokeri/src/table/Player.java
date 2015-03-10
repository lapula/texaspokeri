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
    private int balance;
    private boolean isHuman;
    
    
    public Player(int balance, boolean isHuman) {
        this.balance = balance;
        this.isHuman = isHuman;
    }
    
    
    public void addCard(Card card) {
        hand.add(card);
    }
    
    public ArrayList getCard() {
        return this.hand;
    }
    
    public boolean alterBalance(int sum) {
        
        if (balance - sum < 0) {
            return false;
        }
        
        this.balance += sum;
        return true;
    }
    
    public int getBalance(int sum) {
        return this.balance;
    }
    
}
