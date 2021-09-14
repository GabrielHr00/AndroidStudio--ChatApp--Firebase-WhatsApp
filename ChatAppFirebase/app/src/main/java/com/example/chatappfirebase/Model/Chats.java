package com.example.chatappfirebase.Model;

public class Chats {

    String sender, reciever, message;

    public Chats() {
    }

    public Chats(String sender, String reciever, String message) {
        this.sender = sender;
        this.reciever = reciever;
        this.message = message;
    }


    public String getSender() {
        return sender;
    }

    public String getReciever() {
        return reciever;
    }

    public String getMessage() {
        return message;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

