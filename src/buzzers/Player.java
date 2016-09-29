/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buzzers;

import javafx.scene.paint.Color;

/**
 *
 * @author Markcuz
 */
public class Player {
    String playerName;
    Color colour;
    
    public Player(String playerName, Color col) {       
        this.playerName = playerName;
        this.colour = col;      
    }
}
