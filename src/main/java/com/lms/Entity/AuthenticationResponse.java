package com.lms.Entity;

import lombok.*;
import org.modelmapper.spi.Tokens;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AuthenticationResponse {


    private String token;
    private String message;
    private String username;

    public AuthenticationResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public String getMessage() {

        return message;
    }
}