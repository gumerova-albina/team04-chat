import jdk.internal.net.http.common.Pair;
import jdk.javadoc.internal.doclets.toolkit.util.Utils;

import java.io.*;
import java.net.Socket;

public class Connection implements Runnable{
    final private Socket clientSocket;
    public Connection(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try (final DataInputStream input = new DataInputStream(
             new BufferedInputStream(
                        clientSocket.getInputStream()));
             final DataOutputStream out = new DataOutputStream(
                     new BufferedOutputStream(
                             clientSocket.getOutputStream()))
        ) {
            // "/snd <сообщение>"
            Utils.Pair <DataInputStream, DataOutputStream> pair = new Utils.Pair<>(input, out);
            Server.collection.add(pair);
            while(true) {


                String clientLine = input.readUTF();
                if (clientLine.startsWith("/snd")) {
                    Message clientMessage = new Message(clientLine);

                    // Server.clientSocketList...
                    // send Message to all other clients
                }

                if (clientLine.startsWith("/hist")) {
                    History clientMessage = new History(clientLine);

                    // send History to exact client
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
