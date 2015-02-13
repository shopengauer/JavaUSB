/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ti.msp430.usb.hiddemo.management;

import javafx.concurrent.Task;

/**
 *
 * @author Vasiliy
 */
public class HidDataReceiveTask extends Thread{

     private HidCommunicationManager hMan;
     private DataReceivedActionListener listener;
     private boolean stop = false;
    
    public HidDataReceiveTask(HidCommunicationManager hMan) {
                this.hMan = hMan;         
        }
        
        public DataReceivedActionListener getListener() {
                return listener;
        }

        public boolean isStop() {
                return stop;
        }

        public void setStop(boolean stop) {
                this.stop = stop;
        }

        public void setListener(DataReceivedActionListener listener) {
                this.listener = listener;
        }

    
    
    @Override
    public void run(){
      
        System.out.println("Task 1");
        while(true) {
                        
                        if(stop)
                                return;
                        
                        String data = "";
                        
                        try {
                                data = hMan.receiveData();
                                System.out.println("recieve");
                                if(data.equals("")) {
                                    Thread.sleep(500);     
                               
                             }}catch (HidCommunicationManager.HidCommunicationException e) {
                                
                                listener.fireStringReceivedEvent("Error receiving buffer from device!");
                                listener.fireUnableToReadEvent();
                                return;
                                     }
                            catch (InterruptedException e) {
                                listener.fireStringReceivedEvent("Read polling thread existed");
                                listener.fireUnableToReadEvent();
                                return;
                        } 
                        listener.fireStringReceivedEvent(data);                        
                }
        
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
