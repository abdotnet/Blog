package com.springboot.blog.service;

import com.springboot.blog.contracts.LoginDto;
import com.springboot.blog.contracts.RegisterDto;

public interface IAuthService {

    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);

}
