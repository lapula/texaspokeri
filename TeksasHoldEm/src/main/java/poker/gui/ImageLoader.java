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
import poker.cards.Card;
import poker.cards.Suit;

/**
 *
 * @author Sara ja Laur
 */
public class ImageLoader {

    private BufferedImage image;
    private HashMap<String, BufferedImage> images;

    public ImageLoader() {

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
                    System.exit(1);
                }
                images.put(path, image);
            }

        }

        path = "back";

        try {
            images.put("back", ImageIO.read(new File("images/" + path + ".png")));
        } catch (IOException ex) {
            System.out.println("Failed to load image!");
            System.exit(1);
        }
        

    }

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

}
