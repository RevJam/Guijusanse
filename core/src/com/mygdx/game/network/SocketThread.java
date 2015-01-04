package com.mygdx.game.network;

import com.badlogic.gdx.net.Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by guillaume on 26/11/14.
 */
public class SocketThread extends Thread {

    private MessagesHandler mMessagesHandler;
    private Socket mClientSocket;

    public SocketThread(MessagesHandler messagesHandler, Socket clientSocket) {
        this.mMessagesHandler = messagesHandler;
        this.mClientSocket = clientSocket;
    }

    @Override
    public void run() {
        // Loop forever
        while (true) {

            // Lecture des donnée depuis le socket vers un BufferedReader
            BufferedReader buffer = new BufferedReader(new InputStreamReader(mClientSocket.getInputStream()));

            String header = "";
            String content = "";
            try {
                header = buffer.readLine();
                content = buffer.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Message msg = new Message(header, content);
            msg.setIp(mClientSocket.getRemoteAddress());
            mMessagesHandler.addMessage(msg);
        }
    }
    
    public boolean isConnected() {
        return mClientSocket.isConnected();
    }
    
    public String getRemoteAddress() {
        return mClientSocket.getRemoteAddress();
    }

    public void sendMessage(Message message) {
        String serializedMessage = message.getHeader() + "\n" + message.getContent() + "\n";

        try {
            // On écrit notre message dans le stream
            mClientSocket.getOutputStream().write(serializedMessage.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
