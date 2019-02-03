package com.mike;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Menu {

    private static User currentUser;
    static ArrayList<User> userArrayList = new ArrayList<>();
    static ArrayList<Messages> messageArrayList = new ArrayList<>();
    static ArrayList<Messages_Inbox> messagesInboxArrayList = new ArrayList<>();
    static ArrayList<Messages_Sent> messagesSentArrayList = new ArrayList<>();

    static Scanner in = new Scanner(System.in);
    static Database db = new Database();

    public Menu(){
        db.getAllUser();
        db.getMessages();
        db.clear();
        if (userArrayList.size() == 0){
            db.createUser("admin", "admin", "Mike", "G", 1);
        }
        init();
    }

    private void init(){
        currentUser = null;
        db.clear();
        int choice;
        do {
            System.out.println("-------------------------------");
            System.out.println("Press:");
            System.out.println("1 - Log in");
            System.out.println("2 - Exit");
            while(!in.hasNextInt()){
                System.out.println("That's not a number. Enter again:");
                in.next();
            }
            choice = in.nextInt();
        }while(!(choice == 1 || choice == 2) );

        switch (choice){
            case 1:
                logInMenu();
                break;
            case 2:
                writeMessagesToFile();
                writeMessagesInboxToFile();
                writeMessagesSentToFile();
                db.clear();
                System.out.println("Exiting....");
                break;
        }
    }

    private void showSuperAdminMenu(){
        int choice;
        do {
            showCurrentUser();
            System.out.println("-------------------------------");
            System.out.println("-------Super Admin Menu--------");
            System.out.println("Choose one of the following: ");
            System.out.println("1 - Create User");
            System.out.println("2 - Update User");
            System.out.println("3 - Delete User");
            System.out.println("4 - Create Message");
            System.out.println("5 - Update Message");
            System.out.println("6 - Delete Message");
            System.out.println("7 - Show Inbox Messages");
            System.out.println("8 - Show Sent Messages");
            System.out.println("9 - Show All Messages");
            System.out.println("0 - Log Out");
            while(!in.hasNextInt()){
                System.out.println("That's not a number. Enter again:");
                in.next();
            }
            choice = in.nextInt();
        }while(!(choice == 1 || choice == 2 || choice == 3 || choice == 4 || choice == 5 || choice == 6
                || choice == 7 || choice == 8 || choice == 9 || choice == 0) );

        switch (choice) {
            case 1:
                registerUserMenu();
                break;
            case 2:
                updateUserMenu();
                break;
            case 3:
                deleteUserMenu();
                break;
            case 4:
                createMessageMenu();
                break;
            case 5:
                updateMessageMenuById();
                break;
            case 6:
                deleteMessageMenuById();
                break;
            case 7:
                showInboxMessages();
                messageDelay();
                break;
            case 8:
                showSentMessages();
                messageDelay();
                break;
            case 9:
                showAllMessages();
                messageDelay();
                break;
            case 0:
                db.getSentMessages(currentUser.getUsername());
                db.getInboxMessages(currentUser.getUsername());
                db.getAllSentMessages();
                db.getAllInboxMessages();
                init();
                break;
        }
    }

    private void showAdminMenu(){
        int choice;
        do {
            System.out.println("-------------------------------");
            System.out.println("----------Admin Menu-----------");
            System.out.println("Choose one of the following: ");
            System.out.println("1 - Create User");
            System.out.println("2 - Update User");
            System.out.println("3 - Create Message");
            System.out.println("4 - Update Message");
            System.out.println("5 - Delete Message");
            System.out.println("6 - Show Inbox Messages");
            System.out.println("7 - Show Sent Messages");
            System.out.println("8 - Show All Messages");
            System.out.println("0 - Log Out");
            while(!in.hasNextInt()){
                System.out.println("That's not a number. Enter again:");
                in.next();
            }
            choice = in.nextInt();
        }while(!(choice == 1 || choice == 2 || choice == 3 || choice == 4 || choice == 5 || choice == 6
                || choice == 7 || choice == 8 ||  choice == 0) );

        switch (choice) {
            case 1:
                registerUserMenu();
                break;
            case 2:
                updateUserMenu();
                break;
            case 3:
                createMessageMenu();
                break;
            case 4:
                updateMessageMenuById();
                break;
            case 5:
                deleteMessageMenuById();
                break;
            case 6:
                showInboxMessages();
                messageDelay();
                break;
            case 7:
                showSentMessages();
                messageDelay();
                break;
            case 8:
                showAllMessages();
                messageDelay();
                break;
            case 0:
                db.getSentMessages(currentUser.getUsername());
                db.getInboxMessages(currentUser.getUsername());
                db.getAllSentMessages();
                db.getAllInboxMessages();
                init();
                break;
        }
    }

    private void showUserMenu(){
        int choice;
        do {
            showCurrentUser();
            System.out.println("-------------------------------");
            System.out.println("----------User Menu------------");
            System.out.println("Choose one of the following: ");
            System.out.println("1 - Create Message");
            System.out.println("2 - Show Inbox Messages");
            System.out.println("3 - Show Sent Messages");
            System.out.println("0 - Log Out");
            while(!in.hasNextInt()){
                System.out.println("That's not a number. Enter again:");
                in.next();
            }
            choice = in.nextInt();
        }while(!(choice == 1 || choice == 2 || choice == 3 || choice == 0) );

        switch (choice) {
            case 1:
                createMessageMenu();
                break;
            case 2:
                showInboxMessages();
                messageDelay();
                break;
            case 3:
                showSentMessages();
                messageDelay();
                break;
            case 0:
                db.getSentMessages(currentUser.getUsername());
                db.getInboxMessages(currentUser.getUsername());
                db.getAllSentMessages();
                db.getAllInboxMessages();
                init();
                break;
        }
    }

    private void logInMenu(){
        showCurrentUser();
        System.out.println("-------------------------------");
        System.out.println("-----------Log In--------------");
        System.out.println("Enter Username: ");
        String username = in.next();
        String password;
        int count = 0;
        User finduser = findUser(username);
        if (finduser == null){
            System.out.println("username not exists, Try again.");
            logInMenu();
        }else{
            if (username.equals(finduser.getUsername())){
                do {
                    System.out.println("Enter Password: ");
                    password = in.next();
                    if (password.equals(finduser.getPassword())){
                        currentUser = finduser;
                        System.out.println("Login successful");
                        showRoleMenu();
                    }
                    count = count + 1;
                }while(!password.equals(finduser.getPassword()) && count < 3);
                if (count > 2){
                    System.out.println("");
                    System.out.println("Login failed...");
                }
            }
        }
    }

    private void showRoleMenu(){
        switch (currentUser.getRole()){
            case 1:
                showSuperAdminMenu();
                break;
            case 2:
                showAdminMenu();
                break;
            case 3:
                showUserMenu();
                break;
        }
    }

    private void registerUserMenu(){
        showCurrentUser();
        System.out.println("-------------------------------");
        System.out.println("-----------Register------------");
        System.out.println("Enter Username: ");
        String username = in.next();
        System.out.println("Enter Password: ");
        String password = in.next();
        System.out.println("Enter first name: ");
        String firstName = in.next();
        System.out.println("Enter last name: ");
        String lastName = in.next();
        int role;
        role = enterRole();

        User user = new User();
        user.createUser(username, password, firstName, lastName, role);
        currentUser.getAllUsers();
        showRoleMenu();
    }

    private void updateUserMenu(){
        showCurrentUser();
        System.out.println("-------------------------------");
        System.out.println("------------Update-------------");
        String username;
        String password;
        String firstName;
        String lastName;
        int role;
        int count = 0;
        do {
            System.out.println("Enter Username: ");
            username = in.next();

            if (!(findUser(username) == null) && count < 3) {
                System.out.println("Enter new Password: ");
                password = in.next();
                System.out.println("Enter new first name: ");
                firstName = in.next();
                System.out.println("Enter new last name: ");
                lastName = in.next();
                role = enterRole();

                User user = new User();
                user.updateUser(username, password, firstName, lastName, role);
                System.out.println("The update was successful");
                showRoleMenu();
            } else if ((findUser(username) == null) && count < 3){
                System.out.println("The user " + username + " doesn't exists");
                System.out.println("Try again !");
                System.out.println("");
            }else{
                System.out.println("You have failed to update a user");
                System.out.println("Returning to main menu");
                showRoleMenu();
                break;
            }
            count = count + 1;
        }while(count != 3);
        System.out.println("");
    }

    private void deleteUserMenu(){
        showCurrentUser();
        System.out.println("-------------------------------");
        System.out.println("---------Delete User-----------");
        System.out.println("Enter Username: ");
        String username = in.next();
        if (findUser(username) == null){
            System.out.println("User not found");
            showRoleMenu();
        }else if (!username.equals(currentUser.getUsername()) && currentUser.getRole() == 1){
            currentUser.deleteUser(username);
            currentUser.getAllUsers();
            System.out.println("Username " + username + " no longer exists!");
        }else if(username.equals(currentUser.getUsername()) && currentUser.getRole() != 1) {
            System.out.println("You are going to delete your account");
            String c;
            do{
                System.out.println("Are you sure? y/n");
                c = in.next().toLowerCase();
                if (c.equals("y")){
                    currentUser.deleteUser(username);
                    currentUser = null;
                    System.out.println("Username " + username + " no longer exists!");
                    System.out.println(" ");
                    logInMenu();
                }else if (c.equals("n")){
                    showRoleMenu();
                }
            }while(!(c.equals("y") || c.equals("n")));
        }else{
            System.out.println("Failed to delete the user: " + username);
            System.out.println("*Remember that you cannot delete the super admin");
        }
    }

    private void createMessageMenu(){
        showCurrentUser();
        System.out.println("-------------------------------");
        System.out.println("-------Create Message----------");
        String receiver;
        do{
            System.out.println("Enter Receiver: ");
            receiver = in.next();
            if (!(findUser(receiver) == null)){
                System.out.println("Enter message: ");
                in.nextLine();
                String data = in.nextLine();

                Messages messages = new Messages();
                messages.createMessage(currentUser.getUsername(), receiver, data);
                messages.getAllMessages();
                showRoleMenu();
            }
        }while((findUser(receiver) == null));
    }

    private void updateMessageMenuById(){
        showAllMessages();
        System.out.println("-------------------------------");
        System.out.println("-----Update Message By Id------");
        int id;
        do{
            System.out.println("Enter Id: ");
            id = in.nextInt();
            if (!(findMessageById(id) == null)){
                System.out.println("Edit receiver: ");
                String receiver = in.next();
                System.out.println("Edit message: ");
                in.nextLine();
                String data = in.nextLine();

                Messages messages = new Messages();
                messages.updateMessage(id, currentUser.getUsername(), receiver, data);
                messages.getAllMessages();
                showRoleMenu();
            }
        }while(findMessageById(id) == null);
    }

    private void deleteMessageMenuById(){
        showCurrentUser();
        System.out.println("-------------------------------");
        System.out.println("-----Delete Message By Id------");
        int id;
        do{
            System.out.println("Enter Id: ");
            id = in.nextInt();
            if (!(findMessageById(id) == null)){
                Messages messages = new Messages();
                messages.deleteMessage(id);
                messages.getAllMessages();
                showRoleMenu();
            }
        }while(findMessageById(id) == null);
    }

    private void showInboxMessages(){
        showCurrentUser();
        System.out.println("-------------------------------");
        System.out.println("---------Inbox Message---------");
        db.getInboxMessages(currentUser.getUsername());
        db.getAllInboxMessages();
        for (Messages_Inbox message : messagesInboxArrayList){
            System.out.println("Id: " + message.getId() + ", Sender: " + message.getSender_id() + ", Message: " + message.getData());
        }
        System.out.println(" ");
        messageDelay();
    }

    private void showSentMessages(){
        showCurrentUser();
        System.out.println("-------------------------------");
        System.out.println("---------Sent Message----------");
        db.getSentMessages(currentUser.getUsername());
        db.getAllSentMessages();
        for (Messages_Sent message : messagesSentArrayList){
            System.out.println("Receiver: " + message.getReceiver_id() + ", Message: " + message.getData());
        }
        System.out.println(" ");
        messageDelay();
    }

    private void showAllMessages(){
        showCurrentUser();
        System.out.println("-------------------------------");
        System.out.println("---------All Messages----------");
        for (Messages message : messageArrayList){
            System.out.println("Id: " + message.getId() + ", Sender: " + message.getSender() + ", Receiver: " + message.getReceiver() +
                    ", Date: " + message.getDate() + ", Message: " + message.getData());
        }
        System.out.println(" ");
    }

    private void messageDelay(){
        System.out.println("Returning to menu...");
        try {
            sleep(10000);
            showRoleMenu();
        } catch (InterruptedException e){

        }
    }

    private int enterRole(){
        int role;
        if (currentUser.getRole() == 1) {
            do {
                System.out.println("Enter role: ");
                System.out.println("1 - Super Admin");
                System.out.println("2 - Admin");
                System.out.println("3 - User");
                while (!in.hasNextInt()) {
                    System.out.println("That's not a number. Enter again:");
                    in.next();
                }
                role = in.nextInt();
            } while (!(role == 1 || role == 2 || role == 3));
            return role;
        } else {
            do {
                System.out.println("Enter role: ");
                System.out.println("2 - Admin");
                System.out.println("3 - User");
                while (!in.hasNextInt()) {
                    System.out.println("That's not a number. Enter again:");
                    in.next();
                }
                role = in.nextInt();
            } while (!(role == 2 || role == 3));
            return role;
        }
    }

    private void showCurrentUser(){
        if (!(currentUser == null)){
            System.out.println("-------------------------------");
            System.out.println("-------------------------------");
            System.out.println("Logged in account: " + currentUser.getUsername());
        }
    }

    private Messages findMessageById(int id){
        for (Messages message : messageArrayList){
            if (message.getId() == id){
                return message;
            }
        }
        return null;
    }

    private User findUser(String username){
        for (User person : userArrayList){
            if (person.getUsername().equals(username)){
                return person;
            }
        }
        return null;
    }

    private void writeMessagesToFile() {
        try{
            PrintWriter writer = new PrintWriter("messages-backup.txt", "UTF-8");
            for (Messages message : messageArrayList){
                writer.println("Id: " + message.getId() + ", Sender: " + message.getSender() + ", Receiver: " + message.getReceiver() +
                        ", Date: " + message.getDate() + ", Message: " + message.getData());
            }
            writer.close();
        }catch (FileNotFoundException | UnsupportedEncodingException e){

        }
    }

    private void writeMessagesInboxToFile(){
        try{
            PrintWriter writer = new PrintWriter("messages-inbox-backup.txt", "UTF-8");
            for (Messages_Inbox message : messagesInboxArrayList){
                writer.println("Id: " + message.getId() + ", Sender: " + message.getSender_id() + ", Message: " + message.getData());
            }
            writer.close();
        }catch (FileNotFoundException | UnsupportedEncodingException e){

        }
    }

    private void writeMessagesSentToFile(){
        try{
            PrintWriter writer = new PrintWriter("messages-sent-backup.txt", "UTF-8");
            for (Messages_Sent message : messagesSentArrayList){
                writer.println("Id: " + message.getId() + ", Receiver: " + message.getReceiver_id() + ", Message: " + message.getData());
            }
            writer.close();
        }catch (FileNotFoundException | UnsupportedEncodingException e){

        }
    }
}
