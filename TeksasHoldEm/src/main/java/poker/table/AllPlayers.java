/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.table;

import java.util.ArrayList;

/**
 *
 * @author Sara ja Laur
 */
public class AllPlayers {
    
    private ArrayList<Player> players = new ArrayList<Player>();
    
    /**
     * Luokka jossa pidetään listaa kaikista pelaajista, siis myös niistä jotka
     * ovat tietyltä kierrokselta tippuneet (muttei koko pelistä).
     */
    
    public AllPlayers() {
        
    }
    
    /**
     * Lisätään listalle uusi pelaaja.
     * @param player uusi pelaaja.
     */
    
    public void addPlayer(Player player) {
        players.add(player);
    }
    
    /**
     * @return lista pelaajista.
     */
    
    public ArrayList getPlayers() {
        return players;
    }
    
    /**
     * Poista pelaaja.
     * @param player poistettava
     * @return true jos onnistuu
     */
    
    public boolean removePlayer(Player player) {
        for (int i = 0; i < players.size(); i++) {
            if (player.getId() == players.get(i).getId()) {
                players.remove(i);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Nollataan kaikkien pelaajien panokset panostuskierroksen päätteeksi.
     */
    
    public void resetBids() {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).resetBid();
        }
    }
}
