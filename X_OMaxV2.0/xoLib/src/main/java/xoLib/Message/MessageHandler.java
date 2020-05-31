package xoLib.Message;


import xoLib.Exceptions.* ;

public interface MessageHandler {


    void registrationMessage(Message registerMessage) throws NotUniqueUserNameException ;
    void publicMessage(Message msg);
    void privateMessage(Message msg);
    void onlineUsersMessage(Message message);

    void logoutMessage(Message msg);

    void getAllUsersMessage(Message msg);

    void newUserMessage(Message msg);
}
