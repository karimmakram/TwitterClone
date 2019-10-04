package com.example.karim.twitterclone;

public class CurerntUser {
    private static String Name ;
    private static String Username ;
    private static int Id ;

    CurerntUser(){}
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
