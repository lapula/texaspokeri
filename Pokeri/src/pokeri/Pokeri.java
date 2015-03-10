/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokeri;

import kortit.Kortti;
import kortit.Maa;
import kortit.Pakka;

/**
 *
 * @author Laur
 */


public class Pokeri {
    
    
    
    
    public static void main(String[] args) {
        
        Pakka pakka = new Pakka();
        
        for (int i = 0; i < 52; i++) {
            
            Kortti kortti = pakka.nostaKortti();
            System.out.println("maa: " + kortti.maa() + " arvo: " + kortti.arvo());
        }
        
    }
    
}
