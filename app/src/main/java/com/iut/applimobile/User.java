package com.iut.applimobile;

public class User {
    private String username;
    private String email;
    private String password;
    private double coord_X;
    private double coord_Y;

    public User(String username, String email, String password,
                double coord_X, double coord_Y){
        this.username = username;
        this.email = email;
        this.password = password;
        this.coord_X = coord_X;
        this.coord_Y = coord_Y;
    }

    public User(){
        
    }

    public double getCoord_Y() {
        return coord_Y;
    }

    public double getCoord_X() {
        return coord_X;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }
}
