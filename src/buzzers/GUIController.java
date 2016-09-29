/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buzzers;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
    
    HashMap<Integer, Player> playerMap = new HashMap();
    
    PlayScreenController playController;
    QuickPlayController quickPlay;
    
    //boolean to check is using quickPlay
    int isQuick = 2;
    
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
    ColorPicker c1;
    @FXML
    ColorPicker c2;
    @FXML
    ColorPicker c3;
    @FXML
    ColorPicker c4;
    @FXML
    ColorPicker c5;
    @FXML
    ColorPicker c6;
    @FXML
    ColorPicker c7;
    @FXML
    ColorPicker c8;
    @FXML
    ColorPicker c9;
    @FXML
    ColorPicker c10;
    @FXML
    ColorPicker c11;
    @FXML
    ColorPicker c12;
    
    @FXML
    TextField t1;
    @FXML
    TextField t2;
    @FXML
    TextField t3;
    @FXML
    TextField t4;
    @FXML
    TextField t5;
    @FXML
    TextField t6;
    @FXML
    TextField t7;
    @FXML
    TextField t8;
    @FXML
    TextField t9;
    @FXML
    TextField t10;
    @FXML
    TextField t11;
    @FXML
    TextField t12;
     
    
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

        comms = new Communicator(this);
        try {
            comms.searchForPorts();
        } 
        catch (Exception ex) {
            System.out.println("Error!");
        }
        
        c1.setValue(Color.BLUE);
        c2.setValue(Color.RED);
        c3.setValue(Color.GREEN);
        c4.setValue(Color.ORANGE);
        c5.setValue(Color.YELLOW);
        c6.setValue(Color.PURPLE);
        c7.setValue(Color.OLIVE);
        c8.setValue(Color.LIME);
        c9.setValue(Color.CYAN);
        c10.setValue(Color.PINK);
        c11.setValue(Color.BROWN);
        c12.setValue(Color.SILVER);
        
        p1.setSelected(true);
        p2.setSelected(true);
        p3.setSelected(true);
        p4.setSelected(true);
        p5.setSelected(true);
        p6.setSelected(true);
        p7.setSelected(true);
        p8.setSelected(true);
        p9.setSelected(true);
        p10.setSelected(true);
        p11.setSelected(true);
        p12.setSelected(true);
        
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
                playController.update(split);
            }
            else if (isQuick == 1){
                quickPlay.update(split);
            }
        }
        else {
            if(isQuick ==0) {
                playController.reset();
            }
            else if (isQuick == 1) {
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
    
    public void onExit() {
        comms.disconnect();
    }
    
    //***************** Start Custom Players and Teams ***************//
    
    /**
     * @brief maps all the players into the corresponding teams via HashMap
     */
    public void mapPlayers() {
        
        if(p1.isSelected()) {
            Player player = new Player(t1.getText(), c1.getValue());
            playerMap.put(1, player);
        }
        
        if(p2.isSelected()) {
            Player player = new Player(t2.getText(), c2.getValue());
            playerMap.put(2,player);
        }
        
        if(p3.isSelected()) {
            Player player = new Player(t3.getText(), c3.getValue());
            playerMap.put(3,player);
        }
        
        if(p4.isSelected()) {
            Player player = new Player(t4.getText(), c4.getValue());
            playerMap.put(4,player);
        }
        
        if(p5.isSelected()) {
            Player player = new Player(t5.getText(), c5.getValue());
            playerMap.put(5,player);
        }
        
        if(p6.isSelected()) {
            Player player = new Player(t6.getText(), c6.getValue());
            playerMap.put(6,player);
        }
        
        if(p7.isSelected()) {
            Player player = new Player(t7.getText(), c7.getValue());
            playerMap.put(7,player);
        }
        
        if(p8.isSelected()) {
            Player player = new Player(t8.getText(), c8.getValue());
            playerMap.put(8,player);
        }
        if(p9.isSelected()) {
            Player player = new Player(t9.getText(), c9.getValue());
            playerMap.put(9,player);
        }
        
        if(p10.isSelected()) {
            Player player = new Player(t10.getText(), c10.getValue());
            playerMap.put(10,player);
        }
        
        if(p11.isSelected()) {
            Player player = new Player(t11.getText(), c11.getValue());
            playerMap.put(11,player);
        }
        
        if(p12.isSelected()) {
            Player player = new Player(t12.getText(), c12.getValue());
            playerMap.put(12,player);
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
        }
        
        catch (Exception e) {
            System.out.println("Failed to open Play Window");
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
