package pl.mariuszbuhaj.s23134bank.bank.dto;


import lombok.Data;
import lombok.NonNull;

import javax.validation.Valid;

@Data
@NonNull
@Valid
public class WithDrawRequest {

    @NonNull
    @Valid
    private long id;
    @NonNull
    @Valid
    private Double balance;

}
