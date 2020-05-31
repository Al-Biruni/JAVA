package SlaveServer;

import xoLib.Message.Message;
import xoLib.Message.MessageHandler;
import xoLib.Message.MessageType;
import xoLib.User;

public class ClientThreadMessageHandler implements MessageHandler {
    int clientNumber;
    ClientsConnectionsManager clientsConnectionsManager;
    MasterConnectionManager masterConnectionManager;


    public ClientThreadMessageHandler(int clientNumber, ClientsConnectionsManager clientsConnectionsManager, MasterConnectionManager masterConnectionManager) {
    }

    @Override
    public void registrationMessage(Message registerMessage) {
        String cn = "";
        cn += clientNumber;
        User cUser = new User(cn);
        //cUser.userName=cn;
        registerMessage.users = new User[1];
        registerMessage.users[0] = cUser;
        //registerMessage.TTL = 4;
        masterConnectionManager.sendToMaster(registerMessage);
    }

    @Override
    public void publicMessage(Message msg) {
        clientsConnectionsManager.sendToAll(msg);
        masterConnectionManager.sendToMaster(msg);

    }

    @Override
    public void privateMessage(Message msg) {
        ClientThread pu = clientsConnectionsManager.find(msg.receiver);
        if (pu == null) {
            masterConnectionManager.sendToMaster(msg);
        } else {
            pu.sendToClient(msg);
        }

    }

    @Override
    public void onlineUsersMessage(Message message) {

    }

    public void sendToAll(Message message) {
        clientsConnectionsManager.sendToAll(message);
    }

    @Override
    public void logoutMessage(Message msg) {
        Message m = new Message(clientsConnectionsManager.getServerUser(), null, cUser.userName, MessageType.LOGOUT);
        masterConnectionManager.sendToMaster(m);
        clientsConnectionsManager.sendToAll(m);
        close();
        clientsConnectionsManager.close(this);
    }

    @Override
    public void getAllUsersMessage(Message msg) {

        Message m = new Message(clientsConnectionsManager.getServerUser(),
                msg.sender, "",
                MessageType.GETALLUSERS);
        masterConnectionManager.sendToMaster(m);
    }

    @Override
    public void newUserMessage(Message msg) {

    }

}
