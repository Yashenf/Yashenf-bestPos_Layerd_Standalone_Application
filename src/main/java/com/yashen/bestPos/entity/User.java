package com.yashen.bestPos.entity;

public class User implements SuperEntity{
    private String name;
    private String address;
    private String username;
    private String password;
    private String accType;

    public User() {
    }

    public User(String name, String address, String username, String password, String accType) {
        this.name = name;
        this.address = address;
        this.username = username;
        this.password = password;
        this.accType = accType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }
}
