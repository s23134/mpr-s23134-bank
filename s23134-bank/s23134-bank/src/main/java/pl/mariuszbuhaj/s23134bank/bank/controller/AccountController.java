package pl.mariuszbuhaj.s23134bank.bank.controller;


import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.mariuszbuhaj.s23134bank.bank.dao.AccountEntity;
import pl.mariuszbuhaj.s23134bank.bank.dto.AccountRequest;
import pl.mariuszbuhaj.s23134bank.bank.dto.Status;
import pl.mariuszbuhaj.s23134bank.bank.dto.Transaction;
import pl.mariuszbuhaj.s23134bank.bank.dto.WithDrawRequest;
import pl.mariuszbuhaj.s23134bank.bank.repository.AccountRepository;
import pl.mariuszbuhaj.s23134bank.bank.service.AccountService;

import javax.validation.Valid;

@RestController()
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    // POST METHODS
    @PostMapping("/account/add")
    public String addCGroup(@Valid @NonNull @RequestBody AccountRequest accountRequest, BindingResult result){
        if(accountRequest.getFirstName().isEmpty()){
            return "firstName can not be empty";
        }
        if(accountRequest.getLastName().isEmpty()){
            return "lastName can not be empty";
        }
        if(accountRequest.getBalance()<0){
            return "balance can not be less than 0";
        }
        AccountEntity accountEntity = accountService.addAccount(accountRequest);
        return accountEntity.toString();
    }

    @PostMapping("/account/withdraw")
    public ResponseEntity<Object> withDrawMoney(@Valid @NonNull @RequestBody WithDrawRequest withDrawRequest, BindingResult result){
        Transaction transaction = new Transaction();
        if(withDrawRequest.getBalance() == null){
            return ResponseEntity.status(400).body("balance can not be null");
        }
        // check if account exists
        if(accountRepository.findById(withDrawRequest.getId())==null){
            return ResponseEntity.status(400).body("account not exists");
        }

        // check if withdraw is possible
        if(accountRepository.findById(withDrawRequest.getId()).get().getBalance() < withDrawRequest.getBalance()){
            transaction.setStatus(Status.DECLINED);
            transaction.setMessage("insufficient funds");
            return ResponseEntity.status(400).body(transaction);
        }

        transaction = accountService.withDrawMoney(withDrawRequest);
        return ResponseEntity.status(200).body(transaction);
    }

    @PostMapping("/account/deposit")
    public ResponseEntity<Object> depositMoney(@Valid @NonNull @RequestBody WithDrawRequest withDrawRequest, BindingResult result){
        Transaction transaction = new Transaction();
        if(withDrawRequest.getBalance() == null){
            return ResponseEntity.status(400).body("balance can not be null");
        }
        // check if account exists
        if(accountRepository.findById(withDrawRequest.getId())==null){
            return ResponseEntity.status(400).body("account not exists");
        }

        transaction = accountService.depositMoney(withDrawRequest);
        return ResponseEntity.status(200).body(transaction);
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<Object> getAccount(@PathVariable("id") Long id){
        // check if account exists
        if(accountRepository.findById(id)==null){
            return ResponseEntity.status(400).body("account not exists");
        }
        return ResponseEntity.status(200).body(accountRepository.findById(id));
    }

}
