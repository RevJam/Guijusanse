package com.mygdx.game.network;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by guillaume on 26/11/14.
 */
public abstract class MessagesHandler {

    protected ConcurrentLinkedQueue<Message> mMessages = new ConcurrentLinkedQueue<Message>();

    public MessagesHandler() {
        
    }

    public void addMessage(Message message) {
        mMessages.add(message);
    }

    public Message getOlderMessage() {
        return this.mMessages.poll();
    }
}
