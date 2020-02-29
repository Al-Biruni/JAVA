package Commons;


import java.io.IOException;
import java.security.*;

public class SecretUser extends User {
    protected static PrivateKey prKey;

    private SecretUser(String userName , PublicKey pbKey, PrivateKey prKey){
        super(userName,pbKey);
        this.prKey = prKey;

    }

    public static SecretUser generateSecretUser(String userName) throws Exception {
        try {
            KeyPairGenerator  keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(1024);
            KeyPair pair = keyGen.generateKeyPair();
            PublicKey  pbKey = pair.getPublic();
            PrivateKey  prKey = pair.getPrivate();

            return new SecretUser(userName , pbKey, prKey);

        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        throw new Exception("Couldn't intialize keys");
    }

}