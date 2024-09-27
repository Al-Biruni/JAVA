package SlaveServer;//-------------------------------SlaveServer.ClientThread-----------------------------------------------------------

import xoLib.*;
import xoLib.Exceptions.MessageIsDeadException;
import xoLib.Exceptions.NotUniqueUserNameException;
import xoLib.Message.Message;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class ClientThread extends Thread {

    protected Socket mySocket;

    protected ObjectOutputStream output;
    protected ObjectInputStream input;
    protected Boolean active = true;
    ClientThreadMessageHandler clientMessageHandler;

    protected User cUser;

// -------------------------------constructor------------------------------------------------

    public ClientThread(ClientsConnectionsManager clientsConnectionsManager, MasterConnectionManager masterConnectionManager,
                        int clientNumber, Socket myCS) {

        clientMessageHandler = new ClientThreadMessageHandler(clientNumber, clientsConnectionsManager, masterConnectionManager);

        try {
            mySocket = myCS;
            output = new ObjectOutputStream(myCS.getOutputStream());
            input = new ObjectInputStream(myCS.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();

            this.close();
        }
    }

    Thread clientHandler;

    public void run() {

        // receive("Welcome to Client.View.X_O Secure chat room");

        Thread parent = this;
        clientHandler = new Thread(parent) {
            Message receivedMsg;

            public void run() {
                while (active) {

                    try {
                        receivedMsg = (Message) input.readObject();

                        if (isValidMsg(receivedMsg)) {
                            ((ClientThread) parent).handel(receivedMsg);
                        }
                    } catch (EOFException e) {

                        // continue;
                        System.out.println("end of connection");
                        close();
                        break;

                    } catch (IOException | ClassNotFoundException e) {

                        e.printStackTrace();

                        close();

                    } catch (NotUniqueUserNameException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        clientHandler.start();
    }

    private void handel(Message receivedMsg) throws NotUniqueUserNameException {
        try {
            Message.handel(receivedMsg, clientMessageHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private boolean isValidMsg(Message receivedMsg) {
        if (receivedMsg != null)
            if (receivedMsg.getTTL() > 0)
                return true;
        return false;
    }

    //---------------------------------------helper ----------------------------------------------------------
    void sendToClient(Message msg) {
        System.out.println("Sending to cliend \n " + msg.toString());
        Message m = new Message(msg);


        try {
            m.decTTL();
            output.writeObject(m);
            output.flush();
        } catch (IOException | MessageIsDeadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void close() {
        try {

            this.input.close();
            this.output.close();
            this.mySocket.close();


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

            active = false;

        }


    }


}