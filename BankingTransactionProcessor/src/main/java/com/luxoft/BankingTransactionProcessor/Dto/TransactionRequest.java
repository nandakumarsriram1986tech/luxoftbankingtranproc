package com.luxoft.BankingTransactionProcessor.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record TransactionRequest(@NotNull(message = "Amount cannot be null")
                                 @Min(value = 1, message = "Amount must be greater than zero")
                                 BigDecimal amount) {
}
