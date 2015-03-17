/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapula.poker.table;

import java.util.ArrayList;
import lapula.poker.cards.Card;

/**
 *
 * @author Sara ja Laur
 */
public class Player {
    
    private ArrayList<Card> hand = new ArrayList<>();
    private int balance;
    private boolean isHuman;
    private int id;
    private int bid;
    private boolean allIn;
    private int maxWin;
    
    /**
     * Luokka pelaajalle. Pelaaja voi olla ihminen tai tekoäly. Pelaajalla on omat
     * rahat (balance), kortit ja tämänhetkinen panoksen lisäyksen määrä.
     * @param balance aloitusrahat.
     * @param isHuman onko ihminen.
     */
    
    public Player(int balance, boolean isHuman, int id) {
        this.balance = balance;
        this.isHuman = isHuman;
        this.id = id;
        this.bid = 0;
        this.allIn = false;
        this.maxWin = balance;
    }
    
    /**
     * Lisätään käteen kortti.
     * @param card lisättävä kortti.
     */
    
    public void addCard(Card card) {
        hand.add(card);
    }
    
    /**
     * @return pelaajan kortit.
     */
    
    public ArrayList<Card> getCards() {
        return this.hand;
    }
    
    /**
     * Muutetaan pelaajan rahamäärää.
     * @param sum muutos
     * @return palautetaan true jos on validi muutos, muuten false;
     */
    
    public boolean alterBalance(int sum) {
        
        if (balance + sum < 0) {
            return false;
        } 
        
        this.balance += sum;
        return true;
    }
    
    /**
     * @return pelaajan rahamäärä.
     */
    
    public int getBalance() {
        return this.balance;
    }
    
    /**
     * @return onko ihminen.
     */
    
    public boolean isHuman() {
        return this.isHuman;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void addBid(int sum) {
        this.bid += sum;
    }
    
    public int getBid() {
        return this.bid;
    }
    
    public void resetBid() {
        this.bid = 0;
    }
    
    public boolean isAllIn() {
        return this.allIn;
    }
    
    public void setAllInTrue() {
        this.allIn = true;
    }
    
    public void resetAllIn() {
        this.allIn = false;
    }
    
    public int getMaxWin() {
        return this.maxWin;
    }
    
    public void refreshMaxWin() {
        this.maxWin = this.getBalance();
    }
    
}
