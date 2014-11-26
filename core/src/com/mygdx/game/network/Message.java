package com.mygdx.game.network;

/**
 * Created by guillaume on 26/11/14.
 */
public class Message {

    private String mIp;
    private String mHeader;
    private String mContent;

    public Message(String ip, String header, String content) {
        this.mIp = ip;
        this.mHeader = header;
        this.mContent = content;
    }

    public String getIp() {
        return this.mIp;
    }

    public String getHeader() {
        return this.mHeader;
    }

    public String getContent() {
        return this.mContent;
    }
}
