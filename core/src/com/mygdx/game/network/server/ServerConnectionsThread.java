package com.mygdx.game.network.server;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.mygdx.game.network.MessagesHandler;
import com.mygdx.game.network.SocketThread;

import java.util.ArrayList;

/**
 * Created by guillaume on 26/11/14.
 */
public class ServerConnectionsThread extends Thread {

    private MessagesHandler mMessagesHandler;
    private boolean mAcceptingConnections;
    private ArrayList<SocketThread> mClientSocketThreads;

    public ServerConnectionsThread(MessagesHandler messagesHandler) {
        this.mMessagesHandler = messagesHandler;
        this.mClientSocketThreads = new ArrayList<SocketThread>();
    }

    @Override
    public void run() {
        ServerSocketHints serverSocketHint = new ServerSocketHints();
        serverSocketHint.acceptTimeout = 200;

        ServerSocket serverSocket = Gdx.net.newServerSocket(Net.Protocol.TCP, 9021, serverSocketHint);

        this.mAcceptingConnections = true;

        // Loop forever
        while (this.mAcceptingConnections) {
            // Create a socket
            Socket socket = serverSocket.accept(null);

            SocketThread thread = new SocketThread(mMessagesHandler, socket);
            thread.start();
            mClientSocketThreads.add(thread);
        }
    }

    public void stopAcceptingConnections() {
        this.mAcceptingConnections = false;
    }

    public ArrayList<SocketThread> getClientThreads() {
        return mClientSocketThreads;
    }
}
