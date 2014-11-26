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

            // Read data from the socket into a BufferedReader
            BufferedReader buffer = new BufferedReader(new InputStreamReader(mClientSocket.getInputStream()));

            String header = "";
            String content = "";
            try {
                header = buffer.readLine();
                content = buffer.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            mMessagesHandler.addMessage(new Message(mClientSocket.getRemoteAddress(), header, content));
        }
    }

    public void sendMessage(Message message) {
        String serializedMessage = message.getHeader() + "\n" + message.getContent() + "\n";

        try {
            // write our entered message to the stream
            mClientSocket.getOutputStream().write(serializedMessage.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
