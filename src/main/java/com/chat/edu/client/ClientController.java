package com.chat.edu.client;

import java.io.IOException;
import java.net.Socket;

/**
 * Class that sets up the connection to server and starting user interface
 *
 */

public class ClientController {
    public static void start(String host, int port) {
        try {
            final Socket connection = new Socket(host, port);
            printInstructions();
            new MessageReader(connection).start();
            new MessageWriter(connection).start();
        } catch (IOException e) {
            System.out.println("Can't connect to server");
            return;
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    private static void printInstructions() {
        System.out.println("Please type /exit to exit the application");
        System.out.println("Please type /hist to see the history of messages");
        System.out.println("Please type /snd before the message to send");
        System.out.println("Please type /chid to enter your login");
        System.out.println("Please type /chroom <Room Name> to change your chat room (empty name for global chat)");
        System.out.println("Please type /sdnp <Login You Want Send Message> to send personal message");
    }
}
