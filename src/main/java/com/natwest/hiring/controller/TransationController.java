package com.natwest.hiring.controller;

import com.natwest.hiring.entity.TransactionObjEntity;
import com.natwest.hiring.model.RestResponse;
import com.natwest.hiring.model.TransactionObj;
import com.natwest.hiring.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/app/v1")
public class TransationController {

    @Autowired
    TransactionService transactionService;


    @PostMapping("/transaction")
    public RestResponse addTransaction(@RequestBody TransactionObj transactionBody){
        return transactionService.postTransaction(transactionBody);
    }

    @PostMapping("/transaction/queue")
    public RestResponse addTransaction(@RequestBody String encryptedTransactionBody){
        return transactionService.storeTransactionInDB(encryptedTransactionBody);
    }
}
