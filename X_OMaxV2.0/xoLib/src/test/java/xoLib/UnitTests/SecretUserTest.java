package xoLib.UnitTests;

import org.junit.jupiter.api.Test;
import xoLib.Message.Message;
import xoLib.SecretUser;

import static org.junit.jupiter.api.Assertions.*;

class SecretUserTest {

    @Test
    void sign() throws Exception {
        SecretUser signer = SecretUser.generateSecretUser("signer");

        String text = "text to sign";
        byte[] textBytes = text.getBytes();
        byte[] signedBytes = signer.sign(textBytes);

        assertTrue(signer.verify(textBytes, signedBytes));
    }

    @Test
    void decryptDataWithPrivateKey() throws Exception {
        SecretUser sender = SecretUser.generateSecretUser("Sender");

        String testData = "Data";
        byte[] dataBytes = testData.getBytes("UTF-8");
        byte[] encryptedBytes = sender.encryptWithPublicKey(dataBytes);
        byte[] decryptedBytes = sender.decryptDataWithPrivateKey(encryptedBytes);

        String decryptedData = new String(decryptedBytes, "UTF-8");

        assertEquals(testData, decryptedData);
    }
}