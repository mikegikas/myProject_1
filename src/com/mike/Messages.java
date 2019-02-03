package com.mike;

public class Messages {
    private int id;
    private String sender;
    private String receiver;
    private String date;
    private String data;

    public Messages(){

    }

    public Messages(String sender, String receiver, String date, String data){
        this.sender = sender;
        this.receiver = receiver;
        this.date = date;
        this.data = data;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getSender(){
        return sender;
    }

    public void setSender(String sender){
        this.sender = sender;
    }

    public String getReceiver(){
        return receiver;
    }

    public void setReceiver(String receiver){
        this.receiver = receiver;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getData(){
        return data;
    }

    public void setData(String data){
        this.data = data;
    }

    public void createMessage( String sender, String receiver, String data) {
        Menu.db.createMessage(sender, receiver, date, data);
    }

    public void getAllMessages(){
        Menu.db.getMessages();
    }

    public void updateMessage(int id, String sender, String receiver, String data){
        Menu.db.updateMessage(id, sender, receiver, data);
    }

    public void deleteMessage(int id){
        Menu.db.deleteMessage(id);
    }
}
