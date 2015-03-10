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
public enum Suit {
    
    SPADES(0), HEARTS(1), CLUBS(2), DIAMONDS(3);
    
    public final int id;
    
    Suit(int id) {
        this.id = id;
    }
    
    public static Suit getByID(int id) {
        
        if (id == SPADES.id) {
            return SPADES;
        } else if (id == HEARTS.id) {
            return HEARTS;
        } else if (id == CLUBS.id) {
            return CLUBS;
        } else {
            return DIAMONDS;
        }
        
    }
    
}
