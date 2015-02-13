/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ti.msp430.usb.hiddemo;

import com.ti.msp430.usb.hiddemo.management.HidCommunicationManager;

/**
 *
 * @author Vasiliy
 */
public class Initial {

     protected final HidCommunicationManager hMan;
    
    
    public Initial() {
    
     int[] interfaces= new int[10];
		String[] serials=new String[10];
                 
                
               
               
               
                final int vid = 769;
		final int pid = 8263;
                
                 System.out.println(vid);
                      System.out.println(pid);
                    hMan = new HidCommunicationManager();
               
                    System.out.println(hMan.getNumberOfInterfaces(769, 8263));
                   try {
			/* Calling our native functions and processing the results */
			interfaces = hMan.getInterfacesForVidPid(vid, pid);
		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null,  "getInterfacesForVidPid error", "Alert",JOptionPane.ERROR_MESSAGE);
			//connectLbl.setText("getInterfacesForVidPid error");
		}
                
                
                try {
			/* Calling our native functions and processing the results */
			serials = hMan.getSerialsForVidPid(vid, pid);
		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null,  "getSerialsForVidPid error", "Alert",JOptionPane.ERROR_MESSAGE);
			//connectLbl.setText("getSerialsForVidPid error");
                        e.printStackTrace();
		}
		
		
//		final DefaultComboBoxModel mod = (DefaultComboBoxModel) serialNumberBox.getModel();
		      System.out.println(serials.length);
                      System.out.println(interfaces.length);
               
                      if ((serials.length==0) ||  (interfaces.length == 0) ){
//			JOptionPane.showMessageDialog(null,  "No Serial Interfaces Found for VID/PID combination (vid="+Integer.toHexString(vid)+ 
//					" pid="+Integer.toHexString(pid)+")\nPlease connect a board with USB on this workstation.", "Oops",JOptionPane.WARNING_MESSAGE);
//		
                          // connectLbl.setText("No Serial Interfaces Found for VID/PID combination" + Integer.toHexString(vid) +" " + Integer.toHexString(pid));
                }
		
                
                
               // serialCombo.getItems().addAll(Arrays.asList(serials));//                    if (!serials[i].equals("") && mod.getIndexOf(serials[i]) < 0)
//				serialNumberBox.insertItemAt(serials[i], 0);

		//sets the default HID value for display in Interface box.  Especially necessary for one HID interface
		if ((interfaces.length == 1) && (interfaces[0] == -1)) {
			
                  //  interfaceCombo.getItems().add("HID 0");     
		} else {
			for (int i = 0; i < interfaces.length; i++) {
//				interfaceBox.insertItemAt("HID " + interfaces[i], interfaceBox.getItemCount());
			//  interfaceCombo.getItems().add("HID " + interfaces[i]);
                        }
		     
                
                }
        
    }
    
//    public static void main(String[] args) {
//       final Initial in = new Initial();
//    }
   
    
    
    
    
    
}
