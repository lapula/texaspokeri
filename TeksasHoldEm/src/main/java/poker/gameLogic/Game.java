/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.gameLogic;

import poker.cards.Deck;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JTextArea;
import poker.gui.SoundPlayer;
import poker.table.Player;
import poker.table.Table;
import poker.table.AllPlayers;
import poker.util.CodeToText;
import poker.util.GameFeed;

/**
 * Pelin ns. pääluokka, jossa hoidetaan peliin liittyvät toimitukset kuten
 * korttien jakaminen, pelaajien hallinnoiminen, kierrosten hallinnointi yms.
 */
public class Game {

    private JTextArea feed;
    private int roundNumber;
    private GameSettings gameSettings;
    private ArrayList<Player> currentPlayers;
    private Table table;
    private Deck deck;
    private Bidding bidding;
    private Resolve resolve;
    private CodeToText codeToText;
    private boolean isRunning = false;
    private AllPlayers allPlayers;
    private Player winner;
    private boolean isSetupping = true;
    private SoundPlayer soundPlayer;

    /**
     * Huomionarvoista tässä lähinnä gameSettingsistä kutsuttava initialize, joka
     * luo pelaajat asetusten mukaan.
     * @param feed pelin tekstikenttä.
     */
    public Game(JTextArea feed) {

        this.table = new Table(0);
        this.feed = feed;
        this.roundNumber = 0;
        this.gameSettings = new GameSettings();
        this.gameSettings.initialize();
        this.allPlayers = gameSettings.getAllPlayers();
        this.currentPlayers = new ArrayList<>(allPlayers.getPlayers());
        this.codeToText = new CodeToText();
        this.soundPlayer = new SoundPlayer();

    }
    
    /**
     * Alustetaan kierros, eli laitetaan kierrosta vastaavasti kortteja pöytään,
     * sekä aloitetaan tarjousvaihe. Ensimmäisellä kierroksella tehdään lisäksi setup.
     * @param round kierroksen numero
     */
    public void startRound(int round) {

        isRunning = true;
        this.roundNumber = round;

        if (roundNumber == 0) {
            isSetupping = true;
            setUp();
            isSetupping = false;
        }

        if (round == 0) {
            bidding = new Bidding(true, this);
        } else if (round == 1) {
            table.addCard(deck.drawCard());
            table.addCard(deck.drawCard());
            table.addCard(deck.drawCard());
            soundPlayer.playSound("dealThree");
            bidding = new Bidding(false, this);
        } else {
            table.addCard(deck.drawCard());
            soundPlayer.playSound("deal");
            bidding = new Bidding(false, this);
        }

        GameFeed.addText(feed, "-----------------------------------------------");
        GameFeed.addText(feed, "");

        String roundText;

        switch (roundNumber) {
            case 0:
                roundText = "PRE-FLOP";
                break;
            case 1:
                roundText = "THE FLOP";
                break;
            case 2:
                roundText = "THE TURN";
                break;
            case 3:
                roundText = "THE RIVER";
                break;
            default:
                roundText = "ROUND";

        }
        
        GameFeed.addText(feed, roundText);
        GameFeed.addText(feed, "");
        GameFeed.addText(feed, "----------------------------------------------");
        GameFeed.addText(feed, "");
    }

    /**
     * Tehdään erän loppumiseen liittyvät toimenpiteet, mm. ratkaistaan voittaja
     * ja jaetaan rahat.
     */
    public void finishGame() {

        GameFeed.addText(feed, "----------------------------------------------");
        GameFeed.addText(feed, "");
        GameFeed.addText(feed, "Showdown!");
        GameFeed.addText(feed, "");
        GameFeed.addText(feed, "----------------------------------------------");
        GameFeed.addText(feed, "");

        resolve = new Resolve(currentPlayers, table);
        HashMap<Player, Double> result = resolve.giveWinner();

        GameFeed.addText(feed, "AND THE WINNER IS:");
        
        for (Player player : result.keySet()) {
            GameFeed.addText(feed, "Player " + player.getId() + " with a " + codeToText.ratingToText(result.get(player)));
            winner = player;
        }
        GameFeed.addText(feed, "");

        dividePot(result);

        endOfRound();
        isRunning = false;

        if (allPlayers.getPlayers().size() == 1) {
            Player endWinner = (Player) allPlayers.getPlayers().get(0);
            GameFeed.addText(feed, "PLAYER " + endWinner.getId() + " HAS CLEANED THE TABLE!");
            GameFeed.addText(feed, "GAME HAS ENDED!");
        }

    }
    
