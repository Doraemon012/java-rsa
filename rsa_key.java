/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bu.cset109_java;

/**
 *
 * @author shiva
 */

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
import java.util.Base64;

public class rsa_key {
    public static void main(String[] args) throws Exception {
        // Step 1: Generate a key pair (public and private) for RSA encryption
        // KeyPairGenerator is a class that helps in generating public and private key pairs for RSA.
        // RSA is a public-key encryption algorithm.
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");

        // Step 2: Initialize the KeyPairGenerator with a key size of 2048 bits.
        // RSA supports various key sizes; 2048 is considered secure for most purposes.
        keyGen.initialize(2048);

        // Step 3: Generate the KeyPair
        // The key pair contains both the public key (used for encryption) and private key (used for decryption).
        KeyPair keyPair = keyGen.generateKeyPair();

        // Step 4: Get the Public and Private Keys from the KeyPair
        PublicKey publicKey = keyPair.getPublic();   // The public key is used to encrypt the data.
        PrivateKey privateKey = keyPair.getPrivate(); // The private key is used to decrypt the data.

        // Step 5: Convert the keys to Base64-encoded strings (optional)
        // This is useful if you want to store or send the keys as text.
        String publicKeyStr = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        String privateKeyStr = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        System.out.println("Public Key: " + publicKeyStr);   // Display the public key
        System.out.println("Private Key: " + privateKeyStr); // Display the private key

        // Step 6: Initialize the Cipher for RSA encryption
        // Cipher is a class responsible for encryption and decryption operations.
        // "RSA/ECB/PKCS1Padding" indicates that the RSA algorithm will be used, 
        // ECB is the mode of operation (Electronic Codebook), and PKCS1Padding is the padding scheme.
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        // Step 7: Set the Cipher to ENCRYPT_MODE using the public key
        cipher.init(Cipher.ENCRYPT_MODE, publicKey); // We are using the public key to encrypt the data.

        // Step 8: Define the message that we want to encrypt
        String message = "Hello, RSA Encryption!"; // This is the plaintext message to be encrypted.

        // Step 9: Encrypt the message
        // The doFinal() method processes the input data (in bytes) and returns the encrypted message in bytes.
        byte[] encryptedMessage = cipher.doFinal(message.getBytes()); // Encrypt the plaintext

        // Step 10: Encode the encrypted message in Base64
        // Since the encrypted data is in binary format, we use Base64 encoding to convert it into a readable string.
        String encryptedString = Base64.getEncoder().encodeToString(encryptedMessage);
        System.out.println("Encrypted Message: " + encryptedString); // Display the encrypted message

        // Step 11: Initialize the Cipher for RSA decryption
        // To decrypt the data, we need to initialize the Cipher with DECRYPT_MODE and the private key.
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        // Step 12: Decrypt the message
        // The encrypted message is first decoded from its Base64 representation back to bytes.
        // Then we use the doFinal() method to decrypt the byte array and convert it back into plaintext.
        byte[] decryptedMessage = cipher.doFinal(Base64.getDecoder().decode(encryptedString)); // Decrypt the message
        String decryptedString = new String(decryptedMessage); // Convert the decrypted byte array to a String

        // Step 13: Print the decrypted message
        // After decryption, the original message should be restored.
        System.out.println("Decrypted Message: " + decryptedString); // Display the decrypted message
    }
}
