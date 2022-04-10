package com.natwest.hiring.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

@Configuration
public class CryptographyConfiguration {

    @Value("${crypto.password}")
    String password;

    @Value("${crypto.salt}")
    String salt;

    public SecretKey getKeyFromPassword()
            throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {

//        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
//        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
//        SecretKey secret = new SecretKeySpec(factory.generateSecret(spec)
//                .getEncoded(), "AES");
//        return secret;
        MessageDigest sha = null;
        byte[] key = password.getBytes("UTF-8");
        sha = MessageDigest.getInstance("SHA-1");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16);
        return new SecretKeySpec(key, "AES");
    }

//    public static IvParameterSpec generateIv() {
//        byte[] iv = new byte[16];
//        new SecureRandom().nextBytes(iv);
//        return new IvParameterSpec(iv);
//    }
}
