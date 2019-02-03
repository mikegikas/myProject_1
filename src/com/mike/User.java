package com.mike;

public class User {
    private int id;
    private String username;
    private String password;
    private String fname;
    private String lname;
    private int role;

    public User(){

    }

    public User(String username, String password, String fname, String lname, int role){
        this.username = username;
        this. password = password;
        this.fname = fname;
        this.lname = lname;
        this.role = role;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getFname(){
        return fname;
    }

    public void setFname(String fname){
        this.fname = fname;
    }

    public String getLname(){
        return lname;
    }

    public void setLname(String lname){
        this.lname = lname;
    }

    public int getRole(){
        return role;
    }

    public void setRole(int role){
        this.role = role;
    }

    public void createUser(String username, String password, String fname, String lname, int role) {
        Menu.db.createUser(username, password, fname, lname, role);
    }

    public void getAllUsers(){
        Menu.db.getAllUser();
    }

    public void updateUser(String username, String password, String lname, String fname, int role){
        Menu.db.updateUser(username, password, lname, fname, role);
    }

    public void deleteUser(String username){
        if (findMessage(username)){
            Menu.db.deleteMessage(username);
        }
        Menu.db.deleteUser(username);
    }

    private boolean findMessage(String username){
        for (Messages message : Menu.messageArrayList){
            if (message.getSender().equals(username)){
                return true;
            }
        }
        return false;
    }
}
