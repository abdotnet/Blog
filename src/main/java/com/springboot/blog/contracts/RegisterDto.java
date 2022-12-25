package com.springboot.blog.contracts;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegisterDto {
    @NotEmpty
    private String name;
    @NotEmpty
    private String username;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String password;
}
