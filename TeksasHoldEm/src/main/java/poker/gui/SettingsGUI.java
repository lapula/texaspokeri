/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.plaf.metal.MetalSliderUI;

/**
 *
 * @author Sara ja Laur
 */
public class SettingsGUI implements Runnable {

    private JFrame frame;
    private final int HEIGHT = 400;
    private final int WIDTH = 500;
    private JButton button;

    @Override
    public void run() {

        frame = new JFrame("Ikkuna");
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());
        frame.pack();
        //frame.setResizable(false);

        frame.setTitle("Settings");

        frame.setVisible(true);
        StartTimer();
    }

    public JFrame getFrame() {
        return frame;
    }

    private void createComponents(Container container) {

        container.setLayout(new BorderLayout());

        JPanel sliders = new JPanel(new GridLayout(3, 1));
        button = new JButton("Start Game");

        JSlider balance = new JSlider(JSlider.HORIZONTAL, 50, 300, 100);
        JSlider playersHuman = new JSlider(JSlider.HORIZONTAL, 0, 8, 3);
        JSlider playersAI = new JSlider(JSlider.HORIZONTAL, 0, 8, 0);
        
        JPanel balancePanel = new JPanel(new BorderLayout());
        JLabel balanceLabel = new JLabel("Starting Balance:");
        balancePanel.add(balance);
        balancePanel.add(balanceLabel, BorderLayout.NORTH);
        
        JPanel humansPanel = new JPanel(new BorderLayout());
        JLabel humansLabel = new JLabel("Human Players:");
        humansPanel.add(playersHuman);
        humansPanel.add(humansLabel, BorderLayout.NORTH);
        
        JPanel aiPanel = new JPanel(new BorderLayout());
        JLabel aiLabel = new JLabel("AI Players:");
        aiPanel.add(playersAI);
        aiPanel.add(aiLabel, BorderLayout.NORTH);

        button.setPreferredSize(new Dimension(WIDTH, 50));

        balance.setMajorTickSpacing(50);
        balance.setMinorTickSpacing(10);
        balance.setPaintTicks(true);
        balance.setPaintLabels(true);
        balance.setSnapToTicks(true);

        playersHuman.setMajorTickSpacing(1);
        playersHuman.setMinorTickSpacing(1);
        playersHuman.setPaintTicks(true);
        playersHuman.setPaintLabels(true);
        playersHuman.setSnapToTicks(true);

        playersAI.setMajorTickSpacing(1);
        playersAI.setMinorTickSpacing(1);
        playersAI.setPaintTicks(true);
        playersAI.setPaintLabels(true);
        playersAI.setSnapToTicks(true);

        setSliderUI(balance);
        setSliderUI(playersHuman);
        setSliderUI(playersAI);

        SettingsActionListener sal = new SettingsActionListener(balance, playersHuman, playersAI, button, frame);
        SettingsChangeListener scl = new SettingsChangeListener(playersHuman, playersAI);

        button.addActionListener(sal);
        balance.addChangeListener(scl);
        playersHuman.addChangeListener(scl);
        playersAI.addChangeListener(scl);
        
        
        balancePanel.setBackground(new Color(232,232,232));
        balance.setBackground(new Color(248,248,248));
        humansPanel.setBackground(new Color(232,232,232));
        playersHuman.setBackground(new Color(248,248,248));
        aiPanel.setBackground(new Color(232,232,232));
        playersAI.setBackground(new Color(248,248,248));
        
        sliders.add(balancePanel);
        sliders.add(humansPanel);
        sliders.add(aiPanel);

        container.add(sliders, BorderLayout.CENTER);
        container.add(button, BorderLayout.SOUTH);

    }

    private void StartTimer() {
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //System.out.println("asd");
                frame.repaint();
            }
        };
        new Timer(10, taskPerformer).start();
    }

    private void setSliderUI(JSlider slider) {
        slider.setUI(new MetalSliderUI() {
            @Override
            protected void scrollDueToClickInTrack(int direction) {
                int value = slider.getValue();
                if (slider.getOrientation() == JSlider.HORIZONTAL) {
                    value = this.valueForXPosition(slider.getMousePosition().x);
                } else if (slider.getOrientation() == JSlider.VERTICAL) {
                    value = this.valueForYPosition(slider.getMousePosition().y);
                }
                slider.setValue(value);
            }
        }
        );
    }

}
