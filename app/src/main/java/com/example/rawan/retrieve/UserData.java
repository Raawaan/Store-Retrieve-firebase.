package com.example.rawan.retrieve;

/**
 * Created by rawan on 6/4/18.
 */

public class UserData {
    private String name;
    private String email;
    private  String image;
    public void setImage(String image) {this.image = image;}
    public String getImage() {return image;}
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
}
