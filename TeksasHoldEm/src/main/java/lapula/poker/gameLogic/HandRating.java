/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapula.poker.gameLogic;

import lapula.poker.cards.Card;
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
     * määritellään korkein kortti. Paras käsi saa arvoksi 900.0, toinen 800.0 jne. Arvoon lisätään
     * korkein kortti, eli värisuora alkaen ässästä on 912.0 jne.
     * Alunperin oli tarkoitus käyttää Integeriä mutta jouduin vaihtamaan double:en sillä kahden
     * kortin tapauksessa piti voida ottaa huomioon myös toisen kortin arvo.
     */
    
    
    public HandRating() {
        
    }
    
    
    public double giveRating(ArrayList<Card> cards) {
        
        double[] result = new double[21];
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
    
    private double tryHands(ArrayList<Card> cards) {
        
        double tulos = 0;
        
        if (tulos == 0) {
            tulos = threeOfAKind(cards);
        }
        if (tulos == 0) {
            tulos = twoPairs(cards);
        }
        if (tulos == 0) {
            tulos = onePair(cards);
        }
        if (tulos == 0) {
            return highCard(cards);
        }
        
        return tulos;
        
    }
    
    
    /*private double straightFlush(ArrayList<Card> cards) {
        
    }
    
    private double fourOfAKind(ArrayList<Card> cards) {
        
    }
    
    private double fullHouse(ArrayList<Card> cards) {
        
    }
    
    private double flush(ArrayList<Card> cards) {
        
    }
    
    private double straight(ArrayList<Card> cards) {
        
    }*/
    
    private double threeOfAKind(ArrayList<Card> cards) {
        
        Collections.sort(cards);
        
        for (int i = 2; i < cards.size(); i++) {
            if (cards.get(i).getValue() == cards.get(i - 1).getValue() &&
                cards.get(i - 2).getValue() == cards.get(i - 1).getValue()) {
                
                return 400 + cards.get(i).getValue();
            }
        }
        
        return 0;
        
    }
    
    private double twoPairs(ArrayList<Card> cards) {
        
        Collections.sort(cards);
        int index = 0;
        boolean onePair = false;
        
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i).getValue() == cards.get(i - 1).getValue()) {
                
                if (onePair == false) {
                    index = i;
                    onePair = true;
                    continue;
                }
                
                return 300 + cards.get(i).getValue() + (cards.get(index).getValue() / 100.0);
            }
        }
        
        return 0;
        
    }
    
    private double onePair(ArrayList<Card> cards) {
        
        Collections.sort(cards);
        
        for (int i = cards.size() - 1; i > 0; i--) {
            if (cards.get(i).getValue() == cards.get(i - 1).getValue()) {
                return 200 + cards.get(i).getValue();
            }
        }
        
        return 0;
    }
    
    private double highCard(ArrayList<Card> cards) {
        
        Collections.sort(cards);
        return 100 + cards.get(cards.size() - 1).getValue();
    }
    
    
   
    
}
