package com.example.chatappfirebase.Model;

public class Users {

    String username, imageURL, id;

    public Users(String username, String imageURL, String id) {
        this.username = username;
        this.imageURL = imageURL;
        this.id = id;
    }

    public Users() {
    }

    public String getUsername() {
        return username;
    }

    public String getImageURL() {
        return this.imageURL;
    }

    public String getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setId(String id) {
        this.id = id;
    }
}
