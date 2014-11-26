package com.mygdx.game.network.server;

import com.mygdx.game.network.Message;
import com.mygdx.game.network.MessagesHandler;
import com.mygdx.game.network.SocketThread;

import java.util.ArrayList;

/**
 * Created by guillaume on 26/11/14.
 */
public class Server extends MessagesHandler {

    private ServerConnectionsThread mServerConnectionsThread;

    public Server() {
        super();
    }

    public void startAcceptingConnections() {
        mServerConnectionsThread = new ServerConnectionsThread(this);
        mServerConnectionsThread.start();
    }

    public void stopAcceptingConnections() throws InterruptedException {
        if (mServerConnectionsThread.isAlive()) {
            mServerConnectionsThread.stopAcceptingConnections();
            mServerConnectionsThread.wait();
        }
    }

    public void sendMessageTo(String ip, Message message) {
        // TODO
    }

    public void sendMessageToAll(Message message) {
        ArrayList<SocketThread> clientThreads = mServerConnectionsThread.getClientThreads();
        for (SocketThread client : clientThreads) {
            client.sendMessage(message);
        }
    }


}
