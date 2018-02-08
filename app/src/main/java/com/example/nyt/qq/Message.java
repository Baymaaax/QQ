package com.example.nyt.qq;

/**
 * Created by NYT on 2018/2/8.
 */

public class Message {
    private String messageText;
    private int messageSender;
    public Message(String messageText,int messageSender){
        this.messageText=messageText;
        this.messageSender=messageSender;
    }
    public String getMessageText(){
        return messageText;
    }
    public int getMessageSender(){
        return messageSender;
    }
}
