package com.luxoft.BankingTransactionProcessor.service;

import com.luxoft.BankingTransactionProcessor.Dto.*;
import com.luxoft.BankingTransactionProcessor.entity.*;
import com.luxoft.BankingTransactionProcessor.exception.*;
import com.luxoft.BankingTransactionProcessor.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BankingService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public Account createAccount(AccountRequest request) {
        Account account = new Account();
        account.setAccountNumber("ACC" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        account.setAccountHolderName(request.accountHolderName());
        account.setBalance(request.initialBalance());
        return accountRepository.save(account);
    }

    public Account getAccount(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account " + accountNumber + " does not exist."));
    }

    @Transactional
    public Account deposit(String accountNumber, BigDecimal amount) {
        Account account = getAccount(accountNumber);
        account.setBalance(account.getBalance().add(amount));

        saveLog(accountNumber, "DEPOSIT", amount, "Cash Deposit");
        return accountRepository.save(account);
    }

    @Transactional
    public Account withdraw(String accountNumber, BigDecimal amount) {
        Account account = getAccount(accountNumber);
        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal.");
        }
        account.setBalance(account.getBalance().subtract(amount));

        saveLog(accountNumber, "WITHDRAWAL", amount, "Cash Withdrawal");
        return accountRepository.save(account);
    }

    @Transactional
    public void transfer(String sourceAccountNumber, TransferRequest request) {
        Account source = getAccount(sourceAccountNumber);
        Account target = getAccount(request.toAccountNumber());

        if (source.getBalance().compareTo(request.amount()) < 0) {
            throw new InsufficientFundsException("Insufficient funds for account transfer.");
        }

        source.setBalance(source.getBalance().subtract(request.amount()));
        target.setBalance(target.getBalance().add(request.amount()));

        accountRepository.save(source);
        accountRepository.save(target);

        saveLog(sourceAccountNumber, "TRANSFER_OUT", request.amount(), "Transfer to " + request.toAccountNumber());
        saveLog(request.toAccountNumber(), "TRANSFER_IN", request.amount(), "Transfer from " + sourceAccountNumber);
    }

    public List<Transaction> getHistory(String accountNumber) {
        getAccount(accountNumber); // Verify existence
        return transactionRepository.findByAccountNumberOrderByTimestampDesc(accountNumber);
    }

    private void saveLog(String accNum, String type, BigDecimal amt, String desc) {
        Transaction tx = new Transaction(null, accNum, type, amt, LocalDateTime.now(), desc);
        transactionRepository.save(tx);
    }
}
