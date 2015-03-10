/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kortit;

/**
 *
 * @author Sara ja Laur
 */
public enum Maa {
    
    PATA(0), HERTTA(1), RISTI(2), RUUTU(3);
    
    public final int id;
    
    Maa(int id) {
        this.id = id;
    }
    
    public static Maa getByID(int id) {
        
        
        
        if (id == PATA.id) {
            return PATA;
        } else if (id == HERTTA.id) {
            return HERTTA;
        } else if (id == RISTI.id) {
            return RISTI;
        } else {
            return RUUTU;
        }
        
    }
    
}
