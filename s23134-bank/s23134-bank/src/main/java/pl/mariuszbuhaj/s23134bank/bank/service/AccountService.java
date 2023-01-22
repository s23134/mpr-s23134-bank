package pl.mariuszbuhaj.s23134bank.bank.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mariuszbuhaj.s23134bank.bank.dao.AccountEntity;
import pl.mariuszbuhaj.s23134bank.bank.dto.AccountRequest;
import pl.mariuszbuhaj.s23134bank.bank.dto.Status;
import pl.mariuszbuhaj.s23134bank.bank.dto.Transaction;
import pl.mariuszbuhaj.s23134bank.bank.dto.WithDrawRequest;
import pl.mariuszbuhaj.s23134bank.bank.repository.AccountRepository;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public AccountEntity addAccount(AccountRequest accountRequest){
        AccountEntity accountEntity = new AccountEntity(accountRequest.getFirstName(),accountRequest.getLastName(),
                accountRequest.getBalance());
        return accountRepository.save(accountEntity);
    }

    public Transaction withDrawMoney(WithDrawRequest withDrawRequest){
        Optional<AccountEntity> accountEntity = accountRepository.findById(withDrawRequest.getId());
        accountEntity.get().setBalance(accountEntity.get().getBalance() - withDrawRequest.getBalance());
        accountRepository.save(accountEntity.get());
        Transaction transaction = new Transaction();
        transaction.setStatus(Status.ACCEPTED);
        transaction.setNewBalance(accountRepository.findById(accountEntity.get().getId()).get().getBalance());
        transaction.setMessage("money was withdraw");
        return transaction;
    }

    public Transaction depositMoney(WithDrawRequest withDrawRequest){
        Optional<AccountEntity> accountEntity = accountRepository.findById(withDrawRequest.getId());
        accountEntity.get().setBalance(accountEntity.get().getBalance() + withDrawRequest.getBalance());
        accountRepository.save(accountEntity.get());
        Transaction transaction = new Transaction();
        transaction.setStatus(Status.ACCEPTED);
        transaction.setNewBalance(accountRepository.findById(accountEntity.get().getId()).get().getBalance());
        transaction.setMessage("money was deposited");
        return transaction;
    }




}
