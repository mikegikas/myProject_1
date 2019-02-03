/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mike;

import java.sql.*;

public class Database {

    private static final String DB_URL2 = "localhost:3306";
    private static final String FULL_DB_URL = "jdbc:mysql://" + DB_URL2 + "/myproject";//?zeroDateTimeBehavior=convertToNull";
    private static final String DB_USER = "root";
    private static final String DB_PASSWD = "12345";


    public void createUser(String username, String password, String fname, String lname, int role) {
        String query = "INSERT INTO `myproject`.`users` (`username`,`password`, `fname`, `lname`, `role`)"
                + "VALUES ('" + username + "', '" + password + "', '" + fname + "', '" + lname + "', '" + role + "')";
        connection(query);
    }

    public void createMessage(String sender, String receiver, String date, String data) {
        String query = "INSERT INTO `myproject`.`messages` (`sender`,`receiver`, `date`, `data`)"
                + "VALUES ('" + sender + "', '" + receiver + "', NOW(), '" + data + "')";
        connection(query);
    }

    public void createMessageInbox(int messages_id, String sender_id, String data) {
        String query = "INSERT INTO `myproject`.`messages_inbox` (`messages_id`,`sender_id`, `data`)"
                + "VALUES ('" + messages_id + "', '" + sender_id + "', '" + data + "')";
        connection(query);
    }

    public void createMessageSent(int messages_id, String receiver_id, String data) {
        String query = "INSERT INTO `myproject`.`messages_sent` (`messages_id`,`receiver_id`, `data`)"
                + "VALUES ('" + messages_id + "', '" + receiver_id + "', '" + data + "')";
        connection(query);
    }

    private void connection(String query) {
        try {
            Connection connection = DriverManager.getConnection(FULL_DB_URL, DB_USER, DB_PASSWD);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            statement.close();
            connection.close();
        } catch (SQLException e) {
           //System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void getMessages() {
        Menu.messageArrayList.clear();
        String query = "SELECT * FROM messages";

        try {
            Connection connection = DriverManager.getConnection(FULL_DB_URL, DB_USER, DB_PASSWD);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                Messages messages = new Messages();

                messages.setId(rs.getInt("id"));
                messages.setSender(rs.getString("sender"));
                messages.setReceiver(rs.getString("receiver"));
                messages.setDate(rs.getString("date"));
                messages.setData(rs.getString("data"));

                Menu.messageArrayList.add(messages);
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void getInboxMessages(String username) {
        String query = "SELECT * FROM messages WHERE receiver = '" + username + "'";

        try {
            Connection connection = DriverManager.getConnection(FULL_DB_URL, DB_USER, DB_PASSWD);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                Messages_Inbox messages_inbox = new Messages_Inbox();

                messages_inbox.setMessages_id(rs.getInt("id"));
                messages_inbox.setSender_id(rs.getString("sender"));
                messages_inbox.setData(rs.getString("data"));

                messages_inbox.createMessageInbox(messages_inbox.getMessages_id(), messages_inbox.getSender_id(), messages_inbox.getData());
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void getAllInboxMessages(){
        Menu.messagesInboxArrayList.clear();
        String query = "SELECT * FROM messages_inbox";

        try {
            Connection connection = DriverManager.getConnection(FULL_DB_URL, DB_USER, DB_PASSWD);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                Messages_Inbox messages_inbox = new Messages_Inbox();

                messages_inbox.setId(rs.getInt("id"));
                messages_inbox.setMessages_id(rs.getInt("messages_id"));
                messages_inbox.setSender_id(rs.getString("sender_id"));
                messages_inbox.setData(rs.getString("data"));

                Menu.messagesInboxArrayList.add(messages_inbox);
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void getAllSentMessages(){
        Menu.messagesSentArrayList.clear();
        String query = "SELECT * FROM messages_sent";

        try {
            Connection connection = DriverManager.getConnection(FULL_DB_URL, DB_USER, DB_PASSWD);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                Messages_Sent messages_sent = new Messages_Sent();

                messages_sent.setId(rs.getInt("id"));
                messages_sent.setMessages_id(rs.getInt("messages_id"));
                messages_sent.setReceiver_id(rs.getString("receiver_id"));
                messages_sent.setData(rs.getString("data"));

                Menu.messagesSentArrayList.add(messages_sent);
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void getSentMessages(String username) {
        String query = "SELECT * FROM messages WHERE sender = '" + username + "'";

        try {
            Connection connection = DriverManager.getConnection(FULL_DB_URL, DB_USER, DB_PASSWD);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                Messages_Sent messages_sent = new Messages_Sent();

                messages_sent.setMessages_id(rs.getInt("id"));
                messages_sent.setReceiver_id(rs.getString("receiver"));
                messages_sent.setData(rs.getString("data"));

                messages_sent.createMessageSent(messages_sent.getMessages_id(), messages_sent.getReceiver_id(), messages_sent.getData());
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void updateMessage(int id, String sender, String receiver, String data) {
        String query = "UPDATE messages SET sender='" + sender + "', receiver='" + receiver
                + "', date=NOW(), data='" + data + "' WHERE id ='" + id + "'";
        connection(query);
        getMessages();
    }

    public void deleteMessage(int id) {
        String query = "DELETE FROM `myproject`.`messages` WHERE id ='" + id + "'";
        connection(query);
        getMessages();
    }

    public void deleteMessage(String username) {
        String query = "DELETE FROM `myproject`.`messages` WHERE sender ='" + username + "'";
        connection(query);
        getMessages();
    }

    public void updateUser(String username, String password, String lname, String fname, int role) {
        String query = "UPDATE users SET password='" + password + "', fname='" + fname + "', lname='" + lname
                + "', role='" + role + "' WHERE username ='" + username + "'";
        connection(query);
        getAllUser();
    }

    public void deleteUser(String username) {
        deleteMessage(username);
        String query1 = "DELETE FROM `myproject`.`users` WHERE username ='" + username + "'";
        connection(query1);
        getAllUser();
    }

    public void getAllUser() {
        Menu.userArrayList.clear();
        String query = "SELECT * FROM users";
        try {
            Connection connection = DriverManager.getConnection(FULL_DB_URL, DB_USER, DB_PASSWD);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                User user = new User();

                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFname(rs.getString("fname"));
                user.setLname(rs.getString("lname"));
                user.setRole(rs.getInt("role"));

                Menu.userArrayList.add(user);
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

//    public void reset() {
//        String query = "ALTER TABLE `myproject`.`users` AUTO_INCREMENT = 0";
//        connection(query);
//    }

    public void clear() {
        String query = "TRUNCATE TABLE messages_sent;";
        connection(query);
        String query1 = "TRUNCATE TABLE messages_inbox;";
        connection(query1);
    }
}