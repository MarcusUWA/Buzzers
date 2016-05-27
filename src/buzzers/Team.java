/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buzzers;

import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 *
 * @author Markcuz
 */
public class Team {
    
    String teamName;
    ArrayList<Integer> list;
    Color colour;
    
    public Team(String teamName, Color col) {       
        this.teamName = teamName;
        this.colour = col;
        list = new ArrayList(12);        
    }
}
