package SlaveServer;

import xoLib.Exceptions.MessageIsDeadException;
import xoLib.Message.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MasterConnectionManager {
    protected Socket masterSocket;
    protected ObjectOutputStream masterOutStream;
    protected ObjectInputStream masterInStream;


    public MasterConnectionManager(String masterIP , int masterPort) throws IOException {
        masterSocket = new Socket(masterIP,masterPort);
        masterOutStream = new ObjectOutputStream(this.masterSocket.getOutputStream());
        masterInStream = new ObjectInputStream(this.masterSocket.getInputStream());
    }

    synchronized void sendToMaster(Message msg) {


        try {
            msg.decTTL();
            masterOutStream.writeObject(msg);
            masterOutStream.flush();
        } catch (IOException | MessageIsDeadException e) {

            e.printStackTrace();
        }

    }


    public ObjectInputStream getInputStream() {
        return masterInStream;
    }
}
