package Stockly.src.components;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESUtils {
    private AESUtils() {}

    private static final String ALGORITHM = "AES";
    private static final int AES_KEY_SIZE = 256; // Use 256-bit AES for better security

    // Generate a SecretKey of a valid length (16, 24, or 32 bytes for AES)
    public static SecretKey generateSecretKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(AES_KEY_SIZE); // 256-bit key size
        return keyGenerator.generateKey();
    }

    // Convert byte array into SecretKey for AES (size check should be done when decoding)
    public static SecretKey convertToSecretKey(byte[] keyBytes) {
        if (keyBytes.length == 16 || keyBytes.length == 24 || keyBytes.length == 32) {
            return new SecretKeySpec(keyBytes, ALGORITHM);
        } else {
            throw new IllegalArgumentException("Invalid AES key length: " + keyBytes.length);
        }
    }

    // Encrypt data using AES with the provided key
    public static String encrypt(String data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes); // Return Base64 encoded string
    }

    // Decrypt data using AES with the provided key
    public static String decrypt(String encryptedData, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData); // Decode Base64 string
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }

    // Convert Base64 string to SecretKey
    public static SecretKey decodeBase64Key(String base64Key) {
        byte[] decodedKey = Base64.getDecoder().decode(base64Key);
        return convertToSecretKey(decodedKey);  // Ensure key length is valid
    }
}
