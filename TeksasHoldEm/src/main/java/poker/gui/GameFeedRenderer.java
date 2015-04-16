/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.gui;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Oikealla olevan tekstikentän renderöinti.
 */
public class GameFeedRenderer extends JPanel {
    
    public GameFeedRenderer(LayoutManager lm) {
        super(lm);
        StartTimer();
    }
    
    private void StartTimer() {
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //System.out.println("asd");
                repaint();
            }
        };
        new Timer(10, taskPerformer).start();
    }

    
}
