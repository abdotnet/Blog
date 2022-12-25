package com.springboot.blog.contracts;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginDto {
    @NotEmpty
    private String usernameOrEmail;
    @NotEmpty
    private String password;
}
