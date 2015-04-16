/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.gui;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Asetuspaneelin kuuntelija, estää pelaajia olemasta yli 8.
 */
public class SettingsChangeListener implements ChangeListener {

    private JSlider playersHuman;
    private JSlider playersAI;

    public SettingsChangeListener(JSlider playersHuman, JSlider playersAI) {
        this.playersHuman = playersHuman;
        this.playersAI = playersAI;
    }

    @Override
    public void stateChanged(ChangeEvent ce) {

        if (ce.getSource() == playersHuman) {

            if (playersAI.getValue() + playersHuman.getValue() > 8) {
                playersAI.setValue(8 - playersHuman.getValue());
            }

        } else if (ce.getSource() == playersAI) {
            if (playersAI.getValue() + playersHuman.getValue() > 8) {
                playersHuman.setValue(8 - playersAI.getValue());
            }
        }

    }

}
