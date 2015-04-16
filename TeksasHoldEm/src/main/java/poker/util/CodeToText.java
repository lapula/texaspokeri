/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.util;

import poker.cards.Card;
import poker.cards.Suit;

/**
 * Luokka joka muuttaa käsien ja korttien arvot tekstiksi.
 */
public class CodeToText {

    public CodeToText() {

    }
    
    /**
     * @param card Kortin luokka, jos halutaan kortin teksti.
     * @param numeric numeron arvo, jos halutaan vain arvon teksti.
     * @return koodinumero tekstinä
     */
    public String cardText(Card card, int numeric) {

        String suitText = null;
        int value = numeric;
        
        if (numeric < 0) {
            suitText = suitText(card.getSuit());
            value = card.getValue();
        }
        
        String cardText = null;

        if (value == 0) {
            cardText = "Two";
        } else if (value == 1) {
            cardText = "Three";
        } else if (value == 2) {
            cardText = "Four";
        } else if (value == 3) {
            cardText = "Five";
        } else if (value == 4) {
            cardText = "Six";
        } else if (value == 5) {
            cardText = "Seven";
        } else if (value == 6) {
            cardText = "Eight";
        } else if (value == 7) {
            cardText = "Nine";
        } else if (value == 8) {
            cardText = "Ten";
        } else if (value == 9) {
            cardText = "Jack";
        } else if (value == 10) {
            cardText = "Queen";
        } else if (value == 11) {
            cardText = "King";
        } else if (value == 12) {
            cardText = "Ace";
        }

        if (numeric > -1) {
            return cardText;
        }
        return cardText + " of " + suitText;
    }

    private String suitText(Suit suit) {

        if (suit.id == 0) {
            return "Spades";
        } else if (suit.id == 1) {
            return "Hearts";
        } else if (suit.id == 2) {
            return "Clubs";
        } else {
            return "Diamonds";
        }

    }
    
    /**
     * Käden arvon numeerinen arvo tekstiksi.
     * @param rating käden arvo
     * @return teksti
     */
    public String ratingToText(double rating) {

        String answer = "";
        double cards = rating;

        if (rating < 200) {
            cards -= 100;
            return "High Card ";
        } else if (rating < 300) {
            cards -= 200;
            return "One Pair ";
        } else if (rating < 400) {
            cards -= 300;
            return "Two Pairs ";
        } else if (rating < 500) {
            cards -= 400;
            return "Three of a Kind ";
        } else if (rating < 600) {
            cards -= 500;
            return "Straight ";
        } else if (rating < 700) {
            cards -= 600;
            return "Flush ";
        } else if (rating < 800) {
            cards -= 700;
            return "Full House ";
        } else if (rating < 900) {
            cards -= 800;
            return "Four of a Kind ";
        } else if (rating < 1000) {
            cards -= 900;
            return "Straight Flush";
        }
        
        return answer;

    }

}
