/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buzzers;

import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Markcuz
 */
public class GUIController implements Initializable {

    Communicator comms;  
    StringBuilder message = new StringBuilder(100);
    
    HashMap playerMap = new HashMap();
    LinkedList<Team> teamList = new LinkedList();
    
    PlayScreenController playController;
    QuickPlayController quickPlay;
    
    //boolean to cehck is using quickPlay
    int isQuick;
    
    @FXML
    AnchorPane mainPane;
        
    @FXML
    Button startButton;
    @FXML
    Button quickStart;
    @FXML
    Button refreshButton;
    @FXML 
    Button teamsButton;
    
    @FXML
    Label title;
    
    @FXML
    ChoiceBox<String> ports;
    
    @FXML
    ChoiceBox<String> c1;
    @FXML
    ChoiceBox<String> c2;
    @FXML
    ChoiceBox<String> c3;
    @FXML
    ChoiceBox<String> c4;
    @FXML
    ChoiceBox<String> c5;
    @FXML
    ChoiceBox<String> c6;
    @FXML
    ChoiceBox<String> c7;
    @FXML
    ChoiceBox<String> c8;
    @FXML
    ChoiceBox<String> c9;
    @FXML
    ChoiceBox<String> c10;
    @FXML
    ChoiceBox<String> c11;
    @FXML
    ChoiceBox<String> c12;
    
