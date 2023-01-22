package pl.mariuszbuhaj.s23134bank.bank.dto;


import lombok.Data;
import lombok.NonNull;
import org.springframework.data.relational.core.mapping.Embedded;

import javax.validation.Valid;

@Data
@NonNull
@Valid
public class AccountRequest {

    @NonNull
    @Valid
    private String firstName;
    @NonNull
    @Valid
    private String lastName;
    @NonNull
    @Valid
    private Double balance;

}
