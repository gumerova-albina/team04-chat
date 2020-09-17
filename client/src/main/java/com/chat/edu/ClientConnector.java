package main.java.com.chat.edu;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientConnector {
    public static void start() {
        try (final Socket connection = new Socket(host, port);


        ) {
            System.out.println("Please type /exit to exit the application");
            System.out.println("Please type /hist to see the history of messages");
            System.out.println("Please type /snd before the message to send");

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }



}
