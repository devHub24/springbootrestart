package com.restart.account.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
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
        name="UpdateAccount",
        description="Schema to get the customer and account details to update the same"
)
public class UpdateDto {
    @NotEmpty(message = "Name can't be empty")
    //Documentation
    @Schema(
            description="name fo the customer",
            example="Santhosh"
    )
    private String name;

    @NotEmpty(message = "email can't be empty")
    @Email(message="Wrong email format")
    //Documentation
    @Schema(
            description="email fo the customer",
            example="Santhosh@gmail.com"
    )
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})",message="Invalid mobile number")
    //Documentation
    @Schema(
            description="mobile number fo the customer",
            example="99988899988"
    )
    private String mobileNo;

    //Documentation
    @Schema(
            description="account number fo the customer",
            example="Randomly generated "
    )
    private Long accountNumber;

    @NotEmpty(message = "account type can't be empty")
    //Documentation
    @Schema(
            description="type of the account",
            example="Savings"
    )
    private String accountType;

    @NotEmpty(message = "address can't be empty")
    //Documentation
    @Schema(
            description="Address of the account branch",
            example="123 street"
    )
    private String branchAddress;
}
