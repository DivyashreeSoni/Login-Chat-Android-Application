package com.example.divyashreesoni.chatbot.Model;

public class ChatMessage {

    String TextMessage;
    boolean isMe;

    public ChatMessage(String TextMessage, boolean isMe){
        this.TextMessage = TextMessage;
        this.isMe = isMe;
    }

    public String getTextMessage(){
        return TextMessage;
    }

    public void setTextMessage(String TextMessage){
        this.TextMessage = TextMessage;
    }

    public boolean isMe(){
        return isMe;
    }

    public void setMe(boolean me){
        isMe = me;
    }
}
