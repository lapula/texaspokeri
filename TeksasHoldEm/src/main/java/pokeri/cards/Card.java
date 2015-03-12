/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokeri.cards;

/**
 *
 * @author Sara ja Laur
 */
public class Card implements Comparable<Card>{
    
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
    
    /**
     * @return maan ID
     */
    
    public int getSuitID() {
        return this.suit.id;
    }
    
    /**
     * @return Maan luokka.
     */
    
    public Suit getSuit() {
        return this.suit;
    }
    
    /**
     * @return kortin arvo
     */
    
    public int getValue() {
        return this.value;
    }
    
    /**
     * Compare to metodi korttien arvon vertailuun.
     * @param other verrattava kortti
     * @return onko kortti isompi, samankokoinen vai pienempi (1, 0, -1)
     */

    @Override
    public int compareTo(Card other) {
        if (this.getValue() > other.getValue()) {
            return 1;
        } else if (this.getValue() < other.getValue()) {
            return -1;
        }
        
        return 0;
    }
    
    
}
