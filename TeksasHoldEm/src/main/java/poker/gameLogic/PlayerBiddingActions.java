/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.gameLogic;

import java.util.ArrayList;
import poker.table.Player;
import poker.table.Table;

/**
 * Käsittelee rahatilanteen muutosta vaativat pelaajan toiminnot.
 */
public class PlayerBiddingActions {
    
    private static String order;
    private Table table;
    
    /**
     * @param table pelipöytä
     */
    public PlayerBiddingActions(Table table) {
        this.order = "";
        this.table = table;
    }
    
    /**
     * Call - toiminto, maksetaan päästä mukaan.
     * @param player kyseessä oleva pelaaja
     * @param amount call toimintoon vaadittava rahamäärä.
     * @return true jos onnistuu
     */
    public boolean call(Player player, int amount) {

        boolean succeeded = player.alterBalance(-(amount));

        if (!succeeded) {
            return false;
        }

        table.addToPot(amount);
        return true;
    }

    /**
     * Bid - paljonko tarjoaa.
     * @param player kyseessä oleva pelaaja
     * @param sum tarjottava määrä
     * @return true jos onnistuu
     */
    public boolean bid(Player player, int sum) {

        boolean succeeded = player.alterBalance(-sum);

        if (!succeeded) {
            return false;
        }

        table.addToPot(sum);
        return true;

    }

    /**
     * Raise - lisätään olemassaolevan tarjouken lisäksi
     * @param player kyseessä oleva pelaaja
     * @param highest korkein panos
     * @param sum lisättävä summa
     * @return true jos onnistuu
     */
    public boolean raise(Player player, int highest, int sum) {

        boolean succeeded = player.alterBalance(-(sum + (highest - player.getBid())));

        if (!succeeded) {
            return false;
        }

        table.addToPot(sum + (highest - player.getBid()));
        return true;

    }
    
    
    
}
