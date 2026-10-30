package com.luxoft.BankingTransactionProcessor.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;
    private String transactionType; // DEPOSIT, WITHDRAWAL, TRANSFER_OUT, TRANSFER_IN
    private BigDecimal amount;
    private LocalDateTime timestamp;
    private String referenceDescription;
}
