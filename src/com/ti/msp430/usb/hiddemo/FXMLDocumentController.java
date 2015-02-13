/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ti.msp430.usb.hiddemo;

import com.ti.msp430.usb.hiddemo.management.DataReceivedActionListener;
import com.ti.msp430.usb.hiddemo.management.HidCommunicationManager;
import com.ti.msp430.usb.hiddemo.management.HidDataReceiveTask;
import com.ti.msp430.usb.hiddemo.management.HidDataReceiveThread;
 
import java.awt.Toolkit;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Vasiliy
 */
public class FXMLDocumentController implements Initializable,DataReceivedActionListener {
    
    
    protected HidCommunicationManager hMan;
    protected HidDataReceiveTask readTask;
   //  private HidCommunicationManager hMan;
     private DataReceivedActionListener listener;
     private boolean stop = false;
    
    @FXML
    private Button setVidPidBtn;

    @FXML
    private Button connectBtn;

    @FXML
    private Label connectLbl;

    @FXML
    private TextField inputVid;

    @FXML
    private TextField inputPid;
    
    @FXML
    private TextArea textArea;

     @FXML
    private ComboBox<String> interfaceCombo;

    @FXML
    private ComboBox<String> serialCombo;
    
    
    @FXML
    void handleConnectButtonAction(ActionEvent event) {
           if (connectBtn.getText().equals("Connect")) {
		 //connectBtn.setText("Disconnect");	//
		connect();
           
           } else {
		//connectBtn.setText("Connect");	//disconnect();
		}
	 
    
    }

    @FXML
    void handleSetVidPidButtonAction(ActionEvent event) {
                 int[] interfaces= new int[10];
		String[] serials=new String[10];
                interfaceCombo.getItems().clear();
                serialCombo.getItems().clear();
                
               if (inputVid.getText().trim().equals("0x") || inputPid.getText().trim().equals("0x"))
			return; 
                
               
               
                final int vid = getFormattedVid();
		final int pid = getFormattedPid();
//                final int vid = 0x2047;
//		final int pid = 0x301;
                
                
                 System.out.println(vid);
                      System.out.println(pid);
                
                try {
			/* Calling our native functions and processing the results */
			interfaces = hMan.getInterfacesForVidPid(vid, pid);
		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null,  "getInterfacesForVidPid error", "Alert",JOptionPane.ERROR_MESSAGE);
			connectLbl.setText("getInterfacesForVidPid error");
		}
                
                
                try {
			/* Calling our native functions and processing the results */
			serials = hMan.getSerialsForVidPid(vid, pid);
		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null,  "getSerialsForVidPid error", "Alert",JOptionPane.ERROR_MESSAGE);
			connectLbl.setText("getSerialsForVidPid error");
                        e.printStackTrace();
		}
		
		
//		final DefaultComboBoxModel mod = (DefaultComboBoxModel) serialNumberBox.getModel();
		      System.out.println(serials.length);
                      System.out.println(interfaces.length);
               
                      if ((serials.length==0) ||  (interfaces.length == 0) ){
//			JOptionPane.showMessageDialog(null,  "No Serial Interfaces Found for VID/PID combination (vid="+Integer.toHexString(vid)+ 
//					" pid="+Integer.toHexString(pid)+")\nPlease connect a board with USB on this workstation.", "Oops",JOptionPane.WARNING_MESSAGE);
//		
                           connectLbl.setText("No Serial Interfaces Found for VID/PID combination" + Integer.toHexString(vid) +" " + Integer.toHexString(pid));
                }
		
                
                
                serialCombo.getItems().addAll(Arrays.asList(serials));//                    if (!serials[i].equals("") && mod.getIndexOf(serials[i]) < 0)
