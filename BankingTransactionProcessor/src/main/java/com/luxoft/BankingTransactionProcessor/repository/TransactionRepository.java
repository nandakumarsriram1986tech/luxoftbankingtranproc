package com.luxoft.BankingTransactionProcessor.repository;
import com.luxoft.BankingTransactionProcessor.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountNumberOrderByTimestampDesc(String accountNumber);
}
