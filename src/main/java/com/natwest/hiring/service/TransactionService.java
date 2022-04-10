package com.natwest.hiring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.natwest.hiring.entity.TransactionObjEntity;
import com.natwest.hiring.model.RestClient;
import com.natwest.hiring.model.RestResponse;
import com.natwest.hiring.model.TransactionObj;
import com.natwest.hiring.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Service
public class TransactionService {

    @Autowired
    RestService restService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CryptographyService cryptographyService;

    public RestResponse postTransaction(TransactionObj transactionObj) {
//        RestResponse restResponse = new RestResponse(200, "Successful");
        try {
            RestClient restClient = RestClient.builder().requestBody(encryptTransaction(transactionObj)).build();
            return restService.sendRequest(restClient, HttpMethod.POST, "http://localhost:8080/transaction/queue", RestResponse.class).getBody();
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            return new RestResponse(400, e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new RestResponse(500, e.getLocalizedMessage());
        }
    }

    public RestResponse storeTransactionInDB(String encryptedTransactionObj) {
        RestResponse restResponse = new RestResponse(200, "Successful");
        try {
            TransactionObjEntity transactionObj = decryptTransaction(encryptedTransactionObj);
            transactionRepository.save(transactionObj);
            return restResponse;
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            return new RestResponse(400, e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new RestResponse(500, e.getLocalizedMessage());
        }
    }

    private String encryptTransaction(TransactionObj transactionObj) throws JsonProcessingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeySpecException, InvalidKeyException, InvalidAlgorithmParameterException, UnsupportedEncodingException {
        return cryptographyService.encrypt(objectMapper.writeValueAsString(transactionObj));
    }

    //    AES/CBC/PKCS5Padding
    private TransactionObjEntity decryptTransaction(String encryptedTransactionObj) throws JsonProcessingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeySpecException, InvalidKeyException, InvalidAlgorithmParameterException, UnsupportedEncodingException {
        return objectMapper.readValue(cryptographyService.decrypt(encryptedTransactionObj), TransactionObjEntity.class);
    }
}
