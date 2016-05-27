/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buzzers;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Markcuz
 */
public class PlayScreenController implements Initializable {

    GUIController gui;
    LinkedList<Team> teamList = new LinkedList();
    HashMap playerMap;
    
    @FXML
    Label title;
    
    @FXML
    GridPane mainGrid;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {    
        
    }    

    void setMainWindow(GUIController aThis, HashMap playerMap) {
        this.gui = aThis;
    }
    
    public void update(String recMessage) {
        title.setText(recMessage);
    }
    
    public void reset() {
        System.out.println("Resetting...");
    }

    public void setupGrid() {
        
        int size = teamList.size();
        
        if(size > 0) {
            String col1 = teamList.get(0).colour.toString().substring(2,teamList.get(0).colour.toString().length());
            final BorderPane team1 = new BorderPane();
            team1.setStyle("-fx-background-color: #" + col1 +";");
            
            Label name = new Label();
            Label members = new Label();
            Label time = new Label();
            
            name.setText(teamList.get(0).teamName);
            name.setStyle("-fx-font: 24 arial");
            
            playerMap = gui.getPlayerMap();
            
            StringBuilder membersBuilder = new StringBuilder(100);
            membersBuilder.append("Group Members: ");
            Iterator it = playerMap.entrySet().iterator();
            
            while(it.hasNext()) {
                HashMap.Entry pair = (HashMap.Entry)it.next();
                if(pair.getValue() == teamList.get(0)) {
                    membersBuilder.append(pair.getKey());
                    membersBuilder.append(", ");
                }
                System.out.println(pair.getKey() + " = " + pair.getValue());
                it.remove(); // avoids a ConcurrentModificationException
            }
            members.setText(membersBuilder.toString());
            name.setStyle("-fx-font: 14 arial");
            
            time.setText("time");
            name.setStyle("-fx-font: 18 arial");
            
            BorderPane.setMargin(name, new Insets(10, 10, 10, 10));
            BorderPane.setAlignment(name, Pos.CENTER);
            BorderPane.setAlignment(members, Pos.CENTER);
            BorderPane.setAlignment(time, Pos.CENTER);
            
            team1.setTop(name);
            team1.setCenter(time);
            team1.setBottom(members);
            mainGrid.add(team1, 0, 0);
        }
        if(size > 1) {
            String col2 = teamList.get(1).colour.toString().substring(2,teamList.get(1).colour.toString().length());
            final StackPane team2 = new StackPane();
            team2.setStyle("-fx-background-color: #" + col2+";");
            team2.getChildren().add(new Label(teamList.get(1).teamName));
            team2.setAlignment(team2, Pos.TOP_CENTER);
            mainGrid.add(team2, 1, 0);
        }
        if(size > 2) {
            String col3 = teamList.get(2).colour.toString().substring(2,teamList.get(2).colour.toString().length());
            final StackPane team3 = new StackPane();
            team3.setStyle("-fx-background-color: #" + col3+";");
            team3.getChildren().add(new Label(teamList.get(2).teamName));
            team3.setAlignment(team3, Pos.TOP_CENTER);
            mainGrid.add(team3, 0, 1);
        }
        if(size > 3) {
            String col4 = teamList.get(3).colour.toString().substring(2,teamList.get(3).colour.toString().length());
            final StackPane team4 = new StackPane();
            team4.setStyle("-fx-background-color: #" + col4+";");
            team4.getChildren().add(new Label(teamList.get(3).teamName));
            team4.setAlignment(team4, Pos.TOP_CENTER);
            mainGrid.add(team4, 1, 1);
        }
        if(size > 4) {
            String col5 = teamList.get(4).colour.toString().substring(2,teamList.get(4).colour.toString().length());
            final StackPane team5 = new StackPane();
            team5.setStyle("-fx-background-color: #" + col5+";");
            team5.getChildren().add(new Label(teamList.get(4).teamName));
            team5.setAlignment(team5, Pos.TOP_CENTER);
            mainGrid.add(team5, 0, 2);
        }
        if(size > 5) {
            String col6 = teamList.get(5).colour.toString().substring(2,teamList.get(5).colour.toString().length());
            final StackPane team6 = new StackPane();
            team6.setStyle("-fx-background-color: #" + col6+";");
            team6.getChildren().add(new Label(teamList.get(5).teamName));
            team6.setAlignment(team6, Pos.TOP_CENTER);
            mainGrid.add(team6, 1, 2);
        }
    }
    
    public void setupList(LinkedList<Team> team) {
        this.teamList.addAll(team);
    }
}
