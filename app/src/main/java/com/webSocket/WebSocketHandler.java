package com.webSocket;

import com.model.DataManager;

import org.java_websocket.server.WebSocketServer;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import java.net.InetSocketAddress;

import java.util.Timer;
import java.util.TimerTask;

public class WebSocketHandler extends WebSocketServer {
    public WebSocketHandler(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        conn.send("Welcome to the server!"); // Send a welcome message
        System.out.println("**New connection: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("**Closed connection: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("**Message from client: " + message);
        conn.send("Echo: " + message); // Echo the received message
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public void onStart() {
        System.out.println("**WebSocket server started successfully!");
        System.out.println("**" + this.getAddress());
        startSendingData();
    }

    private void startSendingData() {
        WebSocketHandler socket = this;

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (!socket.getConnections().isEmpty() &&
                        DataManager.getInstance().getDidBVPChange() &&
                        DataManager.getInstance().getDidGSRChange()) {
                    String message = DataManager.getInstance().getDataAsJson();
                    System.out.println("**" + message);
                    socket.broadcast(message);
                }
                else {
                    System.out.println("**" + "pending connection...");
                }
            }
        }, 0, 1000);
    }
}