/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.table;

import poker.cards.Card;
import java.util.ArrayList;

/**
 * Pelipöytää simuloiva luokka.
 */
public class Table {
    
    private ArrayList<Card> tableCards = new ArrayList<>();
    private static int pot;
    
    /**
     * Pelipöytä joka sisältää kortit ja potin.
     * @param pot pöydällä oleva rahamäärä.
     */
    
    public Table(int pot) {
        Table.pot = pot;
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
    
    public static void addToPot(int sum) {
        if (sum < 0) {
            return;
        }
        Table.pot += sum;
    }
    
    /**
     * @return potissa oleva summa.
     */
    
    public int getPot() {
        return Table.pot;
    }
    
    /**
     * Nollataan potti (kierroksen jälkeen).
     */
    
    public void resetPot() {
        Table.pot = 0;
    }
    
    
    
}