//				serialNumberBox.insertItemAt(serials[i], 0);

		//sets the default HID value for display in Interface box.  Especially necessary for one HID interface
		if ((interfaces.length == 1) && (interfaces[0] == -1)) {
			
                    interfaceCombo.getItems().add("HID 0");     
		} else {
			for (int i = 0; i < interfaces.length; i++) {
//				interfaceBox.insertItemAt("HID " + interfaces[i], interfaceBox.getItemCount());
			  interfaceCombo.getItems().add("HID " + interfaces[i]);
                        }
		     
                
                }
		
                
                
		if (!serialCombo.getItems().isEmpty())
			serialCombo.setValue(serialCombo.getItems().get(0));

		if (!interfaceCombo.getItems().isEmpty())
			interfaceCombo.setValue(interfaceCombo.getItems().get(0));
                
                
                
                
                
    }

    private int getFormattedPid() {
		return Integer.parseInt(inputPid.getText().replace("0x", ""), 16);
	}

	private int getFormattedVid() {
		return Integer.parseInt(inputVid.getText().replace("0x", ""), 16);
	}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.hMan = new HidCommunicationManager();
        inputVid.setText("0x" + Integer.toHexString(HidCommunicationManager.USB_VENDOR));
	inputPid.setText("0x0" + Integer.toHexString(HidCommunicationManager.USB_PRODUCT));
    }    

     
    @Override
    public void fireStringReceivedEvent(String s) {
       if (!s.equals("")) {
		         System.out.println("To console");	
              textArea.appendText(s);
		 // connectLbl.setText(s);
		}
        
        
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fireUnableToReadEvent() {
                textArea.appendText("\nDisconnected from device");
//		consoleArea.setRows(consoleArea.getLineCount());
//
//		vidField.setEnabled(true);
//		pidField.setEnabled(true);
//		setVidPidButton.setEnabled(true);
//		serialNumberBox.setEnabled(true);
//		interfaceBox.setEnabled(true);
//		sendButton.setEnabled(false);
//
//		statusLabel.setText("Disconnected");
//		lightLabel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/red.png"))));

        
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void connect() {
		String serial;
		int inf;

		if (serialCombo.getItems().isEmpty() && interfaceCombo.getItems().isEmpty()) {
		    textArea.setText("ERROR: Please ensure that VID/PID information is valid.");
                    
                    connectBtn.setText("Connect");
			return;
		}

		if (serialCombo.getItems().isEmpty()) {
			serial = null;
		} else
			serial = serialCombo.getSelectionModel().getSelectedItem();

		if (interfaceCombo.getItems().size() <= 1) {
			inf = -1;
		} else {
			final String stringInf = interfaceCombo.getSelectionModel().getSelectedItem();
			inf = Integer.parseInt("" + stringInf.charAt(stringInf.length() - 1));
		}

		try {
			hMan.connectDevice(getFormattedVid(), getFormattedPid(), serial, inf);
		        connectBtn.setText("Disconnect");
                } catch (final HidCommunicationManager.HidCommunicationException e) {
			textArea.appendText("\nCould not connect to device");
			connectBtn.setText("Connect");
			//consoleArea.setRows(consoleArea.getLineCount());
			e.printStackTrace();
			return;
		}
                
		readTask = new HidDataReceiveTask(hMan);
		 
                //readTask.setListener(this);
	       Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        //Background work                       

                        for (;;) {
                            final CountDownLatch latch = new CountDownLatch(1);
                            Platform.runLater(() -> {
                                String data = "";
                                try {
                                    //FX Stuff done here
                                    try {
                                data = hMan.receiveData();
                                if(data.equals("")) {
//                                   Thread.sleep(500);     
                                }
                                
                        } catch (HidCommunicationManager.HidCommunicationException e) {
                                listener.fireStringReceivedEvent("Error receiving buffer from device!");
                                listener.fireUnableToReadEvent();
                                return;
                        } 
//                                    catch (InterruptedException e) {
//                                listener.fireStringReceivedEvent("Read polling thread existed");
//                                listener.fireUnableToReadEvent();
//                                return;
//                        } 
                       // listener.fireStringReceivedEvent(data);      
                                    if (!data.equals("")) {
//                                        System.out.println("To console");
                                        textArea.appendText(data);
                                        // connectLbl.setText(s);
                                    }
//                                    textArea.appendText("Run ");

                                } finally {
                                    latch.countDown();
                                }
                            });
                            latch.await();
                            //Keep with the background work
                        }

                    }

                };
            }
        };
        service.start();
              
         
              
		
                
                
                
                
                textArea.appendText("\nConnected to device VID: " + getFormattedVid() + " PID: " + getFormattedPid());
		//textArea.;

//		vidField.setEnabled(false);
//		pidField.setEnabled(false);
//		setVidPidButton.setEnabled(false);
//		serialNumberBox.setEnabled(false);
//		sendButton.setEnabled(true);
//		interfaceBox.setEnabled(false);
//		connectButton.setSelected(true);
//		statusLabel.setText("Connected");
//		lightLabel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/green.png"))));
	}

	
    
    
    
    
}