    @FXML
    CheckBox p1;
    @FXML
    CheckBox p2;
    @FXML
    CheckBox p3;
    @FXML
    CheckBox p4;
    @FXML
    CheckBox p5;
    @FXML
    CheckBox p6;
    @FXML
    CheckBox p7;
    @FXML
    CheckBox p8;
    @FXML
    CheckBox p9;
    @FXML
    CheckBox p10;
    @FXML
    CheckBox p11;
    @FXML
    CheckBox p12;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        isQuick = 0;
        /*mainPane.setBackground(new Background(new BackgroundImage(new Image("gradient.png"),
        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
          BackgroundSize.DEFAULT)));
        */
        comms = new Communicator(this);
        try {
            comms.searchForPorts();
        } 
        catch (Exception ex) {
            System.out.println("Error!");
        }
    } 
    
    /**
     * @param input
     * @brief receives the next piece of serial data
     */
    public void recMessage(String input) {
        message.append(input);
        
        int position;
        String temp;
        
        if((message.toString()).contains("\n")) {
            temp = message.toString();
            position = temp.indexOf('\n');
            message = new StringBuilder(temp.substring(position+1));
            temp = temp.substring(0, position);
            update(temp);
        }
        
    }
    
    /**
     * @param message
     * @brief updates the page with new input
     */
    public void update(String message) {
        System.out.println("Message: " + message);
        String[] split = message.split(",");
        
        if(split.length>11) {
            if(isQuick == 0) {
                playController.update(message);
            }
            else {
                quickPlay.update(split);
            }
        }
        else {
            if(isQuick ==0) {
                playController.reset();
            }
            else {
                quickPlay.reset();
            }
        }
    }
    
    /**
     * @param event
     * @brief button command to open quick play
     * @throws Exception 
     */
    public void onQuickStart(ActionEvent event) throws Exception {
        isQuick = 1;
        
        try {
            comms.connect(ports.getValue());
            comms.initListener();
        }
        catch (Exception e) {
            System.out.println("Failed to connect");
        }
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("QuickPlay.fxml"));
        
        try {
            Pane pane = loader.load();

            quickPlay = loader.getController();
            quickPlay.setMainWindow(this);
        
            Stage stage = new Stage();
        
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(refreshButton.getScene().getWindow());
        
            Scene scene = new Scene(pane);
        
            stage.setScene(scene);
            stage.setTitle("Answer Buzzers!!");
            
            stage.setMaximized(true);
            stage.show();
        }
        
        catch (Exception e) {
            System.out.println("Failed to open QuickPlay Window");
        }
    }
    
    /**
     * @brief refreshes the ports
     * @param event 
     */
    public void onRefresh(ActionEvent event) {
        ports.getItems().clear();
        comms.searchForPorts();
    }
    
    //***************** Start Custom Players and Teams ***************//
    
    /**
     * @brief maps all the players into the corresponding teams via HashMap
     */
    public void mapPlayers() {
        if(p1.isSelected()) {
            System.out.println("added player");
            playerMap.put(1,c1.getValue());
        }
        
        if(p2.isSelected()) {
            playerMap.put(2,c2.getValue());
        }
        
        if(p3.isSelected()) {
            playerMap.put(3,c3.getValue());
        }
        
        if(p4.isSelected()) {
            playerMap.put(4,c4.getValue());
        }
        
        if(p5.isSelected()) {
            playerMap.put(5,c5.getValue());
        }
        
        if(p6.isSelected()) {
            playerMap.put(6,c6.getValue());
        }
        
        if(p7.isSelected()) {
            playerMap.put(7,c7.getValue());
        }
        
        if(p8.isSelected()) {
            playerMap.put(8,c8.getValue());
        }
        if(p9.isSelected()) {
            playerMap.put(9,c9.getValue());
        }
        
        if(p10.isSelected()) {
            playerMap.put(10,c10.getValue());
        }
        
        if(p11.isSelected()) {
            playerMap.put(11,c11.getValue());
        }
        
        if(p12.isSelected()) {
            playerMap.put(12,c12.getValue());
        }
    }
    
    /**
     * @brief starts the custom buzzer window
     * 
     * @param event
     * @throws Exception 
     */
    public void onStart(ActionEvent event) throws Exception {
        
        isQuick = 0;
        mapPlayers();

        try {
            comms.connect(ports.getValue());
            comms.initListener();
        }
        catch (Exception e){
            System.out.println("Failed to connect");
        }
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayScreen.fxml"));
        
        try {
            Pane pane = loader.load();

            playController = loader.getController();
            playController.setMainWindow(this, playerMap);
        
            Stage stage = new Stage();
        
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(refreshButton.getScene().getWindow());
        
            Scene scene = new Scene(pane);
        
            stage.setScene(scene);
            stage.setTitle("Answer Buzzers!!");
            
            stage.setMaximized(true);
            stage.show();
            
            playController.setupList(teamList);
            playController.setupGrid();
        }
        
        catch (Exception e) {
            System.out.println("Failed to open Play Window");
        }
    }
    
    /**
     * @brief opens the Team screen to customise team colours
     * @param event 
     */
    public void onTeam(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TeamScreen.fxml"));
        
        try {
            Pane pane = loader.load();

            TeamScreenController controller = loader.getController();
            controller.setMainWindow(this);
        
            Stage stage = new Stage();
        
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(refreshButton.getScene().getWindow());
        
            Scene scene = new Scene(pane);
        
            stage.setScene(scene);
            stage.setTitle("Teams!!!");
            
            stage.show();
        }
        catch (Exception e) {
            System.out.println("Failed to open Team Window");
        }
    }
    
    /**
     * @brief refreshes the choice boxes for the teams
     * @param list 
     */
    public void refreshTeams(LinkedList<Team> list) {
        
        this.teamList = list;
        
        c1.getItems().clear();
        for(Team temp : list) {
            c1.getItems().add(temp.teamName);
        }
        c2.getItems().clear();
        for(Team temp : list) {
            c2.getItems().add(temp.teamName);
        }
        c3.getItems().clear();
        for(Team temp : list) {
            c3.getItems().add(temp.teamName);
        }
        c4.getItems().clear();
        for(Team temp : list) {
            c4.getItems().add(temp.teamName);
        }
        c5.getItems().clear();
        for(Team temp : list) {
            c5.getItems().add(temp.teamName);
        }
        c6.getItems().clear();
        for(Team temp : list) {
            c6.getItems().add(temp.teamName);
        }
        c7.getItems().clear();
        for(Team temp : list) {
            c7.getItems().add(temp.teamName);
        }
        c8.getItems().clear();
        for(Team temp : list) {
            c8.getItems().add(temp.teamName);
        }
        c9.getItems().clear();
        for(Team temp : list) {
            c9.getItems().add(temp.teamName);
        }
        c10.getItems().clear();
        for(Team temp : list) {
            c10.getItems().add(temp.teamName);
        }
        c11.getItems().clear();
        for(Team temp : list) {
            c11.getItems().add(temp.teamName);
        }
        c12.getItems().clear();
        for(Team temp : list) {
            c12.getItems().add(temp.teamName);
        }
    }
      
    /**
     * @handle for the custom play screen to receive the playerMap
     * @return 
     */
    public HashMap getPlayerMap() {
        return playerMap;
    }
}
