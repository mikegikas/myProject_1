package com.mike;

public class Messages_Inbox {
    private int id;
    private int messages_id;
    private String sender_id;
    private String data;

    public Messages_Inbox() {

    }

    public Messages_Inbox(int id, int messages_id, String sender_id, String data) {
        this.id = id;
        this.messages_id = messages_id;
        this.sender_id = sender_id;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMessages_id() {
        return messages_id;
    }

    public void setMessages_id(int messages_id) {
        this.messages_id = messages_id;
    }

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void createMessageInbox(int message_id, String sender_id, String data) {
        Menu.db.createMessageInbox(message_id, sender_id, data);
    }
}
