/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokeri.cards;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Sara ja Laur
 */
public class Deck {
    
    
    private final int SIZE = 52;
    private ArrayList<Card> deck = new ArrayList<>();
    
    
    /**
     * Luodaan uusi pakka ja sekoitetaan se;
     */
    
    public Deck() {
        this.createDeck();
        this.shuffle();
    }
    
    
    /**
     * Luodaan uusi pakka jossa kortit ovat järjestyksessä.
     */
    
    private void createDeck() {
        
        Suit suit = null;
        
        for (int i = 0; i < SIZE / 13; i++) {
            
            suit = Suit.getByID(i);

            for (int a = 0; a < SIZE / 4; a++) {
                deck.add(new Card(suit, a));   
            }   
        }    
    }
    
    
    /**
     * Metodi pakan sekoittamiseen.
     */
    
    public void shuffle() {
        
        Collections.shuffle(deck);
        
    }
    
    /**
     * Nostaa kortin.
     * @return "päällimmäinen" kortti.
     */
    
    public Card drawCard() {
        
        Card card = deck.get(deck.size() - 1);
        this.deck.remove(deck.size() - 1);
        return card;
    }
    
    
    
}
