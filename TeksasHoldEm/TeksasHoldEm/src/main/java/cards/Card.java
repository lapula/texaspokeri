/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cards;

/**
 *
 * @author Sara ja Laur
 */
public class Card {
    
    private Suit suit;
    private int value;
    
    
    /**
     * Kortin konstruktori, ottaa kortin maan ja arvon.
     * @param suit kortin maa
     * @param value kortin arvo 
     */
    
    public Card(Suit suit, int value) {
        this.suit = suit;
        this.value = value;
    }
    
    
    public int getSuitID() {
        return this.suit.id;
    }
    
    public Suit getSuit() {
        return this.suit;
    }
    
    public int getValue() {
        return this.value;
    }
    
    
}
