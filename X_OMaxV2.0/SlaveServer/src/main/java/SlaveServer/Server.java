package SlaveServer;

import xoLib.*;


import java.io.IOException;

public class Server {

    protected final static int maxClients = 50;
    protected final static String masterIP = "master-server";
    final static int masterPort = 6500;
    final static int clientPort = 6000;

    protected SecretUser serverUser;

    MasterMessageHandler masterRequestHandler;
    MasterConnectionManager masterConnectionManager;
    ClientsConnectionsManager clientsConnectionsManager;


    public Server() {
        try {
            this.serverUser = SecretUser.generateSecretUser("Server-0");

            masterConnectionManager = new MasterConnectionManager(masterIP, masterPort);

            clientsConnectionsManager = new ClientsConnectionsManager(this);

            masterRequestHandler = new MasterMessageHandler(serverUser,clientsConnectionsManager, masterConnectionManager);

            System.out.println(
                    "Connected to Master Server on port " + masterPort + "Waiting for clients on port " + clientPort);

        } catch (IOException e) {
            System.err.println(masterIP+":"+masterPort);
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    public void run() throws IOException {

        ClientConnectionListener clientConnectionListener = new ClientConnectionListener(clientPort, clientsConnectionsManager);
        MasterRequestListener masterRequestListener = new MasterRequestListener(masterRequestHandler,masterConnectionManager.getInputStream());

        clientConnectionListener.start();
        masterRequestListener.start();
    }


    public MasterMessageHandler getMasterRequestHandler() {
        return masterRequestHandler;
    }

    public User getUser() {
        return serverUser;

    }

    public static void main(String[] args) {
        try {
           // SecretUser serverUser = SecretUser.generateSecretUser("server-0");
            Server s = new Server();
            s.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
