package com.natwest.hiring.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TransactionObjEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("AccountNumber")
    private String accountNumber;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("AccountFrom")
    private String accountFrom;
    @JsonProperty("Currency")
    private String currency;
    @JsonProperty("Amount")
    private String ammount;
}
