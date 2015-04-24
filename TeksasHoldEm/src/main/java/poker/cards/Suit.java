/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.cards;

/**
 *
 * Kortin maata simuloiva luokka.
 */
public enum Suit {
    
    SPADES(0), HEARTS(1), CLUBS(2), DIAMONDS(3);
    
    /**
     * ID enumille.
     */
    
    public final int id;
    
    Suit(int id) {
        this.id = id;
    }
    
    /**
     * Antaa Maan ID:n.
     * @param id haettavan kortin id
     * @return Maan enum.
     */
    
    public static Suit getByID(int id) {
        
        if (id > 3 || id < 0) {
            return null;
        }
        
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
