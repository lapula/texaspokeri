/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kortit;

import java.util.ArrayDeque;
import java.util.Random;

/**
 *
 * @author Sara ja Laur
 */
public class Pakka {
    
    
    private final int KOKO = 52;
    private ArrayDeque<Kortti> pakka = new ArrayDeque<>();
    
    public Pakka() {
        
        this.luoPakka();
        
    }
    
    private void luoPakka() {
        
        Maa maa = null;
        
        for (int i = 0; i < KOKO / 13; i++) {
            
            maa = Maa.getByID(i);

            for (int a = 0; a < KOKO / 4; a++) {
                
                pakka.add(new Kortti(maa, a));
                
            }
            
        }
        
    }
    
    public void sekoita() {
        
        Random random = new Random();
        
        
        
    }
    
    public Kortti nostaKortti() {
        return pakka.pop();
    }
    
    public ArrayDeque pakka() {
        return this.pakka;
    }
    
}
