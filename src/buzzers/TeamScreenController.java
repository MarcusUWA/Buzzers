/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buzzers;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Markcuz
 */
public class TeamScreenController implements Initializable {

    GUIController gui;
    
    @FXML 
    Button finishButton;
    
    @FXML
    CheckBox t1;
    @FXML
    CheckBox t2;
    @FXML
    CheckBox t3;
    @FXML
    CheckBox t4;
    @FXML
    CheckBox t5;
    @FXML
    CheckBox t6;
    @FXML
    CheckBox t7;
    @FXML
    CheckBox t8;
    @FXML
    CheckBox t9;
    @FXML
    CheckBox t10;
    @FXML
    CheckBox t11;
    @FXML
    CheckBox t12;
    
    @FXML
    TextField tf1;
    @FXML
    TextField tf2;
    @FXML
    TextField tf3;
    @FXML
    TextField tf4;
    @FXML
    TextField tf5;
    @FXML
    TextField tf6;
    @FXML
    TextField tf7;
    @FXML
    TextField tf8;
    @FXML
    TextField tf9;
    @FXML
    TextField tf10;
    @FXML
    TextField tf11;
    @FXML
    TextField tf12;
    
    @FXML
    ColorPicker cp1;
    @FXML
    ColorPicker cp2;
    @FXML
    ColorPicker cp3;
    @FXML
    ColorPicker cp4;
    @FXML
    ColorPicker cp5;
    @FXML
    ColorPicker cp6;
    @FXML
    ColorPicker cp7;
    @FXML
    ColorPicker cp8;
    @FXML
    ColorPicker cp9;
    @FXML
    ColorPicker cp10;
    @FXML
    ColorPicker cp11;
    @FXML
    ColorPicker cp12;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cp1.setValue(Color.BLUE);
        cp2.setValue(Color.RED);
        cp3.setValue(Color.GREEN);
        cp4.setValue(Color.ORANGE);
        cp5.setValue(Color.YELLOW);
        cp6.setValue(Color.PURPLE);
        cp7.setValue(Color.WHITE);
        cp8.setValue(Color.BLACK);
        cp9.setValue(Color.GREY);
        cp10.setValue(Color.PINK);
        cp11.setValue(Color.BROWN);
        cp12.setValue(Color.YELLOWGREEN);
    }    

    public void setMainWindow(GUIController aThis) {
        this.gui = aThis;
        
    }
    
    public void onFinish(ActionEvent event) {
        
        LinkedList<Team> list = new LinkedList();
        
        if(t1.isSelected()) {
            Team temp = new Team(tf1.getText(), cp1.getValue());
            list.add(temp);
        }
        
        if(t2.isSelected()) {
            Team temp = new Team(tf2.getText(), cp2.getValue());
            list.add(temp);
        }
        if(t3.isSelected()) {
            Team temp = new Team(tf3.getText(), cp3.getValue());
            list.add(temp);
        }
        
        if(t4.isSelected()) {
            Team temp = new Team(tf4.getText(), cp4.getValue());
            list.add(temp);
        }
        
        if(t5.isSelected()) {
            Team temp = new Team(tf5.getText(), cp5.getValue());
            list.add(temp);
        }
        
        if(t6.isSelected()) {
            Team temp = new Team(tf6.getText(), cp6.getValue());
            list.add(temp);
        }
        
        if(t7.isSelected()) {
            Team temp = new Team(tf7.getText(), cp7.getValue());
            list.add(temp);
        }
        
        if(t8.isSelected()) {
            Team temp = new Team(tf8.getText(), cp8.getValue());
            list.add(temp);
        }
        
        if(t9.isSelected()) {
            Team temp = new Team(tf9.getText(), cp9.getValue());
            list.add(temp);
        }
        
        if(t10.isSelected()) {
            Team temp = new Team(tf10.getText(), cp10.getValue());
            list.add(temp);
        }
        
        if(t11.isSelected()) {
            Team temp = new Team(tf11.getText(), cp11.getValue());
            list.add(temp);
        }
        
        if(t12.isSelected()) {
            Team temp = new Team(tf12.getText(), cp12.getValue());
            list.add(temp);
        }
        
        Stage stage = (Stage) finishButton.getScene().getWindow();
        stage.close();
        
        //gui.refreshTeams(list);
             
    }
    
    
    
}
