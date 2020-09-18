package com.chat.edu.client;

public class Client {
    public static void main(String[] args){
        if (args.length != 2) {
            System.out.println("Please specify host and port and try again\");");
        }
        else {
            ClientController.start(args[0], Integer.valueOf(args[1]));
        }
    }
}
