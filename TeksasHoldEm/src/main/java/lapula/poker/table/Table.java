/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapula.poker.table;

import lapula.poker.cards.Card;
import java.util.ArrayList;

/**
 *
 * @author Laur
 */
public class Table {
    
    private ArrayList<Card> tableCards = new ArrayList<Card>();
    private int pot;
    
    /**
     * Pelipöytä joka sisältää kortit ja potin.
     * @param pot pöydällä oleva rahamäärä.
     */
    
    public Table(int pot) {
        this.pot = pot;
    }
    
    /**
     * Lisätään pöydälle kortti.
     * @param card lisättävä kortti
     */
    
    public void addCard(Card card) {
        tableCards.add(card);
    }
    
    /**
     * @return pöydällä olevat kortit.
     */
    
    public ArrayList<Card> getCards() {
        return this.tableCards;
    }
    
    /**
     * Lisätään rahaa pottiin.
     * @param sum lisättävä summa.
     */
    
    public void addToPot(int sum) {
        if (sum < 0) {
            return;
        }
        this.pot += sum;
    }
    
    /**
     * @return potissa oleva summa.
     */
    
    public int getPot() {
        return this.pot;
    }
    
    /**
     * Nollataan potti (kierroksen jälkeen).
     */
    
    public void resetPot() {
        this.pot = 0;
    }
    
    
    
}
