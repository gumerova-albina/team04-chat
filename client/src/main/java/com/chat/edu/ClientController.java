package main.java.com.chat.edu;

import java.io.*;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Scanner;

public class ClientController {
    private static String host = "127.0.0.1";
    private static int port = 10_000;

    public static void start() {
        try {
            final Socket connection = new Socket(host, port);
            System.out.println("Please type /exit to exit the application");
            System.out.println("Please type /hist to see the history of messages");
            System.out.println("Please type /snd before the message to send");

            new MessageReader(connection).start();
            new MessageWriter(connection).start();
        } catch (IOException e) {
            System.out.println("Can't connect to server");
            e.printStackTrace();
        }
    }
}
