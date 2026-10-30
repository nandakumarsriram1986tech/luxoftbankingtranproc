package com.luxoft.BankingTransactionProcessor.exception;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String message) { super(message); }
}
