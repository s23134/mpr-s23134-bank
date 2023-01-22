package pl.mariuszbuhaj.s23134bank.bank.dto;

import lombok.Data;

@Data
public class Transaction {

    private Status status;

    private String message;

    private Double newBalance;

}
