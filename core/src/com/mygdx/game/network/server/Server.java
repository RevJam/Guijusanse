package com.mygdx.game.network.server;

import com.mygdx.game.network.Message;
import com.mygdx.game.network.MessagesHandler;
import com.mygdx.game.network.SocketThread;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by guillaume on 26/11/14.
 */
public class Server extends MessagesHandler {

    private ServerConnectionsThread mServerConnectionsThread;

    public Server() {
        super();
    }

    /**
     * Commence à accepter des connexions
     */
    public void startAcceptingConnections() {
        mServerConnectionsThread = new ServerConnectionsThread(this);
        mServerConnectionsThread.start();
    }

    /**
     * Arrête d'accepter des connexions
     *
     * @throws InterruptedException
     */
    public void stopAcceptingConnections() {
        if (mServerConnectionsThread != null && mServerConnectionsThread.isAlive()) {
            mServerConnectionsThread.stopAcceptingConnections();
            mServerConnectionsThread.interrupt();
        }
    }

    /**
     * Retourne la liste des adresses IP de l'appareil
     *
     * @return
     */
    public List<String> getAddresses() {
        List<String> addresses = new ArrayList<String>();
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface ni : Collections.list(interfaces)) {
                for (InetAddress address : Collections.list(ni.getInetAddresses())) {
                    if (address instanceof Inet4Address) {
                        addresses.add(address.getHostAddress());
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return addresses;
    }
    
    public int numberOfConnectedClient() {
        int connected = 0;
        ArrayList<SocketThread> clientThreads = mServerConnectionsThread.getClientThreads();
        for (SocketThread client : clientThreads) {
            if (client.isConnected()) connected++;
        }
        return connected;
    }

    /**
     * Envoi un message à une adresse précise
     *
     * @param ip      l'adresse du client à qui envioyer le message
     * @param message le message à envoyer
     */
    public void sendMessageTo(String ip, Message message) {
        SocketThread client = mServerConnectionsThread.getClientThread(ip);
        if (client != null)
            client.sendMessage(message);
    }

    /**
     * Envoi un message à tous les clients
     *
     * @param message le message à envoyer
     */
    public void sendMessageToAll(Message message) {
        ArrayList<SocketThread> clientThreads = mServerConnectionsThread.getClientThreads();
        for (SocketThread client : clientThreads) {
            client.sendMessage(message);
        }
    }


}
