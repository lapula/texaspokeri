/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.util;

import java.util.Scanner;

/**
 *
 * @author Sara ja Laur
 */
public class TextReader {
    
    Scanner reader;
    
    public TextReader() {
        reader = new Scanner(System.in);
    }
    
    public String read() {
        String order = reader.nextLine();
        return order;
    }
    
}