    /**
     * Tehdään ensimmäisen kierroksen tarvittavat luokkien alustukset ja
     * nollaukset.
     */
    private void setUp() {

        soundPlayer.playSound("start");

        for (int i = 0; i < allPlayers.getPlayers().size(); i++) {

            Player player = (Player) allPlayers.getPlayers().get(i);

            player.refreshMaxWin();
            player.resetAllIn();

        }

        table.getCards().removeAll(table.getCards());
        table.resetPot();

        for (int i = 0; i < allPlayers.getPlayers().size(); i++) {
            Player player = (Player) allPlayers.getPlayers().get(i);
            player.getCards().removeAll(player.getCards());
        }

        deck = new Deck();

        for (int i = 0; i < currentPlayers.size(); i++) {

            currentPlayers.get(i).addCard(deck.drawCard());
            currentPlayers.get(i).addCard(deck.drawCard());
        }
    }

    private void endOfRound() {

        GameFeed.addText(feed, "CURRENT SITUATION IS:");

        for (int i = 0; i < allPlayers.getPlayers().size(); i++) {

            Player player = (Player) allPlayers.getPlayers().get(i);
            player.refreshMaxWin();
            player.resetAllIn();

            GameFeed.addText(feed, "Player " + player.getId() + " balance: " + player.getBalance());
            if (player.getBalance() < 10) {
                allPlayers.removePlayer(player);
                i--;
            }

        }
        GameFeed.addText(feed, "");

        currentPlayers = new ArrayList<>(allPlayers.getPlayers());
        Player player = currentPlayers.get(0);
        currentPlayers.remove(0);
        currentPlayers.add(player);
    }

    private void dividePot(HashMap<Player, Double> result) {

        ArrayList<Player> copy = new ArrayList<>(currentPlayers);
        int pot = table.getPot();

        while (true) {

            int win = 0;

            for (Player player : result.keySet()) {
                win = player.getMaxWin() * allPlayers.getPlayers().size();

                if (win < pot) {
                    player.alterBalance(win);
                } else {
                    player.alterBalance(pot);

                }
                pot = pot - win;
                copy.remove(player);
            }

            if (pot <= 0 || copy.size() < 1) {
                if (pot > 0) {
                    System.out.println("POT WAS NOT DIVIDED FULLY, " + pot + " REMAINED");
                }
                break;
            } else {
                resolve = new Resolve(copy, table);
                result = new HashMap<>(resolve.giveWinner());
            }
        }
    }

    public int getRoundNumber() {
        return this.roundNumber;
    }

    public ArrayList<Player> getCurrentPlayers() {
        return this.currentPlayers;
    }

    public void setCurrentPlayers(ArrayList<Player> newPlayers) {
        this.currentPlayers = newPlayers;
    }

    public JTextArea getFeed() {
        return this.feed;
    }

    public Bidding getCurrentBidding() {
        return this.bidding;
    }
    
    /**
     * @return onko kierros käynnissä.
     */
    public boolean isRunning() {
        return this.isRunning;
    }

    public AllPlayers getGameAllPlayers() {
        return this.allPlayers;
    }

    public Table getGameTable() {
        return this.table;
    }

    public Player getWinner() {
        return this.winner;
    }

    /**
     * onko setUp metodi käynnissä.
    */
    public boolean isSetupping() {
        return this.isSetupping;
    }

}
