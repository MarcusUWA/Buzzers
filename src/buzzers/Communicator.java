/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buzzers;

import gnu.io.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 *
 * @author Markcuz
 */
public class Communicator implements SerialPortEventListener{
    
    private GUIController gui;
    //for containing the ports that will be found
    private Enumeration ports = null;
    //map the port names to CommPortIdentifiers
    private HashMap portMap = new HashMap();

    //this is the object that contains the opened port
    private CommPortIdentifier selectedPortIdentifier = null;
    private SerialPort serialPort = null;

    //input and output streams for sending and receiving data
    private InputStream input = null;
    private OutputStream output = null;

    //the timeout value for connecting with the port
    final static int TIMEOUT = 2000;
    
    final static int NEW_LINE_ASCII = 10;
    
    byte[] message = new byte[100];   
            
    int message_length = 0;

    
    public Communicator(GUIController gui) {
        this.gui = gui;
    }

    public void searchForPorts() {
        ports = CommPortIdentifier.getPortIdentifiers();

        while (ports.hasMoreElements()) {
            CommPortIdentifier curPort = (CommPortIdentifier)ports.nextElement();

            //get only serial ports
            if (curPort.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                portMap.put(curPort.getName(), curPort);
                System.out.println(curPort.getName());
                gui.ports.getItems().add(curPort.getName());
            }
        }
    }
    
    public void connect(String selectedPort) throws Exception {
        selectedPortIdentifier = (CommPortIdentifier)portMap.get(selectedPort);

        CommPort commPort;
        
        try {
            //the method below returns an object of type CommPort
            commPort = selectedPortIdentifier.open(this.getClass().getName(), 2000);
            //the CommPort object can be casted to a SerialPort object
            serialPort = (SerialPort)commPort;
            
            //serialPort.setSerialPortParams(9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
            
            input = serialPort.getInputStream();
            output = serialPort.getOutputStream();

            System.out.println(selectedPort + " opened successfully.");
        }
        catch (PortInUseException e) {
            System.out.println(selectedPort + " is in use. (" + e.toString() + ")");
        }
        catch (IOException e) {
            System.out.println("Failed to open port");
        }
    }
    
    public void initListener() {
        try {
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        }
        catch (TooManyListenersException e) {
            System.out.println("Too many listeners. (" + e.toString() + ")");
        }
    }
    
    public void disconnect()
    {
        //close the serial port
        try {
            serialPort.removeEventListener();
            serialPort.close();
            input.close();
            output.close();

            System.out.println("Disconnected.");
        }
        catch (Exception e) {
            System.out.println("Failed to close " + serialPort.getName() + "(" + e.toString() + ")");
        }
    }
    
    public void serialEvent(SerialPortEvent evt) {
        if (evt.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            message_length = input.read(message);
                        } catch (IOException ex) {
                            Logger.getLogger(Communicator.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        String finalMessage = new String(message, 0, message_length);
                        gui.recMessage(finalMessage);
                     }
                });
                

            }
            catch (Exception e) {
                System.out.println("Failed to read data. (" + e.toString() + ")");
            }
        }
    }
}
