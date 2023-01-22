package pl.mariuszbuhaj.s23134bank.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mariuszbuhaj.s23134bank.bank.dao.AccountEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity,Long> {



}
