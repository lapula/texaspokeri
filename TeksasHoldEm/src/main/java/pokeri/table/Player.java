/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokeri.table;

import java.util.ArrayList;
import pokeri.cards.Card;

/**
 *
 * @author Sara ja Laur
 */
public class Player {
    
    private ArrayList<Card> hand = new ArrayList<>();
    private int balance;
    private boolean isHuman;
    private int bid;
    
    /**
     * Luokka pelaajalle. Pelaaja voi olla ihminen tai tekoäly. Pelaajalla on omat
     * rahat (balance), kortit ja tämänhetkinen panoksen lisäyksen määrä.
     * @param balance aloitusrahat.
     * @param isHuman onko ihminen.
     */
    
    public Player(int balance, boolean isHuman) {
        this.balance = balance;
        this.isHuman = isHuman;
        this.bid = 0;
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
    
    /**
     * @return tämänhetkisen panoksen määrä.
     */
    
    public int bid() {
        return this.bid;
    }
    
    /**
     * Lisätään panosta.
     * @param sum lisättävä määrä.
     */
    
    public void addBid(int sum) {
        
        if (sum < 0) {
            return;
        }
        
        this.bid += sum;
    }
    
    /**
     * Nollataan pelaajan panos (tältä kierrokselta).
     */
    
    public void resetBid() {
        this.bid = 0;
    }
    
}
