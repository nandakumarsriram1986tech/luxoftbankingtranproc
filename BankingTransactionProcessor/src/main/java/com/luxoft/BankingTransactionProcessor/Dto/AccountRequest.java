package com.luxoft.BankingTransactionProcessor.Dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record AccountRequest(@NotBlank(message = "Account holder name is required")
                             String accountHolderName,

                             @NotNull(message = "Initial balance cannot be null")
                             @Min(value = 0, message = "Initial balance must be positive")
                             BigDecimal initialBalance) {

}
