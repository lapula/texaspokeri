/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kortit;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Sara ja Laur
 */
public class Pakka {
    
    
    private final int KOKO = 52;
    private ArrayList<Kortti> pakka = new ArrayList<>();
    
    
    /**
     * Luodaan uusi pakka ja sekoitetaan se;
     */
    
    public Pakka() {
        this.luoPakka();
        this.sekoita();
    }
    
    
    /**
     * Luodaan uusi pakka jossa kortit ovat järjestyksessä.
     */
    
    private void luoPakka() {
        
        Maa maa = null;
        
        for (int i = 0; i < KOKO / 13; i++) {
            
            maa = Maa.getByID(i);

            for (int a = 0; a < KOKO / 4; a++) {
                pakka.add(new Kortti(maa, a));   
            }   
        }    
    }
    
    
    /**
     * Metodi pakan sekoittamiseen.
     */
    
    private void sekoita() {
        
        Collections.shuffle(pakka);
        
    }
    
    /**
     * Nostaa kortin.
     * @return "päällimmäinen" kortti.
     */
    
    public Kortti nostaKortti() {
        
        Kortti kortti = pakka.get(pakka.size() - 1);
        this.pakka.remove(pakka.size() - 1);
        return kortti;
    }
    
    
    
}
