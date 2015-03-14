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
import lapula.poker.cards.Suit;

/**
 *
 * @author Sara ja Laur
 */
public class HandRating {
    
    /**
     * Tämän luokan idea on antaa kädelle arvo. Mahdollisia käsiä on 9 ja jokaiselle niistä 
     * määritellään korkein kortti. Paras käsi saa arvoksi 900.0, toinen 800.0 jne. Arvoon lisätään
     * korkein kortti, eli värisuora alkaen ässästä on 912.0 jne.
     * Alunperin oli tarkoitus käyttää Integeriä arvona mutta jouduin vaihtamaan double:en sillä kahden
     * kortin tapauksessa piti voida ottaa huomioon myös toisen kortin arvo.
     * 
     * Lista tulosten merkityksistä:
     * 
     * Straight Flush: 900
     * Four of a Kind: 800
     * Full House: 700
     * Flush: 600
     * Straight: 500
     * Three of a Kind: 400
     * Two Pairs: 300
     * One Pair: 200
     * High Card: 100
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
            tulos = straightFlush(cards);
        }
        if (tulos == 0) {
            tulos = fourOfAKind(cards);
        }
        if (tulos == 0) {
            tulos = fullHouse(cards);
        }
        if (tulos == 0) {
            tulos = flush(cards);
        }
        if (tulos == 0) {
            tulos = straight(cards);
        }
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
    
    
    private double straightFlush(ArrayList<Card> cards) {
        
        Collections.sort(cards);
        
        int start = cards.get(0).getValue();
        
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i).getValue() == start + 1) {
                start += 1;
            } else {
                return 0;
            }
        }
        
        Suit define = cards.get(0).getSuit();
        
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i).getSuit() == define) {
                continue;
            } else {
                return 0;
            }
        }
        
        return 900.0 + start;
        
    }
    
    private double fourOfAKind(ArrayList<Card> cards) {
        
        Collections.sort(cards);
        int start = 0;
        
        if (cards.get(0).getValue() != cards.get(1).getValue()) {
            start = 1;
        }
        
        int define = cards.get(start).getValue();
        
        for (int i = start + 1; i < cards.size(); i++) {
            
            if (cards.get(i).getValue() == define) {
                continue;
            } else {
                return 0;
            }
            
        }
        
        return 800.0 + define;
        
    }
    
    private double fullHouse(ArrayList<Card> cards) {
        
        Collections.sort(cards);
        int start = 0;
        int fullOf = 2;
        
        if (cards.get(0).getValue() == cards.get(1).getValue()) {
            start = 2;
            fullOf = 0;
        } else if (cards.get(cards.size() - 1).getValue() == cards.get(cards.size() - 2).getValue()) {
            start = 0;
            fullOf = 5;
        } else {
            return 0;
        }
        
        int define = cards.get(start).getValue();
        
        for (int i = start + 1; i < start + 3; i++) {
            if (cards.get(i).getValue() == define) {
                continue;
            } else {
                return 0;
            }
        }
        
        return 700.0 + define + (cards.get(fullOf).getValue() / 100.0);
        
    } 
    
    private double flush(ArrayList<Card> cards) {
        
        Collections.sort(cards);
        
        Suit define = cards.get(0).getSuit();
        
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i).getSuit() == define) {
                continue;
            } else {
                return 0;
            }
        }
        
        return 600.0 + cards.get(cards.size() - 1).getValue();
        
    }
    
    private double straight(ArrayList<Card> cards) {
        
        Collections.sort(cards);
        
        int straight = cards.get(0).getValue();
        
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i).getValue() == straight + 1) {
                straight += 1;
            } else {
                return 0;
            }
        }
        
        return 500.0 + straight;
    }
    
    private double threeOfAKind(ArrayList<Card> cards) {
        
        Collections.sort(cards);
        
        for (int i = 2; i < cards.size(); i++) {
            if (cards.get(i).getValue() == cards.get(i - 1).getValue() &&
                cards.get(i - 2).getValue() == cards.get(i - 1).getValue()) {
                
                return 400.0 + cards.get(i).getValue();
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
                
                return 300.0 + cards.get(i).getValue() + (cards.get(index).getValue() / 100.0);
            }
        }
        
        return 0;
        
    }
    
    private double onePair(ArrayList<Card> cards) {
        
        Collections.sort(cards);
        
        for (int i = cards.size() - 1; i > 0; i--) {
            if (cards.get(i).getValue() == cards.get(i - 1).getValue()) {
                return 200.0 + cards.get(i).getValue();
            }
        }
        
        return 0;
    }
    
    private double highCard(ArrayList<Card> cards) {
        
        Collections.sort(cards);
        return 100.0 + cards.get(cards.size() - 1).getValue();
    }
    
    
   
    
}
