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
public class Kortti {
    
    private Maa maa;
    private int arvo;
    
    
    /**
     * Kortin konstruktori, ottaa kortin maan ja arvon.
     * @param maa kortin maa
     * @param arvo kortin arvo 
     */
    
    public Kortti(Maa maa, int arvo) {
        this.maa = maa;
        this.arvo = arvo;
    }
    
    
    public int getMaaID() {
        return this.maa.id;
    }
    
    public Maa getMaa() {
        return this.maa;
    }
    
    public int getArvo() {
        return this.arvo;
    }
    
    
}
