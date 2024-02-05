package com.example.psds.personal_account.service.impl;

import com.example.psds.personal_account.dto.UserRegisterDto;
import com.example.psds.personal_account.model.User;
import com.example.psds.personal_account.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.session.StandardSession;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Arrays;
import java.util.Collection;

@Service
@Transactional
@Validated
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User save(UserRegisterDto userRegisterDto) {
        User user = new User();
        user.setLastName(userRegisterDto.getLastName());
        user.setFirstName(userRegisterDto.getFirstName());
        user.setFatherName(userRegisterDto.getFatherName());
        user.setCity(userRegisterDto.getCity());
        user.setPhoneNumber(userRegisterDto.getPhoneNumber());
        user.setEmail(userRegisterDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        log.info("Saving new user with name: {}", userRegisterDto.getFirstName());
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        var user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException
                    ("Invalid username or password.");
        }

        return new org.springframework.security
                .core.userdetails.User(user.getEmail(),
                user.getPassword(),
                authorities());
    }



    public Collection<? extends GrantedAuthority> authorities(){
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_STUDENT"));
    }

}
