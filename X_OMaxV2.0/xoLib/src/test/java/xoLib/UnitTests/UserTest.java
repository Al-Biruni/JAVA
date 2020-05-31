package xoLib.UnitTests;

import org.junit.jupiter.api.Test;
import xoLib.SecretUser;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void encryptWithPublicKey() throws Exception {
        SecretUser sender = SecretUser.generateSecretUser("Sender");
        String testData = "Test data";
        byte [] dataBytes = testData.getBytes();
        byte[] encryptedBytes = sender.encryptWithPublicKey(dataBytes);
        byte[] decryptedBytes = sender.decryptDataWithPrivateKey(encryptedBytes);
        String decryptedData = new String(decryptedBytes);

        assertEquals(testData,decryptedData);

    }


}