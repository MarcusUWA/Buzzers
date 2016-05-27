/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buzzers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
public class QuickPlayController implements Initializable {

    GUIController gui;
    
    @FXML
    GridPane mainGrid;
    
    @FXML
    Button backButton;
    
    @FXML
    BorderPane b1;
    @FXML
    BorderPane b2;
    @FXML
    BorderPane b3;
    @FXML
    BorderPane b4;
    
    @FXML
    Label t1;
    @FXML
    Label t2;
    @FXML
    Label t3;
    @FXML
    Label t4;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reset();
    } 
    
     void setMainWindow(GUIController aThis) {
        this.gui = aThis;
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
        
        int[] teamPlace = {13,13,13,13};
        
        for(int i = 0; i<4; i++) {
            int place = 13;
            for(int j = 0; j<3; j++) {
                if(Integer.parseInt(timesTable[i*3+j][0])<place) {
                    place = Integer.parseInt(timesTable[i*3+j][0]);
                }
            }
            teamPlace[i] = place;
        }
        
        int[] order = new int[4];
        
        for(int i=0; i<4; i++) {
            int min=13;
            int minTeam = 5;
            
            for (int j=0; j<4; j++) {
                if(teamPlace[j]<min) {
                    min = teamPlace[j];
                    minTeam = j;
                }
            }
            
            order[i] = minTeam;
            System.out.println("place"+i+" "+order[i]);
            if(minTeam != 5) {
                teamPlace[minTeam] = 13;
            }
        }
        
        switch (order[0]) {
            case 0:
                {
                    t1.setText("1st");
                    Color col2 = Color.DARKBLUE;
                    String colString2 = col2.toString().substring(2);
                    b2.setStyle("-fx-background-color: #" + colString2 +";");
                    Color col3 = Color.DARKGOLDENROD;
                    String colString3 = col3.toString().substring(2);
                    b3.setStyle("-fx-background-color: #" + colString3 +";");
                    Color col4 = Color.DARKGREEN;
                    String colString4 = col4.toString().substring(2);
                    b4.setStyle("-fx-background-color: #" + colString4 +";");
                    break;
                }
            case 1:
                {
                    t2.setText("1st");
                    Color col1 = Color.DARKRED;
                    String colString1 = col1.toString().substring(2);
                    b1.setStyle("-fx-background-color: #" + colString1 +";");
                    Color col3 = Color.DARKGOLDENROD;
                    String colString3 = col3.toString().substring(2);
                    b3.setStyle("-fx-background-color: #" + colString3 +";");
                    Color col4 = Color.DARKGREEN;
                    String colString4 = col4.toString().substring(2);
                    b4.setStyle("-fx-background-color: #" + colString4 +";");
                    break;
                }
            case 2:
                {
                    t3.setText("1st");
                    Color col1 = Color.DARKRED;
                    String colString1 = col1.toString().substring(2);
                    b1.setStyle("-fx-background-color: #" + colString1 +";");
                    Color col2 = Color.DARKBLUE;
                    String colString2 = col2.toString().substring(2);
                    b2.setStyle("-fx-background-color: #" + colString2 +";");
                    Color col4 = Color.DARKGREEN;
                    String colString4 = col4.toString().substring(2);
                    b4.setStyle("-fx-background-color: #" + colString4 +";");
                    break;
                }
            case 3:
                {
                    t4.setText("1st");
                    Color col1 = Color.DARKRED;
                    String colString1 = col1.toString().substring(2);
                    b1.setStyle("-fx-background-color: #" + colString1 +";");
                    Color col2 = Color.DARKBLUE;
                    String colString2 = col2.toString().substring(2);
                    b2.setStyle("-fx-background-color: #" + colString2 +";");
                    Color col3 = Color.DARKGOLDENROD;
                    String colString3 = col3.toString().substring(2);
                    b3.setStyle("-fx-background-color: #" + colString3 +";");
                    break;
                }
            default:
                break;
        }
        switch(order[1]) {
            case 0:
            {
                t1.setText("2nd");
                break;
            }
            case 1:
            {
                t2.setText("2nd");
                break;
            }
            case 2:
            {
                t3.setText("2nd");
                break;
            }
            case 3:
            {
                t4.setText("2nd");
                break;
            }
            default:
            {
                break;
            }
        }
        
        switch(order[2]) {
            case 0:
            {
                t1.setText("3rd");
                break;
            }
            case 1:
            {
                t2.setText("3rd");
                break;
            }
            case 2:
            {
                t3.setText("3rd");
                break;
            }
            case 3:
            {
                t4.setText("3rd");
                break;
            }
            default:
            {
                break;
            }
        }
        switch(order[3]) {
            case 0:
            {
                t1.setText("4th");
                break;
            }
            case 1:
            {
                t2.setText("4th");
                break;
            }
            case 2:
            {
                t3.setText("4th");
                break;
            }
            case 3:
            {
                t4.setText("4th");
                break;
            }
            default:
            {
                break;
            }
        }
    }
    
    public void reset() {
        Color colRed = Color.RED;  
        String colRedString = colRed.toString().substring(2);  
        b1.setStyle("-fx-background-color: #" + colRedString +";");
        t1.setText("");
        
        Color colBlue = Color.BLUE;
        String colBlueString = colBlue.toString().substring(2);
        b2.setStyle("-fx-background-color: #" + colBlueString +";");
        t2.setText("");
        
        Color colYel = Color.YELLOW;
        String colYelString = colYel.toString().substring(2); 
        b3.setStyle("-fx-background-color: #" + colYelString +";");
        t3.setText("");
        
        Color colGreen = Color.LIME;
        String colGreenString = colGreen.toString().substring(2);
        b4.setStyle("-fx-background-color: #" + colGreenString +";");
        t4.setText("");
    }
    
    public void onBack(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        
        //gui.comms.disconnect();
    }
    
}
