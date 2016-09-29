/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buzzers;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Markcuz
 */
public class PlayScreenController implements Initializable {

    GUIController gui;
    HashMap<Integer, Player> playerMap;
    
    @FXML
    Label title;
    
    @FXML
    GridPane playGrid;
    
    @FXML
    Button playBack;
    
    @FXML
    BorderPane firstPane;
    @FXML
    BorderPane secondPane;
    @FXML
    BorderPane thirdPane;
    @FXML
    BorderPane fourthPane;
    
    @FXML
    Label pt2;
    @FXML
    Label pt3;
    @FXML
    Label pt4;
    
    @FXML
    Label pp1;
    @FXML
    Label pp2;
    @FXML
    Label pp3;
    @FXML
    Label pp4;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {    
        reset();
    }    

    void setMainWindow(GUIController aThis, HashMap playerMap) {
        this.gui = aThis;
        this.playerMap = playerMap;
    }
    
    public void update(String[] split) {
        
        String[][] timesTable = new String[12][2];
        
        for(int i = 0; i<12; i++) {
            String[] minor = split[i].split(" ");
            if(minor.length == 2) {
                timesTable[i][0] = minor[0];
                timesTable[i][1] = minor[1];
            }
            else {
                timesTable[i][0] = minor[0];
                timesTable[i][1] = "0";
            }
        }
        
        int current = 0;
        for(int i=1; i<13; i++) {
            current = Integer.parseInt(timesTable[i-1][0]);
            System.out.println("current: "+current);
            
            if(current==1){
                if(playerMap.containsKey(i)) {
                    Color col = playerMap.get(i).colour;  
                    String colString = col.toString().substring(2);  
                    firstPane.setStyle("-fx-background-color: #" + colString +";");
                    
                    pp1.setText(playerMap.get(i).playerName);
                }
            }
            if(current==2) {
                if(playerMap.containsKey(i)) {
                    Color col = playerMap.get(i).colour;  
                    String colString = col.toString().substring(2);  
                    secondPane.setStyle("-fx-background-color: #" + colString +";");
                    
                    pp2.setText(playerMap.get(i).playerName);
                    
                    pt2.setText(timesTable[i-1][1]);
                }
            }
            if(current==3) {
                if(playerMap.containsKey(i)) {
                    Color col = playerMap.get(i).colour;  
                    String colString = col.toString().substring(2);  
                    thirdPane.setStyle("-fx-background-color: #" + colString +";");
                    
                    pp3.setText(playerMap.get(i).playerName);
                    
                    pt3.setText(timesTable[i-1][1]);
                }
            }
            if(current==4) {
                if(playerMap.containsKey(i)) {
                    Color col = playerMap.get(i).colour;  
                    String colString = col.toString().substring(2);  
                    fourthPane.setStyle("-fx-background-color: #" + colString +";");
                    
                    pp4.setText(playerMap.get(i).playerName);
                    
                    pt4.setText(timesTable[i-1][1]);
                }
            }  
        }
    }
    
    public void reset() {
        Color colRed = Color.TRANSPARENT;  
        String colRedString = colRed.toString().substring(2);  
        firstPane.setStyle("-fx-background-color: #" + colRedString +";");
        secondPane.setStyle("-fx-background-color: #" + colRedString +";");
        thirdPane.setStyle("-fx-background-color: #" + colRedString +";");
        fourthPane.setStyle("-fx-background-color: #" + colRedString +";");
        
        pp1.setText("");
        pp2.setText("");
        pp3.setText("");
        pp4.setText("");

        pt2.setText("");
        pt3.setText("");
        pt4.setText("");
    }
    
    public void onBack() {
        Stage stage = (Stage) playBack.getScene().getWindow();
        stage.close();
        
        gui.isQuick = 2;
        gui.onExit();
    }
}
