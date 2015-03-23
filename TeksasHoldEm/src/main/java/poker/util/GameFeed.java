/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.util;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JTextArea;

/**
 *
 * @author Sara ja Laur
 */
public class GameFeed {
    
    private static int maxLines = 200;
    
    public GameFeed() {
        
    }
    
    public static void addText(JTextArea field, String text) {
        
        field.append(text + "\n");
        
        Scanner scanner = new Scanner(field.getText());
        
        ArrayList<String> textField = new ArrayList<>();
        
        while (scanner.hasNextLine()) {
            textField.add(scanner.nextLine());
        }
        
        int beginning = textField.size() - maxLines;
        
        if (textField.size() < maxLines) {
            beginning = 0;
        }
        
        field.setText("");
        
        for (int i = beginning; i < textField.size(); i++) {
            field.append(textField.get(i) + "\n");
        }
    }
    
}
