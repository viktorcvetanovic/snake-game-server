/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import comunnication.ServerComunnicationModel;
import static dataUtil.DataUtil.checkMethodForDatabase;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vikto
 */
public class Server {

    public static ServerSocket serverSocket;

    public static Integer sessionId = 1;
    private static List<Socket> listOfScokets = new ArrayList<>();

    /**
     * main metoda
     *
     * @param args parametar
     * @throws IOException exception
     * @throws InterruptedException exception
     */
    public static void main(String[] args) throws IOException, InterruptedException {

        serverSocket = new ServerSocket(ServerConfig.PORT);

        while (true) {
            System.out.println("SERVER IS STARTED");
            Socket socket = serverSocket.accept();
            listOfScokets.add(socket);
            Thread serverThread = new Thread(() -> {
                try {
                    System.out.println("first thread");

                    run(socket);

                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    sessionId = 1;
                }
            });
            serverThread.start();

        }

    }

    /**
     * metoda koja sluzi da cita informacije sa klijenta
     *
     * @param socket za konekciju
     * @throws IOException exception
     */
    public static void readInfoFromClient(Socket socket) throws IOException {
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ServerComunnicationModel model = (ServerComunnicationModel) in.readObject();
            checkMethodForDatabase(socket, model);

        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * metoda koja sluzi da salje informacije klijentu
     *
     * @param type razlika izmedju slanje jednom klijentu ili svima
     * @param socket za slanje
     * @param model podatak koji se salje
     */
    public static void sendInfoToClient(String type, Socket socket, ServerComunnicationModel model) {
        if (type.equals("many")) {
            listOfScokets.forEach(e -> {
                if (e.isConnected()) {
                    try {
                        ObjectOutputStream data = new ObjectOutputStream(e.getOutputStream());
                        data.writeObject(model);
                        data.flush();
                    } catch (IOException ex) {
                        System.err.println(ex.getMessage());
                    }
                } else {
                    listOfScokets.remove(e);
                }
            });
        } else {
            try {
                ObjectOutputStream data = new ObjectOutputStream(socket.getOutputStream());
                data.writeObject(model);
                data.flush();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }

    }

    /**
     * metoda koja se izvrsava u threadu
     *
     * @param socket za konekciju sa klijentom
     */
    public static void run(Socket socket) {
        while (true) {
            try {
                // Connect to the server
                System.out.println("recieved connection");
                readInfoFromClient(socket);
                System.out.println("read info from client connection");
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
                sessionId = 1;
            }
        }
    }

}
