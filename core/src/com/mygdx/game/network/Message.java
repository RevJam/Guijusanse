package com.mygdx.game.network;

/**
 * Created by guillaume on 26/11/14.
 */
public class Message {

    private String mHeader;
    private String mContent;
    private String mIp;

    public Message(String header, String content) {
        this.mHeader = header;
        this.mContent = content;
    }

    public String getHeader() {
        return this.mHeader;
    }

    public String getContent() {
        return this.mContent;
    }

    public void setIp(String ip) {
        this.mIp = ip;
    }

    public String getIp() {
        return this.mIp;
    }
}
