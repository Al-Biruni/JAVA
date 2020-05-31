package SlaveServer;

import xoLib.Exceptions.NotUniqueUserNameException;
import xoLib.Message.Message;


import java.io.IOException;
import java.io.ObjectInputStream;


public class MasterRequestListener extends Thread {
    ObjectInputStream masterInputStream;
    MasterMessageHandler masterRequestHandler;

    public MasterRequestListener(MasterMessageHandler masterRequestHandler,ObjectInputStream masterInputStream) {
        this.masterRequestHandler = masterRequestHandler;
        this.masterInputStream = masterInputStream;

    }

    public void run() {
        Message masterReq;
        while (true) {
            // check for master
            try {
                masterReq = (Message) masterInputStream.readObject();

                if (validMasterRequest(masterReq)) {
                    Message.handel(masterReq,masterRequestHandler);
                    System.out.println("MAster  request: " + masterReq.toString());

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NotUniqueUserNameException e) {
                e.printStackTrace();
            }

        }
    }


    private boolean validMasterRequest(Message masterReq) {
        if(masterReq == null || !masterReq.isAlive())
            return false;
        return true;
    }


}
