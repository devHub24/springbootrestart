package com.restart.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

//Documentation
@Schema(
        name="Error",
        description = "Schema to hold Error Details"
)
public class ErrorDto {

    //Documentation
    @Schema(
            description = "Path of the eror Occured"
    )
    private String path;

    //Documentation
    @Schema(
            description = "Error Code"
    )
    private HttpStatus code;

    //Documentation
    @Schema(
            description = "Error Message"
    )
    private String message;

    //Documentation
    @Schema(
            description = "Time of the Error"
    )
    private LocalDateTime timeStamp;
}
