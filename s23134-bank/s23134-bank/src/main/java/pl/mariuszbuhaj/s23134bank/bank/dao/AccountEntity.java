package pl.mariuszbuhaj.s23134bank.bank.dao;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

@Entity
@Table(name = "account")
@Data
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NonNull
    private String firstName;

    @Column
    @NonNull
    private String lastName;

    @Column
    @NonNull
    private Double balance;

    public AccountEntity() {

    }

    public AccountEntity(@NonNull String firstName, @NonNull String lastName, @NonNull double balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
    }
}
