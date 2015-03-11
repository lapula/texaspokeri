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
    private int bid;
    
    
    public Player(int balance, boolean isHuman) {
        this.balance = balance;
        this.isHuman = isHuman;
        this.bid = 0;
    }
    
    
    public void addCard(Card card) {
        hand.add(card);
    }
    
    public ArrayList<Card> getCards() {
        return this.hand;
    }
    
    public boolean alterBalance(int sum) {
        
        if (balance - sum < 0) {
            return false;
        }
        
        this.balance += sum;
        return true;
    }
    
    public int getBalance() {
        return this.balance;
    }
    
    public boolean isHuman() {
        return this.isHuman;
    }
    
    public int bid() {
        return this.bid;
    }
    
    public void addBid(int sum) {
        this.bid += sum;
    }
    
    public void resetBid() {
        this.bid = 0;
    }
    
}
