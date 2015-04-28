/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import poker.cards.Card;
import poker.cards.Suit;

/**
 * Luokka joka lataa kuvat ja josta kuvat haetaan.
 */
public class ImageLoader {

    private BufferedImage image;
    private HashMap<String, BufferedImage> images;

    public ImageLoader(JFrame frame) {

        images = new HashMap<>();

        String path = "";

        for (int i = 0; i < 4; i++) {
            path = "";
            if (i == 0) {
                path += "s";
            } else if (i == 1) {
                path += "h";
            } else if (i == 2) {
                path += "c";
            } else if (i == 3) {
                path += "d";
            }
            String copy = path;
            for (int a = 0; a < 13; a++) {
                path = copy;
                path += "" + a;
                try {
                    image = ImageIO.read(new File("images/" + path + ".png"));
                } catch (IOException ex) {
                    System.out.println("Failed to load image!");
                    JOptionPane.showMessageDialog(frame, "Some game files are missing!");
                    System.exit(1);
                }
                images.put(path, image);
            }

        }

        path = "back";

        try {
            images.put("back", ImageIO.read(new File("images/" + path + ".png")));
            path = "background";
            images.put("background", ImageIO.read(new File("images/" + path + ".png")));
            images.put("set1", ImageIO.read(new File("images/set1.png")));
            images.put("set2", ImageIO.read(new File("images/set2.png")));
            images.put("set3", ImageIO.read(new File("images/set3.png")));
            images.put("set4", ImageIO.read(new File("images/set4.png")));
            images.put("start1", ImageIO.read(new File("images/start1.png")));
            images.put("start2", ImageIO.read(new File("images/start2.png")));
            images.put("start3", ImageIO.read(new File("images/start3.png")));
            images.put("start4", ImageIO.read(new File("images/start4.png")));
        } catch (IOException ex) {
            System.out.println("Failed to load image!");
            JOptionPane.showMessageDialog(frame, "Some game files are missing!");
            System.exit(1);
        }
        

    }

    /**
     * Tällä saa haettua valmiiksi ladattuja korttien kuvia.
     * @param card kortti jonka kuva haetaan
     * @return kuva
     */
    public BufferedImage loadCardImage(Card card) {

        String key = "";

        if (card.getSuit().id == 0) {
            key += "s";
        } else if (card.getSuit().id == 1) {
            key += "h";
        } else if (card.getSuit().id == 2) {
            key += "c";
        } else if (card.getSuit().id == 3) {
            key += "d";
        }

        int value = card.getValue();

        if (value == 0) {
            key += "0";
        } else if (value == 1) {
            key += "1";
        } else if (value == 2) {
            key += "2";
        } else if (value == 3) {
            key += "3";
        } else if (value == 4) {
            key += "4";
        } else if (value == 5) {
            key += "5";
        } else if (value == 6) {
            key += "6";
        } else if (value == 7) {
            key += "7";
        } else if (value == 8) {
            key += "8";
        } else if (value == 9) {
            key += "9";
        } else if (value == 10) {
            key += "10";
        } else if (value == 11) {
            key += "11";
        } else if (value == 12) {
            key += "12";
        }

        return images.get(key);
    }
    
    /**
     * KOSKEE KAIKKIA ALLAOLEVIA!
     * nappien kuvat, jokaiselle 4 eri kuvaa, jotka ovat:
     * perus, hover, klikattu, poistettu käytöstä.
     * @return kuva
     */
    
    
    public BufferedImage loadBackground() {
        return this.images.get("background");
    }
    
    public BufferedImage loadCardBack() {
        return this.images.get("back");
    }
    
    public BufferedImage loadButtonStyle1() {
        return this.images.get("set1");
    }
    public BufferedImage loadButtonStyle2() {
        return this.images.get("set2");
    }
    public BufferedImage loadButtonStyle3() {
        return this.images.get("set3");
    }
    
    public BufferedImage loadButtonStyle4() {
        return this.images.get("set4");
    }
    
    public BufferedImage loadStart1() {
        return this.images.get("start1");
    }
    
    public BufferedImage loadStart2() {
        return this.images.get("start2");
    }
    
    public BufferedImage loadStart3() {
        return this.images.get("start3");
    }
    
    public BufferedImage loadStart4() {
        return this.images.get("start4");
    }
    

}
