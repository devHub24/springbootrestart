package com.restart.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
//Documentation
@Schema(
        name="Customer",
        description = "Schema to hold Customer  details"
)
public class CustomerDto {


    @NotEmpty(message = "Name can't be empty")
    //Documentation
    @Schema(
            description = "Name of the customer",
            example="Sandy"
    )
    private String name;

    @NotEmpty(message = "Email can't be empty")
    @Email(message="Wrong email format")
    //Documentation
    @Schema(
            description="Email id of the customer",
            example="sandy@gmail.com"
    )
    private String email;

    @NotEmpty(message = "Mobile number can't be empty")
    @Pattern(regexp = "(^$|[0-9]{10})",message="Invalid mobile number")
    //Documentation
    @Schema(
            description="Mobile number  of the customer",
            example="9998899988"
    )
    private String mobileNo;
}
