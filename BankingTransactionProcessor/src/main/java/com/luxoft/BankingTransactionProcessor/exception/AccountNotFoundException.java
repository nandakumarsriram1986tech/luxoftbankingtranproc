package com.luxoft.BankingTransactionProcessor.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String message) { super(message); }
}
