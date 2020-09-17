package com.chat.edu.client;

import java.io.*;
import java.net.Socket;

/**
 * Class that build and send messages from user to server
 *
 */
public class MessageWriter extends Thread{
    private DataOutputStream out;
    private String login  = "";
    private final Socket connection;
    private final BufferedReader reader;

    public MessageWriter(Socket connection){
        this.connection = connection;
        reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            out = new DataOutputStream(
                    new BufferedOutputStream(
                            connection.getOutputStream()));
        } catch (IOException e) {
            System.out.println("Can't connect to send messages");
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        String message;
        do {
            message = getMessageFromConsole();
            if(message != null){
                if (message.startsWith("/chid")){
                    login = message.split("/chid ")[1];
                }
                sendMessageToServer(message);
            }
        } while (!"/exit".equals(message));
        closeConnection();
    }

    /**
     *  Close connection with server when user send /exit command
     *
     */
    private void closeConnection() {
        try {
            connection.close();
        } catch (IOException e) {
            System.out.println("Can't close connection");
            e.printStackTrace();
        }
    }

    /**
     *  Get and return message typed by user in console
     *
     */
    private String getMessageFromConsole() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("Can't read login from console");
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  Send message to server in special form using by server
     *
     * @param message
     */
    private void sendMessageToServer(String message){
        try {
            out.writeUTF(login + " " + message);
            out.flush();
        } catch (IOException e) {
            System.out.println("Can't send message to server");
            e.printStackTrace();
        }
    }
}
