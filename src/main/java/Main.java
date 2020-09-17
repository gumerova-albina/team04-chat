import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {

        while(true) {
            try (final ServerSocket connectionPortListener = new ServerSocket(10_000);
                 final Socket clientConnection = connectionPortListener.accept();
                 final DataInputStream input = new DataInputStream(
                         new BufferedInputStream(
                                 clientConnection.getInputStream()));
                 final DataOutputStream out = new DataOutputStream(
                         new BufferedOutputStream(
                                 clientConnection.getOutputStream()))) {

                Connection connection = new Connection();

                final String readLine = input.readUTF();
                out.writeUTF("!!! " + readLine); //INT 5
                out.flush();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
