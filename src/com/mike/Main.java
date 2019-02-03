package com.mike;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("|---------------------------------------------------------------------|");
        System.out.println("|----------------- Welcome To My Console Application -----------------|");
        System.out.println("|---------------------------------------------------------------------|");
        System.out.println("|---- My name is Mixalis and i am the creator of this application ----|");
        System.out.println("|---------------------------------------------------------------------|");
        System.out.println("|------------------------ P.S. Mr Pasparakis -------------------------|");
        System.out.println("|---------------------------------------------------------------------|");
        System.out.println("|-- I WILL NOT ANSWER ANY QUESTION DURING OR AFTER THE PRESENTATION --|");
        System.out.println("|---------------------------------------------------------------------|");

        Scanner in = new Scanner(System.in);
        String choice ;
        do {
            System.out.println("Do you agree? y / n");
            while(in.hasNextInt()){
                System.out.println("That's not a Character. Enter again:");
                in.nextInt();
            }
            choice = in.next().toLowerCase();
        }while(!(choice.equals("y") || choice.equals("n")) );

        if (choice.equals("y")){
            new Menu();
        }else{
            System.out.println("|---------------------------------------------------------------------|");
            System.out.println("|-------- Thank you for you time but the presentation is over --------|");
            System.out.println("|---------------------------------------------------------------------|");
        }
    }
}
