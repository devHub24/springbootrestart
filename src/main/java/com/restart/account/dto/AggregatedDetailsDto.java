package com.restart.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

//Documentation
@Schema(
        name="ResultDto",
        description="To hold the values of both customer and account "
)
public class AggregatedDetailsDto {

    //Documentation
    @Schema(
            description = "To hold the customer details"
    )
    private CustomerDto customer;

    //Documentation
    @Schema(
            description = "schema to hold account details"
    )
    private AccountsDto accounts;
}
