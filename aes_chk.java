/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bu.cset109_java;

/**
 *
 * @author shiva
 */

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class aes_chk {
    public static void main(String[] args) throws Exception {
        // Step 1: Generate an AES Key
        // The KeyGenerator class is used to create a symmetric key for AES (Advanced Encryption Standard).
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");

        // Step 2: Initialize the KeyGenerator for 128-bit key size
        // AES supports key sizes of 128, 192, and 256 bits. Here, we use a 128-bit key.
        keyGen.init(128);
        SecretKey secretKey = keyGen.generateKey();  // Generates a random AES key

        // Step 3: Initialize the Cipher for AES encryption
        // The Cipher class provides encryption and decryption functionality.
        // "AES/ECB/PKCS5Padding" specifies the algorithm (AES), mode of operation (ECB), and padding scheme (PKCS5Padding).
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

        /*
    The string "AES/ECB/PKCS5Padding" breaks down as follows:

    AES: Refers to the Advanced Encryption Standard, a symmetric encryption algorithm used to encrypt and decrypt data.

    ECB (Electronic Codebook Mode): A mode of operation where each block of plaintext is encrypted independently. This can be less secure because identical plaintext blocks produce identical ciphertext blocks, potentially revealing patterns in the data.

    PKCS5Padding: A padding scheme used to handle plaintext messages that do not perfectly fit into a block size (128 bits for AES). PKCS5 adds extra bytes to the last block to fill the space, ensuring the length matches the block size.

    In Summary:

    This string specifies the encryption algorithm (AES), mode of operation (ECB), and a padding scheme (PKCS5Padding) to handle message lengths that don’t align with block size.
        */
        // Step 4: Set the Cipher to ENCRYPT_MODE with the generated secret key
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Step 5: Encrypt the message
        String message = "Hello, AES Encryption!";
        byte[] encryptedMessage = cipher.doFinal(message.getBytes());  // Encrypt the message

        // Step 6: Encode the encrypted message in Base64
        // Base64 encoding is used to convert the encrypted byte array into a readable string format.
        String encryptedString = Base64.getEncoder().encodeToString(encryptedMessage);
        System.out.println("Encrypted Message: " + encryptedString);

        // Step 7: Initialize the Cipher for decryption (DECRYPT_MODE)
        // The same key must be used to decrypt the message.
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        // Step 8: Decrypt the message
        // We need to decode the Base64-encoded string before decryption.
        byte[] decryptedMessage = cipher.doFinal(Base64.getDecoder().decode(encryptedString));
        String decryptedString = new String(decryptedMessage);

        // Step 9: Print the decrypted message
        System.out.println("Decrypted Message: " + decryptedString);
    }
}
