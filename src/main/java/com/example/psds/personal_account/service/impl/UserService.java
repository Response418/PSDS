package com.example.psds.personal_account.service.impl;

import com.example.psds.personal_account.dto.UserRegisterDto;
import com.example.psds.personal_account.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegisterDto userRegisterDto);

}
