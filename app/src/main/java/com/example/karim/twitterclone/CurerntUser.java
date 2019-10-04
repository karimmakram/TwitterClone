package com.example.karim.twitterclone;

public class CurerntUser {
    private String Name ;
    private String Username ;
    private int Id ;

    CurerntUser(int id,String name,String usernaem){
        Name =name ;
        Username = usernaem;
        Id = id ;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public int getId() {
        return Id;
    }
    public void setId(int id) {
        this.Id = id;
    }

}
