package com.luxoft.BankingTransactionProcessor.controller;

import com.luxoft.BankingTransactionProcessor.Dto.*;
import com.luxoft.BankingTransactionProcessor.entity.*;
import com.luxoft.BankingTransactionProcessor.service.BankingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bank")
@RequiredArgsConstructor
public class BankingController {

    private final BankingService bankingService;

    @PostMapping("/accounts")
    public ResponseEntity<Account> createAccount(@Valid @RequestBody AccountRequest request) {
        return new ResponseEntity<>(bankingService.createAccount(request), HttpStatus.CREATED);
    }

    @GetMapping("/accounts/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountNumber) {
        return ResponseEntity.ok(bankingService.getAccount(accountNumber));
    }

    @PostMapping("/accounts/{accountNumber}/deposit")
    public ResponseEntity<Account> deposit(@PathVariable String accountNumber, @Valid @RequestBody TransactionRequest request) {
        return ResponseEntity.ok(bankingService.deposit(accountNumber, request.amount()));
    }

    @PostMapping("/accounts/{accountNumber}/withdraw")
    public ResponseEntity<Account> withdraw(@PathVariable String accountNumber, @Valid @RequestBody TransactionRequest request) {
        return ResponseEntity.ok(bankingService.withdraw(accountNumber, request.amount()));
    }

    @PostMapping("/accounts/{accountNumber}/transfer")
    public ResponseEntity<String> transfer(@PathVariable String accountNumber, @Valid @RequestBody TransferRequest request) {
        bankingService.transfer(accountNumber, request);
        return ResponseEntity.ok("Transfer execution completed successfully.");
    }

    @GetMapping("/accounts/{accountNumber}/transactions")
    public ResponseEntity<List<Transaction>> getTransactionHistory(@PathVariable String accountNumber) {
        return ResponseEntity.ok(bankingService.getHistory(accountNumber));
    }
}

