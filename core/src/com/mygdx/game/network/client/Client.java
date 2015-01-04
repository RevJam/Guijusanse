package com.mygdx.game.network.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.mygdx.game.network.Message;
import com.mygdx.game.network.MessagesHandler;
import com.mygdx.game.network.SocketThread;

/**
 * Created by guillaume on 26/11/14.
 */
public class Client extends MessagesHandler {

    private SocketThread mSocketThread;
    // Socket will time our in 4 seconds
    final private int timeout = 4000;

    public Client() {
        super();
    }

    public boolean connectToServer(String ipAddress) {

        SocketHints socketHints = new SocketHints();
        socketHints.connectTimeout = timeout;

        //create the socket and connect to the server entered in the text box ( x.x.x.x format ) on port 9021
        Socket socket;
        try {
            socket = Gdx.net.newClientSocket(Net.Protocol.TCP, ipAddress, 9021, socketHints);

            mSocketThread = new SocketThread(this, socket);
            mSocketThread.start();
            
            return true;
        }
        catch (Exception e) {

            Gdx.app.log("Network", e.toString());
            
            return false;
        }
    }
    
    public int getTimeout() {
        return timeout;
    }
    
    public boolean isConnected() {
        return mSocketThread.isConnected();
    }

    public void sendMessage(Message message) {
        mSocketThread.sendMessage(message);
    }

}
