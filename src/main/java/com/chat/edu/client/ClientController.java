package com.chat.edu.client;

import java.io.IOException;
import java.net.Socket;

/**
 * Class that sets up the connection to server and starting user interface
 *
 */

public class ClientController {
    private static String host = "127.0.0.1";
    private static int port = 10_000;

    public static void start() {
        try {
            final Socket connection = new Socket(host, port);
            printInstructions();
            new MessageReader(connection).start();
            new MessageWriter(connection).start();
        } catch (IOException e) {
            System.out.println("Can't connect to server");
            e.printStackTrace();
        }
    }

    private static void printInstructions() {
        System.out.println("Please type /exit to exit the application");
        System.out.println("Please type /hist to see the history of messages");
        System.out.println("Please type /snd before the message to send");
        System.out.println("Please type /chid to enter your login");
    }
}
