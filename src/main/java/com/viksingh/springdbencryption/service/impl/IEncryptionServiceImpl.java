package com.viksingh.springdbencryption.service.impl;

import com.viksingh.springdbencryption.service.IEncryptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

@Slf4j
@Service
public class IEncryptionServiceImpl<T extends Serializable> implements IEncryptionService<T> {

    private static final String AES_KEY = "THIS IS EXAMPLE OF ENCRYPTION AND DE-ENCRYPTION";
    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5PADDING";

    @Override
    public String encrypt(T data) {
        AES aes = new AES(AES_KEY);
        return aes.encrypt(data);
    }

    @Override
    public byte[] decrypt(T data) {
        AES aes = new AES(AES_KEY);
        return aes.decrypt(data);
    }

    private class AES {
        private SecretKeySpec secretKeySpec;
        private byte[] key;

        AES(String secret) {
            MessageDigest messageDigest;
            try {
                key = secret.getBytes(StandardCharsets.ISO_8859_1);
                messageDigest = MessageDigest.getInstance("SHA-1");
                key = messageDigest.digest(key);
                key = Arrays.copyOf(key, 16);
                secretKeySpec = new SecretKeySpec(key, "AES");
            } catch (NoSuchAlgorithmException e) {
                log.error("Error while setting key");
            }
        }

        String encrypt(T s) {
            try {
                Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
                return Base64.getEncoder().encodeToString(cipher.doFinal(getBytesFromObject(s)));
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Error while encryption", e.getStackTrace());
            }
            return null;
        }

        private byte[] getBytesFromObject(T s) throws IOException {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out;
            byte[] bytes;
            try {
                out = new ObjectOutputStream(bos);
                out.writeObject(s);
                out.flush();
                bytes = bos.toByteArray();
            } finally {
                try {
                    bos.close();
                } catch (IOException ex) {

                }
            }
            return bytes;
        }

        byte[] decrypt(T s) {
            try {
                Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
                cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
                System.out.println("====================" + s);
                if (s instanceof String) {
                    System.out.println("Haan hai toh");
                }
                return cipher.doFinal(Base64.getDecoder().decode(s.toString()));
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Error while decrypting ");
            }
            return null;
        }
    }

}

