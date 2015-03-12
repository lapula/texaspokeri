/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokeri.gameLogic;

import pokeri.cards.Card;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

/**
 *
 * @author Sara ja Laur
 */
public class HandRating {
    
    /**
     * Tämän luokan idea on antaa kädelle arvo. Mahdollisia käsiä on 9 ja jokaiselle niistä 
     * määritellään korkein kortti. Paras käsi saa arvoki 900, toinen 800 jne. Arvoon lisätään
     * korkein kortti, eli värisuora alkaen ässästä on 912.
     */
    
    
    public HandRating() {
        
    }
    
    
    public int giveRating(ArrayList<Card> cards) {
        
        int[] result = new int[21];
        int count = 0;
        
        
        for (int i = 0; i < cards.size(); i++) {
            
            ArrayList<Card> test = new ArrayList<Card>(cards);
            
            test.remove(i);
            
            for (int a = i; a < test.size(); a++) {
                ArrayList<Card> test2 = new ArrayList<Card>(test);
                test2.remove(a);
                result[count] = tryHands(test2);
                count++;
            }
            
        }
        
        Arrays.sort(result);
        
        
        
        return result[result.length - 1];
        
    }
    
    private int tryHands(ArrayList<Card> cards) {
        
        return highCard(cards);
        
    }
    
    
    /*private Card straightFlush(ArrayList<Card> cards) {
        
    }
    
    private Card fourOfAKind(ArrayList<Card> cards) {
        
    }
    
    private Card fullHouse(ArrayList<Card> cards) {
        
    }
    
    private Card flush(ArrayList<Card> cards) {
        
    }
    
    private Card straight(ArrayList<Card> cards) {
        
    }
    
    private Card threeOfAKind(ArrayList<Card> cards) {
        
    }
    
    private Card twoPairs(ArrayList<Card> cards) {
        
    }
    
    private Card onePair(ArrayList<Card> cards) {
        
    }*/
    
    private int highCard(ArrayList<Card> cards) {
        
        
        
        Collections.sort(cards);
        
        
        
        return 100 + cards.get(cards.size() - 1).getValue();
        
        
    }
    
    
   
    
}
