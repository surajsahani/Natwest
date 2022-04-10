package com.natwest.hiring.service;

import com.natwest.hiring.configuration.CryptographyConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@Service
public class CryptographyService {

    @Value("${crypto.algorithm}")
    String algorithm;

    @Autowired
    CryptographyConfiguration cryptographyConfiguration;

    public String encrypt(String input) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException, InvalidAlgorithmParameterException, UnsupportedEncodingException {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, cryptographyConfiguration.getKeyFromPassword());
        byte[] cipherText = cipher.doFinal(input.getBytes("UTF-8"));
        return Base64.getEncoder()
                .encodeToString(cipherText);
    }

    public String decrypt(String cipherText) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException, InvalidAlgorithmParameterException, UnsupportedEncodingException {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, cryptographyConfiguration.getKeyFromPassword());
        byte[] plainText = cipher.doFinal(Base64.getDecoder()
                .decode(cipherText));
        return new String(plainText);
    }
}
