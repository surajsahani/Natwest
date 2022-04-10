package com.natwest.hiring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionObj {

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
