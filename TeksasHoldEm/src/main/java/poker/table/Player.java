/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.table;

import java.util.ArrayList;
import poker.cards.Card;

/**
 * Pelaajaa simuloiva luokka.
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
     * @param id joka on pelaajan numero ja "avain".
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
    
    /**
     * @return pelaajan I.
     */
    
    public int getId() {
        return this.id;
    }
    
    /**
     * Lisätään panosta.
     * @param sum määrä.
     */
    
    public void addBid(int sum) {
        this.bid += sum;
    }
    
    /**
     * @return Pelaajan tämän kierroksen panos.
     */
    
    public int getBid() {
        return this.bid;
    }
    
    /**
     * Nollataan panos.
     */
    
    public void resetBid() {
        this.bid = 0;
    }
    
    /**
     * @return onko pelaaja all in;
     */
    
    public boolean isAllIn() {
        return this.allIn;
    }
    
    /**
     * Pelaaja on all in.
     */
    
    public void setAllInTrue() {
        this.allIn = true;
    }
    
    /**
     * Nollataan All In status.
     */
    
    public void resetAllIn() {
        this.allIn = false;
    }
    
    /**
     * @return Maksimi summa minkä voi voittaa.
     */
    
    public int getMaxWin() {
        return this.maxWin;
    }
    
    /**
     * Päivitetään maksimivoittosumma.
     */
    
    public void refreshMaxWin() {
        this.maxWin = this.getBalance();
    }
    
}
