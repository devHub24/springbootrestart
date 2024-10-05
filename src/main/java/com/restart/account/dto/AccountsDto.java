package com.restart.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        name="Account",
        description = "Schema to hold Account information"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountsDto {

    @Schema(
            description="Account number",
            example="435718309 {It is automatically generated}"
    )
    private Long accountNumber;

    @Schema(
            description="Type of the account",
            example="Savings"
    )
    private String accountType;

    @Schema(
            description="Branc address of the account",
            example="123 street"
    )
    private String branchAddress;
}
