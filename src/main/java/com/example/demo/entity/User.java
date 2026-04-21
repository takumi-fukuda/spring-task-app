package com.example.demo.entity;

public class User {
    private int id;
    private String username;
    private String password;
    private int user_id;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setId(int id) {
        this.id = id;
    } 

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String passowrd) {
        this.password = passowrd;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
