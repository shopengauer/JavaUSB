/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ti.msp430.usb.hiddemo;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

/**
 *
 * @author Vasiliy
 */
@ClientEndpoint
public class JavaFxWebsocket {
    
  private Session session;
  private static final Logger LOGGER = Logger.getLogger(JavaFxWebsocket.class.getName());  
      
      public void connectToWebSocket(String stringUri) {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        try {
            // URI uri = URI.create("ws://localhost:8080/BinaryWebSocketServer/images");
            URI uri = URI.create(stringUri);
            container.connectToServer(this, uri);
        } catch (DeploymentException | IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            System.exit(-1);
        }
    }
    
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
    }

    @OnMessage
    public void onMessage(String message) {
        
        System.out.println("WebSocket message Received!");
    }

    @OnClose
    public void onClose() {
        //connectToWebSocket();
    }
      
      
      
      
      
      
}
