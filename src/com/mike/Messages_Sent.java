package com.mike;

public class Messages_Sent {
    private int id;
    private int messages_id;
    private String receiver_id;
    private String data;

    public Messages_Sent() {

    }

    public Messages_Sent(int id, int messages_id, String receiver_id, String data) {
        this.id = id;
        this.messages_id = messages_id;
        this.receiver_id = receiver_id;
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

    public String getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(String receiver_id) {
        this.receiver_id = receiver_id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void createMessageSent(int messages_id, String receiver_id, String data) {
        Menu.db.createMessageSent(messages_id, receiver_id, data);
    }
}
