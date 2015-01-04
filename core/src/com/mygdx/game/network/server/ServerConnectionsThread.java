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
    private ServerSocket mServerSocket;

    public ServerConnectionsThread(MessagesHandler messagesHandler) {
        this.mMessagesHandler = messagesHandler;
        this.mClientSocketThreads = new ArrayList<SocketThread>();
    }

    @Override
    public void run() {
        ServerSocketHints serverSocketHint = new ServerSocketHints();
        serverSocketHint.acceptTimeout = 200;

        mServerSocket = Gdx.net.newServerSocket(Net.Protocol.TCP, 9021, serverSocketHint);

        this.mAcceptingConnections = true;

        // Loop forever
        while (this.mAcceptingConnections) {
            // Crée un socket
            try {
                Socket socket = mServerSocket.accept(null);

                Gdx.app.log("Network", "connexion de " + socket.getRemoteAddress());

                SocketThread thread = new SocketThread(mMessagesHandler, socket);
                thread.start();
                mClientSocketThreads.add(thread);
            } catch (Exception e) {
                // Rien, c'est que le socket n'a pas pu se connecter, on continue
            }
        }
    }

    public void stopAcceptingConnections() {
        try {
            mServerSocket.dispose();
            this.mAcceptingConnections = false;
        } catch (NullPointerException e) {
            // Rien
        }
    }

    public SocketThread getClientThread(String ip) {
        for (SocketThread client : mClientSocketThreads) {
            if (client.getRemoteAddress().equals(ip))
                return client;
        }
        // Si pas de client trouvé, on retourne null
        return null;
    }

    public ArrayList<SocketThread> getClientThreads() {
        return mClientSocketThreads;
    }
}
