/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import comunnication.ServerComunnicationModel;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vikto
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        // CrudUser.addUser(new User(null, "viktor2", "viktor2", "viktor2", "viktor2", 1234, Arrays.asList(new Skins(2)), Arrays.asList(new Bestscores(2))));

        ServerSocket serverSocket = new ServerSocket(ServerConfig.PORT);

        Thread serverThread = new Thread(() -> {
            run(serverSocket);
        });
        serverThread.start();
        while (true) {
            Thread.sleep(100);
        }
    }

    public static void readInfoFromClient(Socket s) {
        try {
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());
            ServerComunnicationModel model = (ServerComunnicationModel) in.readObject();
            System.out.println(model.toString());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void sendInfoToClient(Socket s, ServerComunnicationModel model) {
        try {
            ObjectOutputStream data = new ObjectOutputStream(s.getOutputStream());
            data.writeObject(model);
            data.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void run(ServerSocket serverSocket) {
        while (true) {
            try {
                // Connect to the server
                Socket s = serverSocket.accept();
                System.out.println("recieved connection");
                readInfoFromClient(s);
                System.out.println("read info from client connection");
//                sendInfoToClient(s);
                System.out.println("send info to client");
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
