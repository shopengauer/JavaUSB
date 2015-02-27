/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ti.msp430.usb.hiddemo;





//import com.ti.msp430.usb.hiddemo.management.HidCommunicationManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Vasiliy
 */

public class JavaUSBFXMain extends Application {
    
    
// protected final HidCommunicationManager hMan;

//    public JavaUSBFXMain() {
//    
//         hMan = new HidCommunicationManager();
//    
//    }
     
    
     
 
    @Override
    public void start(Stage primaryStage) throws Exception{
        
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml")); 
        
       // StackPane root = new StackPane();
       // root.getChildren().add(btn);

        Scene scene = new Scene(root, 1000, 700);
        
        primaryStage.setTitle("HID Datapipe USB");
        primaryStage.setScene(scene);
        primaryStage.show();
        
         
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
        
    }

    @Override
    public void init() throws Exception {
        super.init(); //To change body of generated methods, choose Tools | Templates.
//        this.hMan = new HidCommunicationManager();
//        vidField.setText("0x" + Integer.toHexString(HidCommunicationManager.USB_VENDOR));
//		pidField.setText("0x0" + Integer.toHexString(HidCommunicationManager.USB_PRODUCT));
        ApplicationContext ap = new ClassPathXmlApplicationContext("spring.xml");
    }
    
    
    
}
