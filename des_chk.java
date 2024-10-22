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
import java.util.Base64;

class des_chk {
    public static void main(String[] args) throws Exception {
        // Step 1: Generate a DES Key
        // KeyGenerator is used to create a symmetric key for encryption and decryption.
        // Here, we specify DES (Data Encryption Standard) as the algorithm.        
        KeyGenerator keyGen = KeyGenerator.getInstance("DES");

        // Generates a random DES key that will be used for both encryption and decryption.
        SecretKey secretKey = keyGen.generateKey();

        // Step 2: Initialize the Cipher for DES encryption
        // The Cipher class provides encryption and decryption functionality.
        // "DES/ECB/PKCS5Padding" specifies the encryption mode (ECB) and padding scheme (PKCS5Padding).
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

        /*
    In the context of "DES/ECB/PKCS5Padding":

    DES: Refers to the Data Encryption Standard algorithm, which uses a symmetric key for encryption and decryption.
    ECB (Electronic Codebook Mode): A mode of operation where each block of plaintext is encrypted independently. This can lead to patterns if the same blocks are repeated.
    PKCS5Padding: A padding scheme that ensures the plaintext data fits perfectly into fixed-size blocks by adding padding bytes. This padding is necessary when the data isn't a multiple of the block size (64 bits for DES).

    Together, this configuration describes how DES will encrypt the data block-by-block and handle data sizes that don't align with the block size using padding.
        
        */
        // Step 3: Set the Cipher to ENCRYPT_MODE with the generated secret key
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // The plaintext message to be encrypted.
        String message = "Hello, World!";

        // Encrypt the message. The doFinal() method processes the plaintext and produces the ciphertext.
        // The result is a byte array containing the encrypted message.
        byte[] encryptedMessage = cipher.doFinal(message.getBytes());

        // Step 4: Encode the encrypted message in Base64
        // Base64 encoding converts binary data into a string format that can be easily stored or transmitted.
        String encryptedString = Base64.getEncoder().encodeToString(encryptedMessage);

        // Print the encrypted message in Base64 format
        System.out.println("Encrypted Message: " + encryptedString);

        // Step 5: Initialize the Cipher for decryption (DECRYPT_MODE)
        // Here, we use the same secret key that was used for encryption to decrypt the message.
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        // Step 6: Decrypt the encrypted message
        // The doFinal() method processes the ciphertext and produces the original plaintext.
        // We first decode the Base64-encoded ciphertext back into a byte array before decryption.
        byte[] decryptedMessage = cipher.doFinal(Base64.getDecoder().decode(encryptedString));

        // Convert the decrypted byte array back into a string
        String decryptedString = new String(decryptedMessage);

        // Print the decrypted message
        System.out.println("Decrypted Message: " + decryptedString);
    }
}
