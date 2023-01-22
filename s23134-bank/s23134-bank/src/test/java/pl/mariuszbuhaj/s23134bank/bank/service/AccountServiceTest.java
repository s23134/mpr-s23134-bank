package pl.mariuszbuhaj.s23134bank.bank.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import pl.mariuszbuhaj.s23134bank.bank.dao.AccountEntity;
import pl.mariuszbuhaj.s23134bank.bank.dto.AccountRequest;
import pl.mariuszbuhaj.s23134bank.bank.dto.Status;
import pl.mariuszbuhaj.s23134bank.bank.dto.Transaction;
import pl.mariuszbuhaj.s23134bank.bank.dto.WithDrawRequest;
import pl.mariuszbuhaj.s23134bank.bank.repository.AccountRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccountServiceTest {




    String testName = "Test";
    String testLastName = "Test";
    double testBalance = 100.50;

    @Test
    void addAccount() {
        AccountEntity accountEntity = new AccountEntity();
        AccountRequest accountRequest = new AccountRequest(testName,testLastName,testBalance);
        AccountRepository accountRepository = mock(AccountRepository.class);
        AccountService accountService = mock(AccountService.class);
        when(accountRepository.save(accountEntity)).thenReturn(new AccountEntity(testName,testLastName,testBalance));
        when(accountService.addAccount(accountRequest)).thenReturn(new AccountEntity(testName,testLastName,testBalance));


        accountEntity = accountService.addAccount(accountRequest);
        assertEquals(accountEntity.getFirstName(),testName);
        assertEquals(accountEntity.getLastName(),testLastName);
        assertEquals(accountEntity.getBalance(),testBalance);
    }

    @Test
    void withDrawMoney() {
        WithDrawRequest bigWithDrawRequest = new WithDrawRequest(1L,1000.50d);
        WithDrawRequest smallWithDrawRequest = new WithDrawRequest(1L,100.50d);
        Double actualBalance = 550.50d;
        Transaction accepted = new Transaction();
        Transaction rejected = new Transaction();
        accepted.setStatus(Status.ACCEPTED);
        rejected.setStatus(Status.DECLINED);

        AccountEntity accountEntity = new AccountEntity();
        AccountRepository accountRepository = mock(AccountRepository.class);
        AccountService accountService = mock(AccountService.class);
        when(accountRepository.save(accountEntity)).thenReturn(new AccountEntity(testName,testLastName,testBalance));
        if(bigWithDrawRequest.getBalance() > actualBalance) {
            when(accountService.withDrawMoney(bigWithDrawRequest)).thenReturn(rejected);
        }
        if(smallWithDrawRequest.getBalance() <= actualBalance) {
            when(accountService.withDrawMoney(smallWithDrawRequest)).thenReturn(accepted);
        }
        assertEquals(accountService.withDrawMoney(bigWithDrawRequest).getStatus(),Status.DECLINED);
        assertEquals(accountService.withDrawMoney(smallWithDrawRequest).getStatus(),Status.ACCEPTED);
    }

    @Test
    void depositMoney() {
        WithDrawRequest bigWithDrawRequest = new WithDrawRequest(1L,1000.50d);
        WithDrawRequest smallWithDrawRequest = new WithDrawRequest(1L,100.50d);
        Double actualBalance = 550.50d;
        Transaction accepted = new Transaction();
        Transaction rejected = new Transaction();
        accepted.setStatus(Status.ACCEPTED);
        rejected.setStatus(Status.DECLINED);

        AccountEntity accountEntity = new AccountEntity();
        AccountRepository accountRepository = mock(AccountRepository.class);
        AccountService accountService = mock(AccountService.class);



    }
}